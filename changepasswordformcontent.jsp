<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import = "java.util.*,
                   ir.ac.tums.mail.db.entities.Unit,
                   ir.ac.tums.mail.db.entities.Speciality,
                   ir.ac.tums.mail.db.entities.MailHost" %>

<%@ page import="java.util.*"%>

<logic:present name="formNotFilled">
	<h3 align="center"><font color="#FF0000"> Please fill the form </font></h3>
</logic:present>

<logic:present name="wrongPassword">
	<h3 align="center"><font color="#FF0000"> Wrong old password </font></h3>
</logic:present>

<logic:present name="matchPassword">
	<h3 align="center"><font color="#FF0000"> Passwords do not match </font></h3>
</logic:present>

<h2 align="center">Change Password</h2>
<form method="POST" action="changepassword.do">
<p>
  <table border="0" cellpadding="5" cellspacing="5" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
  <tr>
    <td width="50%" align="right">
    <p align="right">Old Password: </td>
    <td width="50%"><input type="password" name="oldpassword" size="20"></td>
  </tr>
  <tr>
    <td width="50%" align="right">
    <p align="right">New Password: </td>
    <td width="50%"><input type="password" name="newpassword1" size="20"></td>
  </tr>
  <tr>
    <td width="50%" align="right">
    <p align="right">New Password Confirm: </td>
    <td width="50%"><input type="password" name="newpassword2" size="20"></td>
  </tr>

  </table>

  <p align="center">

  <input type="submit" value="Change Password" name="B1"></p>
</p>
</form>