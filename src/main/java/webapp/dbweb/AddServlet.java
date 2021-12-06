package webapp.dbweb;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.sql.*;

@WebServlet(name = "AddServlet", value = "/add")
public class AddServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<body>");

        String table = request.getParameter("table");

        if(table.equalsIgnoreCase("users")) {

            out.println("<h1>Add new User</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=users' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Email:</td><td><input type='email' name='email'/></td></tr>");
            out.print("<tr><td>Name:</td><td><input type='text' name='name'/></td></tr>");
            out.print("<tr><td>Surname:</td><td><input type='text' name='surname'/> </td></tr>");
            out.print("<tr><td>Salary:</td><td><input type='salary' name='salary'/></td></tr>");
            out.print("<tr><td>Phone:</td><td><input type='phone' name='phone'/></td></tr>");
            out.print("<tr><td>Country:</td><td><input type='country' name='country'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }
        else if(table.equalsIgnoreCase("dtype")){
            out.println("<h1>Add new Disease Type</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=dtype' method='post'>");
            out.println("<table>");

            out.print("<tr><td>id:</td><td><input type='id' name='id'/></td></tr>");
            out.print("<tr><td>Description:</td><td><input type='text' name='description'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }
        else if(table.equalsIgnoreCase("country")){
            out.println("<h1>Add new Country</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=country' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Country:</td><td><input type='country' name='country'/></td></tr>");
            out.print("<tr><td>Population:</td><td><input type='text' name='population'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }
        else if(table.equalsIgnoreCase("disease")){
            out.println("<h1>Add new Disease</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=disease' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Disease code:</td><td><input type='text' name='dcode'/></td></tr>");
            out.print("<tr><td>Pathogen:</td><td><input type='text' name='pathogen'/></td></tr>");
            out.print("<tr><td>Description:</td><td><input type='text' name='description'/></td></tr>");
            out.print("<tr><td>id:</td><td><input type='text' name='id'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }else if(table.equalsIgnoreCase("discover")){
            out.println("<h1>Add new Discovery</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=discover' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Country:</td><td><input type='text' name='cname'/></td></tr>");
            out.print("<tr><td>Disease code:</td><td><input type='text' name='dcode'/></td></tr>");
            out.print("<tr><td>First Encounter date (YYYY-MM-DD):</td><td><input type='text' name='fed'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }else if(table.equalsIgnoreCase("pservant")){
            out.println("<h1>Add new Public Servant</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=pservant' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Email:</td><td><input type='text' name='email'/></td></tr>");
            out.print("<tr><td>Department:</td><td><input type='text' name='dep'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }else if(table.equalsIgnoreCase("doctor")){
            out.println("<h1>Add new Doctor</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=doctor' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Email:</td><td><input type='text' name='email'/></td></tr>");
            out.print("<tr><td>Degree:</td><td><input type='text' name='degree'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }else if(table.equalsIgnoreCase("spec")){
            out.println("<h1>Add new Specialization</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=spec' method='post'>");
            out.println("<table>");

            out.print("<tr><td>id:</td><td><input type='text' name='id'/></td></tr>");
            out.print("<tr><td>Email:</td><td><input type='text' name='email'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }
        else if(table.equalsIgnoreCase("record")) {

            out.println("<h1>Add new Record</h1>");
            out.println("</body></html>");
            out.println("<form action='save?table=record' method='post'>");
            out.println("<table>");

            out.print("<tr><td>Email:</td><td><input type='email' name='email'/></td></tr>");
            out.print("<tr><td>Country:</td><td><input type='country' name='country'/></td></tr>");
            out.print("<tr><td>Disease code:</td><td><input type='text' name='dcode'/></td></tr>");
            out.print("<tr><td>Total deaths:</td><td><input type='text' name='totd'/></td></tr>");
            out.print("<tr><td>Total patients:</td><td><input type='text' name='totp'/></td></tr>");
            out.print("<tr><td colspan='2'><input type='submit' value='Save'/></td></tr>");

            out.println("</table>");
            out.println("</form>");
        }
    }

}