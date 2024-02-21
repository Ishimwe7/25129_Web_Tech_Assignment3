package org.nyanja.webtechassignment2;

import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

@WebFilter("/admission")
@MultipartConfig
public class AdmissionFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;
        String email = servletRequest.getParameter("email");
        Part passport = req.getPart("passport");
        Part diploma = req.getPart("diploma");
        String passportName = passport.getSubmittedFileName();
        String diplomaName = diploma.getSubmittedFileName();


        if(!email.matches("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")){
            req.setAttribute("invalidEmail","Please provided valid  Email");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admission.jsp");
            try {
                dispatcher.forward(req,res);
            }catch (Exception e){e.printStackTrace();}
        }
        if(!(passportName.endsWith(".png") || passportName.endsWith(".jpg"))){
            req.setAttribute("invalidPassport","Please provide .jpg or .png passport Picture");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admission.jsp");
            try {
                dispatcher.forward(req,res);
            }catch (Exception e){e.printStackTrace();}
        }
        if(!diplomaName.endsWith(".pdf")){
            req.setAttribute("invalidDiploma","Please provide .pdf diploma file");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/admission.jsp");
            try {
                dispatcher.forward(req,res);
            }catch (Exception e){e.printStackTrace();}
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }
}
