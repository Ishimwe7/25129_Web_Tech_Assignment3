package org.nyanja.webtechassignment2;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

@WebServlet("/admission")
@MultipartConfig
public class Admission extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response){
        try {
            String url = "jdbc:mysql://localhost:3306/admission";
            String username = "root";
            String password = "";
            String fname =request.getParameter("f_name");
            String sname =request.getParameter("s_name");
            String phoneNumber =request.getParameter("phone_number");
            String email =request.getParameter("email");
            String dep = request.getParameter("department");
            String gender =request.getParameter("gender");
            String dob =request.getParameter("bd");
            String country =request.getParameter("country");
            String city =request.getParameter("city");
            String address =request.getParameter("address");

            Part passportPart = request.getPart("passport");
            Part diplomaPart = request.getPart("diploma");
            String passportName = passportPart.getSubmittedFileName();
            String diplomaName = diplomaPart.getSubmittedFileName();
            String imagesUploadPath = "C:/Users/hp/IdeaProjects/WebTechAssignment2/src/main/webapp/images/" + passportName;
            String documentsUploadPath = "C:/Users/hp/IdeaProjects/WebTechAssignment2/src/main/webapp/documents/" + diplomaName;

            try {
                FileOutputStream pfos = new FileOutputStream(imagesUploadPath);
                FileOutputStream dfos = new FileOutputStream(documentsUploadPath);
                InputStream pins = passportPart.getInputStream();
                InputStream dins = diplomaPart.getInputStream();
                byte[] pdata = new byte[pins.available()];
                byte[] ddata = new byte[dins.available()];
                pins.read(pdata);
                dins.read(ddata);
                pfos.write(pdata);
                dfos.write(ddata);
                pfos.close();
                dfos.close();
            } catch (IOException e) {
                System.out.println(e);
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement pst = con.prepareStatement("insert into admission(f_name,s_name,phone_number,email,department,passport,diploma,gender,birth_date,country,city,address) values (?,?,?,?,?,?,?,?,?,?,?,?)");
            pst.setString(1,fname);
            pst.setString(2,sname);
            pst.setString(3,phoneNumber);
            pst.setString(4,email);
            pst.setString(5,dep);
            pst.setString(6,passportName);
            pst.setString(7,diplomaName);
            pst.setString(8,gender);
            pst.setString(9,dob);
            pst.setString(10,country);
            pst.setString(11,city);
            pst.setString(12,address);

            HelperClass help = new HelperClass();

            if(help.emailUsed(email)){
                request.setAttribute("email","Email Already Used! ");
                RequestDispatcher dispatcher = request.getRequestDispatcher("/admission.jsp");
                dispatcher.forward(request,response);
            }
            else {
                if(pst.executeUpdate()>0){
                    SendEmails send = new SendEmails();
                    send.sendEmail(email,"Admission Request Send","Hello "+fname+" "+sname +" "+ " Thank you for sending your admssion request.");
                    request.setAttribute("success","Form Submitted Successfully");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admission.jsp");
                    dispatcher.forward(request,response);
                }
                else{
                    request.setAttribute("error","Failed to save data");
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/admission.jsp");
                    dispatcher.forward(request,response);
                }
            }
            con.close();
        }catch (Exception ex){
            ex.printStackTrace();
            request.setAttribute("error","An expected error occurred!!");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admission.jsp");
            try {
                dispatcher.forward(request,response);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

//    public void sendEmail(String to, String subject, String body) {
//        // Set mail properties
//        java.util.Properties properties = System.getProperties();
//        properties.setProperty("mail.smtp.host", "smtp.gmail.com");
//        properties.setProperty("mail.smtp.port", "587");
//        properties.setProperty("mail.smtp.auth", "true");
//        properties.setProperty("mail.smtp.starttls.enable", "true");
//        properties.setProperty("mail.smtp.tsl.trust", "smtp.gmail.com");
//        //properties.setProperty("mail.smtp.password", "NyanjaCyane");
//
//        // Create session
//        Session session = Session.getInstance(properties, new Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication("ishimweinstein@gmail.com", "feec zkjr rwvg gbkt");
//            }
//        });
//
//        try {
//            // Create message
//            MimeMessage mimeMessage = new MimeMessage(session);
//            mimeMessage.setFrom(new InternetAddress("ishimweinstein@gmail.com"));
//            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
//            mimeMessage.setSubject(subject);
//            mimeMessage.setText(body);
//            // Send the message
//            Transport.send(mimeMessage);
//
//            System.out.println("Email sent successfully!");
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
}
