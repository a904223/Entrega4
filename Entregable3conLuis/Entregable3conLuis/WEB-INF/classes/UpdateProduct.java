import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class UpdateProduct extends HttpServlet {
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
		System.out.println("Starting Comprobacion...");
	}
	
	public void destroy() {
		super.destroy();
        System.out.print("Closing connection ...");
        try {
            connection.close();   
			System.out.println("Connection closed");
        } catch(SQLException ex){
            System.out.println("Problem closing connection");
            System.out.println(ex.getMessage());
        }
    }

    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String Id = req.getParameter("Id");
        String Name = req.getParameter("Name");
		String Sell = req.getParameter("Sell");
		String Buy = req.getParameter("Buy");
	 

		
		resp.setContentType("text/html");
        PrintWriter toClient = resp.getWriter();

			
        
        Statement stmt = null;
		
		String sql = "UPDATE Products SET Product_Name ='"+ Name +"', Sell_Price='"+Sell+"', Buy_Price='"+Buy+"' WHERE ProductId =" + Id +"";
				
				try{
					Statement statement = connection.createStatement();
					statement.executeUpdate(sql);
					statement.close();

         
				} catch(SQLException e) {
					e.printStackTrace();
					System.out.println("Error: " + e);
				}
						toClient.println("<!DOCTYPE HTML>");
		toClient.println("<HTML>");
		toClient.println("<HEAD>");
		toClient.println("<title>Update Product</title>");
		toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
		toClient.println("</head>");
		toClient.println("<BODY >");
		toClient.println("<div class=\"header\">");
		toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
		toClient.println("<h1 align=\"center\">Update Succesful!</h1>");
		toClient.println("<p align=\"right\"><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a>");
		toClient.println("</div>");
	toClient.println("	<font color=\"black\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"products\">Back to Products</a></font>");
toClient.println("<ul class=\"navbar\">");
            toClient.println("<li class=\"dropdown-1\"><a class=\"menu\" class=\"dropbtn\"><font face=\"Arial\">Menu</font></a>");
                toClient.println("<ul>");
                    toClient.println("<div class=\"dropdown-content-1\">");
                        toClient.println("<li class=\"dropdown-2\"><a class=\"dropbtn\" class=\"active\">Purchases</a>");
                            toClient.println("<ul>");
                            toClient.println("<div class=\"dropdown-content-2\">");
                                toClient.println("<a href=\"suppliers\">Suppliers</a>");
                                toClient.println("<a href=\"#\">New Order</a>");
                                toClient.println("<a href=\"#\">Orders</a>");
                            toClient.println("</div>");
                            toClient.println("</ul>");
                        toClient.println("</li>");
                        toClient.println("<li class=\"dropdown-2\"><a class=\"dropbtn\">Sales</a>");
                            toClient.println("<ul>");
                            toClient.println("<div class=\"dropdown-content-2\">");
                                toClient.println("<a href=\"customers\">Customers</a>");
                                toClient.println("<a href=\"#\">New Order</a>");
                                toClient.println("<a href=\"#\">Orders</a>");
                            toClient.println("</div>");
                            toClient.println("</ul>");
                        toClient.println("</li>");
                        toClient.println("<li class=\"dropdown-2\"><a class=\"dropbtn\">Inventory</a>");
                            toClient.println("<ul>");
                            toClient.println("<div class=\"dropdown-content-2\">");
                                toClient.println("<a href=\"products\">Products</a>");
                                toClient.println("<a href=\"warehouses\">Warehouse</a>");
                            toClient.println("</div>");
                            toClient.println("</ul>");
                        toClient.println("</li>");
                        toClient.println("<li class=\"dropdown-2\"><a class=\"dropbtn\" href=\"accounting.html\">Accounting</a></li>");
                        toClient.println("<li class=\"dropdown-2\"><a class=\"dropbtn\" href=\"bills.html\">Bills</a></li>");
                    toClient.println("</div>");
                toClient.println("</ul>");
            toClient.println("</li>");
        toClient.println("</ul>");
	toClient.println("</BODY>");
    toClient.println("</HTML>");
	toClient.flush();
    toClient.close();
	}
}