package com.app.playtrip.entities.Wrapper;

import java.util.ArrayList;

public class ResponseWrapper<T> {

    private Boolean success;
    private String message;
    private ArrayList<T> data;
    private ArrayList<ErrorModel> errors;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<T> getData() {
        return data;
    }

    public void setData(ArrayList<T> data) {
        this.data = data;
    }

    public ArrayList<ErrorModel> getErrors() {
        return errors;
    }

    public void setErrors(ArrayList<ErrorModel> errors) {
        this.errors = errors;
    }
}
