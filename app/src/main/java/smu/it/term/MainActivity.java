package smu.it.term;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    MediaPlayer mediaPlayer;
    public int musicStatus=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button loginButton = findViewById(R.id.loginBTN);
        Button signinButton = findViewById(R.id.signinBTN);
        Button musicBTN = findViewById(R.id.musincBTN);

        mediaPlayer = MediaPlayer.create(this, R.raw.background_music);
        musicBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(musicStatus == 0){
                    mediaPlayer.setLooping(true); //무한재생
                    mediaPlayer.start();
                    musicStatus=1;
                }else{
                    mediaPlayer.pause();
                    musicStatus=0;
                }
            }
        });

        //로그인 창으로 이동
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        //회원가입 창으로 이동
        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }
}