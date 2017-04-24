import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class search extends HttpServlet {
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
    }

   public void doGet (HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
        
            
		try {
            
            String strOrderID = req.getParameter("OrderID");
            strOrderID = strOrderID.replaceAll("\\s","");
            System.out.println("strOrderID: " + strOrderID);
            
            Statement statement=connection.createStatement();
			Statement statement2=connection.createStatement();
			Statement statement3=connection.createStatement();
			
			String sql = "SELECT SupplierName, CIF, Telephone, Email, AccountingDate, DeliveryDate FROM SupplierOrders WHERE OrderID LIKE " + strOrderID + "";
            String sql2 = "SELECT count(*) As veces FROM OrderLines WHERE OrderID LIKE " + strOrderID + "";
            System.out.println("sql: " + sql);
            System.out.println("sql2: " + sql2);
			ResultSet result = statement.executeQuery(sql);
			
			result.next();
            String strCIF = result.getString("CIF");
			System.out.println("CIF :" + strCIF);
            String strTelephone = result.getString("Telephone");
			System.out.println("Telephone :" + strTelephone);
            String strEmail = result.getString("Email");
			System.out.println("Email :" + strEmail);
			String strSupplierName = result.getString("SupplierName");
			System.out.println("SupplierName :" + strSupplierName);
            String strAccountingDate = result.getString("AccountingDate");
			System.out.println("Accounting date :" + strAccountingDate);
            String strDeliveryDate = result.getString("DeliveryDate");
			System.out.println("DeliveryDate :" + strDeliveryDate);
			
            ResultSet result2 = statement2.executeQuery(sql2);
			result2.next();
			Integer veces = result2.getInt("veces");
			System.out.println("veces: " + veces); 
			
			
			String sql3 = "SELECT ProductName, Quantity, UnitPrice, TotalPrice FROM OrderLines WHERE OrderID LIKE " + strOrderID + "";
			System.out.println("sql3: " + sql3);
			ResultSet result3 = statement3.executeQuery(sql3);
			String[] ProductName = new String[veces];
			String[] Quantity = new String[veces];
			String[] UnitPrice = new String[veces];
			String[] TotalPrice = new String[veces];
			int i=0;
			while (result3.next()) {
				ProductName[i] = result3.getString("ProductName");
				System.out.println(ProductName[i]);
				Quantity[i] = result3.getString("Quantity");
				System.out.println(Quantity[i]);
				UnitPrice[i] = result3.getString("UnitPrice");
				System.out.println(UnitPrice[i]);
				TotalPrice[i] = result3.getString("TotalPrice");
				System.out.println(TotalPrice[i]);
				i++;
			}
			
			
            
			
			 out.println("<form action='save' name='save'>");
			out.println("<input type='hidden' name='count' id='count' value='" + veces + "'></input>");
			out.println("<input type='hidden' name='veces' id='veces' value=\"0\"></input>");
			out.println("<div id='basic'>");
            out.println("<table cellspacing='1' cellpadding='1' border='1' align='center' width='80%';>");
                out.println("<tr><td>");
                out.println("<table cellspacing='1' cellpadding='1' border='0' align='center' width='80%' summary=\"\">");
                out.println("<tbody>");
                   out.println(" <tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Supplier Name </font></td>");
                        out.println("<td align='left'><input type='text' id='SupplierName' value=\"" + strOrderID + "\" name='SupplierName' size='30' /></td>");
                        out.println("<td><div id='description'></div></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> CIF </font></td>");
                        out.println("<td align='left'><input type='text' id='CIF' name='CIF' value=\"" + strCIF + "\" size='20' /></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Email </font></td>");
                       out.println("<td align='left'><input type='text' id='Email' value=\"" + strEmail + "\" name='Email' size='20' /></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Telephone </font></td>");
                        out.println("<td align='left'><input type='text' id='Telephone' value=\"" + strSupplierName + "\" name='Telephone' size='20' /></td>");                

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> AccountingDate </font></td>");;
                        out.println("<td align='left'><input type='date' name='AccountingDate' value=\"" + strDeliveryDate + "\" id='AccountingDate' size='20' /></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> DeliveryDate </font></td>");
                        out.println("<td align='left'><input type='date' name='DeliveryDate' value=\"" + strAccountingDate + "\" id='DeliveryDate' size='20' /></td>");
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
				  
				   for (int u=0 ; u < veces; u++) {
                    out.println("<tr>");
						
						out.println("<td align='left'><input type='text' id='ProductName" + u + "'  value=\"" + ProductName[u] + "\"name='ProductName" + u + "' size='20' /></td>");
                        out.println("<td align='left'><input type='text' id='Quantity" + u + "' value=\"" + Quantity[u] + "\" onkeyup='calculate()' name='Quantity" + u + "' size='20' /></td>");
						out.println("<td align='left'><input type='text' name='UnitPrice" + u + "' value=\"" + UnitPrice[u] + "\" id='UnitPrice" + u + "' size='20' /></td>");
						out.println("<td align='left'><input type='text' name='TotalPrice" + u + "' value=\"" + TotalPrice[u] + "\" id='TotalPrice" + u + "' size='20' /></td>");

                    out.println("</tr>");
				   }
                    out.println("<tr>");
                    out.println("<td align='center' colspan='2'><button TYPE='submit' value='Add'>ADD NEW ORDER </button></td>");
                    out.println("</tr>");
                out.println("</table>");
                out.println("</td></tr>");
            out.println("</table>");
			out.println("<table>");
				out.println("<tr><td align='center'><font size='4' color='#04B4AE' face='Calibri'> search the order of the following OrderId </font></td>");
                out.println("<td align='left'><input type='text' name='search' id='search' size='20' /></td></tr>");
				out.println("<input type='button' id='btnsearch' value='Search' onclick='doSomething()' />");
			out.println("</table>");
			out.println("</div>");
			out.println("</form>"); 
            
			out.flush();
			out.close();
		
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset:  Exception: " + e);
		}  
			out.close();
	}
}