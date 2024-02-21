package org.nyanja.webtechassignment2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebFilter("/signUp")
public class signUpFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String email = servletRequest.getParameter("email");
        String password = servletRequest.getParameter("password");
        String confirm_password = servletRequest.getParameter("confirm-password");

        if(!password.equals(confirm_password)){
            req.setAttribute("passwordMismatches","Password Mismatches !!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
            try {
                dispatcher.forward(req,res);
            }catch (Exception e){e.printStackTrace();}
        }

        if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
            req.setAttribute("invalidEmail","Please provide valid  Email !!!");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/signUp.jsp");
            try {
                dispatcher.forward(req,res);
            }catch (Exception e){e.printStackTrace();}
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

}
