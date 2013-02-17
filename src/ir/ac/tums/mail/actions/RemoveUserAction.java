package ir.ac.tums.mail.actions;

import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.ConnectionPool;
import ir.ac.tums.mail.db.entities.User;
import ir.ac.tums.mail.db.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;

public class RemoveUserAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public RemoveUserAction()
    {
        pool=ConnectionPool.getInstance();
    }

    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,ServletException
    {
        ActionErrors errors=new ActionErrors();
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


            User user = UserDAO.findByPrimaryKey(Integer.parseInt(UID));
            String[] callAndArgs = { "userdel",
                                     user.getUsername()};

            Runtime rt = Runtime.getRuntime();
            try {
                Process child = rt.exec(callAndArgs);
                child.waitFor();
//                System.out.println("Process exit code is: " + child.exitValue());
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

            UserDAO.remove(Integer.parseInt(UID));

			return mapping.findForward("success");
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
			return mapping.findForward("error");
		} catch (NoSuchEntityException e) {
            e.printStackTrace();
			return mapping.findForward("error");
        } catch (FinderException e) {
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