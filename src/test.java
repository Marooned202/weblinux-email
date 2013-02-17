

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by IntelliJ IDEA.
 * User: OEMUSER
 * Date: Jul 26, 2004
 * Time: 5:08:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class test {
    public static void main (String[] argv)
    {

//        ConnectionPool pool = ConnectionPool.getInstance();
        /*
        Connection con = pool.getConnection();

        User e = new User();
        e.setUsername("dsds");
        e.setPassword("dfsfsdf");
        e.setType((byte)1);
        e.setHomephone("0911-324-4324");
        UserDAO.create(e);
        */




        Runtime rt = Runtime.getRuntime();
        String[] callAndArgs = { "passwd",
                                 "--stdin",
                                 "ehi" };
        try {
            String line = null;
            Process child = rt.exec(callAndArgs);

            InputStream stdin = child.getInputStream();
            InputStreamReader isr = new InputStreamReader(stdin);
            BufferedReader br = new BufferedReader(isr);

            OutputStream os = child.getOutputStream();
            PrintWriter pw = new PrintWriter(os);

            pw.println ("empty");
            pw.flush();

            if ((line = br.readLine()) != null)
                           System.out.println(line);

            pw.println("password2");
            pw.flush();

            child.waitFor();

            System.out.println("Process exit code is: " + child.exitValue());
        }
        catch(IOException e) {
            System.err.println(
                    "IOException starting process!");
        } catch (InterruptedException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        //      catch(InterruptedException e) {
        //          System.err.println(
        //                  "Interrupted waiting for process!");
        //      }

    }
}
