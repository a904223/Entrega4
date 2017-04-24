import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MostrarCustomer extends HttpServlet {
    Connection connection;  
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando MostrarCustomer...");
  }

  public void destroy() {
    System.out.println("There is nothing to do...");
  }
  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
    //HttpSession session = req.getSession(true);
    //String sesion = (String)session.getAttribute("sesion");
    
    ResultSet result1=null;
    String Customer="";
    ArrayList<String> CustomerIDA= new ArrayList<String>();
    ArrayList<String> CustomerA= new ArrayList<String>();
    ArrayList<String> ProductA= new ArrayList<String>();
    ArrayList<String> PriceA= new ArrayList<String>();
    ArrayList<String> DateA= new ArrayList<String>();
    
    //String sql2 = "SELECT * FROM  Customer ";
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            System.out.println("1");
            connection=DriverManager.getConnection(url); 
            System.out.println("2");
            //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\log.txt", true);
            //PrintWriter toFile = new PrintWriter(fileWriter);
            //toFile.println(" ok:");
            //fileWriter.close();
            Statement statement = connection.createStatement();
            System.out.println("3");
          
             result1=statement.executeQuery("SELECT * FROM  Customer");
            System.out.println("4");
            while (result1.next() ){
                CustomerIDA.add(result1.getString(1));
                CustomerA.add(result1.getString(2));
                ProductA.add(result1.getString(3));
                PriceA.add(result1.getString(4));
                DateA.add(result1.getString(5));
                //Customer=result1.getString("Customer");
                //System.out.println(result1.getString("Customer"));
            }
           //devolverPaginaHTML(resp, "", "", "", "", result1);
            connection.close(); 
            
            
            
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\log.txt", true);
            //PrintWriter toFile = new PrintWriter(fileWriter);
            //toFile.println("no ok:");
            //fileWriter.close();
            e.printStackTrace();
        }
                 
    //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
    //PrintWriter toFile = new PrintWriter(fileWriter);
    
    //toFile.println("> " + strCustomer + "\t" + strProduct + "\t" + strPrice + "\t" + strDate + "\t" + sesion);
    //fileWriter.close();
    devolverPaginaHTML(resp, Customer, ProductA, PriceA, DateA, CustomerA, CustomerIDA);
  }
    
  public void devolverPaginaHTML(HttpServletResponse resp, String customer, ArrayList<String> ProductA, ArrayList<String> PriceA, ArrayList<String> DateA, ArrayList<String> CustomerA, ArrayList<String> CustomerIDA  ) {

    resp.setContentType("text/html");

    PrintWriter out = null;
    try {
        //while (result1.next() ){
               // String Customer=result1.getString("Customer");
                //System.out.println(Customer);
           // }
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("There has been an exception");    
    }
       
    out.println("<HTML>");
    out.println("<HEAD>");
    out.println("<TITLE>Orders</TITLE>");
    out.println("<link rel=\"Stylesheet\" type=\"text/css\" href=\"pattern.css\">");
    out.println("<script type=\"text/javascript\" src=\"ModificarPedido.js\"></script>");
    out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
    out.println("</HEAD>");
    out.println("<BODY background=\"gris.jpg\">");
    out.println("<div class=\"header\">");
	out.println("<img align=\"left\" src=\"Logo ERP Tecnun.png\">");
	out.println("<h1 align=\"center\"></h1>");
	out.println("<form action=\"buscarorder\" method=\"get\">");
	out.println("<p align=\"right\"><font color=\"black\"> Search by Customer Name<img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/lupa_318-50440.jpg\" style=\"width: 16px; height: 16px;\" /><input maxlength=\"26\" name=\"Name\" type=\"text\"></input>");
	out.println("<input class=\"boton grisn\" type=\"submit\" value=\"Buscar\" /> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</font><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a></P></form>");
	out.println("</div>");
    
    //out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">CLIENT ORDERS</h1></div><ul class=\"navbar\"><li class=\"dropdown\"><a class=\"dropbtn\"><font face=\"Arial\">Menu</font></a><div class=\"dropdown-content\"><a class=\"active\" href=\"Pedido.html\">Orders</a><a href=\"customers.html\">Customers</a><a href=\"producttxt.html\">Products</a><a href=\"accounting.html\">Accounting</a><a href=\"bills.html\">Bills</a></div></li></ul>");
    out.println("<button align=\"right\" id=\"modificar\" onclick=\"edit_row()\" class=\"boton grisn\">Modify</button><button id=\"guardar\" onclick=\"save_row()\" style=\"display: none;\" class=\"boton grisn\">Save</button><div id=\"mensaje\"></div><div id=\"contador\"></div><p align=\"center\"><font size=\"6\"><b> </b></font></p>");
    
    out.println("<center>");
    //while (result1.next()){
        //String Customer=result1.getString("Customer");
        
   out.println("<B><font align=\"center\" size=\"7\" color=\"#088A85\" face=\"Calibri\">ORDERS</FONT></B>");
   out.println("<form action=\"Delete\">");
   
    
        
    out.println( "<table id=\"data_table\" class=\"table\" align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:800px;\">");
    out.println("<thead>");
    
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">CustomerID</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Customer</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Product</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Price</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Date</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Delete</span></th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");
        out.println("<tr class=\"tr\">");
        for (int i=0; i<CustomerA.size();i++){
            out.println("<td id=\"" + (i+1)+ "_1\" class=\"td\" bgcolor=\"#81BEF7\">" + CustomerIDA.get(i) + "</td>");
            out.println("<td id=\"" + (i+1)+ "_2\" class=\"td\" bgcolor=\"#81BEF7\">" + CustomerA.get(i) + "</td>");
            out.println("<td id=\"" + (i+1)+ "_3\" class=\"td\" bgcolor=\"#81BEF7\">"+ ProductA.get(i) +"</td>");
            out.println("<td id=\"" + (i+1)+ "_4\" class=\"td\" bgcolor=\"#81BEF7\">"+ PriceA.get(i) +"</td>");
            out.println("<td id=\"" + (i+1)+ "_5\" class=\"td\" bgcolor=\"#81BEF7\">"+ DateA.get(i) +"</td>");
            out.println("<td class=\"td\" bgcolor=\"#81BEF7\"><input type=\"submit\" name=\"delete\"value=delete:" + CustomerA.get(i) +"></td>");
            out.println("</tr>");
        }
  
    out.println("</tbody>");
    out.println("</table>");
   out.println("</form>");
    out.println("<BR><a href=\"Pedido.html\">Back to menu</a>");
    out.println("</center>");
    out.println("</BODY>");
   

    out.println("</HTML>");

    out.flush();
    out.close();
  }
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  }
}