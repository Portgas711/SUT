package com.example.sut3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mp;
    ImageButton play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        play = (ImageButton)findViewById(R.id.play1);



        configureNextButton();
    }
    private void configureNextButton(){

        ImageButton nextButton = (ImageButton) findViewById(R.id.play1);

        mp = MediaPlayer.create(this,R.raw.gatito2);
        ImageButton Button = (ImageButton) findViewById(R.id.play1);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.start();
                Vibrator vibrador = (Vibrator)getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                vibrador.vibrate(500);
                startActivity(new Intent(MainActivity.this, pantalla2.class));
            }

        });
    }
}
