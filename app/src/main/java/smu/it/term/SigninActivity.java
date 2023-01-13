package smu.it.term;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SigninActivity extends AppCompatActivity {
    //Context로 다음 Activity에서 정보 사용
    public static Context SigninContext;

    TextView tvID, tvPW, tvName, tvEmail;
    EditText signID, signPW, signName, signEmail;
    CheckBox checkBox;
    Button signinBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        //Context 설정
        SigninContext = this;

        //EditText
        signID = (EditText) findViewById(R.id.editID);
        signPW = (EditText) findViewById(R.id.editPW);
        signName = (EditText) findViewById(R.id.editName);
        signEmail = (EditText) findViewById(R.id.editMail);

        //CheckBox
        checkBox = (CheckBox) findViewById(R.id.pwCheck);

        //TextView
        tvID = (TextView) findViewById(R.id.checkID);
        tvPW = (TextView) findViewById(R.id.checkPW);
        tvName = (TextView) findViewById(R.id.checkName);
        tvEmail = (TextView) findViewById(R.id.checkEmail);

        //Button
        signinBTN = findViewById(R.id.signinOKBTN);

        //아이디 조건
        signID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력되는 텍스트에 변화가 있을 시 호출
                //ID 형식 체크 결과를 결과 뷰에 세팅하는 기능
                tvID.setText(checkIDForm(signID.getText().toString()));
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
        signPW.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력되는 텍스트에 변화가 있을 시 호출
                //비밀번호 형식 체크 결과를 결과 뷰에 세팅하는 기능
                tvPW.setText(checkPWForm(signPW.getText().toString()));
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
                    signPW.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
                else {
                    //Hide pw
                    signPW.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        //이름 조건
        signName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력되는 텍스트에 변화가 있을 시 호출
                //이름 형식 체크 결과를 결과 뷰에 세팅하는 기능
                tvName.setText(checkNameForm(signName.getText().toString()));
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

        //mail 조건
        signEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //입력되는 텍스트에 변화가 있을 시 호출
                //이메일 형식 체크 결과를 결과 뷰에 세팅하는 기능
                tvEmail.setText(checkEmailForm(signEmail.getText().toString()));
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

        //조건에 맞을 경우 회원가입 완료 창으로 넘어감
        signinBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tvID.getText().toString() == "올바른 ID 입니다." &&
                        tvPW.getText().toString() == "올바른 PW 입니다." &&
                        tvName.getText().toString() == "올바른 이름 입니다." &&
                        tvEmail.getText().toString() == "올바른 Email 입니다.") {
                    Intent intent = new Intent(SigninActivity.this, SigninFinishActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    //id 조건
    public String checkIDForm(String inputID) {
        if (signID.length() >= 5)
            return "올바른 ID 입니다.";
        else
            return "ID는 5글자 이상 작성해주세요.";
    }

    //pw 조건
    public String checkPWForm(String inputPW) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9]{8,12}$");
        Matcher matcher = pattern.matcher(inputPW);

        if (matcher.find())
            return "올바른 PW 입니다.";
        else
            return "PW는 영어, 숫자 조합 8~12글자로 작성해주세요.";
    }

    //name 조건
    public String checkNameForm(String inputName) {
        Pattern pattern = Pattern.compile("^[a-zA-Zㄱ-ㅎ가-힣]*$");
        Matcher matcher = pattern.matcher(inputName);

        if (matcher.find())
            return "올바른 이름 입니다.";
        else
            return "숫자와 특수문자는 제외해주세요.";
    }

    //email 조건
    public String checkEmailForm(String inputEmail) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        Matcher matcher = pattern.matcher(inputEmail);

        if (matcher.find())
            return "올바른 Email 입니다.";
        else
            return "올바른 Email을 입력해주세요.";
    }
}