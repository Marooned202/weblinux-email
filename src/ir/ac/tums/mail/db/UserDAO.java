package ir.ac.tums.mail.db;

import java.sql.*;
import java.util.*;
import java.util.Date;

import ir.ac.tums.mail.db.entities.*;
import ir.ac.tums.mail.db.utils.ConnectionPool;

public class UserDAO
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

	public static long create(User user) throws CreateException
	{
		PreparedStatement ps=null;
		Connection con=null;
		String sql="INSERT INTO "+DBInfo.user+
			" (hostID, unitID, specialityID, username, password, firstname, lastname, type, oldemail, homephone, workphone, workplace, expdate)  "+
			" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try
		{
            try {
                findByUserName(user.getUsername());
                throw new DuplicateKeyException("error.duplicate.user");
            } catch (FinderException e) {
                // do nothing
            }

            con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
            ps.setLong(1, user.getHost().getHostID());
            ps.setLong(2, user.getUnit().getUnitID());
            ps.setLong(3, user.getSpec().getSpecialityID());
			ps.setString(4,user.getUsername());
			ps.setString(5,user.getPassword());
			ps.setString(6,user.getFirstname());
			ps.setString(7,user.getLastname());
			ps.setByte(8,user.getType());
			ps.setString(9,user.getOldemail());
			ps.setString(10,user.getHomephone());
			ps.setString(11,user.getWorkplace());
			ps.setString(12,user.getWorkphone());
            ps.setDate(13,user.getExpdate());
			if(ps.executeUpdate()!=1)
			{
				throw new CreateException("error.create.user");
			}
			long identity=getIdentity(con);
			user.setUserID(identity);
			con.commit();
			return identity;
		}
		catch(SQLException e)
		{
			if(e.getErrorCode()==DBInfo.ERR_DUPLICATE_KEY)
				throw new DuplicateKeyException("error.duplicate.user");
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

	public static void updateCon(Connection con,User user) throws NoSuchEntityException
	{
		PreparedStatement ps=null;
		String sql="UPDATE "+DBInfo.user+" SET hostID = ?, unitID = ?, specialityID = ?, "+
			"username = ?, password = ?, firstname = ?, lastname = ?, type = ?, "+
			"oldemail = ?, homephone = ?, workphone = ?, workplace = ? ,expdate = ? "+
			"WHERE userID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setLong(1, user.getHost().getHostID());
            ps.setLong(2, user.getUnit().getUnitID());
            ps.setLong(3, user.getSpec().getSpecialityID());
			ps.setString(4,user.getUsername());
			ps.setString(5,user.getPassword());
			ps.setString(6,user.getFirstname());
			ps.setString(7,user.getLastname());
			ps.setByte(8,user.getType());
			ps.setString(9,user.getOldemail());
			ps.setString(10,user.getHomephone());
			ps.setString(11,user.getWorkplace());
			ps.setString(12,user.getWorkphone());
            ps.setDate(13,user.getExpdate());
            ps.setLong(14,user.getUserID());
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.user");
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

	public static void update(User user) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			updateCon(con,user);
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

	public static void remove(int userID) throws NoSuchEntityException
	{
		Connection con=null;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			removeCon(con,userID);
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

	public static void removeCon(Connection con,int userID) throws NoSuchEntityException
	{
		PreparedStatement ps=null;
		String sql="DELETE FROM "+DBInfo.user+" WHERE USERID = ?";
		try
		{
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setInt(1,userID);
			if(ps.executeUpdate()!=1)
			{
				throw new NoSuchEntityException("error.removed.user");
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

	public static User findByUserName(String userName) throws FinderException
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		Connection con=null;
		String sql="SELECT * from "+DBInfo.user+" WHERE USERNAME = ?";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setString(1,userName);
			rs=ps.executeQuery();
			if(rs.next())
			{
				user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));
				con.commit();
				return user;
			}
			else
			{
				throw new ObjectNotFoundException("error.removed.user");
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

	public static User findByPrimaryKey(long userID) throws FinderException
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		Connection con=null;
		String sql="SELECT * from "+DBInfo.user+" WHERE USERID = ?";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			ps.setLong(1,userID);
			rs=ps.executeQuery();
			if(rs.next())
			{
				user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));
				con.commit();
				return user;
			}
			else
			{
				throw new ObjectNotFoundException("error.removed.user");
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

	public static ArrayList findAll(int SP)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		Connection con=null;
		String sql=
			"SELECT * from "+DBInfo.user;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            int i = 0;
			while(rs.next())
			{
				User user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));
                i++;
                if (i >= SP)
                    list.add(user);
                if ((i - SP)+2 > DBInfo.PAGE_SIZE) break;
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

    public static ArrayList findByUnitID(int UID, int SP)
	{
		PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		Connection con=null;
		String sql=
			"SELECT * from "+DBInfo.user + " WHERE (unitID = " + UID + ")";
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            int i = 0;
			while(rs.next())
			{
				User user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));
                i++;
                if (i >= SP)
                    list.add(user);
                if ((i - SP)+2 > DBInfo.PAGE_SIZE) break;
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

    public static Collection findExpiredUsers(int SP)
    {
        PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		Connection con=null;
		String sql=
			"SELECT * from "+DBInfo.user;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            int i = 0;
			while(rs.next())
			{
				User user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));

                if (user.getExpdate() != null)
                    if (user.getExpdate().getTime() < (new java.util.Date()).getTime())
                    {
                        i++;
                        if (i >= SP)
				            list.add(user);
                        if ((i - SP)+2 > DBInfo.PAGE_SIZE) break;
                    }
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

    public static Collection findBy(String username, String firstname, String lastname, String oldemail, int SP)
    {
        PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		Connection con=null;
		String sql=
			"SELECT * from "+DBInfo.user;

        boolean thereiswhere = false;

        if ((username != null) && (!username.trim().equals("")))
        {
            sql = sql + " WHERE (";
            thereiswhere = true;
            sql = sql + " username = \'" + username;
        }
        if ((firstname != null) && (!firstname.trim().equals("")))
        {
            if (!thereiswhere)
            {
                sql = sql + " WHERE (";
                thereiswhere = true;
            }
            else {
                sql = sql + "\' AND ";
            }
            sql = sql + " firstname = \'" + firstname;
        }

        if ((lastname != null) && (!lastname.trim().equals("")))
        {
            if (!thereiswhere)
            {
                sql = sql + " WHERE (";
                thereiswhere = true;
            }
            else {
                sql = sql + "\' AND ";
            }
            sql = sql + " lastname = \'" + lastname;
        }

        if ((oldemail != null) && (!oldemail.trim().equals("")))
        {
            if (!thereiswhere)
            {
                sql = sql + " WHERE (";
                thereiswhere = true;
            }
            else {
                sql = sql + "\' AND ";
            }
            sql = sql + " oldemail = \'" + oldemail;
        }

        if (thereiswhere) sql = sql + "\')";

        try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            int i = 0;
			while(rs.next())
			{
				User user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));
                i++;
                if (i >= SP)
                    list.add(user);
                if ((i - SP)+2 > DBInfo.PAGE_SIZE) break;
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

    public static Collection findExpiredUsersAfter(Date sdate, int SP) {

        PreparedStatement ps=null;
		ResultSet rs=null;
		ArrayList list=new ArrayList();
		Connection con=null;
		String sql=
			"SELECT * from "+DBInfo.user;
		try
		{
			con=pool.getConnection();
			if(con.isClosed())
			{
				throw new IllegalStateException("error.unexpected");
			}
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
            int i = 0;
			while(rs.next())
			{
				User user=new User();
				user.setUserID(rs.getInt(1));
                user.getHost().setHostID(rs.getLong(2));
                user.getUnit().setUnitID(rs.getLong(3));
                user.getSpec().setSpecialityID(rs.getLong(4));
				user.setUsername(rs.getString(5));
				user.setPassword(rs.getString(6));
				user.setFirstname(rs.getString(7));
				user.setLastname(rs.getString(8));
				user.setType(rs.getByte(9));
				user.setOldemail(rs.getString(10));
				user.setHomephone(rs.getString(11));
				user.setWorkphone(rs.getString(12));
				user.setWorkplace(rs.getString(13));
                user.setExpdate(rs.getDate(14));

                if (user.getExpdate() != null)
                    if (user.getExpdate().getTime() < (sdate.getTime()))
                    {
                        i++;
                        if (i >= SP)
				            list.add(user);
                        if ((i - SP)+2 > DBInfo.PAGE_SIZE) break;
                    }
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
