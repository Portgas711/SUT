package com.example.sut3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class pantalla2 extends AppCompatActivity {
    private EditText TextEmail;
    private EditText TextPassword;
    private Button btnRegistrar;
    private ImageButton btnLogin;
    private ProgressDialog progressDialog;
    MediaPlayer mp;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        firebaseAuth = FirebaseAuth.getInstance();

        TextEmail = (EditText) findViewById(R.id.ingrese_correo);
        TextPassword = (EditText) findViewById(R.id.ingrese_password);

        btnRegistrar =(Button) findViewById(R.id.registrar);
        btnLogin =(ImageButton) findViewById(R.id.next1);
        progressDialog = new ProgressDialog(this);



        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }

        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ingresarUsuario();
            }

        });

        configureNextButton2();

    }

    private void  ingresarUsuario(){
        String email = TextEmail.getText().toString().trim();
        String password =TextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Toast.makeText(pantalla2.this,"Bienvenido",Toast.LENGTH_LONG).show();
                            Intent intencion= new Intent(getApplicationContext(),pantalla3.class);
                            Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                            assert vibrador != null;
                            vibrador.vibrate(500);
                            startActivity(intencion);
                        }else{

                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(pantalla2.this,"Ese usuario ya existe",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(pantalla2.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }
    private void configureNextButton(){

        ImageButton nextButton = (ImageButton) findViewById(R.id.next1);

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view){

                Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                assert vibrador != null;
                vibrador.vibrate(500);
                startActivity(new Intent(pantalla2.this,pantalla3.class));
            }

        });
    }

    private void registrarUsuario (){

        String email = TextEmail.getText().toString().trim();
        String password =TextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Se debe ingresar un email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Falta contraseña",Toast.LENGTH_LONG).show();
            return;
        }

        progressDialog.setMessage("Realizando registro en linea...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){


                            Toast.makeText(pantalla2.this,"Se ha registrado el email",Toast.LENGTH_LONG).show();
                        }else{

                            if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                Toast.makeText(pantalla2.this,"Ese usuario ya existe",Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(pantalla2.this, "No se pudo registrar el usuario", Toast.LENGTH_LONG).show();
                            }
                        }
                        progressDialog.dismiss();
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
                startActivity(new Intent(pantalla2.this,MainActivity.class));
            }

        });
    }
}
