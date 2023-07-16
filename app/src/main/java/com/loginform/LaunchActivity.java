package com.loginform;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Toast;

import com.loginformlibrary.listener.CompleteListener;
import com.loginformlibrary.model.LoginFormModel;
import com.loginformlibrary.view.ShowForm;

@SuppressLint("CustomSplashScreen")
public class LaunchActivity extends AppCompatActivity implements CompleteListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ShowForm.Builder()
                .with(this)
                .setListener(this)
                .build();
    }

    @Override
    public void onComplete(LoginFormModel formModel) {
        if (formModel==null){
            finish();
        }else {
            Toast.makeText(this, formModel.toString(), Toast.LENGTH_SHORT).show();
        }
    }
}