import javax.servlet.*;
import java.sql.*;

public class MyDBListener implements ServletContextListener
{   private Connection conn;
    public void contextInitialized(ServletContextEvent ev)
    {
        ServletContext ctxt=ev.getServletContext();
        String url=ctxt.getInitParameter("connurl");
        String username=ctxt.getInitParameter("dbuser");
        String password=ctxt.getInitParameter("dbpassword");
        try{
            conn=DriverManager.getConnection(url,username,password);
            System.out.println("Connection opened!");
           }catch(SQLException ex){
            conn=null;
            System.out.println("Error in opening connection:"+ex.getMessage()); 
        }finally{
            ctxt.setAttribute("dbConn",conn);
        }

    }
    public void contextDestroyed(ServletContextEvent ev)
    {
        try{
            if(conn!=null)
                conn.close();
        }catch(SQLException ex){
            
            System.out.println("Error in closing connection"); 
        }
    }
   
}
