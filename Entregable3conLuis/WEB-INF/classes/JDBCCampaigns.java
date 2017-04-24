import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;



// names from http://deron.meranda.us/data/census-dist-female-first.txt
@SuppressWarnings("serial")
public class JDBCCampaigns extends HttpServlet {
    Connection connection;
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            String url="jdbc:odbc:Database";
            connection=DriverManager.getConnection(url); 
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
	public void destroy () {
		super.destroy();
		System.out.println("Cerrando conexion...");
		try {
		  connection.close();
		} catch(SQLException ex){
		  System.out.println("No se pudo cerrar la conexion");
		  System.out.println(ex.getMessage());
		}
	}
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = req.getParameter("Name");
		String StartDate = req.getParameter("StartDate");
		System.out.println(StartDate);
        String EndDate = req.getParameter("EndDate");
		System.out.println(EndDate);
        String Method = req.getParameter("Method");
		String Country = req.getParameter("Country");
		Statement statement = null;

        try{
            statement = connection.createStatement();
			String sql = "INSERT INTO Campaigns (Name,StartDate,EndDate,Method,Country) VALUES (";
			sql +=  "'" + name + "'";
			sql +=  ",'" + StartDate + "'";
			sql +=  ",'" + EndDate + "'";
			sql +=  ",'" + Method + "'";
			sql +=  ",'" + Country + "');";
			System.out.println("Insert sql: " + sql);
            statement.executeUpdate(sql);
			tablilla(req,resp);
			return;
		
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Error en insertar un usuario nuevo: " + e);
			return;
        }finally {
			if (statement!=null){
				try {
					statement.close();
				}catch(SQLException e){
					System.out.println("Error al cerra el statement");
					System.out.println(e.getMessage());
					return;
				}
			}
		}
		
	}	
	public void tablilla(HttpServletRequest req, HttpServletResponse resp){
			resp.setContentType("text/html");
			PrintWriter out=null;
		
			try {
            out=resp.getWriter();
			} catch (IOException io) {
				System.out.println("Error opening PrintWriter");
			}
			
			String name = req.getParameter("Name");
			String StartDate = req.getParameter("StartDate");

			System.out.println(StartDate);
			String EndDate = req.getParameter("EndDate");
			System.out.println(EndDate);
			String Method = req.getParameter("Method");
			String Country = req.getParameter("Country");
		
			out.println("<html>");
			out.println("<head>");
			out.println("<link rel='StyleSheet' type='text/css' href='pattern.css'>");
			out.println("<script>");
			out.println("function Delete() {");
			  out.println("var xmlhttp =new XMLHttpRequest();");
			  out.println("xmlhttp.onreadystatechange=function() {");
			  out.println("if (xmlhttp.readyState==4 && xmlhttp.status==200) {");
				out.println("document.getElementById('id2').innerHTML=xmlhttp.responseText;");
			  out.println("}");
			out.println("}");
			out.println("xmlhttp.open('GET','Delete1',true);");
			out.println("xmlhttp.send();");
			out.println("}");
			out.println("</script>");
			out.println("<title>Campaigns</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<div class='header'>");
			out.println("<img align='left' src='Logo ERP Tecnun.png'>");
			out.println("<h1 align='center'>TECNUN ERP-ADVERTISING CAMPAIGNS</h1>");
			out.println("</div>");
			out.println("<ul class='navbar'>");
				out.println("<li class='dropdown'>");
					out.println("<a class='dropbtn'><font face='Arial'>Menu</font></a>");
					out.println("<div class='dropdown-content'>");
						out.println("<a class='active' href='Pedido.html'>Orders</a>");
						out.println("<a href='customers.html'>Customers</a>");
						out.println("<a href='producttxt.html'>Products</a>");
						out.println("<a href='accounting.html'>Accounting</a>");
						out.println("<a href='bills.html'>Bills</a>");
					out.println("</div>");
				out.println("</li>");
			out.println("</ul>");
			out.println("<BR>");
			out.println("<div id=id2 class='right'>");
			out.println("<H2> You have inserted the following campaign requirements </H2>");
			out.println("<table border=1>");
			out.println("<TR>");
			out.println("<TD>Campaign name</TD>");
			out.println("<TD>");
			out.println(name);
			
			out.println("</TD>");
			out.println("</TR>");
			out.println("<TR>");
			out.println("<TD>");
			out.println("Start Date");
			out.println("</TD>");
			out.println("<TD>");
			out.println(StartDate);
			out.println("</TD>");
			out.println("</TR>");
			out.println("<TR>");
			out.println("<TD>");
			out.println("End date");
			out.println("</TD>");
			out.println("<TD>");
			out.println(EndDate);
			out.println("</TD>");
			out.println("</TR>");
			out.println("<TR>");
			out.println("<TD>");
			out.println("Method");
			out.println("</TD>");
			out.println("<TD>");
			out.println(Method);
			out.println("</TD>");
			out.println("</TR>");
			out.println("<TR>");
			out.println("<TD>");
			out.println("Country");
			out.println("</TD>");
			out.println("<TD>");
			out.println(Country);
			out.println("</TD>");
			out.println("</TR>");
			
			out.println("</table>");
			out.println("<p></p>");
			out.println("<button class='button' onclick=\"Delete()\"> Delete </button>");
			out.println("</div>");
			out.println("</body>");
			out.println("</html>");

			out.flush();
			out.close();
        
	} 
}