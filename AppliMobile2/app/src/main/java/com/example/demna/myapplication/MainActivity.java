package com.example.demna.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button butOuvert = (Button) findViewById(R.id.butOuvert);
        final Button butFermer = (Button) findViewById(R.id.butFerme);
        butFermer.setOnClickListener(this);
        butOuvert.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.butOuvert:
                Toast.makeText(this, "Ouvrir la porte", Toast.LENGTH_SHORT).show();
                break;
            case R.id.butFerme:
                Toast.makeText(this, "Fermer la porte", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}
