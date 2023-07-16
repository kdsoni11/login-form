package com.loginformlibrary.view;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.loginformlibrary.viewModel.ViewModel;
import com.loginformlibrary.listener.CompleteListener;

public class ShowForm {


    /**
     * init for Load Dialog
     * @param context  activity context
     * @param listener call onComplete method when submit reach
     */
    public void init(Context context, CompleteListener listener){

        //Collect the instance of context else return exception
        if (context instanceof AppCompatActivity){

            //Initialize ViewModel
            ViewModel viewModel = new ViewModelProvider(((AppCompatActivity) context)).get(ViewModel.class);
            viewModel.showForm(context,listener);
        }else {
            throw  new RuntimeException("Instance must be AppCompatActivity instance");
        }
    }

    /**
     * Static class of builder for binding context and complete listener
    * */
    public static class Builder {
        private Context context;
        private CompleteListener listener;

        /**
         * binding context to parameter
         * @param context view context
         * @return current instance
        * */
        public Builder with(Context context){
            this.context = context;
            return this;
        }

        /**
         * binding listener to parameter
         * @param listener task complete listener
         * @return current instance
         * */
        public Builder setListener(CompleteListener listener){
            this.listener = listener;
            return this;
        }

        /**
         * init build for loading login form
         * */
        public void build(){
            ShowForm showForm = new ShowForm();
            showForm.init(context, listener);
        }
    }
}
