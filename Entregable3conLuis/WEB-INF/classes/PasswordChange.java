import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class PasswordChange extends HttpServlet{
    
    Connection connection;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
        
        try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
        
        String usuario = request.getParameter("usuario");
        String pass_actual = request.getParameter("pass_actual");
        System.out.println("Email: " + usuario + ", pass_actual: " + pass_actual);
        String pass_nueva1 = request.getParameter("pass_nueva1");
        String pass_nueva2 = request.getParameter("pass_nueva2");
        
        if(usuario==null) {
            System.out.println("Problem reading username from request");
            return;
        }
        if(pass_actual==null) {
          System.out.println("Problem reading the actual password from request");
          return;
        }
        if(pass_nueva1==null) {
          System.out.println("Problem reading the new password from request");
          return;
        }
        if(pass_nueva2==null) {
          System.out.println("Problem reading the new password from request");
          return;
        }
        
                
        String sql_contra = "SELECT Password FROM Users WHERE Mail='" + usuario + "'"; 
        String sql_insert = "UPDATE Users SET Password = '" + pass_nueva1 + "' WHERE Mail='" + usuario + "'";
        System.out.println(sql_contra);
        
        try{
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement();
            
            ResultSet result = statement.executeQuery(sql_contra);
            result.next();
            String SqlContrasena = result.getString("Password");
            System.out.println("SqlContrasena:/" +SqlContrasena+ "/");
            System.out.println("Clase de SqlContrasena:" +SqlContrasena.getClass().getName());
            System.out.println("pass_actual:/" +pass_actual+ "/");
            System.out.println("Clase de pass_actual:" +pass_actual.getClass().getName());
            
            if (SqlContrasena.equals(pass_actual)){
                System.out.println("Siiiiiiiiiiiiiiiiiiiiiii");
                statement2.executeUpdate(sql_insert);
                statement2.close();
                
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Password Change</title>");
                out.println("<link rel=StyleSheet type=text/css href=pattern.css>");
                out.println("</head>");
                out.println("<body bgcolor=\"#FFFFFF\" text=\"#631818\">");
                out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">PASSWORD CHANGE</h1></div>");
                out.println("<p align=\"center\"><font size=\"6\"><b>PASSWORD CHANGE WAS SUCCESSFULLY ACHIEVED</b></font></p>");
                out.println("<p>Old password: " + pass_actual + "</p>");
                out.println("<p>New password: " + pass_nueva1 + "</p>");
				out.println("<p align=\"right\"><a href=\"Login.html\"><input type=\"button\" class=\"boton grisn\" value=\"Go back to Login\" name=\"Go back to Login\"></a>");
                out.println("</body>");
                out.println("</html>");
                
            } else if (SqlContrasena!=pass_actual){
                System.out.println("Nooooooooooooooooooooooo");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Password Change</title>");
                out.println("<link rel=StyleSheet type=text/css href=pattern.css>");
                out.println("</head>");
                out.println("<body bgcolor=\"#FFFFFF\" text=\"#631818\">");
                out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">PASSWORD CHANGE</h1></div>");
                out.println("<p align=\"center\"><font size=\"6\"><b>THERE WAS A MISTAKE</b></font></p>");
                out.println("<p align=\"center\"><font size=\"4\"><b>Wrong password. Try again</b></font></p>");
                out.println("<p align=\"right\"> <a href=\"PasswordChange.html\"><input type=\"button\" class=\"boton grisn\" value=\"Back\" name=\"Back\"></a>");
                out.println("</body>");
                out.println("</html>");
            }
            
            
            
            //out.println(resString);
           

            // String strFilas = request.getParameter("employee");
            // int intFilas = Integer.parseInt(strFilas);
            // int intColumnas = 7 ;
            
            
            // String[] name=new String[intFilas] ;
            
            // for (int a=0; a<intFilas ; a++) {
                // name [a] = request.getParameter("name" + (a+1));
            // }
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql_contra + " Exception: " + e);
            System.out.println("Nooooooooooooooooooooooo");
            out.println("<html>");
                out.println("<head>");
                out.println("<title>Password Change</title>");
                out.println("<link rel=StyleSheet type=text/css href=pattern.css>");
                out.println("</head>");
                out.println("<body bgcolor=\"#FFFFFF\" text=\"#631818\">");
                out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">PASSWORD CHANGE</h1></div>");
                out.println("<p align=\"center\"><font size=\"6\"><b>THERE WAS A MISTAKE</b></font></p>");
                out.println("<p align=\"center\"><font size=\"4\"><b>Wrong password. Try again</b></font></p>");
                out.println("<a class=\"boton grisn\" href=\"http://localhost:8080/ErpTecnun_funciones/PasswordChange.html\">Go back</a>");
                out.println("</body>");
                out.println("</html>");
        }
        out.flush();
        out.close();
    }
}