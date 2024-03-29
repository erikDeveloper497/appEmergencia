package com.example.appemergencia.Login;

public interface LoginInterface {
    interface View{
        void disableInputs();
        void enableInputs();

        void showProgress();
        void hideProgress();

        void handleLogin();
        boolean isValidEmail();
        boolean isValidPassword();

        void onLogin();
        void onError(String error);

    }

    interface Presenter{
        void onDestoy();

        void toLogin(String email, String password);
    }


    interface  Model {

        void doLogin(String email, String Password);
    }



    interface TalkListener{
        void onSucess();
        void onError(String error);
    }
}
