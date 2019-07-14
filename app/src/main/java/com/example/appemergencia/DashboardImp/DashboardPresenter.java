package com.example.appemergencia.DashboardImp;

public class DashboardPresenter implements Dashboard.Presenter, Dashboard.TaskListener {
    private  Dashboard.View view;
    private Dashboard.Model model;

    public DashboardPresenter(Dashboard.View view) {
        this.view = view;
        model = new DashboardModel(this);
    }

    @Override
    public void onSave(String number) {
        if(view!= null){
            view.disableInputs();
            model.setNumber(number);
        }
    }

    @Override
    public void onCharge() {
        if(view!=null){
            view.disableInputs();
            model.chargeNumber();
        }
    }

    @Override
    public void onDestroy() {
        view=null;
    }

    @Override
    public void onSucessSave() {
        if(view!=null){
            model.chargeNumber();
        }
    }

    @Override
    public void onSucessCharge(String number) {
        if(view!= null){
            view.enableInputs();
            view.fillEditText(number);
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
