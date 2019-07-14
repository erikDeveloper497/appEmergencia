package com.example.appemergencia;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.example.appemergencia.Login.LoginInterface;
import com.example.appemergencia.Login.LoginPresenter;
import com.rengwuxian.materialedittext.MaterialEditText;

import mehdi.sakout.fancybuttons.FancyButton;

@SuppressWarnings("ALL")
public class MainActivity extends AppCompatActivity implements LoginInterface.View {


    private MaterialEditText ed_email, ed_password;
    private FancyButton btn_signin;
    private MaterialDialog dialog;
    private LoginInterface.Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();
        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleLogin();
            }
        });
    }

    private void setViews() {
        presenter = new LoginPresenter(this);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_signin = findViewById(R.id.btn_signin);

    }
    private void setInputs(boolean enable){
        ed_email.setEnabled(enable);
        ed_password.setEnabled(enable);
        btn_signin.setEnabled(enable);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void showProgress() {
        //dialog.show();
    }

    @Override
    public void hideProgress() {
        //dialog.dismiss();
    }

    @Override
    public void handleLogin() {
        if(!isValidEmail()){
            Toast.makeText(this,"No es un email Válido", Toast.LENGTH_SHORT).show();
        }else if(!isValidPassword()){
            Toast.makeText(this, "No es un Password Válido 1", Toast.LENGTH_SHORT).show();
        }else{
            presenter.toLogin(ed_email.getText().toString().trim(), ed_password.getText().toString().trim());
        }
    }

    @Override
    public boolean isValidEmail() {
        return Patterns.EMAIL_ADDRESS.matcher(ed_email.getText().toString()).matches();
    }

    @Override
    public boolean isValidPassword() {
        if (TextUtils.isEmpty(ed_password.getText().toString()) || ed_password.getText().toString().length()<4){
            Toast.makeText(this, "No es una Password  Válido por tener menos de 4 caracteres", Toast.LENGTH_SHORT).show();
            ed_password.setError("No es una contraseña válida 3");
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void onLogin() {
        Toast.makeText(this, "Has hecho Loggin Correctamente", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Dashboard.class));
        finish();
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestoy();
    }

    public void register(View view){
        startActivity(new Intent(this, SignUpActivity.class));
    }
}
