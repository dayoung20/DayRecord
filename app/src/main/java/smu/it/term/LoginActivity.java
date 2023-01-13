package smu.it.term;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    //Context로 다음 Activity에서 정보 사용
    public static Context LoginContext;

    TextView tvID, tvPW;
    EditText logID, logPW;
    CheckBox checkBox;
    Button loginOKBTN, signinBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Context 설정
        LoginContext = this;

        //EditText
        logID = (EditText) findViewById(R.id.editID);
        logPW = (EditText) findViewById(R.id.editPW);
        tvID = (TextView) findViewById(R.id.checkID);
        tvPW = (TextView) findViewById(R.id.checkPW);

        //CheckBox
        checkBox = (CheckBox) findViewById(R.id.pwCheck);

        //Button
        loginOKBTN = findViewById(R.id.loginOKBTN);
        signinBTN = findViewById(R.id.loginToSignBTN);

        //아이디 조건
        logID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력되는 텍스트에 변화가 있을 시 호출
                //ID 형식 체크 결과를 결과 뷰에 세팅하는 기능
                tvID.setText(checkIDForm(logID.getText().toString()));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력 끝났을 때 호출
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //입력 전에 호출
            }
        });

        //비밀번호 조건
        logPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력되는 텍스트에 변화가 있을 시 호출
                //비밀번호 형식 체크 결과를 결과 뷰에 세팅하는 기능
                tvPW.setText(checkPWForm(logPW.getText().toString()));
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력 끝났을 때 호출
            }

            @Override
            public void afterTextChanged(Editable editable) {
                //입력 전에 호출
            }
        });

        //비밀번호 보이는 설정
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //Show pw
                    logPW.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    //Hide pw
                    logPW.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //조건에 맞을 경우 로그인 완료 창으로 넘어감
        loginOKBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvID.getText().toString() == "올바른 ID 입니다." &&
                        tvPW.getText().toString() == "올바른 PW 입니다.") {
                    Intent intent = new Intent(LoginActivity.this, LoginFinishActivity.class);
                    startActivity(intent);
                }
            }
        });

        //회원가입 창으로
        signinBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SigninActivity.class);
                startActivity(intent);
            }
        });
    }

    //id 조건
    public String checkIDForm(String inputID) {
        if (logID.length() >= 5)
            return "올바른 ID 입니다.";
        else
            return "ID는 5글자 이상 작성해주세요.";
    }

    //pw 조건
    public String checkPWForm(String inputPW) {
        //정규식
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,12}$");
        Matcher matcher = pattern.matcher(inputPW);

        if (matcher.find())
            return "올바른 PW 입니다.";
        else
            return "PW는 영어, 숫자 조합 8~12글자로 작성해주세요.";
    }
}