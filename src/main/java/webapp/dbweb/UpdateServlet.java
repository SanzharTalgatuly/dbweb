package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;


@WebServlet(name = "UpdateServlet", value = "/update")
public class UpdateServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:postgresql://localhost:5432/homework2";
        String user = "postgres";
        String password = "code";
        String table = request.getParameter("table");
        String key = request.getParameter("key");
        String sql = null;

        if(table.equalsIgnoreCase("users")) {
            String email = request.getParameter("email");
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String stsalary = request.getParameter("salary");
            int salary=0;
            try{
                salary = Integer.parseInt(stsalary);
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Update");
            }
            String phone = request.getParameter("phone");
            String country = request.getParameter("country");
            sql = "UPDATE users SET email='"+email+"', name='"+name+"',surname='"+surname+"',salary="+salary+",phone='"+phone+"',cname='"+country+"'  WHERE email='"+key+"'";
        }else if(table.equalsIgnoreCase("dtype")){
            String stid = request.getParameter("id");
            int id = 0;
            try {
                id = Integer.parseInt(stid);
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Update");
            }
            String description = request.getParameter("description");
            sql = "UPDATE diseasetype SET id=" + id + ", description='" + description + "' WHERE id='"+key+"'";
        }else if(table.equalsIgnoreCase("country")){
            String stpop = request.getParameter("population");
            String country = request.getParameter("country");
            try {
                long pop = Integer.parseInt(stpop);
                sql = "UPDATE country SET cname='" + country+ "', population=" + pop + " WHERE cname='"+key+"'";
                System.out.println(stpop+country+sql);
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Update");
            }
        }else if(table.equalsIgnoreCase("disease")){
            String dcode = request.getParameter("dcode");
            String pathogen = request.getParameter("pathogen");
            String descr = request.getParameter("description");
            String stid = request.getParameter("id");
            try {
                int id = Integer.parseInt(stid);
                sql = "UPDATE disease SET disease_code='" + dcode+ "', pathogen='" + pathogen + "', description='" +descr +"', id="+id+ " WHERE disease_code='"+key+"'";
            }catch(Exception e) {
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Update");
            }
        }else if(table.equalsIgnoreCase("discover")){
            String dcode = request.getParameter("dcode");
            String country = request.getParameter("cname");
            String fed = request.getParameter("fed");
            sql = "UPDATE discover SET disease_code='" + dcode+ "', cname='" + country + "', first_enc_date='" +fed+ "' WHERE cname='" +key+ "' AND disease_code='"+request.getParameter("key2")+"'";
        }else if(table.equalsIgnoreCase("pservant")){
            String email = request.getParameter("email");
            String dep = request.getParameter("dep");
            sql = "UPDATE publicservant SET email='" + email+ "', department='" + dep + "' WHERE email='" +key+"'";
        }else if(table.equalsIgnoreCase("doctor")){
            String email = request.getParameter("email");
            String degree = request.getParameter("degree");
            sql = "UPDATE doctor SET email='" + email+ "', degree='" + degree + "' WHERE email='" +key+"'";
        }else if(table.equalsIgnoreCase("spec")){
            String stid = request.getParameter("id");
            String email = request.getParameter("email");
            try {
                int id = Integer.parseInt(stid);
                sql = "UPDATE specialize SET id=" + id + ", email='" + email + "' WHERE id='" + key + "' AND email='" + request.getParameter("key2") + "'";
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Update");
            }
        }else if(table.equalsIgnoreCase("record")){
            String dcode = request.getParameter("dcode");
            String country = request.getParameter("country");
            String email = request.getParameter("email");
            String sttotd = request.getParameter("totd");
            String sttotp = request.getParameter("totp");
            try {
                int totd = Integer.parseInt(sttotd);
                int totp = Integer.parseInt(sttotp);
                sql = "UPDATE record SET email='" + email + "', cname='" + country + "', disease_code='" +dcode+ "', total_deaths=" +totd+ ", total_patients=" +totp+ " WHERE cname='" + request.getParameter("key2") + "' AND email='" + key + "' AND disease_code='" +request.getParameter("key3")+ "'";
                System.out.println(sql);
            }catch(Exception e){
                System.out.println("Exception: " + e.getMessage());
                out.println("Unable to Update");
            }
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
            int status = stmt.executeUpdate(sql);
            if(status>0){
                response.sendRedirect(table);
            }else{
                out.println("Unable to Update");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

}