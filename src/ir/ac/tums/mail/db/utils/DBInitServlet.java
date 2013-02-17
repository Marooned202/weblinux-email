package ir.ac.tums.mail.db.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Ehsan
 * Date: Sep 19, 2003
 * Time: 6:05:55 PM
 * To change this template use Options | File Templates.
 */
import java.sql.*;
import java.io.PrintWriter;
import java.io.FileWriter;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.apache.struts.util.*;

public class DBInitServlet extends HttpServlet
{

	public void init(ServletConfig config) throws ServletException
	{
		super.init(config);
		try
		{
/*
			GenericDataSource ds=new GenericDataSource();
			ds.setDriverClass(getInitParameter("driverClass"));
			ds.setUrl(getInitParameter("jdbcURL"));
			ds.setMinCount(Integer.parseInt(getInitParameter("minCount")));
			ds.setMaxCount(Integer.parseInt(getInitParameter("maxCount")));
			ds.setAutoCommit(false);
			ds.setUser(getInitParameter("user"));
			ds.setPassword(getInitParameter("password"));
*/
            
            Context initCtx = new InitialContext();
            DataSource ds = (DataSource)initCtx.lookup("java:comp/env/jdbc/TestDB");

			ConnectionPool.init(ds);
		}
        catch (Exception e)
        {
            e.printStackTrace();
        }
	}

}
