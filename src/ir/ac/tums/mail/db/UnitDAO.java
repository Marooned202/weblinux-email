package ir.ac.tums.mail.db;

import ir.ac.tums.mail.db.utils.ConnectionPool;
import ir.ac.tums.mail.db.entities.User;
import ir.ac.tums.mail.db.entities.Unit;

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

public class UnitDAO
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

	public static long create(Unit unit) throws CreateException
	{
		PreparedStatement ps=null;
		Connection con=null;
		String sql="INSERT INTO "+DBInfo.unit+
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
			ps.setString(1,unit.getName());

			if(ps.executeUpdate()!=1)
			{
				throw new CreateException("error.create.unit");
			}
			long identity=getIdentity(con);
			unit.setUnitID(identity);
			con.commit();
			return identity;
		}
		catch(SQLException e)
		{
			if(e.getErrorCode()==DBInfo.ERR_DUPLICATE_KEY)
				throw new DuplicateKeyException("error.duplicate.unit");
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

	public static void updateCon(Connection con,Unit unit) throws NoSuchEntityException
	{

		PreparedStatement ps=null;
		String sql="UPDATE "+DBInfo.unit+" SET name = ? "+
			"WHERE unitID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setString(1,unit.getName());
			ps.setLong(2,unit.getUnitID());
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.unit");
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

	public static void update(Unit unit) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			updateCon(con,unit);
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

	public static void remove(int unitID) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			removeCon(con,unitID);
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

	public static void removeCon(Connection con,int unitID) throws NoSuchEntityException
	{
		PreparedStatement ps=null;
		String sql="DELETE FROM "+DBInfo.unit+" WHERE unitID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,unitID);
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.unit");
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


	public static Unit findByPrimaryKey(int unitID) throws FinderException
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		Unit unit=null;
		Connection con=null;
		String sql="SELECT * from "+ DBInfo.unit +" WHERE unitID = ?";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,unitID);
			rs=ps.executeQuery();
			if(rs.next())
			{
				unit=new Unit();
				unit.setUnitID(rs.getInt(1));
				unit.setName(rs.getString(2));
				con.commit();
				return unit;
			}
			else
			{
				throw new ObjectNotFoundException("error.removed.unit");
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
			"SELECT unitID, name from "+DBInfo.unit;
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
				Unit unit=new Unit();
				unit.setUnitID(rs.getInt(1));
				unit.setName(rs.getString(2));
				list.add(unit);
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
