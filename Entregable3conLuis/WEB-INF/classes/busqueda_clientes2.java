import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class busqueda_clientes2 extends HttpServlet {
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

   public void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
        
            
		try {
            
            String ValorStr = request.getParameter("valor");
            ValorStr = ValorStr.replaceAll("\\s","");
            System.out.println("ValorStr: " + ValorStr);
            
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			Statement statement3=connection.createStatement();
			
			String sql = "SELECT CIF, Telephone, Email FROM Suppliers WHERE SupplierName LIKE '" + ValorStr + "'";
            String sql2 = "SELECT ProductName FROM Products1 WHERE SupplierName LIKE '" + ValorStr + "'";
            String sql3 = "SELECT count(*) As count FROM Products1 WHERE SupplierName LIKE '" + ValorStr + "'";
			System.out.println("sql: " + sql);
            System.out.println("sql2: " + sql2);
			System.out.println("sql3: " + sql3);
            ResultSet result = statement.executeQuery(sql);
            ResultSet result2 = statement2.executeQuery(sql2);
			ResultSet result3 = statement3.executeQuery(sql3);
			result3.next();
			Integer count = result3.getInt("count");
			
			System.out.println("count: " + count); 
            result.next();
            String strCIF = result.getString("CIF");
            String strTelephone = result.getString("Telephone");
            String strEmail = result.getString("Email");
			out.println("<form action='save' name='save'>");
			out.println("<input type='hidden' name='count' id='count' value='" + count + "'></input>");
			out.println("<input type='hidden' name='counter' id='counter' value=\"0\"></input>");
			out.println("<div id='basic'>");
            out.println("<table cellspacing='1' cellpadding='1' border='1' align='center' width='80%';>");
                out.println("<tr><td>");
                out.println("<table cellspacing='1' cellpadding='1' border='0' align='center' width='80%' summary=\"\">");
                out.println("<tbody>");
                   out.println(" <tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Supplier Name </font></td>");
                        out.println("<td align='left'><input type='text' id='SupplierName' value=\"" + ValorStr + "\" name='SupplierName' size='30' /></td>");
                        out.println("<td><div id='description'></div></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> CIF </font></td>");
                        out.println("<td align='left'><input type='text' id='CIF' name='CIF' value=\"" + strCIF + "\" size='20' /></td>");

                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Email </font></td>");
                       out.println("<td align='left'><input type='text' id='Email' value=\"" + strEmail + "\" name='Email' size='20' /></td>");
                    out.println("</tr>");
                    out.println("<tr>");
                        out.println("<td align='center'><font size='4' color='#04B4AE' face='Calibri'> Telephone </font></td>");
                        out.println("<td align='left'><input type='text' id='Telephone' value=\"" + strTelephone + "\" name='Telephone' size='20' /></td>");                

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
				  
				   for (int i=0 ; i < count; i++) {
                    out.println("<tr>");
						out.println("<td align='left'><select  onchange='myFunction(" + i + ")' name='ProductName" + i +"' id='ProductName" + i +"'>");
                        out.println("<option selected='selected'  id='option' value='  - - '></option>");
						if (i==0 || i==2) {
							while (result2.next()) {
								 String strProductName = result2.getString("ProductName");
								 out.println("<option id='option' name='ProductName" + i + "' value='" + strProductName + "'> "+strProductName+"</option>");
							}
						}else {
								while (result2.previous()) {
								 String strProductName = result2.getString("ProductName");
								 out.println("<option id='option' name='ProductName" + i + "' value='" + strProductName + "'> "+strProductName+"</option>");
								}
							}
                        out.println("</select>");
                       
                        out.println("<td align='left'><input type='text' id='Quantity" + i + "' onkeyup='calculate()' name='Quantity" + i + "' size='20' /></td>");
						out.println("<td align='left'><input type='text' name='UnitPrice" + i + "'  id='UnitPrice" + i + "' size='20' /></td>");
						out.println("<td align='left'><input type='text' name='TotalPrice" + i + "'  id='Price" + i + "' size='20' /></td>");

                    out.println("</tr>");
				   }
                    out.println("<tr>");
                    out.println("<td align='center' colspan='2'><button TYPE='submit' value='Add'>ADD NEW ORDER </button></td>");
                    out.println("<td align='center' colspan='2'><button><a href='MostrarSupplier'> SHOW PREVIOUS ORDERS </button></td>");
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