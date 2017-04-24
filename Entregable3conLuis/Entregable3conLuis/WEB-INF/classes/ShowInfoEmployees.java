import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ShowInfoEmployees extends HttpServlet{
    
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
        
        String sql = "SELECT Nombre, Apellidos, Email, [Mobile Phone], [Date of Birth], [Job Title], Department, Salary, [Works with Commision], [Commision(% on sales)] FROM Employees";
        String sql_count = "SELECT MAX(IDEmployee) AS Count FROM Employees" ;
        
        try {
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement();
            
            ResultSet result = statement.executeQuery(sql);
            ResultSet result2 = statement2.executeQuery(sql_count);
            
            result2.next();
            int count=result2.getInt("Count");
            System.out.println(count);
            int columnas = 10;
            
            String[][] resString = new String[count][columnas];
            int a = 0;
            
            while(result.next()) {
                
                resString[a][0] = result.getString("Nombre");
                resString[a][1] = result.getString("Apellidos");
                resString[a][2] = result.getString("Email");
                resString[a][3] = result.getString("Mobile Phone");
                resString[a][4] = result.getString("Date of Birth");
                System.out.println("Como llega Date of Birth: " + resString[a][4]);
                resString[a][5] = result.getString("Job Title");
                resString[a][6] = result.getString("Department");
                resString[a][7] = result.getString("Salary");
                resString[a][8] = result.getString("Works with Commision");
                resString[a][9] = result.getString("Commision(% on sales)");
                a ++ ;
                
            }
            
            
            // System.out.println("Long year: " + SqlYear.length);
            // System.out.println("Long month: " + SqlMonth.length);
            // System.out.println("Long day: " + SqlDay.length);
            
        
            out.println("<html>");
            out.println("<head>");
            out.println("<title>ShowInfoEmployees timetable</title>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js\"></script>");
            out.println("<script type=\"text/javascript\" src=\"ShowInfoEmployees.js\"></script>");
            out.println("<meta charset=\"utf-8\"> <link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\"><link rel=\"StyleSheet\" type=\"text/css\" href=\"tabla.css\">");
            out.println("</head>");
            out.println("<body bgcolor=\"#FFFFFF\" text=\"#631818\">");
            out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">ACCOUNTING - Staff</h1></div><ul class=\"navbar\"> <li class=\"dropdown-1\"><a class=\"menu\" class=\"dropbtn\"><font face=\"Arial\">Menu</font></a> <ul> <div class=\"dropdown-content-1\"> <li class=\"dropdown-2\"><a class=\"dropbtn\" class=\"active\">Purchases</a> <ul> <div class=\"dropdown-content-2\"> <a href=\"suppliers\">Suppliers</a> <a href=\"#\">New Order</a> <a href=\"#\">Orders</a> </div> </ul> </li> <li class=\"dropdown-2\"><a class=\"dropbtn\">Sales</a> <ul> <div class=\"dropdown-content-2\"> <a href=\"customers\">Customers</a> <a href=\"#\">New Order</a> <a href=\"#\">Orders</a> </div> </ul> </li> <li class=\"dropdown-2\"><a class=\"dropbtn\">Inventory</a> <ul> <div class=\"dropdown-content-2\"> <a href=\"products\">Products</a> <a href=\"warehouses\">Warehouse</a> </div> </ul> </li> <li class=\"dropdown-2\"><a class=\"dropbtn\" href=\"accounting.html\">Accounting</a></li> <li class=\"dropdown-2\"><a class=\"dropbtn\" href=\"bills.html\">Bills</a></li> </div> </ul> </li> </ul>");
            out.println("<br>");
            out.println("<br>");
            out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
			out.println("<br>");
            out.println("<button id=\"modificar\" onclick=\"edit_row()\" class=\"boton grisn\">Modify</button><button id=\"guardar\" onclick=\"save_row()\" style=\"display: none;\" class=\"boton grisn\">Save</button><div id=\"mensaje\"></div><div id=\"contador\"></div><p align=\"center\"><font size=\"6\"><b>Employees </b></font></p>");
            // out.println("<input type=\"hidden\" name=\"day\" value=\"" + Day + "\">");
            // out.println("<input type=\"hidden\" name=\"month\" value=\"" + Month + "\">");
            // out.println("<input type=\"hidden\" name=\"year\" value=\"" + Year + "\">");
            out.println("<p align=\"center\">");
            out.println("<TABLE id=\"data_table\" border=\"1\" class=\"tabla\" >");
            
            
            for (int i=0; i< (count+1); i++) {
                out.println("<TR>");
                for (int j=0; j< (columnas+1); j++) {
                    if (i==0) {
                        switch (j) {
                            
                            case 0: out.println ("<TH><h1>Nombre</h1></TH>");
                                    break;
                            case 1: out.println ("<TH><h1>Apellido</h1></TH>");
                                    break;
                            case 2: out.println ("<TH><h1>Email</h1></TH>");
                                    break;
                            case 3: out.println ("<TH><h1>Mobile Phone</h1></TH>");
                                    break;
                            case 4: out.println ("<TH><h1>Date of Birth</h1></TH>");
                                    break;
                            case 5: out.println ("<TH><h1>Job Title</h1></TH>");
                                    break;
                            case 6: out.println ("<TH><h1>Department</h1></TH>");
                                    break;
                            case 7: out.println ("<TH><h1>Salary</h1></TH>");
                                    break;
                            case 8: out.println ("<TH><h1>Works with Commision</h1></TH>");
                                    break;
                            case 9: out.println ("<TH><h1>Commision( % on sales )</h1></TH>");
                                    break;
                        }
                    }
                    if (i!=0) { 
                        switch (j) {
                                    case 0: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 1: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 2: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 3: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 4: String[] fecha = (resString[i-1][j]).split(" ");
                                            out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + fecha[0] + "</TD>");
                                            break;
                                    case 5: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 6: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 7: out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            break;
                                    case 8: if (Integer.parseInt(resString[i-1][j]) == 1) {
                                                out.println ("<TD id=\"" + i + "_" + (j+1) + "\">Yes</TD>");
                                            } else {
                                                out.println ("<TD id=\"" + i + "_" + (j+1) + "\">No</TD>");
                                            }
                                            break;
                                    case 9: if (resString[i-1][j] == null) {
                                                out.println ("<TD id=\"" + i + "_" + (j+1) + "\">&nbsp</TD>");
                                            } else {
                                                out.println ("<TD id=\"" + i + "_" + (j+1) + "\">" + resString[i-1][j] + "</TD>");
                                            }
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