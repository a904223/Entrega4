import java.sql.*;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Delete extends HttpServlet {
    Connection connection;
     
  public void init(ServletConfig config) throws ServletException {
    super.init(config);
    System.out.println("Iniciando Eliminacion...");
  }

  public void destroy() {
    System.out.println("There is nothing to do...");
  }

  public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  
    HttpSession session = req.getSession(true);
    //String sesion = (String)session.getAttribute("sesion");
    String deleteCustomer = req.getParameter("delete");
    
    
    System.out.println(deleteCustomer);
    deleteCustomer=deleteCustomer.replace("delete:","");
    System.out.println(deleteCustomer);
    
    
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
            //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
            //PrintWriter toFile = new PrintWriter(fileWriter);
            //toFile.println("ok");
            Statement statement = connection.createStatement();
            statement.executeQuery("delete FROM  Customer WHERE Customer= '" + deleteCustomer+ "'");
            
                connection.close(); 
            //fileWriter.close();
        } catch(Exception e) {
            System.out.println("Problem creating connection");
            //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
            //PrintWriter toFile = new PrintWriter(fileWriter);
            //toFile.println("no ok:");
            //fileWriter.close();
            e.printStackTrace();
        }
                 
    //FileWriter fileWriter = new FileWriter("E:\\apache-tomcat-5.5.12\\webapps\\ErpTecnun\\MisPedidos.txt", true);
    //PrintWriter toFile = new PrintWriter(fileWriter);
    
    //toFile.println("> " + strCustomer + "\t" + strProduct + "\t" + strPrice + "\t" + strDate + "\t" + sesion);
    //fileWriter.close();
    devolverPaginaHTML(resp);
  }
    
  public void devolverPaginaHTML(HttpServletResponse resp) {

    resp.setContentType("text/html");

    PrintWriter out = null;
    try {
		resp.sendRedirect("Buscador.html");
      out=resp.getWriter();
    } catch (IOException io) {
      System.out.println("There has been an exception");    
    }
 
    
   

    out.println("</HTML>");

    out.flush();
    out.close();
  }
     
  public String getServletInfo() {
    return "Este servlet lee los datos de un formulario y los muestra en pantalla";
  }
}