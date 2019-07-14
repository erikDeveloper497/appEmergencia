package com.example.appemergencia;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.appemergencia.SignUp.SignUpInterface;
import com.example.appemergencia.SignUp.SignUpPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.Objects;

import mehdi.sakout.fancybuttons.FancyButton;

public class SignUpActivity extends AppCompatActivity implements SignUpInterface.View {


    private MaterialEditText ed_name, ed_password, ed_email, ed2_password;
    private FancyButton btn_signup;
    private SignUpInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        showToolbar("registro", true );
        setViews();
    }

    private void setViews() {
        presenter = new SignUpPresenter(this);
        ed_name= findViewById(R.id.ed_name_sign);
        ed_email=findViewById(R.id.ed_email_sign);
        ed_password=findViewById(R.id.ed_password_sign);
        ed2_password=findViewById(R.id.ed2_password_sign);
        btn_signup=findViewById(R.id.btn_signup);
        btn_signup.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                handleSignUp();
            }
        });

    }

    private void showToolbar(String registro, boolean b) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setTitle(registro);
            getSupportActionBar().setDisplayHomeAsUpEnabled(b);
        }
    }

    @Override
    public void disableInputs() {
        setEnable(false);
    }

    private void setEnable(boolean b) {
        ed2_password.setEnabled(b);
        ed_password.setEnabled(b);
        ed_email.setEnabled(b);
        ed_name.setEnabled(b);
        btn_signup.setEnabled(b);
    }

    @Override
    public void enableInputs() {
        setEnable(true);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void handleSignUp() {
        if(validateViews()){
            presenter.doSignUp(ed_name.getText().toString().trim(), ed_email.getText().toString().trim(), ed_password.getText().toString().trim());
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public boolean validateViews() {
        boolean retVal = true;
        if(TextUtils.isEmpty(ed_name.getText())){
            ed_name.setError("Este campo es obligatorio");
            retVal = false;
        }else if(ed_name.getText().toString().length()<3){
            ed_name.setError("Debes escribir al menos 3 caracteres");
            retVal=false;
        }
        if(TextUtils.isEmpty(ed_email.getText())){
            ed_email.setError("Este campo es obligatorio");
            retVal = false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches()){
            ed_email.setError("Email INVALIDO! ");
            retVal = false;
        }
        if(TextUtils.isEmpty(ed_password.getText())){
            ed_password.setError("Este campo es obligatorio");
            retVal = false;

        }else if(ed_password.getText().toString().length()<4){
            ed_password.setError("Debes poner al menos 4 caracteres.");
            retVal=false;
        }
        if(TextUtils.isEmpty(ed2_password.getText())){
            ed2_password.setError("Este campo es obligatorio");
            retVal=false;
        }else if(!ed_password.getText().toString().trim().equals(ed2_password.getText().toString().trim())){
            retVal=false;
            ed2_password.setError("Las contraseñar no coinciden.");
        }

        return  retVal;
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLogin() {
        Toast.makeText(this, "Ustes ya está REGISTRADO", Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
