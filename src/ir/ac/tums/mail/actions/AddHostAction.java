package ir.ac.tums.mail.actions;
import java.io.*;
import java.util.Collection;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.*;
import ir.ac.tums.mail.db.*;
import ir.ac.tums.mail.db.entities.*;

public class AddHostAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;


    public AddHostAction()
    {
        pool=ConnectionPool.getInstance();
    }

    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,ServletException
    {
        ActionErrors errors=new ActionErrors();
        boolean expdays;
        int days=0;
        java.sql.Date expdate = null;
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
                if(su.getType() != 2) // Is not Admin
                {
                    return mapping.findForward("login");
                }        
            }
            else
            {
                return mapping.findForward("login");
            }
            //****************** End of Validation *************************

            MailHost host = new MailHost();
            host.setHostname(request.getParameter("hostname"));
            host.setIp( request.getParameter("ip"));
            host.setOs( Byte.parseByte(request.getParameter("os")));
            host.setMailer( Byte.parseByte(request.getParameter("mailer")));
            HostDAO.create(host);

            return mapping.findForward("success");
        }
        catch (NumberFormatException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            request.setAttribute("numError",new Object());
            return new ActionForward(mapping.getInput());
        }
        catch (CreateException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            request.setAttribute("dupError",new Object());
            return new ActionForward(mapping.getInput());
        }
        catch(RuntimeException e)
        {
            e.printStackTrace();
            request.setAttribute("runError", e);
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