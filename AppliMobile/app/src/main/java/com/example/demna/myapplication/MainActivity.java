package com.example.demna.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button butOuvert = (Button) findViewById(R.id.button);
        butOuvert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getMain(),"Ouvrir la porte",Toast.LENGTH_SHORT).show();

            }
        });


        final Button butFermer = (Button) findViewById(R.id.button2);
        butFermer.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Toast.makeText(getMain(),"Fermer la porte",Toast.LENGTH_SHORT).show();

            }
        });
    }

    public MainActivity getMain()
    {
        return this;
    }
}
