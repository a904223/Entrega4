import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowPreviousCampaigns extends HttpServlet{
    
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
    
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
        
        try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
        
        out = response.getWriter();
        
        String sql = "SELECT Name, StartDate, EndDate, Country, Method FROM Campaigns";
        String sql_count = "SELECT MAX(CampaignID) AS Count FROM Campaigns" ;
        
        try {
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement();
            
            ResultSet result = statement.executeQuery(sql);
            ResultSet result2 = statement2.executeQuery(sql_count);
            
            result2.next();
            int count=result2.getInt("Count");
            System.out.println(count);
            int columnas = 5;
            
            String[][] resString = new String[count][columnas];
			boolean[] bool = new boolean[count];
            int a = 0;
            
            while(result.next()) {
                
                resString[a][0] = result.getString("Name");
                resString[a][1] = result.getString("StartDate");
                resString[a][2] = result.getString("EndDate");
                resString[a][3] = result.getString("Method");
                resString[a][4] = result.getString("Country");
                a ++ ;
                
            }
            
            
        
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Show previous camapigns</title>");
            out.println("<meta charset=\"utf-8\"> <link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\"><link rel=\"StyleSheet\" type=\"text/css\" href=\"tabla.css\">");
            out.println("</head>");
            out.println("<body bgcolor=\"#FFFFFF\" text=\"#631818\">");
            out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">ACCOUNTING - Marketing</h1></div><ul class=\"navbar\"><li class=\"dropdown\"><a class=\"dropbtn\"><font face=\"Arial\">Menu</font></a><div class=\"dropdown-content\"><a class=\"active\" href=\"Pedido.html\">Orders</a><a href=\"customers.html\">Customers</a><a href=\"producttxt.html\">Products</a><a href=\"accounting.html\">Accounting</a><a href=\"bills.html\">Bills</a></div></li></ul><br /><br />");
            out.println("<p align=\"center\"><font size=\"6\"><b>Previous Campaigns</b></font></p>");

            out.println("<p align=\"center\">");
            out.println("<TABLE border=\"1\" class=\"tabla\" >");
            
            
            for (int i=0; i< (count+1); i++) {
                out.println("<TR>");
                for (int j=0; j< (columnas+1); j++) {
                    if (i==0) {
                        switch (j) {
                            
                            case 0: out.println ("<TH><h1>Campaign name</h1></TH>");
                                    break;
                            case 1: out.println ("<TH><h1>Start date</h1></TH>");
                                    break;
                            case 2: out.println ("<TH><h1>End date</h1></TH>");
                                    break;
                            case 3: out.println ("<TH><h1>Method</h1></TH>");
                                    break;
                            case 4: out.println ("<TH><h1>Country</h1></TH>");
                                    break;
                            case 5: out.println ("<TH><h1>Statistics</h1></TH>");
                                    break;
                        }
                    }
                    if (i!=0) { 
                        switch (j) {
                                    case 0: out.println ("<TD>" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 1: out.println ("<TD>" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 2:out.println ("<TD>" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 3: out.println ("<TD>" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 4: out.println ("<TD>" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 5: out.println ("<TD ><form method='GET' action='Statistics'><input type='hidden' name='number' value=''/><a href='Statistics' data-value='"+i+"'><img src='statistic.jpg' /></a></form></TD>");
                                            break;
                        }
                    }
                }
                out.println("</TR>");
            }
            out.println("</TABLE>  <p align=\"center\">");
            out.println("</body>");
            out.println("</html>");
            out.flush();
            out.close();
            
            //out.println(resString);
        } catch(SQLException e) {
            e.printStackTrace();
            System.out.println("Resulset: " + sql + " Exception: " + e);
        }
        out.close();

        // String strFilas = request.getParameter("employee");
        // int intFilas = Integer.parseInt(strFilas);
        // int intColumnas = 7 ;
        
        
        // String[] name=new String[intFilas] ;
        
        // for (int a=0; a<intFilas ; a++) {
            // name [a] = request.getParameter("name" + (a+1));
        // }
    }
}