package com.loginformlibrary.viewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.loginformlibrary.databinding.LoginFormLayBinding;
import com.loginformlibrary.listener.CompleteListener;
import com.loginformlibrary.model.LoginFormModel;

public class ViewModel extends androidx.lifecycle.ViewModel {


    /**
     * Load Dialog of login for
     * @param context  activity context
     * @param listener call onComplete method when submit reach
     */
    public void showForm(Context context, CompleteListener listener) {

        //Initialize builder instance
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        //Initialize layout binding
        LoginFormLayBinding binding = LoginFormLayBinding.inflate(((AppCompatActivity) context).getLayoutInflater());

        //Set dialog cancelable disable
        builder.setCancelable(false);

        //Set custom view on dialog box
        builder.setView(binding.getRoot());

        //Create dialog box
        AlertDialog alertDialog = builder.create();

        //Set dialog cancel listener
        binding.cancelBtn.setOnClickListener(v -> {

            //Dismissing dialog
            alertDialog.dismiss();

            //Call onComplete method of CompleteListener interface
            listener.onComplete(null);
        });

        //Set dialog submit listener
        binding.submitBtn.setOnClickListener(v -> {

            //Getting all text from view
            String firstName = binding.firstNameEditText.getText().toString().trim();
            String lastName = binding.lastNameEditText.getText().toString().trim();
            String mobile = binding.mobileNoEditText.getText().toString().trim();
            String emailId = binding.emailIdEditText.getText().toString().trim();

            //Checking empty validation
            if (firstName.isEmpty()) {
                binding.firstNameEditText.setError("Required");
            }
            if (lastName.isEmpty()) {
                binding.lastNameEditText.setError("Required");
            }
            if (mobile.isEmpty()) {
                binding.mobileNoEditText.setError("Required");
            }
            if (emailId.isEmpty()) {
                binding.emailIdEditText.setError("Required");
            } else if (!isValid(emailId)) {//Checking email validation
                binding.emailIdEditText.setError("Invalid EmailId");
            }

            //init when all validation comes true
            if (!firstName.isEmpty() && !lastName.isEmpty() && (!emailId.isEmpty() && isValid(emailId))) {
                alertDialog.dismiss();

                //Model initialize
                LoginFormModel formModel = new LoginFormModel();
                formModel.setFirstName(firstName);
                formModel.setLastName(lastName);
                formModel.setMobileNo(mobile);
                formModel.setEmailId(emailId);

                //passing model to interface
                listener.onComplete(formModel);
            }
        });

        //setting transparent background of Dialog box
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }

        //Show dialog box on view
        alertDialog.show();
    }

    /**
     * isValid method is email validation
     * @param email  email id string
     * @return return true if email id is valid else false
     */
    boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
