package ir.ac.tums.mail.db;

import ir.ac.tums.mail.db.utils.ConnectionPool;
import ir.ac.tums.mail.db.entities.Speciality;

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

public class SpecialityDAO
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

	public static long create(Speciality speciality) throws CreateException
	{
		PreparedStatement ps=null;
		Connection con=null;
		String sql="INSERT INTO "+DBInfo.speciality+
			" (name)  "+
			" VALUES(?)";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setString(1,speciality.getName());

			if(ps.executeUpdate()!=1)
			{
				throw new CreateException("error.create.speciality");
			}
			long identity=getIdentity(con);
			speciality.setSpecialityID(identity);
			con.commit();
			return identity;
		}
		catch(SQLException e)
		{
			if(e.getErrorCode()==DBInfo.ERR_DUPLICATE_KEY)
				throw new DuplicateKeyException("error.duplicate.speciality");
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

	public static void updateCon(Connection con,Speciality speciality) throws NoSuchEntityException
	{

		PreparedStatement ps=null;
		String sql="UPDATE "+DBInfo.speciality+" SET name = ? "+
			"WHERE specialityID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setString(1,speciality.getName());
			ps.setLong(2,speciality.getSpecialityID());
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.speciality");
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

	public static void update(Speciality speciality) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			updateCon(con,speciality);
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

	public static void remove(int specialityID) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			removeCon(con,specialityID);
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

	public static void removeCon(Connection con,int specialityID) throws NoSuchEntityException
	{
		PreparedStatement ps=null;
		String sql="DELETE FROM "+DBInfo.speciality+" WHERE specialityID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,specialityID);
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.speciality");
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


	public static Speciality findByPrimaryKey(int specialityID) throws FinderException
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		Speciality speciality=null;
		Connection con=null;
		String sql="SELECT * from "+ DBInfo.speciality +" WHERE specialityID = ?";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,specialityID);
			rs=ps.executeQuery();
			if(rs.next())
			{
				speciality=new Speciality();
				speciality.setSpecialityID(rs.getInt(1));
				speciality.setName(rs.getString(2));
				con.commit();
				return speciality;
			}
			else
			{
				throw new ObjectNotFoundException("error.removed.speciality");
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
			"SELECT specialityID, name from "+DBInfo.speciality;
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
				Speciality speciality=new Speciality();
				speciality.setSpecialityID(rs.getInt(1));
				speciality.setName(rs.getString(2));
				list.add(speciality);
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
