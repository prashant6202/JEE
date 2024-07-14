import javax.servlet.*;
import java.io.*;
import javax.servlet.http.*;
import java.util.*;
public class MyRedirectionServlet extends HttpServlet
{
   protected void doGet(HttpServletRequest req,HttpServletResponse resp)throws ServletException,IOException
   {
        resp.setContentType("text/html");
        String browserName=req.getHeader("user-agent");
        if(browserName.indexOf("Fire-fox")==-1){
            resp.sendRedirect("http://www.mozilla.org");
        }else if(browserName.indexOf("Chrome")==-1){
            resp.sendRedirect("http://www.google.com");
        }else{
            resp.sendRedirect("http://www.scalive.in");
        }
   }
}
 