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
import ir.ac.tums.mail.db.UserDAO;
import ir.ac.tums.mail.db.FinderException;
import ir.ac.tums.mail.db.entities.User;

public class LoginAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public LoginAction()
    {
        pool=ConnectionPool.getInstance();
    }

    public ActionForward execute(ActionMapping mapping,ActionForm form,HttpServletRequest request,
                                 HttpServletResponse response) throws IOException,ServletException
    {
        ActionErrors errors=new ActionErrors();

        try {

            String username=request.getParameter("username");
            String password=request.getParameter("password");
            if(username.trim().equals(""))
            {
                errors.add(ActionErrors.GLOBAL_ERROR,new ActionError("error.missing.all"));
                request.setAttribute("formNotFilled",new Object());
                return new ActionForward(mapping.getInput());
            }

            User user;

            try {
                user = UserDAO.findByUserName(username);
            } catch (FinderException e) {
                request.setAttribute("authError",new Object());
                return new ActionForward(mapping.getInput());
            }

            if (!password.equals(user.password))
            {
                request.setAttribute("authError",new Object());
                return new ActionForward(mapping.getInput());
            }

            request.getSession().setAttribute("userInfo",user);
            if (user.getType() == 2)
                return mapping.findForward("headadmin");
            else if (user.getType() == 1)
                return mapping.findForward("admin");
            else if (user.getType() == 0)
                return mapping.findForward("user");
            else
                return new ActionForward(mapping.getInput());

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