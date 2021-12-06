package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

import com.google.gson.Gson;

@WebServlet(name = "DeleteServlet", value = "/delete")
public class DeleteServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:postgresql://localhost:5432/homework2";
        String user = "postgres";
        String password = "code";
        String table = request.getParameter("table");
        String key = request.getParameter("key");

        String sql = null;
        if(table.equalsIgnoreCase("users")) {
            sql = "DELETE FROM users WHERE email='" + key + "'";
        }else if(table.equalsIgnoreCase("dtype")){
            sql = "DELETE FROM diseasetype WHERE id='" + key + "'";
        }else if(table.equalsIgnoreCase("country")){
            sql = "DELETE FROM country WHERE cname='" + key + "'";
        }else if(table.equalsIgnoreCase("disease")){
            sql = "DELETE FROM disease WHERE disease_code='" + key + "'";
        }else if(table.equalsIgnoreCase("discover")){
            sql = "DELETE FROM discover WHERE cname='" + key + "' AND disease_code='" + request.getParameter("key2") + "'";
        }else if(table.equalsIgnoreCase("pservant")){
            sql = "DELETE FROM publicservant WHERE email='" + key + "'";
        }else if(table.equalsIgnoreCase("doctor")){
            sql = "DELETE FROM doctor WHERE email='" + key + "'";
        }else if(table.equalsIgnoreCase("spec")){
            sql = "DELETE FROM specialize WHERE email='" + request.getParameter("key2") + "' AND id =" +key;
        }else if(table.equalsIgnoreCase("record")){
            sql = "DELETE FROM record WHERE email='" + key + "' AND cname ='" + request.getParameter("key2")+"' AND disease_code='"+ request.getParameter("key3")+ "'";
        }

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Successful database connection");
        } catch(Exception e) {
            e.printStackTrace();
        }


        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();
            System.out.println(sql);
            int status = stmt.executeUpdate(sql);
            System.out.println(status);
            response.sendRedirect(table);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

}