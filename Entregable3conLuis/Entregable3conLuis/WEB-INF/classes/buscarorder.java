import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class buscarorder extends HttpServlet {
	Connection connection;
     

  public void destroy() {
    System.out.println("Nothing to do...");
  }
	public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connection=DriverManager.getConnection("jdbc:odbc:Database"); 
        } catch(Exception e) {
            e.printStackTrace();
        }
  } 
          

  public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	res.setContentType("text/html");
	String Name = req.getParameter("Name");


    PrintWriter out = null;
    try {
      out=res.getWriter();
    } catch (IOException io) {
      System.out.println("Exception"); 
	
    }
	res.setContentType("text/html");
    PrintWriter toClient = res.getWriter();
    toClient.println("<!DOCTYPE HTML>");
	toClient.println("<HTML>");
    toClient.println("<HEAD>");
	toClient.println("<title>Customers</title>");
	toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
	toClient.println("</head>");
    toClient.println("<BODY >");
	toClient.println("<div class=\"header\">");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">CUSTOMERS</h1>");
	toClient.println("<form action=\"supplierssorted\" method=\"get\">");
	toClient.println("<p align=\"right\"><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a></p>");
	toClient.println("</div>");
	toClient.println("<TR>");
	toClient.println("<table class=\"table\" align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:800px;\">");
	toClient.println("<p>&nbsp;</p>");
	toClient.println("<p>&nbsp;</p>");
	toClient.println("<caption>Your Order</caption>");
	toClient.println("<thead>");
	toClient.println("<tr class=\"tr\">");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Customer Id</span></th>");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Customer </span></th>");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Product</span></th>");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Price</span></th>");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Date</span></th>");
	toClient.println("</tr>");
	toClient.println("</thead>");
	toClient.println("<tbody>");
	
   try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery("Select CustomerID,Customer,Product,Price,Fecha FROM Customer WHERE Customer like '%" + Name +"%'");
   
   while (result.next()) {
        
		String Id1=result.getString("CustomerID");
		String Customer1=result.getString("Customer");
		String Product1=result.getString("Product");
		String Price1=result.getString("Price");
		String Fecha1=result.getString("Fecha");
		toClient.println("<tr class=\"tr\">");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\">" + Id1 + "</td>");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\">" + Customer1 + "</td>");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\">" + Product1 + "</td>");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\">" + Price1 + "</td>");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\">" + Fecha1 + "</td>");
		toClient.println("</tr>");
            
        }
		}
		catch(SQLException e) {
            e.printStackTrace();
            System.out.println("nada");
        }
		
        

    toClient.println( "</Tbody>");
	toClient.println("</TR>");
    toClient.println( "</TABLE>");
	toClient.println("<p>&nbsp;</p>");
	toClient.println("	<font color=\"black\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; <a href=\"Pedido.html\">Back to Menu</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</font>");
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