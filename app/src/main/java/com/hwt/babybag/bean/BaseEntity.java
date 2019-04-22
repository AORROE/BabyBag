package com.hwt.babybag.bean;

public class BaseEntity<T> {
    private int status;
    private String message;
    private T result;
    private static int SUCCESS_CODE = 1;

    public boolean isSuccess(){
        return getStatus() == SUCCESS_CODE;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
