import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MostrarSupplier extends HttpServlet {
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
	ResultSet result2=null;
	ResultSet result3=null;
    String Supplier="";
    ArrayList<String> ProductName= new ArrayList<String>();
    ArrayList<String> Quantity= new ArrayList<String>();
    ArrayList<String> UnitPrice= new ArrayList<String>();
    ArrayList<String> TotalPrice= new ArrayList<String>();
	ArrayList<String> OrderID= new ArrayList<String>();
    
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
			Statement statement2 = connection.createStatement();
			Statement statement3 = connection.createStatement();
            System.out.println("3");
			result1=statement.executeQuery("SELECT ProductName, Quantity, UnitPrice, TotalPrice FROM  OrderLines");
            result2=statement2.executeQuery("SELECT OrderID FROM OrderLines");
			result3=statement3.executeQuery("select count(*) AS suma FROM OrderLines");
			result3.next();
				int suma=result3.getInt("suma");
            System.out.println("suma: "+ suma);
            while (result1.next() ){
                ProductName.add(result1.getString(1));
                Quantity.add(result1.getString(2));
                UnitPrice.add(result1.getString(3));
                TotalPrice.add(result1.getString(4));
                //Customer=result1.getString("Customer");
                //System.out.println(result1.getString("Customer"));
            }
			while (result2.next()) {
				OrderID.add(result2.getString("OrderID"));
			}
           //devolverPaginaHTML(resp, "", "", "", "", result1);
            connection.close(); 
            
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
	out.println("<link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\">");
    out.println("<TITLE>Suppliers Orders</TITLE>");
    out.println("</HEAD>");
    
    
    out.println("<center>");
    //while (result1.next()){
        //String Customer=result1.getString("Customer");
			out.println("<div class='header'>");
            out.println("<img align='left' src='Logo ERP Tecnun.png'>");
            out.println("<h1 align='center'>ERP TECNUN</h1>");
        out.println("</div>");
        out.println("<ul class='navbar'>");
            out.println("<li class='dropdown-1'><a class='menu' class='dropbtn'><font face='Arial'>Menu</font></a>");
                out.println("<ul>");
                    out.println("<div class='dropdown-content-1'>");
                        out.println("<li class='dropdown-2'><a class='dropbtn' class='active'>Purchases</a>");
                            out.println("<ul>");
                            out.println("<div class='dropdown-content-2'>");
                               out.println(" <a href='suppliers'>Suppliers</a>");
                                out.println("<a href='Buscador.html'>New Order</a>");
                                out.println("<a href='MostrarCustomer'>Orders</a>");
                            out.println("</div>");
                            out.println("</ul>");
                        out.println("</li>");
                        out.println("<li class='dropdown-2'><a class='dropbtn'>Sales</a>");
                            out.println("<ul>");
                            out.println("<div class='dropdown-content-2'>");
                                out.println("<a href='customers'>Customers</a>");
                                out.println("<a href='BuscadorCustomer.html'>New Order</a>");
                                out.println("<a href='#'>Orders</a>");
                            out.println("</div>");
                            out.println("</ul>");
                        out.println("</li>");
                        out.println("<li class='dropdown-2'><a class='dropbtn'>Inventory</a>");
                            out.println("<ul>");
                            out.println("<div class='dropdown-content-2'>");
                                out.println("<a href='products'>Products</a>");
                                out.println("<a href='warehouses'>Warehouse</a>");
                            out.println("</div>");
                            out.println("</ul>");
                        out.println("</li>");
                        out.println("<li class='dropdown-2'><a class='dropbtn' href='accounting.html'>Accounting</a></li>");
                        out.println("<li class='dropdown-2'><a class='dropbtn' href='bills.html'>Bills</a></li>");
                    out.println("</div>");
                out.println("</ul>");
            out.println("</li>");
        out.println("</ul>");
        
   out.println("<B><font align=\"center\" size=\"7\" color=\"#088A85\" face=\"Calibri\">ORDERS</FONT></B>");
   out.println("<form action=\"Delete\">");
        
    out.println( "<table id=\"data_table\" class=\"table\" align=\"center\" border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:800px;\">");
    out.println("<thead>");
    out.println("<tr class=\"tr\">");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">OrderID</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Product Name</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Quantity</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">UnitPrice</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">TotalPrice</span></th>");
    out.println("<th class=\"th\" scope=\"col\" bgcolor=\"#58ACFA\"><span style=\"font-size:20px;\">Delete</span></th>");
    out.println("</tr>");
    out.println("</thead>");
    out.println("<tbody>");
        out.println("<tr class=\"tr\">");
        for (int i=0; i<suma;i++){
			out.println("<td id=\"" + (i+1)+ "-5\" class=\"td\" bgcolor=\"#81BEF7\">"+ OrderID.get(i) +"</td>");
            out.println("<td id=\"" + (i+1)+ "-1\" class=\"td\" bgcolor=\"#81BEF7\">" + ProductName.get(i) + "</td>");
            out.println("<td id=\"" + (i+1)+ "-2\" class=\"td\" bgcolor=\"#81BEF7\">" + Quantity.get(i) + "</td>");
            out.println("<td id=\"" + (i+1)+ "-3\" class=\"td\" bgcolor=\"#81BEF7\">"+ UnitPrice.get(i) +"</td>");
            out.println("<td id=\"" + (i+1)+ "-4\" class=\"td\" bgcolor=\"#81BEF7\">"+ TotalPrice.get(i) +"</td>");
            out.println("<td class=\"td\" bgcolor=\"#81BEF7\"><input type=\"submit\" name=\"delete\" value=delete:" + OrderID.get(i) +"></td>");
            out.println("</tr>");
        }
  
    out.println("</tbody>");
    out.println("</table>");
   out.println("</form>");
    out.println("<BR><a href=\"Buscador.html\">Back to menu</a>");
    out.println("</center>");
    out.println("</BODY>");
   

    out.println("</HTML>");

    out.flush();
    out.close();
  }
            
         catch(Exception e) {
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
  
	}
  }
    