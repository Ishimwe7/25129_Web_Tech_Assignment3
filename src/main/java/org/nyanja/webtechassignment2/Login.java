package org.nyanja.webtechassignment2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/Login")
public class Login extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/admission","root","");
            PreparedStatement pst =con.prepareStatement("select * from users where email=? and password=?");
            pst.setString(1,email);
            pst.setString(2,password);
            if(pst.executeQuery()!=null){
                HttpSession session = request.getSession();
                session.setAttribute("username",email);
                // request.setAttribute("username","Logged in as:  Admin");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admission.jsp");
                try {
                    dispatcher.forward(request,response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                request.setAttribute("error","Invalid login! ");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                try {
                    dispatcher.forward(request,response);
                } catch (ServletException e) {
                    request.setAttribute("con","Connection failed!");
                    RequestDispatcher dis = request.getRequestDispatcher("/login.jsp");
                    dis.forward(request,response);
                    throw new RuntimeException(e);
                }
            }
            con.close();
        } catch (Exception e){ e.printStackTrace();}

    }
}
