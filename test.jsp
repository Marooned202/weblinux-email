<%@ page import="java.io.PrintWriter,
                 java.io.FileWriter,
                 ir.ac.tums.mail.db.utils.ConnectionPool,
                 java.sql.*,
                 ir.ac.tums.mail.db.utils.ConnectionPool,
                 ir.ac.tums.mail.db.entities.User,
                 ir.ac.tums.mail.db.UserDAO,
                 java.io.IOException,
                 java.io.OutputStream"%>
<html>
    <head></head>
    <body>

        <%

              User e = new User();
            e.setUsername("dsds");
            e.setPassword("dfsfsdf");
            e.setFirstname("sasa");
            e.setType((byte)1);
            e.setHomephone("0911-324-4324");
      //      UserDAO.create(e);
       /*
            Runtime rt = Runtime.getRuntime();

            String[] callAndArgs = { "adduser",
                                     "-c",
                                     e.getFirstname() + "," + e.getLastname(),
                                     "-e",
                                     Integer.toString(344),
                                     e.getUsername()};
            try {
                Process child = rt.exec(callAndArgs);
                child.waitFor();
//                System.out.println("Process exit code is: " + child.exitValue());
            }
            catch(IOException ee) {
                ee.printStackTrace(new PrintWriter (out));
                throw new RuntimeException (ee.getMessage());
  //              System.err.println(
 //                       "IOException starting process!");
            }
            catch(InterruptedException ee) {
                throw new RuntimeException (ee.getMessage());
  //              System.err.println(
  //                      "Interrupted waiting for process!");
            }
         */
   /*
            ConnectionPool pool = ConnectionPool.getInstance();
            Connection con = pool.getConnection();
            PreparedStatement ps = null;
            try {
                int i;

                String sql = "CREATE TABLE Ehsan2 (Customer_ID INT NOT NULL, "
                     + "Last_Name VARCHAR(30) NOT NULL)";
		        ps = con.prepareStatement (sql);
                ps.executeUpdate();
            } catch (Exception ee)
            {
                out.print ("NASHODDD:PD!!!!");
                out.print (ee.getMessage());
 //               www.println (e.getMessage ());
  //              e.printStackTrace(www);
            }
            finally {
   //             www.close();
        	    ps.close();
            }
    */

            Connection con = null;
            Class.forName("org.gjt.mm.mysql.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql://194.225.62.83:3306/mail?user=tmail&password=baran1");
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE MYTABLE2 (TT INTEGER NOT NULL PRIMARY KEY, KL CHAR(50));";
            stmt.executeUpdate(sql);

        %>
    </body>
</html>