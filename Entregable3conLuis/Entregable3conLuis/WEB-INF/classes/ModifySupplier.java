import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ModifySupplier extends HttpServlet {
	int i=0;
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
	toClient.println("<title>Suppliers</title>");
	toClient.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
	toClient.println("</head>");
    toClient.println("<BODY >");
	toClient.println("<div class=\"header\">");
	toClient.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	toClient.println("<h1 align=\"center\">SUPPLIERS</h1>");
	toClient.println("<p align=\"right\"><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a>");
	toClient.println("</div>");
	toClient.println("<meta align=\"right\" http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
	toClient.println("<TR>");
	toClient.println("<table class=\"table\" align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:800px;\">");
	toClient.println("<p>&nbsp;</p>");
	toClient.println("<p>&nbsp;</p>");
	toClient.println("<thead>");
	toClient.println("<tr class=\"tr\">");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Supplier Id</span></th>");
	toClient.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Supplier Name</span></th>");
	toClient.println("</tr>");
	toClient.println("</thead>");
	toClient.println("<tbody>");
	
   try {
            Statement statement=connection.createStatement();
            ResultSet result = statement.executeQuery("Select SupplierID,SupplierName,CIF,Telephone,Email FROM Suppliers");
   
   while (result.next()) {
        
		i=i+1;
		String Id=result.getString("SupplierID");
		String Name=result.getString("SupplierName");
		String CIF=result.getString("CIF");
		String TLF=result.getString("Telephone");
		String Email=result.getString("Email");
		toClient.println("<form method=\"get\" action=\"ModSupplier\" >");
		toClient.println("<tr class=\"tr\">");
		toClient.println("<input type=\"hidden\" name=\"Id\" value="+ Id +"  />");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\"><INPUT  TYPE=\"submit\" value=\"" + Id + "\"></td></td>");
		toClient.println("<td class=\"td\" bgcolor=\"#81BEF7\"><INPUT  TYPE=\"submit\" value=\"" + Name + "\"></td></td>");
		toClient.println("<input type=\"hidden\" name=\"Name\" value=\"" + Name + "\"></td>");
		toClient.println("<input type=\"hidden\" name=\"CIF\" value=\"" + CIF + "\"></td>");
		toClient.println("<input type=\"hidden\" name=\"TLF\" value=\"" + TLF + "\"></td>");
		toClient.println("<input type=\"hidden\" name=\"Email\" value=\"" + Email + "\"></td>");
		toClient.println("</tr>");
		toClient.println("</form>");
            
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
	toClient.println("	<font color=\"black\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"suppliers\">Cancel</a></font>");
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