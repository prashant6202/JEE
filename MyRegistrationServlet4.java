import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.sql.*;
public class MyRegistrationServlet4 extends HttpServlet
{    private PreparedStatement ps;
    public void init()throws ServletException
    {
        ServletContext ctxt=super.getServletContext();
        ServletConfig cfg=super.getServletConfig();
        Connection conn=(Connection)ctxt.getAttribute("dbconn");
        System.out.println("Connection is:"+conn);
        try{
            if(conn==null){
                throw new SQLException();
            }
            String qry=cfg.getInitParameter("qry");
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
        

   
}
