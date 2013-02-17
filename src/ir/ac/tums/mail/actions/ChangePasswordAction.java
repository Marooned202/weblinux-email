package ir.ac.tums.mail.actions;

/**
 * Created by IntelliJ IDEA.
 * User: OEMUSER
 * Date: Aug 8, 2004
 * Time: 8:30:58 PM
 * To change this template use File | Settings | File Templates.
 */
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.ConnectionPool;
import ir.ac.tums.mail.db.UserDAO;
import ir.ac.tums.mail.db.FinderException;
import ir.ac.tums.mail.db.NoSuchEntityException;
import ir.ac.tums.mail.db.entities.User;

public class ChangePasswordAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public ChangePasswordAction()
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
            }
            else
            {
                return mapping.findForward("login");
            }
            //****************** End of Validation *************************

            String oldpass = request.getParameter("oldpassword");
            String newpass1 = request.getParameter("newpassword1");
            String newpass2 = request.getParameter("newpassword2");

            if (!newpass1.equals(newpass2)) {
                request.setAttribute("matchPassword",new Object());
                return new ActionForward(mapping.getInput());
            }

            User user = UserDAO.findByPrimaryKey(su.getUserID());
            if (!user.getPassword().equals(oldpass))
            {
                request.setAttribute("wrongPassword",new Object());
                return new ActionForward(mapping.getInput());
            }
            user.setPassword(newpass1);
            UserDAO.update(user);

            if (su.getType() == 0)
            {
                Runtime rt = Runtime.getRuntime();
                String line;
                String[] callAndArgs2 = { "passwd",
                                          "--stdin",
                                          user.getUsername() };
                try {
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
            }
            request.setAttribute("usertype",new Integer (user.getType()));
            return mapping.findForward("success");
        }
        catch (NullPointerException e)
        {
            request.setAttribute("formNotFilled",new Object());
            return new ActionForward(mapping.getInput());
        }
        catch(RuntimeException e)
        {
            e.printStackTrace();
            return mapping.findForward("error");
        } catch (FinderException e)
        {
            return new ActionForward("error");
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