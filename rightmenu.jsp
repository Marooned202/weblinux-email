<%@ page import = "java.util.*,
                   ir.ac.tums.mail.db.entities.User" %>
<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<% User user=(User)request.getSession().getAttribute("userInfo");%>


<table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber3" height="44">

 <%if(user!=null)if(user.getType()==2){%>
    <tr>
        <td width="20%" height="1" align="center">
        </td>
        <td width="80%" height="1" align="center">
        <p align="left"><a class="one" href="headadminhome.jsp">Home</a></td>
    </tr>
    <tr>
        <td width="20%" height="1" align="center">
        </td>
        <td width="80%" height="1" align="center">
        <p align="left"><a class="one" href="changepasswordform.do">Change Password</a></td>
    </tr>
    <tr>
        <td width="20%" height="15" align="center">
        </td>
        <td width="80%" height="15" align="center">
        <p align="left"><a class="one" href="logout.do">Logout</a></td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
  <%}%>
  <%if(user!=null)if(user.getType()==1){%>
    <tr>
        <td width="20%" height="1" align="center">
        </td>
        <td width="80%" height="1" align="center">
        <p align="left"><a class="one" href="adminhome.do">Home</a></td>
    </tr>
    <tr>
        <td width="20%" height="1" align="center">
        </td>
        <td width="80%" height="1" align="center">
        <p align="left"><a class="one" href="changepasswordform.do">Change Password</a></td>
    </tr>
    <tr>
        <td width="20%" height="15" align="center">
        </td>
        <td width="80%" height="15" align="center">
        <p align="left"><a class="one" href="logout.do">Logout</a></td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
  <%}%>
  <%if(user!=null)if(user.getType()==0){%>
    <tr>
        <td width="20%" height="1" align="center">
        </td>
        <td width="80%" height="1" align="center">
        <p align="left"><a class="one" href="userhome.do">Home</a></td>
    </tr>
    <tr>
        <td width="20%" height="1" align="center">
        </td>
        <td width="80%" height="1" align="center">
        <p align="left"><a class="one" href="changepasswordform.do">Change Password</a></td>
    </tr>
    <tr>
        <td width="20%" height="15" align="center">
        </td>
        <td width="80%" height="15" align="center">
        <p align="left"><a class="one" href="logout.do">Logout</a></td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
  <%}%>
  <%if(user==null){%>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center"><p align="left"><a class="one" href="login.jsp">login</a>
         </td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
    <tr>
         <td width="20%" height="15" align="center">
         </td>
         <td width="80%" height="15" align="center">
         </td>
    </tr>
  <%}%>


</table>
