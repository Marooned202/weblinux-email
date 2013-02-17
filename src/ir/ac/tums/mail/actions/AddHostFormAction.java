package ir.ac.tums.mail.actions;
import java.io.*;
import java.util.Collection;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.*;
import ir.ac.tums.mail.db.*;
import ir.ac.tums.mail.db.entities.*;

public class AddHostFormAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public AddHostFormAction()
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

            Collection hostCol = HostDAO.findAll();
            request.setAttribute("hostList",hostCol);
			return mapping.findForward("success");
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
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