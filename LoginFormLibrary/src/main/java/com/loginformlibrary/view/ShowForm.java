package com.loginformlibrary.view;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.loginformlibrary.viewModel.ViewModel;
import com.loginformlibrary.listener.CompleteListener;

public class ShowForm {

    public ShowForm() {
    }

    public void init(Context context, CompleteListener listener){
        if (context instanceof AppCompatActivity){

            ViewModel viewModel = new ViewModelProvider(((AppCompatActivity) context)).get(ViewModel.class);
            viewModel.showForm(context,listener);
        }else {
            throw  new RuntimeException("Instance must be AppCompatActivity instance");
        }
    }
    public static class Builder {
        private Context context;
        private CompleteListener listener;

        public Builder with(Context context){
            this.context = context;
            return this;
        }

        public Builder setListener(CompleteListener listener){
            this.listener = listener;
            return this;
        }

        public void build(){
            ShowForm showForm = new ShowForm();
            showForm.init(context, listener);
        }
    }
}
