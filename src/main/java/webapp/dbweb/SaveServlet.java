package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

import com.google.gson.Gson;

@WebServlet(name = "SaveServlet", value = "/save")
public class SaveServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:postgresql://localhost:5432/homework2";
        String user = "postgres";
        String password = "code";
        String sql = null;

        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Successful database connection");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String table = request.getParameter("table");

        if(table.equalsIgnoreCase("users")) {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String stsalary = request.getParameter("salary");
            int salary = Integer.parseInt(stsalary);
            String phone = request.getParameter("phone");
            String country = request.getParameter("country");
            sql = "INSERT INTO users VALUES ('" + email + "','" + name + "','" + surname + "'," + salary + ",'" + phone + "','" + country + "')";
        }else if(table.equalsIgnoreCase("dtype")){
            String stid = request.getParameter("id");
            try {
                int id = Integer.parseInt(stid);
                String description = request.getParameter("description");
                sql = "INSERT INTO diseasetype VALUES (" + id + ",'" + description + "')";
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Save");
            }

        }else if(table.equalsIgnoreCase("country")){
            String country = request.getParameter("country");
            String strpop = request.getParameter("population");
            try {
                int pop = Integer.parseInt(strpop);
                sql = "INSERT INTO country VALUES ('" + country + "'," + pop + ")";
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Save");
            }

        }else if(table.equalsIgnoreCase("disease")){
            String dcode = request.getParameter("dcode");
            String pathogen = request.getParameter("pathogen");
            String descr = request.getParameter("description");
            String stid = request.getParameter("id");
            try {
                int id = Integer.parseInt(stid);
                sql = "INSERT INTO disease VALUES ('" + dcode + "','" + pathogen +"','" + descr + "',"+ id + ")";
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Save");
            }

        }else if(table.equalsIgnoreCase("discover")){
            String dcode = request.getParameter("dcode");
            String country = request.getParameter("cname");
            String fed = request.getParameter("fed");
            sql = "INSERT INTO discover VALUES ('" + country + "','" + dcode +"','" + fed + "')";
        }else if(table.equalsIgnoreCase("pservant")){
            String email = request.getParameter("email");
            String dep = request.getParameter("dep");
            sql = "INSERT INTO publicservant VALUES ('" + email + "','" + dep + "')";
        }else if(table.equalsIgnoreCase("doctor")){
            String email = request.getParameter("email");
            String degree = request.getParameter("degree");
            sql = "INSERT INTO doctor VALUES ('" + email + "','" + degree + "')";
        }else if(table.equalsIgnoreCase("spec")){
            String email = request.getParameter("email");
            String id = request.getParameter("id");
            sql = "INSERT INTO specialize VALUES ('" + id + "','" + email + "')";
        }else if(table.equalsIgnoreCase("record")) {
            String email = request.getParameter("email");
            String country = request.getParameter("country");
            String dcode = request.getParameter("dcode");
            String sttotd = request.getParameter("totd");
            String sttotp = request.getParameter("totp");
            try {
                int totd = Integer.parseInt(sttotd);
                int totp = Integer.parseInt(sttotp);
                sql = "INSERT INTO record VALUES ('" + email + "','" + country + "','" + dcode + "'," + totd + "," + totp + ")";
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Save");
            }
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(true);
            Statement stmt = conn.createStatement();
            int status = stmt.executeUpdate(sql);
            if (status > 0) {
                response.sendRedirect(table);
            } else {
                out.println("Unable to Save");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            out.println("Unable to Save");
        }
    }

}