import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Save_Shifts extends HttpServlet {
    Connection connection;
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
			
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            e.printStackTrace();
        }
    }

   public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
        
            
		try {
            
            String strdias = request.getParameter("dias");
            int dias = Integer.parseInt(strdias);
            
            String strmes = request.getParameter("mes");
            int mes = Integer.parseInt(strmes);
            
            String stranyo = request.getParameter("anyo");
            int anyo = Integer.parseInt(stranyo);
            
            String a = request.getParameter("id");
            System.out.println("a: " + a);
            String[] strid = a.split("\\.");
            System.out.println("strid[0]: " + strid[0]);
            System.out.println("strid[0]: " + strid[0] + " strid[1]: " + strid[1]);
            
            int id_employee = Integer.parseInt(strid[0]);
            int id_day = Integer.parseInt(strid[1]);
            System.out.println("id_employee: " + id_employee + " id_day: " + id_day);
            
            String sql2 = "SELECT Nombre FROM Employees WHERE IDEmployee LIKE " + id_employee + "";
            Statement statement2=connection.createStatement();
            ResultSet result2 = statement2.executeQuery(sql2);
            
            result2.next();
            String name = result2.getString("Nombre");
            
            Statement statement=connection.createStatement();
            
            String sql = "INSERT INTO [Day Off] (IDEmployee, Nombre, Fecha, [Day Off]) VALUES (" + id_employee + ", '" + name + "', '" + dias + "/" + mes + "/" + anyo + "', 1)";
            System.out.println("sql: " +sql);
            
            statement.executeUpdate(sql);
            System.out.println("se ha ejecutado");

            
			out.flush();
			out.close();
			
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset:  Exception: " + e);
		}  
			out.close();
		}
}