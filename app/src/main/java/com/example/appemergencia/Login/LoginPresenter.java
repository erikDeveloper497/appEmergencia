package com.example.appemergencia.Login;

import com.example.appemergencia.Login.LoginInterface;
import com.example.appemergencia.MainActivity;

public class LoginPresenter implements LoginInterface.Presenter, LoginInterface.TalkListener{

    private LoginInterface.View view;
    private LoginInterface.Model model;

    public LoginPresenter(LoginInterface.View view) {
        this.view = view;
        model = new LoginModel(this);
    }

    @Override
    public void onDestoy(){
        view = null;
    }

    @Override
    public void toLogin(String email, String password){
        if( view !=  null){
            view.disableInputs();
           // view.showProgress();

        }
        model.doLogin(email, password);
    }

    @Override
    public void onSucess() {
        if(view != null){
            view.enableInputs();
            //view.hideProgress();
            view.onLogin();
        }
    }

    @Override
    public void onError(String error) {
        if ( view!=null){
            view.enableInputs();
            //view.hideProgress();
            view.onError(error);
        }
    }
}
