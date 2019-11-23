package com.example.sut3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;
import android.os.Vibrator;

public class pantalla3 extends AppCompatActivity {
    MediaPlayer mp;
     DatabaseReference mRootReference;
     ImageButton agregarBD;
     EditText mEditTextModulo;
    EditText mEditTextPiso;
    EditText mEditTextSalon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla3);
        agregarBD = findViewById(R.id.next2);
        mRootReference = FirebaseDatabase.getInstance().getReference();

        mEditTextModulo= findViewById(R.id.modulo);
        mEditTextPiso= findViewById(R.id.ingrese_piso);
        mEditTextSalon= findViewById(R.id.ingrese_salon);



        agregarBD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modulo = mEditTextModulo.getText().toString();
                String piso = mEditTextPiso.getText().toString();
                String salon = mEditTextSalon.getText().toString();

                cagarDatos(modulo, piso, salon);
            }
        });

        configureNextButton2();
        configureNextButton();
    }
    private void configureNextButton(){

        ImageButton nextButton = (ImageButton) findViewById(R.id.next2);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrador.vibrate(500);
                startActivity(new Intent(pantalla3.this,pantalla4.class));
            }

        });
    }
    private void cagarDatos(String modulo, String piso, String salon) {
        Map<String, Object> datosDestino = new HashMap<>();
        datosDestino.put("modulo",modulo);
        datosDestino.put("piso",piso);
        datosDestino.put("salon",salon);

        mRootReference.child("Destino").push().setValue(datosDestino);
        Toast.makeText(pantalla3.this,"Espera mientras se genera ruta",Toast.LENGTH_LONG).show();
    }

    private void configureNextButton2(){

        ImageButton nextButton = (ImageButton) findViewById(R.id.atras);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                assert vibrador != null;
                vibrador.vibrate(500);
                startActivity(new Intent(pantalla3.this,MainActivity.class));
            }

        });
    }
}
