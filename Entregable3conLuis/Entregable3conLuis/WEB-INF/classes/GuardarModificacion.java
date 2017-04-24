import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class GuardarModificacion extends HttpServlet {
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
            
            String strcont = request.getParameter("filas");
            String strcustomerID = request.getParameter("customerID");
            String strcustomer = request.getParameter("customer");
            String strproduct = request.getParameter("product");
            String strprice = request.getParameter("price");
            String strdate = request.getParameter("date");
            System.out.println("strcont: " + strcont);
            System.out.println("customerID: " + strcustomerID);
            System.out.println("customer: " + strcustomer);
            System.out.println("product: " + strproduct);
            System.out.println("price: " + strprice);
            System.out.println("date: " + strdate);
            int cont = Integer.parseInt(strcont);
            
            String[] sql = new String[cont];
            
            
            String[] customer2 = strcustomer.split(",");
            System.out.println("customer2: " + customer2);
            
            String[] product2 = strproduct.split(",");
            System.out.println("product2: " + product2);
            
            String[] price2 = strprice.split(",");
             System.out.println("price2: " + price2);
            
             String[] date2 = strdate.split(",");
             System.out.println("date2: " + date2);
            
            
            
            Statement statement=connection.createStatement();
            System.out.println("prueba: " + customer2);
            
            for (int i=1; i<cont; i++){
                System.out.println("customerbucle2: " + customer2[i]);
                customer2[i] = customer2[i].replace("\"", "");
                customer2[i] = customer2[i].replace("]", "");
                
                product2[i] = product2[i].replace("\"", "");
                product2[i] = product2[i].replace("]", "");
                
                price2[i] = price2[i].replace("\"", "");
                 price2[i] = price2[i].replace("]", "");
                
                date2[i] = date2[i].replace("\"", "");
                 date2[i] = date2[i].replace("]", "");
                
                
                
                sql[i]="UPDATE Customer SET Customer = '" + customer2[i] + "',Product = '" + product2[i] + "',Price = '" + price2[i] + "',Fecha = '" + date2[i] + "' WHERE CustomerID = " + i + "";
                //sql[i]="UPDATE Customer SET Product = '" + product2[i] + "' WHERE CustomerID = " + i + "";
                System.out.println("sql: " +sql[i]);
                
                System.out.println("Ha entrado en el for del try");
                statement.executeUpdate(sql[i]);
            }
            
			out.flush();
			out.close();
			
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset:  Exception: " + e);
		}  
			out.close();
		}
}