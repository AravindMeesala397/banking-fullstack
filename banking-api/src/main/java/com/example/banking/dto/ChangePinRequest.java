package com.example.banking.dto;

import jakarta.validation.constraints.NotBlank;

public class ChangePinRequest {

    @NotBlank
    private String oldPin;

    @NotBlank
    private String newPin;

    public String getOldPin() {
        return oldPin;
    }

    public void setOldPin(String oldPin) {
        this.oldPin = oldPin;
    }

    public String getNewPin() {
        return newPin;
    }

    public void setNewPin(String newPin) {
        this.newPin = newPin;
    }
}
