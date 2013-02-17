
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: OEMUSER
 * Date: Sep 1, 2004
 * Time: 5:04:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class tim {
    public static void main (String[] Argv)
    {
        String line;
        try {
            BufferedReader bf = new BufferedReader(new FileReader("\\etc\\passwd"));
            while ((line = bf.readLine()) != null)
            {
                System.out.println (line);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
