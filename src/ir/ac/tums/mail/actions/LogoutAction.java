package ir.ac.tums.mail.actions;

/**
 * Created by IntelliJ IDEA.
 * User: OEMUSER
 * Date: Aug 2, 2004
 * Time: 11:58:49 AM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.ConnectionPool;

public class LogoutAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public LogoutAction()
    {
        pool=ConnectionPool.getInstance();
    }

    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,ServletException
    {
        ActionErrors errors=new ActionErrors();

        try {

            request.getSession().removeAttribute("userInfo");
			return mapping.findForward("success");

        } catch (RuntimeException e) {
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