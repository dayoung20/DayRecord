package smu.it.term;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginFinishActivity extends AppCompatActivity {
    //LoginActivity에서 가져올 변수
    TextView tvName;

    Button startRecord;
    AnimationDrawable rAnimation = new AnimationDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_finish);

        //이전 LoginActivity 변수
        tvName = (TextView) findViewById(R.id.finName);
        tvName.setText(((LoginActivity)LoginActivity.LoginContext).logID.getText());

        //토끼 애니메이션
        ImageView iv_rabbit = (ImageView) findViewById(R.id.anim_rabbit);

        BitmapDrawable rb1 = (BitmapDrawable) getResources().getDrawable(R.drawable.rabbit1);
        BitmapDrawable rb2 = (BitmapDrawable) getResources().getDrawable(R.drawable.rabbit2);
        BitmapDrawable rb3 = (BitmapDrawable) getResources().getDrawable(R.drawable.rabbit3);
        BitmapDrawable rb4 = (BitmapDrawable) getResources().getDrawable(R.drawable.rabbit4);

        int rbDuration = 120;
        rAnimation = new AnimationDrawable();

        rAnimation.addFrame(rb1, rbDuration);
        rAnimation.addFrame(rb2, rbDuration);
        rAnimation.addFrame(rb3, rbDuration);
        rAnimation.addFrame(rb4, rbDuration);
        rAnimation.addFrame(rb3, rbDuration);
        rAnimation.addFrame(rb2, rbDuration);

        iv_rabbit.setBackgroundDrawable(rAnimation);

        rAnimation.setVisible(true,true);
        rAnimation.start();

        //다음 창으로
        startRecord = (Button) findViewById(R.id.dayRecordStartBTN);
        startRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginFinishActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });
    }
}