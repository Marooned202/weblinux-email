<%@ page import="java.io.BufferedReader,
                 java.io.File,
                 java.io.FileReader,
                 java.io.IOException,
                 ir.ac.tums.mail.db.entities.User,
                 ir.ac.tums.mail.db.UserDAO,
                 ir.ac.tums.mail.db.CreateException"%>
 <%--
Created by IntelliJ IDEA.
User: OEMUSER
Date: Sep 1, 2004
Time: 3:49:53 PM
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head><title>Simple jsp page</title></head>
  <body>

  <%
      String line, username;
      int index;
      int i = 0;
      User user = new User();
      try {
          BufferedReader bf = new BufferedReader(new FileReader("/etc/passwd"));
          while ((line = bf.readLine()) != null)
          {
              i++;
              if (i < 22) continue;
              index = line.indexOf(':');
              username = line.substring(0, index);
              user.setUsername(username);
              try {
                  UserDAO.create(user);
                  out.println ("Adding user ---> " + username + "<BR>");
              } catch (CreateException e) {
                  out.println ("user " + username + " already exists <BR>");
              }
          }
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
           out.print (e.getMessage());
        }
   %>

  </body>
</html>