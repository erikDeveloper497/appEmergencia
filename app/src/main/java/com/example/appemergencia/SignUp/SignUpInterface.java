package com.example.appemergencia.SignUp;

public interface SignUpInterface {
    interface View{
        void disableInputs();
        void enableInputs();

        void showProgress();
        void hideProgress();

        void handleSignUp();
        boolean validateViews();

        void onError(String error);
        void onLogin();

    }
    interface Model{
        void onSignUp(String name, String email, String password);
    }

    interface Presenter{
        void onDestroy();
        void doSignUp(String name, String email, String password);
    }

    interface TaskListener{
        void onSuccess();
        void onError(String error);
    }
}
