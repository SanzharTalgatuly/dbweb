package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "Disease", value = "/disease")
public class Disease extends HttpServlet {
    private String message;


    public void init() {
        message = "Diseases";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<style>table,th,td,tr{border: 1px solid black};</style>");
        out.println("<body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");

        String url = "jdbc:postgresql://localhost:5432/homework2";
        String user = "postgres";
        String password = "code";

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
            String sql = "SELECT * FROM disease";
            ResultSet rset = stmt.executeQuery(sql);

            out.println("<table>");
            out.println("<tr><th>Disease code</th><th>Pathogen</th><th>Description</th></th><th>id</th></th><th>Action</th></tr>");

            while(rset.next()) {
                String key = rset.getString("disease_code");
                out.println("<tr><td>" + rset.getString("disease_code") + "</td><td>" + rset.getString("pathogen") + "</td><td>" + rset.getString("description") + "</td><td>" + rset.getString("id") + "</td><td>\n" +
                        "<a href=\"/dbweb_war_exploded/edit?table=disease&key=" +key + "\" class=\"badge badge-dark\">Edit</a>  <a href=\"/dbweb_war_exploded/delete?table=disease&key=" + key+ "\" class=\"badge badge-dark\">Delete</a>" + "</td></tr>");
            }

            out.println("</table>");
            out.println("<a href=\"add?table=disease\">Add new Disease </a>");

            out.println("<br><a href=\"index.html\">Main Page </a><br>");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    public void destroy() {
    }
}