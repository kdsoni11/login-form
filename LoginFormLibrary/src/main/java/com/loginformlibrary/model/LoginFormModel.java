package com.loginformlibrary.model;

import androidx.annotation.NonNull;

public class LoginFormModel {
    private String firstName,lastName,emailId,mobileNo;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    @NonNull
    public String toString(){
        return "FirstName:"+firstName+"\n"+
                "LastName:"+lastName+"\n"+
                "EmailId:"+emailId+"n"+
                "Mobile:"+mobileNo;
    }
}
