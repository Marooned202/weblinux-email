package ir.ac.tums.mail.actions;
import java.io.*;
import java.util.Collection;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.*;
import ir.ac.tums.mail.db.*;
import ir.ac.tums.mail.db.entities.*;
import ir.ac.tums.mail.util.PDate;

public class UpdateUserAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public UpdateUserAction()
    {
        pool=ConnectionPool.getInstance();
    }

    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,ServletException
    {
        int days=0;
        java.sql.Date expdate = null;
        ActionErrors errors=new ActionErrors();
        boolean expdays;
		try
		{
            //***************** Validate Reporter ***************************
			 User su=null;
			 if(request.getSession()!=null)
			{
				su=(User)request.getSession().getAttribute("userInfo");
				if(su==null)
				{
					return mapping.findForward("login");
				}
				if(su.getType() < 1) // Is not Admin
				{
					return mapping.findForward("login");
				}
			}
			else
			{
				return mapping.findForward("login");
			}
			//****************** End of Validation *************************

            String UID=(String)request.getParameter("UID");
			 if(UID==null)
			{
				UID=(String)request.getAttribute("UID");
			}

            User user = new User();
            user.setUserID(Integer.parseInt(UID));
            user.setUsername( request.getParameter("username"));
            user.setFirstname( request.getParameter("name"));
            user.setLastname( request.getParameter("lastname"));
            user.setType( new Byte (request.getParameter("type")).byteValue());
            user.setOldemail( request.getParameter("oldemail"));
            user.setHomephone( request.getParameter("homephone"));
            user.setWorkphone( request.getParameter("workphone"));
            user.setWorkplace( request.getParameter("workplace"));
            user.setPassword( request.getParameter ("password"));

            user.getHost().setHostID( new Integer (request.getParameter ("host")).intValue());
            user.getSpec().setSpecialityID( new Integer (request.getParameter ("spec")).intValue());
            user.getUnit().setUnitID( new Integer (request.getParameter ("unit")).intValue());

            if (request.getParameter ("exp").equals("expdays")) expdays = true; else expdays=false;
            if (expdays)
            {
                days = Integer.parseInt( request.getParameter ("expdays"));
                java.util.Date now = new java.util.Date();
                long nowmili = now.getTime();
                long then = nowmili + 6*1000*60*60*24*days;
                expdate = new java.sql.Date(then);
            } else {
                java.util.Date sdate = PDate.toGregorianDate(Integer.parseInt(request.getParameter("day")),
                        Integer.parseInt(request.getParameter("month")),
                                Integer.parseInt(request.getParameter("year")));
                expdate = new java.sql.Date(sdate.getTime());
            }
            user.setExpdate(expdate);
            UserDAO.update(user);
            Runtime rt = Runtime.getRuntime();
            String line;
            String[] callAndArgs = { "usermod",
                                     "-c",
                                     user.getFirstname() + "," + user.getLastname(),
                                     "-e",
                                     Integer.toString(days),
                                     user.getUsername()};
            String[] callAndArgs2 = { "passwd",
                                      "--stdin",
                                      user.getUsername() };
            try {
                Process child = rt.exec(callAndArgs);
                child.waitFor();
                // Set Password
                Process child2 = rt.exec(callAndArgs2);
                InputStream stdin = child2.getInputStream();
                InputStreamReader isr = new InputStreamReader(stdin);
                BufferedReader br = new BufferedReader(isr);
                OutputStream os = child2.getOutputStream();
                PrintWriter pw = new PrintWriter(os);
                pw.println (user.getPassword());
                pw.flush();
                if ((line = br.readLine()) != null)
                    System.out.println(line);
                pw.println(user.getPassword());
                pw.flush();
                child2.waitFor();
            }
            catch(IOException e) {
                throw new RuntimeException (e.getMessage());
  //              System.err.println(
 //                       "IOException starting process!");
            }
            catch(InterruptedException e) {
                throw new RuntimeException (e.getMessage());
  //              System.err.println(
  //                      "Interrupted waiting for process!");
            }




			return mapping.findForward("success");
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
			return mapping.findForward("error");
        } catch (NoSuchEntityException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return mapping.findForward("error");
        }

        /*
        try
        {
        return mapping.findForward("success");
        }
        catch(RuntimeException e)
        {
        e.printStackTrace();
        return mapping.findForward("error");
        }
        */
    }

}