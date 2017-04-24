import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ModificarPedido extends HttpServlet {
    Connection connection;
     
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando ModificarPedido...");
  }

  public void destroy() {
    System.out.println("There is nothing to do...");
  }

  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
    HttpSession session = req.getSession(true);
    String sesion = (String)session.getAttribute("sesion");
    String strCustomer = req.getParameter("customer");
    String strProduct = req.getParameter("product");
    String strPrice = req.getParameter("price");
    String strDate = req.getParameter("date");
    
    String sql2 = "UPDATE Customer SET Product='"  + strProduct + "', Price='" + strPrice +"', Fecha='" + strDate + "' WHERE Customer='" + strCustomer +"'";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
            Statement statement = connection.createStatement();
            ResultSet result=statement.executeQuery(sql2);
           
            connection.close(); 
            
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            e.printStackTrace();
        }
                 
    //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
    //PrintWriter toFile = new PrintWriter(fileWriter);
    
    //toFile.println("> " + strCustomer + "\t" + strProduct + "\t" + strPrice + "\t" + strDate + "\t" + sesion);
    //fileWriter.close();
    devolverPaginaHTML(resp, strCustomer, strProduct, strPrice, strDate);
  }
    
  public void devolverPaginaHTML(HttpServletResponse resp, String customer, String product, String price, String date ) {

    resp.setContentType("text/html");

    PrintWriter out = null;
    try {
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("There has been an exception");    
    }
        
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Orders</TITLE>");
    out.println("<link rel=\"Stylesheet\" type=\"text/css\" href=\"pattern.css\">");
    out.println("</HEAD>");
    out.println("<BODY background=\"gris.jpg\">");
    out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">ACCOUNTING - Staff</h1></div><ul class=\"navbar\"><li class=\"dropdown\"><a class=\"dropbtn\"><font face=\"Arial\">Menu</font></a><div class=\"dropdown-content\"><a class=\"active\" href=\"Pedido.html\">Orders</a><a href=\"customers.html\">Customers</a><a href=\"producttxt.html\">Products</a><a href=\"accounting.html\">Accounting</a><a href=\"bills.html\">Bills</a></div></li></ul>");
    out.println("<center>");
    out.println("<B><font size=\"7\" color=\"#088A85\" face=\"Calibri\">THE ORDER HAS BEEN MODIFIED TO</FONT></B>");
    out.println("<P><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Customer: </B><font color=\"#000000\">" + customer + "</font></FONT>");
    out.println("<BR><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Product: </B><font color=\"#000000\">" + product + "</font></FONT>");
    out.println("<BR><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Price: </B><font color=\"#000000\">" + price + "</font></FONT>");
    out.println("<BR><font size=\"5\" color=\"#04B4AE\" face=\"Calibri\"> <B>Date: </B><font color=\"#000000\">" + date + "</font></FONT>");
    out.println("</BODY>");
    out.println("<BR><a href=\"Pedido.html\">Back to menu</a>");
    out.println("</center>");
    
   

    out.println("</HTML>");

    out.flush();
    out.close();
  }
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  }
}