package com.isep.appointement.controller.email;

public interface EmailSender {
    void send(String to, String email);
}