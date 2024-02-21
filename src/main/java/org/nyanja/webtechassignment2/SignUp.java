package org.nyanja.webtechassignment2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/signUp")
public class SignUp extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String url = "jdbc:mysql://localhost:3306/admission";
            String username = "root";
            String password = "";
            String fname =request.getParameter("f_name");
            String sname =request.getParameter("s_name");
            String email =request.getParameter("email");
            String userPassword =request.getParameter("password");

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement pst = con.prepareStatement("insert into users(f_name,s_name,email,password) values (?,?,?,?)");
            pst.setString(1,fname);
            pst.setString(2,sname);
            pst.setString(3,email);
            pst.setString(4,userPassword);

            HelperClass help = new HelperClass();
            if(help.userExists(email)){
                request.setAttribute("exist","User Already Exist!!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
                dispatcher.forward(request,response);
            }else {
                if(pst.executeUpdate()>0){
                    request.setAttribute("success","Registered Successfully");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
                    dispatcher.forward(request,response);
                }
                else{
                    request.setAttribute("error","Failed to save data");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
                    dispatcher.forward(request,response);
                }
            }
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
            request.setAttribute("error","An expected error occurred!!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/signUp.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
