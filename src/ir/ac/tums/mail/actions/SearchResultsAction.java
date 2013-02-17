package ir.ac.tums.mail.actions;

import org.apache.struts.action.*;
import ir.ac.tums.mail.db.utils.ConnectionPool;
import ir.ac.tums.mail.db.entities.User;
import ir.ac.tums.mail.db.*;
import ir.ac.tums.mail.util.PDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Collection;

public class SearchResultsAction extends Action
{
//	private static Logger logger=Logger.getLogger(LogOnAction.class.getName());
    private ConnectionPool pool;

    public SearchResultsAction()
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

            int SP;
            if (request.getParameter ("SP") == null) SP = 1;
            else SP = Integer.parseInt(request.getParameter("SP"));

            Collection userCol = null;

            int choice = Integer.parseInt (request.getParameter("stype"));
            if (choice == 1) userCol = UserDAO.findExpiredUsers(SP);
            if (choice == 2) {
                String username = null, firstname = null, lastname = null, oldemail = null;
                if (request.getParameter("sb1") != null)
                {
                    username = request.getParameter("tt1");
                    request.setAttribute("sb1", new String ("1"));
                    request.setAttribute("tt1", username);
                }
                if (request.getParameter("sb2") != null)
                {
                    firstname = request.getParameter("tt2");
                    request.setAttribute("sb2", new String ("2"));
                    request.setAttribute("tt2", firstname);
                }
                if (request.getParameter("sb3") != null)
                {
                    lastname = request.getParameter("tt3");
                    request.setAttribute("sb3", new String ("3"));
                    request.setAttribute("tt3", lastname);
                }if (request.getParameter("sb4") != null)
                {
                    oldemail = request.getParameter("tt4");
                    request.setAttribute("sb4", new String ("4"));
                    request.setAttribute("tt4", oldemail);
                }
                userCol = UserDAO.findBy(username, firstname, lastname, oldemail, SP);
            }

            if (choice == 3) {
                int unit = Integer.parseInt(request.getParameter("unit"));
                userCol = UserDAO.findByUnitID( unit, SP);
                request.setAttribute("unit", new Integer (unit));
            }

            if (choice == 4) {
                int day = Integer.parseInt(request.getParameter("day"));
                int month = Integer.parseInt(request.getParameter("month"));
                int year = Integer.parseInt(request.getParameter("year"));
                java.util.Date sdate = PDate.toGregorianDate(day, month , year);
//                java.sql.Date expdate = new java.sql.Date(sdate.getTime());
                userCol = UserDAO.findExpiredUsersAfter (sdate, SP);
                request.setAttribute("day", new Integer (day));
                request.setAttribute("month", new Integer (month));
                request.setAttribute("year", new Integer (year));
            }

            if (userCol == null)
            {
                request.setAttribute("isEmpty",new Boolean(true));
            } else
            {
                if(userCol.size()>(DBInfo.PAGE_SIZE-1))
                {
                    request.setAttribute("hasNext",new Boolean(true));
                }
            }
            if(SP!=1)
            {
                request.setAttribute("hasPrev",new Boolean(true));
            }

            request.setAttribute("SP", new Integer (SP));
            request.setAttribute("userList",userCol);
            request.setAttribute("stype",new Integer (choice));

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