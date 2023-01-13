package smu.it.term;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;


public class ScheduleActivity extends AppCompatActivity {
    RadioGroup radioGroup;
    RadioButton smilRB, sadRB, angryRB;
    ImageView ivEmoji;
    CalendarView calD;
    Button dreamBtn, recordBtn, logoutBtn;
    TextView tvYear, tvMonth, tvDay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        //캘린더 날짜 설정
        calD = findViewById(R.id.calenD);
        tvYear = findViewById(R.id.year);
        tvMonth = findViewById(R.id.month);
        tvDay = findViewById(R.id.day);

        //날짜 제한
        Calendar today = Calendar.getInstance();
        Calendar minDay = (Calendar) today.clone();
        minDay.add(Calendar.DATE, -100);
        Calendar maxDay = (Calendar) today.clone();
        maxDay.add(Calendar.DATE, 0);
        calD.setMinDate(minDay.getTimeInMillis());
        calD.setMaxDate(maxDay.getTimeInMillis());

        calD.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                tvYear.setText(String.format("%d", year));
                tvMonth.setText(String.format("%d", month + 1));
                tvDay.setText(String.format("%d", day));
                radioGroup.clearCheck();
                ivEmoji.setImageResource(0);
            }
        });

        //라디오 버튼 클릭시마다 이미지 변경
        radioGroup = findViewById(R.id.rgEmoji);
        smilRB = findViewById(R.id.smile);
        sadRB = findViewById(R.id.sad);
        angryRB = findViewById(R.id.angry);
        ivEmoji = findViewById(R.id.emoji);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.smile:
                        ivEmoji.setImageResource(R.drawable.smile);
                        break;
                    case R.id.sad:
                        ivEmoji.setImageResource(R.drawable.sad);
                        break;
                    case R.id.angry:
                        ivEmoji.setImageResource(R.drawable.angry);
                        break;
                }
            }
        });

        //창 이동 버튼
        dreamBtn = findViewById(R.id.dreamBTN);
        recordBtn = findViewById(R.id.recordBTN);
        logoutBtn = findViewById(R.id.logoutBTN);

        dreamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, GoalActivity.class);
                startActivity(intent);
            }
        });

        recordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, RecordActivity.class);
                startActivity(intent);
            }
        });

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScheduleActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}