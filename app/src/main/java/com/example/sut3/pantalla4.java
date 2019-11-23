package com.example.sut3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class pantalla4 extends AppCompatActivity {
    MediaPlayer mp;
    DatabaseReference mRootReference;
    Button agregarBD;
    EditText mEditTextReco;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla4);
        agregarBD = findViewById(R.id.recomendar);
        mRootReference = FirebaseDatabase.getInstance().getReference();

        mEditTextReco= findViewById(R.id.recomedacion);




        agregarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String recomendacion = mEditTextReco.getText().toString();


                cagarDatos(recomendacion);
            }
        });
        configureNextButton();
        configureNextButton2();
    }

    private void cagarDatos(String recomendacion) {
        Map<String, Object> datosDestino = new HashMap<>();
        datosDestino.put("recomendacion",recomendacion);


        mRootReference.child("Recomendaciones").push().setValue(datosDestino);
        Toast.makeText(pantalla4.this,"Espera mientras se guarda recomendacion",Toast.LENGTH_LONG).show();
    }

    private void configureNextButton(){

        Button nextButton = (Button) findViewById(R.id.recomendar);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                assert vibrador != null;
                vibrador.vibrate(500);
                startActivity(new Intent(pantalla4.this,pantalla5.class));
            }

        });
    }
    private void configureNextButton2(){

        ImageButton nextButton = (ImageButton) findViewById(R.id.atras);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                assert vibrador != null;
                vibrador.vibrate(500);
                startActivity(new Intent(pantalla4.this,pantalla3.class));
            }

        });
    }
}

