package org.nyanja.webtechassignment2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class HelperClass {

    public boolean userExists(String email){
        try {

            String url = "jdbc:mysql://localhost:3306/admission";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement pst = con.prepareStatement("SELECT  * FROM users where email=?");
            pst.setString(1,email);
            if(pst.executeQuery()!=null){
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();} return false;
    }

    public boolean emailUsed(String email){
        try {

            String url = "jdbc:mysql://localhost:3306/admission";
            String username = "root";
            String password = "";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(url,username,password);
            PreparedStatement pst = con.prepareStatement("SELECT  * FROM admission where email=?");
            pst.setString(1,email);
            if(pst.executeQuery()!=null){
                return true;
            }
        }catch (Exception ex){
            ex.printStackTrace();} return false;
    }

}
