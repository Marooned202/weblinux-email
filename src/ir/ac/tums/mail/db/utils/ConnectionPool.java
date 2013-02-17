package ir.ac.tums.mail.db.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Ehsan
 * Date: Sep 19, 2003
 * Time: 6:05:09 PM
 * To change this template use Options | File Templates.
 */

import java.sql.*;
import javax.sql.*;
import org.apache.struts.util.*;

public class ConnectionPool
{

	private DataSource ds;

	private static ConnectionPool mySelf;

	private ConnectionPool(DataSource ds)
	{
		this.ds=ds;
	}

	public static void init(DataSource ds)
	{
		mySelf=new ConnectionPool(ds);
	}

	public static ConnectionPool getInstance()
	{
		if(mySelf==null)
		{
			throw new IllegalStateException("Pool not initialized.");
		}
		return mySelf;

	}

	public Connection getConnection() throws SQLException
	{
        Connection con = ds.getConnection();
        con.setAutoCommit(false);

        

		return con;
	}
 /*
	public int getActiveCount()
	{
		return ((GenericDataSource)ds).getActiveCount();
	}
	public int getUseCount()
	{
		return ((GenericDataSource)ds).getUseCount();
	}
    */
}
