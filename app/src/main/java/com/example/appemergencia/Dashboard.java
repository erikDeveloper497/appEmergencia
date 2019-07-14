package com.example.appemergencia;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appemergencia.DashboardImp.DashboardPresenter;

public class Dashboard extends AppCompatActivity implements com.example.appemergencia.DashboardImp.Dashboard.View {

    private EditText edt_number;
    private Button btn_save;
    private com.example.appemergencia.DashboardImp.Dashboard.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new DashboardPresenter(this);
        setContentView(R.layout.activity_dashboard);
        showToolbar("Posicion", true );
        setViews();
    }

    private void setViews() {

        edt_number = findViewById(R.id.edt_number);
        btn_save = findViewById((R.id.btn_save_number));
        presenter.onCharge();
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onSave(edt_number.getText().toString().trim());//getText().toString().trim();
            }
        });
    }

    private void showToolbar(String Posicion, boolean b) {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar()!= null) {
            getSupportActionBar().setTitle(Posicion);
            getSupportActionBar().setDisplayHomeAsUpEnabled(b);
        }
    }


    private void setInputs(boolean enable){
        edt_number.setEnabled(enable);
        btn_save.setEnabled(enable);

    }

    @Override
    public void enableInputs() {
        setInputs(true);
    }

    @Override
    public void disableInputs() {
        setInputs(false);
    }

    @Override
    public void fillEditText(String number) {
        edt_number.setText(number);//setIntType
    }

    @Override
    public void onError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }
}
