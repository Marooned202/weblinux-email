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

public class RemoveSpecialityAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public RemoveSpecialityAction()
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
				if(su.getType() < 2) // Is not Head Admin
				{
					return mapping.findForward("login");
				}
			}
			else
			{
				return mapping.findForward("login");
			}
			//****************** End of Validation *************************
            int specID = new Integer (request.getParameter ("speciality")).intValue();
            SpecialityDAO.remove(specID);
			return mapping.findForward("success");
		}
		catch(RuntimeException e)
		{
			e.printStackTrace();
			return mapping.findForward("error");
		} catch (NoSuchEntityException e) {
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