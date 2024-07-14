import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class MyRegistrationServlet3 extends HttpServlet
{   private Connection conn;
    private PreparedStatement ps;
    public void init()throws ServletException
    {
        ServletContext ctxt=super.getServletContext();
        ServletConfig cfg=super.getServletConfig();
        String url=ctxt.getInitParameter("connurl");
        String username=ctxt.getInitParameter("dbuser");
        String password=ctxt.getInitParameter("dbpassword");
        String qry=cfg.getInitParameter("qry");

        try{
            conn=DriverManager.getConnection(url,username,password);
            System.out.println("connection opended successfully!");
            ps=conn.prepareStatement(qry);
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
                    ps.setString(3,username);
                    int result=ps.executeUpdate();
                    if(result==1)
                    {
                       
                        pw.println("<p>Thank you <strong>"+username+"</strong><br> For Registering With Us!</p>");
                        pw.println("<p><a href='signin.html'>You can now login</a></p>");
                    }
                    else{
                        pw.println("<p>Registration Failed.</p>");
                        pw.println("<a href='signup.html'>Try Again ?</a><br></p>");

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
