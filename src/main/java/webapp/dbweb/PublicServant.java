package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "PublicServant", value = "/pservant")
public class PublicServant extends HttpServlet {
    private String message;


    public void init() {
        message = "Public Servant";
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
            String sql = "SELECT * FROM PublicServant";
            ResultSet rset = stmt.executeQuery(sql);

            out.println("<table>");
            out.println("<tr><th>Email</th><th>Department</th><th>Action</th></tr>");

            while(rset.next()) {
                String key = rset.getString("email");
                out.println("<tr><td>" + rset.getString("email") + "</td><td>" + rset.getString("department") + "</td><td>\n" +
                        "<a href=\"/dbweb_war_exploded/edit?table=pservant&key=" +key + "\" class=\"badge badge-dark\">Edit</a>  <a href=\"/dbweb_war_exploded/delete?table=pservant&key=" + key+ "\" class=\"badge badge-dark\">Delete</a>" + "</td></tr>");
            }

            out.println("</table>");
            out.println("<a href=\"add?table=pservant\">Add new Public Servant </a><br>");

            out.println("<br><a href=\"index.html\">Main Page </a>");

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }

    public void destroy() {
    }
}