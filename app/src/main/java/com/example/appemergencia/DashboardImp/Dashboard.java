package com.example.appemergencia.DashboardImp;

public interface Dashboard {
    interface View{
        void enableInputs();
        void disableInputs();

        void fillEditText(String number);
        void onError (String error);
    }
    interface Presenter{
        void onSave(String number);
        void onCharge();

        void onDestroy();
    }
    interface Model{
        void chargeNumber();
        void setNumber(String number);
    }
    interface TaskListener{
        void onSucessSave();
        void onSucessCharge(String number);
        void onError(String error);
    }
}
