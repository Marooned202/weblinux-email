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


<h2 align="center">Add Host</h2>
<form method="POST" action="addhost.do">
<p>
  <table border="0" cellpadding="5" cellspacing="5" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
  <tr>
    <td width="50%" align="right">
    <p align="right">Host Name: </td>
    <td width="50%"><input type="text" name="hostname" size="20"></td>
  </tr>
  <tr>
    <td width="50%" align="right">IP Address:</td>
    <td width="50%"><input type="text" name="ip" size="20"></td>
  </tr>
  <tr>
    <td width="50%" align="right">OS: </td>
    <td width="50%"><select size="1" name="os">
    <option selected value="1">Linux</option>
    <option value="2">Windows</option>
    </select></td>
  </tr>
  <tr>
    <td width="50%" align="right">Mailer:</td>
    <td width="50%"><select size="1" name="mailer">
    <option selected>1</option>
    <option>3</option>
    <option>2</option>
    </select></td>
  </tr>
  </table>

  <p align="center">

  <input type="submit" value="Add Host" name="B1"></p>
</p>
</form>
<br>
<hr>
<br>
<h2 align="center">Remove Host</h2>
<form method="POST" action="removehost.do">
  <table border="0" cellpadding="2" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" height="19">

    <tr>
    <td width="50%" align="right">Host:</td>
    <td width="50%"><select size="1" name="host">

    <%
	  	Collection col = (Collection) request.getAttribute("hostList");
		if (col != null) {
		  Iterator hostIter = col.iterator();
		  while (hostIter.hasNext()) {
		    MailHost host = (MailHost) hostIter.next();
	%>
       <option value="<%= host.getHostID()%>"><%= host.getIp()%></option>
    <%
		  }
		}
	%>

    </select></td>
  </tr>
  </table>
  <p align="center"><input type="submit" value="Remove Host" name="B1"></p>
</form>