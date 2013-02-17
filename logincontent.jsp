<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import = "java.util.*" %>


<html>

<head>
<meta http-equiv="Content-Language" content="en-gb">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>New Page 1</title>
</head>

<body>

<logic:present name="formNotFilled">
	<h3 align="center"><font color="#FF0000"> Please enter a valid username and password</font></h3>
</logic:present>
<logic:present name="authError">
	<h3 align="center"><font color="#FF0000"> Username or password wrong</font></h3>
</logic:present>

<h2 align="center">&nbsp;Login </h2>

<hr>

<form method="POST" action="login.do">
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
    <tr>
      <td width="38%">
      <p align="right">username:</td>
      <td width="5%">&nbsp;</td>
      <td width="57%"><input type="text" name="username" size="20"></td>
    </tr>
    <tr>
      <td width="38%">
      <p align="right">password:</td>
      <td width="5%">&nbsp;</td>
      <td width="57%"><input type="password" name="password" size="20"></td>
    </tr>
  </table>
  <p align="center"><input type="submit" value="Submit" name="B1"><input type="reset" value="Reset" name="B2"></p>
  </form>

</body>

</html>