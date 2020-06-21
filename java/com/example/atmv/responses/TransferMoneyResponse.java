package com.example.atmv.responses;

public class TransferMoneyResponse {

    private boolean success;
    private long senderNewBalance;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public long getSenderNewBalance() {
        return senderNewBalance;
    }

    public void setSenderNewBalance(long senderNewBalance) {
        this.senderNewBalance = senderNewBalance;
    }

}
