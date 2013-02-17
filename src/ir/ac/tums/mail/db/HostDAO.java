package ir.ac.tums.mail.db;

import ir.ac.tums.mail.db.utils.ConnectionPool;
import ir.ac.tums.mail.db.entities.MailHost;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by IntelliJ IDEA.
 * User: OEMUSER
 * Date: Aug 9, 2004
 * Time: 10:30:01 AM
 * To change this template use File | Settings | File Templates.
 */

public class HostDAO
{
	private static ConnectionPool pool;

	static
	{
		pool=ConnectionPool.getInstance();
	}

	private static long getIdentity(Connection con)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		String sql="select last_insert_id()";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			rs.next();
			long nextVal=rs.getLong(1);
			return nextVal;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(ps!=null)
				{
					ps.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}

	public static long create(MailHost host) throws CreateException
	{
		PreparedStatement ps=null;
		Connection con=null;
		String sql="INSERT INTO "+DBInfo.host+
			" (hostname, ip, os, mailer)  "+
			" VALUES(?, ?, ?, ?)";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setString(1,host.getHostname());
            ps.setString(2,host.getIp());
            ps.setByte(3,host.getOs());
            ps.setByte(4,host.getMailer());


			if(ps.executeUpdate()!=1)
			{
				throw new CreateException("error.create.host");
			}
			long identity=getIdentity(con);
			host.setHostID(identity);
			con.commit();
			return identity;
		}
		catch(SQLException e)
		{
			if(e.getErrorCode()==DBInfo.ERR_DUPLICATE_KEY)
				throw new DuplicateKeyException("error.duplicate.host");
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(ps!=null)
				{
					ps.close();
				}
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}

	public static void updateCon(Connection con,MailHost host) throws NoSuchEntityException
	{

		PreparedStatement ps=null;
		String sql="UPDATE "+DBInfo.host+" SET hostname = ?, ip = ?, os = ?, mailer = ?, "+
			"WHERE hostID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setString(1,host.getHostname());
            ps.setString(2,host.getIp());
            ps.setByte(3,host.getOs());
            ps.setByte(4,host.getMailer());
			ps.setLong(5,host.getHostID());
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.host");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();

			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(ps!=null)
				{
					ps.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}

	public static void update(MailHost host) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			updateCon(con,host);
			con.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();

				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}

	public static void remove(int hostID) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			removeCon(con,hostID);
			con.commit();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}

	public static void removeCon(Connection con,int hostID) throws NoSuchEntityException
	{
		PreparedStatement ps=null;
		String sql="DELETE FROM "+DBInfo.host+" WHERE hostID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,hostID);
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.host");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(ps!=null)
				{
					ps.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}


	public static MailHost findByPrimaryKey(int hostID) throws FinderException
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		MailHost host=null;
		Connection con=null;
		String sql="SELECT * from "+ DBInfo.host +" WHERE hostID = ?";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,hostID);
			rs=ps.executeQuery();
			if(rs.next())
			{
				host=new MailHost();
				host.setHostID(rs.getInt(1));
				host.setHostname(rs.getString(2));
                host.setIp(rs.getString(3));
                host.setOs(rs.getByte(4));
                host.setMailer(rs.getByte(5));

				con.commit();
				return host;
			}
			else
			{
				throw new ObjectNotFoundException("error.removed.host");
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(ps!=null)
				{
					ps.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}

	public static ArrayList findAll()
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		Connection con=null;
		String sql=
			"SELECT * from "+DBInfo.host;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next())
			{
				MailHost host=new MailHost();
				host.setHostID(rs.getInt(1));
				host.setHostname(rs.getString(2));
                host.setIp(rs.getString(3));
                host.setOs(rs.getByte(4));
                host.setMailer(rs.getByte(5));
				list.add(host);
			}
			con.commit();
			return list;
		}
		catch(SQLException e)
		{
			e.printStackTrace();
			throw new RuntimeException("Redirected:"+e.toString());
		}
		finally
		{
			try
			{
				if(ps!=null)
				{
					ps.close();
				}
				if(rs!=null)
				{
					rs.close();
				}
				if(con!=null)
				{
					con.close();
				}
			}
			catch(SQLException e)
			{
				e.printStackTrace();
				throw new RuntimeException("Redirected:"+e.toString());
			}
		}
	}
}
