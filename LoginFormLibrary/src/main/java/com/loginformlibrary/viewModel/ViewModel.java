package com.loginformlibrary.viewModel;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;

import com.loginformlibrary.databinding.LoginFormLayBinding;
import com.loginformlibrary.listener.CompleteListener;
import com.loginformlibrary.model.LoginFormModel;

public class ViewModel extends androidx.lifecycle.ViewModel {

    public void showForm(Context context, CompleteListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LoginFormLayBinding binding = LoginFormLayBinding.inflate(((AppCompatActivity) context).getLayoutInflater());
        builder.setCancelable(false);
        builder.setView(binding.getRoot());
        AlertDialog alertDialog = builder.create();
        binding.cancelBtn.setOnClickListener(v -> {
            alertDialog.dismiss();
            listener.onComplete(null);
        });
        binding.submitBtn.setOnClickListener(v -> {
            String firstName = binding.firstNameEditText.getText().toString().trim();
            String lastName = binding.lastNameEditText.getText().toString().trim();
            String mobile = binding.mobileNoEditText.getText().toString().trim();
            String emailId = binding.emailIdEditText.getText().toString().trim();

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
            } else if (!isValid(emailId)) {
                binding.emailIdEditText.setError("Invalid EmailId");
            }
            if (!firstName.isEmpty() && !lastName.isEmpty() && (!emailId.isEmpty() && isValid(emailId))) {
                alertDialog.dismiss();
                LoginFormModel formModel = new LoginFormModel();
                formModel.setFirstName(firstName);
                formModel.setLastName(lastName);
                formModel.setMobileNo(mobile);
                formModel.setEmailId(emailId);
                listener.onComplete(formModel);
            }
        });
        Window window = alertDialog.getWindow();
        if (window != null) {
            window.setBackgroundDrawable(new ColorDrawable());
        }
        alertDialog.show();
    }

    boolean isValid(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }
}
