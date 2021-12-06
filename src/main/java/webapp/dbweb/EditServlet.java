package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "EditServlet", value = "/edit")
public class EditServlet extends HttpServlet {
    private String message;


    public void init() {
        message = "Edit Users";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        String url = "jdbc:postgresql://localhost:5432/homework2";
        String user = "postgres";
        String password = "code";

        String table = request.getParameter("table");
        String key = request.getParameter("key");

        try{
            Class.forName("org.postgresql.Driver");
            System.out.println("Successful database connection");
        } catch(Exception e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            conn.setAutoCommit(false);
            Statement stmt = conn.createStatement();
            if(table.equalsIgnoreCase("users")) {
                String sql = "SELECT * FROM users WHERE email='" + key + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=users&key=" + key + "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Email:</td><td><input type='email' name='email' value='" + rset.getString("email") + "'/></td></tr>");
                    out.print("<tr><td>Name:</td><td><input type='text' name='name' value='" + rset.getString("name") + "'/></td></tr>");
                    out.print("<tr><td>Surname:</td><td><input type='text' name='surname' value='" + rset.getString("surname") + "'/> </td></tr>");
                    out.print("<tr><td>Salary:</td><td><input type='salary' name='salary' value='" + rset.getString("salary") + "'/></td></tr>");
                    out.print("<tr><td>Phone:</td><td><input type='phone' name='phone' value='" + rset.getString("phone") + "'/></td></tr>");
                    out.print("<tr><td>Country:</td><td><input type='country' name='country' value='" + rset.getString("cname") + "'/></td></tr>");
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("dtype")){
                String sql = "SELECT * FROM diseasetype WHERE id='" + key + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=dtype&key=" + key + "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>id:</td><td><input type='id' name='id' value='" + rset.getString("id") + "'/></td></tr>");
                    out.print("<tr><td>Description:</td><td><input type='text' name='description' value='" + rset.getString("description") + "'/></td></tr>");
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("country")){
                String sql = "SELECT * FROM country WHERE cname='" + key + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=country&key=" + key + "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Country:</td><td><input type='country' name='country' value='" + rset.getString("cname") + "'/></td></tr>");
                    out.print("<tr><td>Population:</td><td><input type='text' name='population' value='" + rset.getString("population") + "'/></td></tr>");
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("disease")){
                String sql = "SELECT * FROM disease WHERE disease_code='" + key + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=disease&key=" + key + "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Disease code:</td><td><input type='text' name='dcode' value='" + rset.getString("disease_code") + "'/></td></tr>");
                    out.print("<tr><td>Pathogen:</td><td><input type='text' name='pathogen' value='" + rset.getString("pathogen") + "'/></td></tr>");
                    out.print("<tr><td>Description:</td><td><input type='text' name='description' value='" + rset.getString("description") + "'/></td></tr>");
                    out.print("<tr><td>id:</td><td><input type='text' name='id' value='" + rset.getString("id") + "'/></td></tr>");

                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("discover")){
                String sql = "SELECT * FROM discover WHERE cname='" + key + "' AND disease_code='" + request.getParameter("key2") + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=discover&key=" + key + "&key2=" +request.getParameter("key2")+ "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Country:</td><td><input type='text' name='cname' value='" + rset.getString("cname") + "'/></td></tr>");
                    out.print("<tr><td>Disease code:</td><td><input type='text' name='dcode' value='" + rset.getString("disease_code") + "'/></td></tr>");
                    out.print("<tr><td>First encounter date:</td><td><input type='text' name='fed' value='" + rset.getString("first_enc_date") + "'/></td></tr>");

                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("pservant")) {
                String sql = "SELECT * FROM publicservant WHERE email='" + key + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=pservant&key=" + key + "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Email:</td><td><input type='text' name='email' value='" + rset.getString("email") + "'/></td></tr>");
                    out.print("<tr><td>Department:</td><td><input type='text' name='dep' value='" + rset.getString("department") + "'/></td></tr>");

                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("doctor")) {
                String sql = "SELECT * FROM doctor WHERE email='" + key + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=doctor&key=" + key + "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Email:</td><td><input type='text' name='email' value='" + rset.getString("email") + "'/></td></tr>");
                    out.print("<tr><td>Degree:</td><td><input type='text' name='degree' value='" + rset.getString("degree") + "'/></td></tr>");

                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("spec")){
                String sql = "SELECT * FROM specialize WHERE id='" + key + "' AND email='" + request.getParameter("key2") + "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=spec&key=" + key + "&key2=" +request.getParameter("key2")+ "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>id:</td><td><input type='text' name='id' value='" + rset.getString("id") + "'/></td></tr>");
                    out.print("<tr><td>Email:</td><td><input type='text' name='email' value='" + rset.getString("email") + "'/></td></tr>");
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }else if(table.equalsIgnoreCase("record")) {
                String sql = "SELECT * FROM record WHERE email='" + key + "' AND cname ='" + request.getParameter("key2")+"' AND disease_code='"+ request.getParameter("key3")+ "'";
                ResultSet rset = stmt.executeQuery(sql);
                if (rset.next()) {

                    out.println("<form action='update?table=record&key=" + key + "&key2=" +request.getParameter("key2")+ "&key3=" +request.getParameter("key3")+ "' method='post'>");
                    out.println("<table>");

                    out.print("<tr><td>Email:</td><td><input type='email' name='email' value='" + rset.getString("email") + "'/></td></tr>");
                    out.print("<tr><td>Country:</td><td><input type='country' name='country' value='" + rset.getString("cname") + "'/></td></tr>");
                    out.print("<tr><td>Disease code:</td><td><input type='text' name='dcode' value='" + rset.getString("disease_code") + "'/></td></tr>");
                    out.print("<tr><td>Total_deaths:</td><td><input type='text' name='totd' value='" + rset.getString("total_deaths") + "'/></td></tr>");
                    out.print("<tr><td>Total patients:</td><td><input type='text' name='totp' value='" + rset.getString("total_patients") + "'/> </td></tr>");
                    out.print("<tr><td colspan='2'><input type='submit' value='Edit & Save '/></td></tr>");

                    out.println("</table>");
                    out.println("</form>");
                }
            }
            } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

}