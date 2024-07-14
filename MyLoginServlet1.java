import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class MyLoginServlet1 extends HttpServlet
{   private Connection conn;
    private PreparedStatement ps;
    public void init()throws ServletException
    {
        try{
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","advjavabatch","mystudents");
            System.out.println("connection opended successfully!");
            ps=conn.prepareStatement("Select username from users where userid=? and password=?");
            System.out.println("query set successfully!");
        }catch(SQLException ex){
           ServletException  se=new ServletException(ex.getMessage());
           throw se;
        }
    }
        protected  void doPost(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
        {
                String userid=req.getParameter("userid");
                String userpwd=req.getParameter("userpwd");
                PrintWriter pw=resp.getWriter();
                resp.setContentType("text/html");
                pw.println("<html><head><title>Login Response</title></head>");
                pw.println("<body>");

                try{
                    ps.setString(1,userid);
                    ps.setString(2,userpwd);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next())
                    {
                        String username=rs.getString(1);
                        pw.println("<p>Hello <strong>"+username+"</strong><br>Welcome to our site!</p>");
                    }
                    else{
                        pw.println("<p>Sorry! You cannot login<br>Invalid credential<br>");
                        pw.println("<a href='signin.html'>Try again</a><br></p>");

                    }
                }catch(SQLException ex){
                    pw.println("<p>Server is experiencing heavy traffic.Please try later</p>");
                    System.out.println("Exception in doPost:"+ex);
                 }
                 finally{
                    pw.println("</body>");
                    pw.println("</html>");
                 }
        }
        public void destroy(){
            try{
                conn.close();
            }catch(SQLException ex){
                
                System.out.println("Exception in destroy:"+ex);
             }
        }

   
}
