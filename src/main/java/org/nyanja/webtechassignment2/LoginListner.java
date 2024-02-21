package org.nyanja.webtechassignment2;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.*;
//import jakarta.servlet.http.HttpSessionEvent;
//import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class LoginListner implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent listener) {
        HttpSession session = listener.getSession();
        SendEmails send = new SendEmails();
        send.sendEmail("ishimwe.nyanja@gmail.com", "User login detected", "User with email: " + session.getAttribute("username") + " have logged in.");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent listener) {
        HttpSession session = listener.getSession();
        SendEmails send = new SendEmails();
        send.sendEmail("ishimwe.nyanja@gmail.com", "User logout detected", "User with email: " + session.getAttribute("username") + " is logging out.");
    }

}
