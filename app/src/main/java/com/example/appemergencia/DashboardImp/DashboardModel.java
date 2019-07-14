package com.example.appemergencia.DashboardImp;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DashboardModel implements Dashboard.Model {

    private Dashboard.TaskListener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private FirebaseUser user;


    public  DashboardModel(Dashboard.TaskListener listener){
        this.listener=listener;
        database=FirebaseDatabase.getInstance();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = database.getReference("users").child(user.getUid());
    }

    @Override
    public void chargeNumber() {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){
                    String number = (String) dataSnapshot.child("number").getValue();//number
                    listener.onSucessCharge(number);
                }else{
                    listener.onError("Posicion NO ASIGNADA");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    @Override
    public void setNumber(String number) {
            reference.child("number").setValue(number).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        listener.onSucessSave();
                    }else{
                        if(task.getException()!=null){
                            listener.onError(task.getException().getMessage());
                        }
                    }
                }
            });
    }
}
