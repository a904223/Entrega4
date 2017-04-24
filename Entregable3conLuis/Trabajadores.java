import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class Trabajadores extends HttpServlet{
    
    Connection connection;
    private static int h = 0;
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
        
        String strDate = request.getParameter("date");
        System.out.println(strDate);
        String[] strDate2=strDate.split("-");
        String strYear=strDate2[0];
        int Year=Integer.parseInt(strYear);
        System.out.println("Year introduced: " +Year);
        String strMonth=strDate2[1];
        int Month=Integer.parseInt(strMonth);
        System.out.println("Month introduced: " + Month);
        String strDay=strDate2[2];
        int Day=Integer.parseInt(strDay);
        
        System.out.println(Year + "," + Month + "," + Day);
        
        //String strYear = request.getParameter("year");
        //int Year=Integer.parseInt(strYear);
        
        if(Month == 1 || Month == 2) {
            System.out.println("Ha entrado month 1 o 2");
            int Month2 = Month+12;
            int Year2 = Year-1;
        
        
            h = (1 + (int)(((Month2 + 1) * 26) / 10.0) + Year2 + (int)(Year2 / 4.0) + 6 * (int)(Year2 / 100.0) + (int)(Year2 / 400.0)) % 7;
            String dayName = "";
            if (h>1) {h=h-1;}
            else if (h<=1) {h=h+6;}
            
            switch(h)
            {
                case 1: dayName = "Monday"; break;
                case 2: dayName = "Tuesday"; break;
                case 3: dayName = "Wednesday"; break;
                case 4: dayName = "Thursday"; break;
                case 5: dayName = "Friday"; break;
                case 6: dayName = "Saturday"; break;
                case 7: dayName = "Sunday"; break;
            }
            System.out.println("The first day of the month is " + dayName);
            System.out.println("H " + h);
        } else {
            h = (1 + (int)(((Month + 1) * 26) / 10.0) + Year + (int)(Year / 4.0) + 6 * (int)(Year / 100.0) + (int)(Year / 400.0)) % 7;
            String dayName = "";
            if (h>1) {h=h-1;}
            else if (h<=1) {h=h+6;}
            
            switch(h)
            {
                case 1: dayName = "Monday"; break;
                case 2: dayName = "Tuesday"; break;
                case 3: dayName = "Wednesday"; break;
                case 4: dayName = "Thursday"; break;
                case 5: dayName = "Friday"; break;
                case 6: dayName = "Saturday"; break;
                case 7: dayName = "Sunday"; break;
            }
            System.out.println("The first day of the month is " + dayName);
            System.out.println("H " + h);
        }
        
        int l=0;
        if (h<2){
            l=2-h;
        }else if (h>1) { l=9-h; }
        
        System.out.println("El valor de l es: " + l);
        
        int semana=0;
        int dif=Day-l ;
        int negativo=1;
        
        if (l==1){
            semana=1;
            negativo=0;
        }
        else if (l>1) {
            semana=1;
            if(dif<0) {
                semana=1;
                negativo=0;
            } else if ((0<=dif) && (dif<7)) {
                semana=2;
            } else if ((7<=dif) && (dif<14)) {
                semana=3;
            } else if ((14<=dif) && (dif<22)) {
                semana=4;
            }else if (22<=dif) {
                semana=5;
            }
        }
        
        // for (int a=1; a<Day; a+=7) {
            // semana =(semana+1) ;
        // }
        
        
        System.out.println("Semana: " + semana);
        
        try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
        
        out = response.getWriter();
        
        String sql = "SELECT Nombre, [Day Off] FROM [Day Off]";
        String sql2 = "SELECT count(*) AS Total FROM [Day Off]"; 
        String sql3 = "SELECT DATEPART('yyyy', Fecha) AS yr, DATEPART('m',Fecha) AS mnth, DATEPART('d',Fecha) AS dy FROM [Day Off]"; 
        System.out.println(sql);
        //  
        try {
            Statement statement=connection.createStatement();
            Statement statement2=connection.createStatement();
            Statement statement3=connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            ResultSet result2 = statement2.executeQuery(sql2);
            ResultSet result3 = statement3.executeQuery(sql3);
            result2.next();
            int count=result2.getInt("Total");
            //System.out.println(count);
            
            String resString = "";
            String resString2 = "";
            String resString3Year="";
            String resString3Month = "";
            String resString3Day = "";
            boolean first = true;
            while(result.next() && result3.next()) {
                if (!first) {
                    resString += "\t";
                    resString2 += "\t";
                    resString3Year += "/";
                    resString3Month += "/";
                    resString3Day += "/";
                } else {
                    first = false;
                }
                resString += result.getString("Nombre");
                resString2 += result.getString("Day Off");
                //resString += " " + result.getString("surname") + "\"";
                resString3Year += result3.getString("yr");
                resString3Month += result3.getString("mnth");
                resString3Day += result3.getString("dy");
            }
            
            String[] palabrasSeparadas = resString.split("\t");
            String[] DayOff = resString2.split("\t");
            //String[] SqlDate = resString3.split("/");
            String[] SqlYear = resString3Year.split("/");
            String[] SqlMonth = resString3Month.split("/");
            String[] SqlDay = resString3Day.split("/");
            
            int columnas=7;
            
            System.out.println("SqlDay[0]: " + SqlDay[0]);
            System.out.println("SqlMonth[0]: " + SqlMonth[0]);
            System.out.println("SqlYear[0]: " + SqlYear[0]);
            System.out.println("Day Off[0]: " + DayOff[0]);
                
            System.out.println("Long year: " + SqlYear.length);
            System.out.println("Long month: " + SqlMonth.length);
            System.out.println("Long day: " + SqlDay.length);
        
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Working timetable</title>");
            out.println("<meta charset=\"utf-8\"> <link rel=\"StyleSheet\" type=\"text/css\" href=\"pattern.css\"><link rel=\"StyleSheet\" type=\"text/css\" href=\"tabla.css\"><link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.11.1/themes/smoothness/jquery-ui.css\"> <script src=\"//code.jquery.com/jquery-1.10.2.js\"></script> <script src=\"//code.jquery.com/ui/1.11.1/jquery-ui.js\"></script> <style> #draggable { width: 100px; background-color: rgba(173, 255, 47, 0.2); border-radius: 26px; border-width: 5px; height: 100px; padding: 0.5em; } #droppable td{ width: 100px; height: 100px; padding: 0.5em; } </style> <script src=\"https://code.jquery.com/jquery-1.12.4.js\"></script> <script src=\"https://code.jquery.com/ui/1.12.1/jquery-ui.js\"></script> <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js\"></script> <script src=\"http://ajax.googleapis.com/ajax/libs/jqueryui/1.11.1/jquery-ui.js\"></script> <link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">");
            out.println("<script> $( function() { $( \"#draggable\" ).draggable({ revert: \"valid\" }); $( \'#droppable td\' ).droppable({ drop: function ( event, ui ) { var parenttd = $(this).attr(\'id\'); var td = []; td = parenttd.split('.'); var td1 = td[1]; var dias = $(\"#dias\"+td1).val(); var mes = $(\"#month\").val(); var anyo = $(\"#year\").val(); alert(\"dias: \" + dias + \", month: \" + mes + \", anyo: \" + anyo); alert(\"parenttd=\" + parenttd); $( this ) .find( \"p\" ) .html( \"Day Off!\" ); $.ajax({type: \"post\", url: \"Save_Shifts\", data: {id: parenttd, dias: dias, mes: mes, anyo: anyo}, success: function(){ alert(\"Success\");}});} }) }); </script>");
            out.println("</head>");
            out.println("<body bgcolor=\"#FFFFFF\" text=\"#631818\">");
            out.println("<div class=\"header\"><img align=\"left\" src=\"Logo ERP Tecnun.png\"><h1 align=\"center\">ACCOUNTING - Staff</h1><p align=\"right\"><a href=\"Login.html\"><img alt=\"rftger\" src=\"https://image.freepik.com/iconos-gratis/desconectarte-opcion_318-41892.jpg\" style=\"width: 38px; height: 38px;\" /></a><a href=\"Login.html\"><FONT size=8 color=\"black\" >Log out</FONT></a></p></div><ul class=\"navbar\"> <li class=\"dropdown-1\"><a class=\"menu\" class=\"dropbtn\"><font face=\"Arial\">Menu</font></a> <ul> <div class=\"dropdown-content-1\"> <li class=\"dropdown-2\"><a class=\"dropbtn\" class=\"active\">Purchases</a> <ul> <div class=\"dropdown-content-2\"> <a href=\"suppliers\">Suppliers</a> <a href=\"Pedido.html\">New Order</a> <a href=\"MostrarCustomer\">Orders</a> </div> </ul> </li> <li class=\"dropdown-2\"><a class=\"dropbtn\">Sales</a> <ul> <div class=\"dropdown-content-2\"> <a href=\"customers\">Customers</a> <a href=\"Buscador.html\">New Order</a> <a href=\"#\">Orders</a> </div> </ul> </li> <li class=\"dropdown-2\"><a class=\"dropbtn\">Inventory</a> <ul> <div class=\"dropdown-content-2\"> <a href=\"products\">Products</a> <a href=\"warehouses\">Warehouse</a> </div> </ul> </li> <li class=\"dropdown-2\"><a class=\"dropbtn\" href=\"accounting.html\">Accounting</a></li> <li class=\"dropdown-2\"><a class=\"dropbtn\" href=\"bills.html\">Bills</a></li> </div> </ul> </li> </ul><div id=\"draggable\" class=\"ui-widget-content\"> <p>Day Off</p> </div> <br /><br />");
            out.println("<p align=\"center\"><font size=\"6\"><b>WORKING TIMETABLE</b></font></p>");
            out.println("<input type=\"hidden\" name=\"day\" id=\"day\" value=\"" + Day + "\">");
            out.println("<input type=\"hidden\" name=\"month\" id=\"month\" value=\"" + Month + "\">");
            out.println("<input type=\"hidden\" name=\"year\" id=\"year\" value=\"" + Year + "\">");
            out.println("<input type=\"hidden\" name=\"date\" id=\"date\" value=\"" + Day + "/" + Month + "/" + Year + "\">");
            out.println("<p align=\"center\">");
            out.println("<TABLE id=\"droppable\" border=\"1\" class=\"tabla\" cellspacing=\"0\">");
            
            int[] dias=new int[8];
            for (int i=0; i< (count+1); i++) {
                out.println("<TR>");
                for (int j=0; j< (columnas+1); j++) {
                    if (i==0) {
                        switch (j) {

                            case 0: out.println ("<TD>&nbsp</TD>");
                                    break;
                            case 1: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Monday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias1\" name=\"dias1\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;
                            case 2: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Tuesday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias2\" name=\"dias2\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;
                            case 3: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Wednesday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias3\" name=\"dias3\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;
                            case 4: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Thursday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias4\" name=\"dias4\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;
                            case 5: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Friday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias5\" name=\"dias5\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;
                            case 6: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Saturday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias6\" name=\"dias6\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;
                            case 7: dias[j]=negativo*((l+(j-1))+7*(semana-2));
                                    out.println ("<TH><h1>Sunday, " + negativo*((l+(j-1))+7*(semana-2)) + "</h1></TH><input id=\"dias7\" name=\"dias7\" value=\"" + dias[j] + "\" type=\"hidden\"></input>");
                                    if (j>=(h-2)) {negativo=1;}
                                    System.out.println("I: " + i + " j: " + j + " dias: " + dias[j] );
                                    break;

                        }
                    }
                    if (i!=0) { 
                        switch (j) {
                                    case 0: out.println ("<TH>" + palabrasSeparadas[i-1] + "</TH>");
                                            break;
                                    case 1: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".1\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".1\"><p>&nbsp</p></TD>");}
                                            break;
                                    case 2: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".2\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".2\"><p>&nbsp</p></TD>");}
                                            break;
                                    case 3: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".3\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".3\"><p>&nbsp</p></TD>");}
                                            break;
                                    case 4: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".4\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".4\"><p>&nbsp</p></TD>");}
                                            break;
                                    case 5: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".5\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".5\"><p>&nbsp</p></TD>");}
                                            break;
                                    case 6: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".6\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".6\"><p>&nbsp</p></TD>");}
                                            break;
                                    case 7: if (dias[j]==Integer.parseInt(SqlDay[i-1]) && Month==Integer.parseInt(SqlMonth[i-1]) && Year==Integer.parseInt(SqlYear[i-1]) && Integer.parseInt(DayOff[i-1])==1) {
                                                out.println ("<TD id=\"" + i + ".7\"><p>Day Off</p></TD>");
                                            }else { out.println ("<TD id=\"" + i + ".7\"><p>&nbsp</p></TD>");}
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