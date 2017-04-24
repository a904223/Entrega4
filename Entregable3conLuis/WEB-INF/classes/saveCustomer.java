import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;



// names from http://deron.meranda.us/data/census-dist-female-first.txt
@SuppressWarnings("serial")
public class saveCustomer extends HttpServlet {
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
		
			String strCount = req.getParameter("counter");
			int counter=Integer.parseInt(strCount);
			System.out.println(counter);
			String CustomerName = req.getParameter("CustomerName");
			System.out.println(CustomerName);
			String CIF = req.getParameter("CIF");
			System.out.println(CIF);
			String Telephone = req.getParameter("Telephone");
			System.out.println(Telephone);
			String Email = req.getParameter("Email");
			String AccountingDate = req.getParameter("AccountingDate");
			System.out.println(AccountingDate);
			String DeliveryDate = req.getParameter("DeliveryDate");
			System.out.println(DeliveryDate);
			
			String[] ProductName = new String[counter];
			String[] Quantity = new String[counter];
			String[] UnitPrice = new String[counter];
			String[] TotalPrice = new String[counter];
			
			for (int i=0;i<counter;i++) {
				ProductName[i] = req.getParameter("ProductName"+ i);
				System.out.println(ProductName[i]);
				Quantity[i] = req.getParameter("Quantity"+ i);
				System.out.println(Quantity[i]);
				UnitPrice[i] = req.getParameter("UnitPrice"+ i);
				System.out.println(UnitPrice[i]);
				TotalPrice[i] = req.getParameter("TotalPrice"+ i);
				System.out.println(TotalPrice[i]);
			}
			Statement statement = null;
			Statement statement2 = null;
			Statement statement3 = null;

          try{
             statement = connection.createStatement();
			 statement2 = connection.createStatement();
			 statement3 = connection.createStatement();
			String sql = "INSERT INTO CustomerOrders (CustomerName, CIF, Telephone, AccountingDate, DeliveryDate) VALUES (";
			sql +=  "'" + CustomerName + "'";
			sql +=  ",'" + CIF + "'";
			sql +=  ",'" + Telephone + "'";
			sql +=  ",'" + AccountingDate + "'";
			sql +=  ",'" + DeliveryDate + "');";
		
			System.out.println("Insert sql: " + sql);
			statement.executeUpdate(sql);
			
			String sql3= "SELECT MAX(OrderID) As maximo FROM CustomerOrders";
			ResultSet result =statement3.executeQuery(sql3);
			result.next();
			Integer OrderID=result.getInt("maximo");
			System.out.println("OrderID: " + OrderID);
			
			
			for (int j=0;j<counter;j++) {
				String sql2 = "INSERT INTO OrderLines2 (OrderID, ProductName, Quantity, UnitPrice, TotalPrice) VALUES (";
				sql2 +=  "'" + OrderID + "'";
				sql2 +=  ",'" + ProductName[j] + "'";
				sql2 +=  ",'" + Quantity[j] + "'";
				sql2 +=  ",'" + UnitPrice[j] + "'";
				sql2 +=  ",'" + TotalPrice[j] + "');";
				System.out.println("Insert sql: " + sql2);
				statement2.executeUpdate(sql2);
			}
            
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
			resp.sendRedirect("BuscadorCustomer.html");	
            out=resp.getWriter();
			} catch (IOException io) {
				System.out.println("Error opening PrintWriter");
			}
			
/* 		out.println("<html>");
			out.println("<body>");
				out.println("<script>");
					out.println("<window.location='Buscador.html';>");
				out.println("</script>");
			out.println("</body>");
		out.println("</html>"); */
		
			/* out.println("<div id='basic'>");
            out.println("<table cellspacing='1' cellpadding='1' border='1' align='center' width='80%';>");
                out.println("<tr><td>");
                out.println("<table cellspacing='1' cellpadding='1' border='0' align='center' width='80%' summary=\"\">");
                out.println("<tbody>");
                   out.println(" <tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Supplier Name </font></td>");
                        out.println("<td align='left'><input type='text' id='SupplierName'  name='SupplierName' size='30' /></td>");
                        out.println("<td><div id='description'></div></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> CIF </font></td>");
                        out.println("<td align='left'><input type='text' id='CIF' name='CIF'  size='20' /></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Email </font></td>");
                       out.println("<td align='left'><input type='text' id='Email'  name='Email' size='20' /></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Telephone </font></td>");
                        out.println("<td align='left'><input type='text' id='Telephone'  name='Telephone' size='20' /></td>");                

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> AccountingDate </font></td>");;
                        out.println("<td align='left'><input type='date' name='AccountingDate' id='AccountingDate' size='20' /></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> DeliveryDate </font></td>");
                        out.println("<td align='left'><input type='date' name='DeliveryDate' id='DeliveryDate' size='20' /></td>");
                    out.println("</tr>");

                out.println("</tbody>");  
                out.println("</table>");
                out.println("</td></tr>");
                out.println("<tr><td>");
                out.println("<table cellspacing='1' cellpadding='1' border='0' align='center' width='80%' summary=\"\">");
                    out.println("<tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> ProductName </font></td>");;
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Quantity </font></td>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'>Unit Price </font></td>");
                       out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Total Price </font></td>");
					   out.println(" <p><div id='description'></div></p>");
                   out.println(" </tr>");
				  
				   
                    out.println("<tr>");

						out.println("<td align='left'<input type='text' id='ProductName' name='ProductName' size='30' /></td>");
                        out.println("<td align='left'><input type='text' id='Quantity' onkeyup='calculate()' name='Quantity' size='20' /></td>");
						out.println("<td align='left'><input type='text' name='UnitPrice'  id='UnitPrice' size='20' /></td>");
						out.println("<td align='left'><input type='text' name='Price'  id='Price' size='20' /></td>");

                    out.println("</tr>");
                    out.println("<tr>");
                    out.println("<td align='center' colspan='2'><button TYPE='submit' onclick='save()' value='Add'>ADD NEW ORDER </button></td>");
                    out.println("</tr>");
                out.println("</table>");
                out.println("</td></tr>");
            out.println("</table>");
			out.println("</div>"); */

			out.flush();
			out.close();
        
	} 
}