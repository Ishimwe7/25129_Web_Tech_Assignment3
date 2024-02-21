package org.nyanja.webtechassignment2;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/logout")
public class Logout extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response){
        request.getSession().invalidate();
        try {
//            LoginListner log = new LoginListner();
//            log.sessionDetsroyed();
            response.sendRedirect("/login.jsp");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
