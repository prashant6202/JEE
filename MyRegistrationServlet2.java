import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class MyRegistrationServlet2 extends HttpServlet
{   private Connection conn;
    private PreparedStatement ps;
    public void init()throws ServletException
    {
        try{
            conn=DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","advjavabatch","mystudents");
            System.out.println("connection opended successfully!");
            ps=conn.prepareStatement("insert into users values(?,?,?)");
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
                String username=req.getParameter("username");
                PrintWriter pw=resp.getWriter();
                resp.setContentType("text/html");
                pw.println("<html><head><title>Registration Response</title></head>");
                pw.println("<body>");

                try{
                    ps.setString(1,userid);
                    ps.setString(2,userpwd);
                    ps.setString(3, username);
                    int result= ps.executeUpdate();
                    if(result==1)
                    {
                        pw.println("<p>Thank you <strong>"+username+"</strong><br>for Registration with us!</p>");
                        pw.println("<p><a href='signin.html'>You can now login</a></p>");
                    }
                    else{
                        pw.println("<p>Registration failed!.<br>");
                        pw.println("<a href='signup.html'>Try again !</a><br></p>");

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
