package smu.it.term;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class GoalActivity extends AppCompatActivity {

    EditText userGoalText;
    TextView finishGoalListText, checkingGoal;
    Button finishBTN,backBTN, clearBTN;
    String str1,str2;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);

        userGoalText = findViewById(R.id.userGoalText);
        checkingGoal = findViewById(R.id.checkGoal);
        finishGoalListText = findViewById(R.id.finishGoalListText);
        finishBTN = findViewById(R.id.finishBTN);
        backBTN = findViewById(R.id.backBTN);
        clearBTN = findViewById(R.id.clearGoalBTN);
        str1 = null;
        str2 = null;

        checkingGoal.setVisibility(View.INVISIBLE);

        finishBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (count <= 20) {
                    checkingGoal.setVisibility(View.INVISIBLE);
                    str2 = userGoalText.getText().toString();
                    str1 = finishGoalListText.getText().toString() + "✔ " + str2 + "\n";
                    finishGoalListText.setText(str1);
                    userGoalText.setText("");
                    count++;
                }
                if (count > 20) {
                    checkingGoal.setVisibility(View.VISIBLE);
                }
            }
        });

        clearBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = 0;
                finishGoalListText.setText("");
                checkingGoal.setVisibility(View.INVISIBLE);
            }
        });

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(GoalActivity.this, ScheduleActivity.class);
                startActivity(intent);
            }
        });

    }
}
