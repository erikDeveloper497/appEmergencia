package com.example.appemergencia.SignUp;

public class SignUpPresenter implements SignUpInterface.Presenter, SignUpInterface.TaskListener{


    private SignUpInterface.View view;
    private SignUpInterface.Model model;

    public SignUpPresenter(SignUpInterface.View view) {
        this.view = view;
        model = new SignUpModel(this);
    }

    @Override
    public void onDestroy() {
        view  = null;
    }

    @Override
    public void doSignUp(String name, String email, String password) {
        if(view!=null){
            view.disableInputs();
            //view.showProgress();
        }
        model.onSignUp(name, email, password);
    }

    @Override
    public void onSuccess() {
        if(view!=null){
            view.enableInputs();
            view.onLogin();
        }

    }

    @Override
    public void onError(String error) {
        if(view!=null){
            view.enableInputs();
            view.onError(error);
        }
    }
}
