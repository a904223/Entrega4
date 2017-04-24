import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class busqueda_clientes3 extends HttpServlet {
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

   public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
        
            
		try {
            
            String OptionStr = request.getParameter("x");
            System.out.println("OptionStr: " + OptionStr);
            
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement();
			
            String sql = "SELECT UnitPrice FROM Products1 WHERE ProductName LIKE '" + OptionStr + "'";
            System.out.println("sql: " + sql);
            ResultSet result = statement.executeQuery(sql);
            result.next();
            String strPrice = result.getString("UnitPrice");
            int IntPrice = Integer.parseInt(strPrice);
            System.out.println("IntPrice: " + IntPrice);
            
            out.println(IntPrice);
            
    
            
			out.flush();
			out.close();
			
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset:  Exception: " + e);
		}  
			out.close();
		}
}