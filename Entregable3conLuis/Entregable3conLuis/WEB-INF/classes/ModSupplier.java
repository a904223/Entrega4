import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class ModSupplier extends HttpServlet {
      

  public void doGet (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

	res.setContentType("text/html");
	String Id = req.getParameter("Id");
	String Name = req.getParameter("Name");
	String CIF = req.getParameter("CIF");
	String TLF = req.getParameter("TLF");
	String Email = req.getParameter("Email");


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
	toClient.println("<h1 align=\"center\">MODIFY Supplier</h1>");
	toClient.println("<p align=\"right\"><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a>");
	toClient.println("</div>");
	toClient.println("<form method=\"get\" action=\"UpdateSupplier\" >");
	toClient.println("<table cellspacing=\"1\" cellpadding=\"1\" border=\"0\" align=\"right\" width=\"80%\" summary=\"\">");
	toClient.println("<tbody>");
	toClient.println("<tr>");
	toClient.println("<td><font face=\"Agency FB\">Supplier Id</font></td>");
	toClient.println("<td align=\"left\">" +Id+ "</td>");
	toClient.println("</tr>");
	toClient.println("<input type=\"Hidden\" name=\"Id\"  value=\""+Id+"\"/>");
	toClient.println("<tr>");
	toClient.println("<td><font face=\"Agency FB\">Supplier Name</font></td>");
	toClient.println("<td align=\"left\"><input type=\"text\" name=\"Name\" value=\"" + Name + "\" ></td>");
	toClient.println("</tr>");
	toClient.println("<tr>");
	toClient.println("<td><font face=\"Agency FB\">CIF</font></td>");
	toClient.println("<td align=\"left\"><input type=\"text\" name=\"CIF\" value=\"" + CIF + "\" ></td>");
	toClient.println("</tr>");
	toClient.println("<tr>");
	toClient.println("<td><font face=\"Agency FB\">Telephone</font></td>");
	toClient.println("<td align=\"left\"><input type=\"text\" name=\"TLF\" value=\"" + TLF + "\" ></td>");
	toClient.println("</tr>");
	toClient.println("<tr>");
	toClient.println("<td><font face=\"Agency FB\">Email</font></td>");
	toClient.println("<td align=\"left\"><input type=\"text\" name=\"Email\" value=\"" + Email + "\" ></td>");
	toClient.println("</tr>");
	toClient.println("<td align=\"left\"><INPUT class=\"boton grisn\" TYPE=\"submit\" value=\"Update Supplier\"></td>");
	toClient.println("</form>");
            
        

        
		
        

    toClient.println( "</Tbody>");
	toClient.println("</TR>");
    toClient.println( "</TABLE>");
	toClient.println("<p>&nbsp;</p>");
	toClient.println("	<font color=\"black\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"suppliers\">Cancel</a></font>");
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