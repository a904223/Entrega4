import java.sql.*;
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Save_InfoEmployees extends HttpServlet {
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

   public void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out=null;
		
		try {
            out=response.getWriter();
        } catch (IOException io) {
            System.out.println("Error opening PrintWriter");
        }
		
        
        
            
		try {
            
            String strcont = request.getParameter("filas");

            int cont = Integer.parseInt(strcont);
            
            
            String[] sql = new String[cont];
            
            String name = request.getParameter("name");
            
            String[] name2 = name.split(",");
            
            
            String surname = request.getParameter("surname");
            String[] surname2 = surname.split(",");
            
            
            String email = request.getParameter("email");
            String[] email2 = email.split(",");
            
            
            String mobile_phone = request.getParameter("mobile_phone");
            String[] mobile_phone2 = mobile_phone.split(",");
            
            
            String date_of_birth = request.getParameter("date_of_birth");
            String[] date_of_birth2 = date_of_birth.split(",");
            
            
            String[][] date_of_birth3 = new String[cont][] ;
            
            int[][] array2d = new int[3][];
            
            int lim = (date_of_birth2.length-1) ;
            
            for (int a = 0; a < lim; ++a) {
                
                date_of_birth3[a] = date_of_birth2[a+1].split("-");
                date_of_birth3[a][0] = date_of_birth3[a][0].replace("\"","");
                date_of_birth3[a][2] = date_of_birth3[a][2].replace("\"","");
                
            }
            
            
            String job_title = request.getParameter("job_title");
            String[] job_title2 = job_title.split(",");
            
            
            String department = request.getParameter("department");
            String[] department2 = department.split(",");
            
            
            String salary = request.getParameter("salary");
            String[] salary2 = salary.split(",");
            
            
            String works_with_commision = request.getParameter("works_with_commision");
            String[] works_with_commision2 = works_with_commision.split(",");
            
            Integer[] works_with_commision3 = new Integer[cont];
            
            
            String commision = request.getParameter("commision");
            String[] commision2 = commision.split(",");
            Integer[] commision3 = new Integer[cont];
            
            
            Statement statement=connection.createStatement();
            
            for (int i=1; i<cont; i++){
                
                name2[i] = name2[i].replace("\"", "");
                name2[i] = name2[i].replace("]", "");
                
                surname2[i] = surname2[i].replace("\"", "");
                surname2[i] = surname2[i].replace("]", "");
                
                email2[i] = email2[i].replace("\"", "");
                email2[i] = email2[i].replace("]", "");
                
                mobile_phone2[i] = mobile_phone2[i].replace("\"", "");
                mobile_phone2[i] = mobile_phone2[i].replace("]", "");
                
                
                date_of_birth2[i-1] = date_of_birth3[i-1][2] + "/" + date_of_birth3[i-1][1] + "/" + date_of_birth3[i-1][0];
                
                date_of_birth2[i-1] = date_of_birth2[i-1].replace("\"", "");
                date_of_birth2[i-1] = date_of_birth2[i-1].replace("]", "");
                
                String Day = date_of_birth3[i-1][2];

                String Month = date_of_birth3[i-1][1];

                String Year = date_of_birth3[i-1][0];

                
                
                job_title2[i] = job_title2[i].replace("\"", "");
                job_title2[i] = job_title2[i].replace("]", "");
                
                department2[i] = department2[i].replace("\"", "");
                department2[i] = department2[i].replace("]", "");
                
                salary2[i] = salary2[i].replace("\"", "");
                salary2[i] = salary2[i].replace("]", "");
                
                works_with_commision2[i] = works_with_commision2[i].replace("\"", "");
                works_with_commision2[i] = works_with_commision2[i].replace("]", "");
                
                if (works_with_commision2[i].equals("Yes")) {
                    works_with_commision3[i] = 1;
                } else if (works_with_commision2[i]=="No"){
                    works_with_commision3[i] = 0;
                }
                
                commision2[i] = commision2[i].replace("\"", "");
                commision2[i] = commision2[i].replace("]", "");
                commision3[i] = Integer.parseInt(commision2[i]);
                
                if (commision3[i]==null) {
                    commision3[i]=0;
                }
                
                sql[i]="UPDATE Employees SET Nombre = '" + name2[i] + "', Apellidos = '" + surname2[i] + "', Email = '" + email2[i] + "', [Mobile Phone] = '" + mobile_phone2[i] + "', [Date of Birth] = '" + date_of_birth2[i-1] + "', [Job Title] = '" + job_title2[i] + "', Department = '" + department2[i] + "', Salary = " + salary2[i] + " , [Works with Commision] =" + works_with_commision3[i] + ", [Commision(% on sales)] = " + commision2[i] + " WHERE IDEmployee = " + i + "";
                
                statement.executeUpdate(sql[i]);
            }
            
			out.flush();
			out.close();
			
			
		}catch(SQLException e){
			e.printStackTrace ();
			System.out.println("Resulset:  Exception: " + e);
		}  
			out.close();
		}
}