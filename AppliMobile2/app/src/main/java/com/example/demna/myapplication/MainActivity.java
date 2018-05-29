package com.example.demna.myapplication;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.UUID;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    // Permet verifie qu'on ecoute sur la bonne port et permet verifier s'il y a la connection
    private static final String SPP_UUID = "00001101-0000-1000-8000-00805F9B34FB";

    private final static int REQUEST_CODE_ENABLE_BLUETOOTH = 0;

    // Adaptateur Bluetooth sur le smarthphone
    BluetoothAdapter bluetoothAdapter;

    //Adaptateur de Lejos EV3
    BluetoothSocket socket_ev3;

    final String macEV3 = "00:16:53:5C:89:FD";

    //Sert a envoyer des messages a EV3
    private DataInputStream in ;

    // Sert a recevoir des messages a partir de EV3
    private DataOutputStream out ;

    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            final String action = intent.getAction();

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                final int bluetoothState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE,
                        BluetoothAdapter.ERROR);
                switch (bluetoothState) {
                    case BluetoothAdapter.STATE_ON:

                        try {

                            Thread.sleep(500);
                            connction();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                        break;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (bluetoothAdapter == null) {

            Toast.makeText(MainActivity.this, "Pas de Bluetooth",  Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(MainActivity.this, "Bluetooth detectee", Toast.LENGTH_SHORT).show();


            if (!bluetoothAdapter.isEnabled()) {

                Intent enableBlueTooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);

                startActivityForResult(enableBlueTooth, REQUEST_CODE_ENABLE_BLUETOOTH);

                IntentFilter filter = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);

                registerReceiver(mReceiver, filter);
                Toast.makeText(MainActivity.this, "Tentative de connection, veuillez Patientez",  Toast.LENGTH_LONG).show();


            } else {

                connction();

            }



        }

        final Button butOuvert = (Button) findViewById(R.id.butOuvert);
        final Button butFermer = (Button) findViewById(R.id.butFerme);

        butFermer.setOnClickListener(this);
        butOuvert.setOnClickListener(this);

    }

    private void connction() {
        try {
            Thread.sleep(2500);
            BluetoothDevice brickEV3 = bluetoothAdapter.getRemoteDevice(macEV3);

            socket_ev3 = brickEV3.createRfcommSocketToServiceRecord(UUID.fromString(SPP_UUID));

            socket_ev3.connect();

            out = new DataOutputStream(socket_ev3.getOutputStream());

            in = new DataInputStream(socket_ev3.getInputStream());

            Field mServiceField = bluetoothAdapter.getClass().getDeclaredField("mService");

            mServiceField.setAccessible(true);

            Object btManagerService = mServiceField.get(bluetoothAdapter);

            String macOfDevice = "";

            if (btManagerService != null) {

                macOfDevice = (String) btManagerService.getClass().getMethod("getAddress").invoke(btManagerService);

            }


            Thread.sleep(1000);

            out.writeUTF(macOfDevice);

            out.flush();



        } catch (IOException e) {

            // le cas ou ev3 n'est pas allumex
            Toast.makeText(MainActivity.this, "Impossible de se connecter a EV3",  Toast.LENGTH_SHORT).show();


        } catch (InvocationTargetException e) {

            e.printStackTrace();

        } catch (NoSuchMethodException e) {

            e.printStackTrace();

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (IllegalAccessException e) {

            e.printStackTrace();

        } catch (NoSuchFieldException e) {

            e.printStackTrace();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Permet d'activer le bluetooth
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View v){
        switch ( v.getId() ) {

            case R.id.butOuvert :

                Toast.makeText(this, "Ouvrir la porte", Toast.LENGTH_SHORT).show();
                try {

                    writeMessage((byte) 1);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }
                
                break;

            case R.id.butFerme :

                Toast.makeText(this, "Fermer la porte", Toast.LENGTH_SHORT).show();

                try {

                    writeMessage((byte) 2);

                } catch (InterruptedException e) {

                    e.printStackTrace();

                }

                break;



        }
    }

    public void writeMessage(byte msg) throws InterruptedException{
        BluetoothSocket connSock;

        if(socket_ev3!=null){
            try {
                out.write(msg);
                out.flush();
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
        }
    }


}
