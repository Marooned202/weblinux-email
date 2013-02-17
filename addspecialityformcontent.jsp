<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import = "java.util.*,
                   ir.ac.tums.mail.db.entities.Unit,
                   ir.ac.tums.mail.db.entities.Speciality,
                   ir.ac.tums.mail.db.entities.Speciality" %>

<%@ page import="java.util.*"%>

<logic:present name="formNotFilled">
	<h3 align="center"><font color="#FF0000"> Please fill the form </font></h3>
</logic:present>


<h2 align="center">Add Speciality</h2>
<form method="POST" action="addspeciality.do">
<p>
  <table border="0" cellpadding="5" cellspacing="5" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
  <tr>
    <td width="50%" align="right">
    <p align="right">Name: </td>
    <td width="50%"><input type="text" name="name" size="20"></td>
  </tr>

  </table>

  <p align="center">

  <input type="submit" value="Add Speciality" name="B1"></p>
</p>
</form>

<br>
<hr>
<br>
<h2 align="center">Remove Speciality</h2>
<form method="POST" action="removespeciality.do">
  <table border="0" cellpadding="2" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber2" height="19">

    <tr>
    <td width="50%" align="right">Speciality:</td>
    <td width="50%"><select size="1" name="speciality">

    <%
	  	Collection col = (Collection) request.getAttribute("specialityList");
		if (col != null) {
		  Iterator specialityIter = col.iterator();
		  while (specialityIter.hasNext()) {
		    Speciality speciality = (Speciality) specialityIter.next();
	%>
       <option value="<%= speciality.getSpecialityID()%>"><%=speciality.getName()%></option>
    <%
		  }
		}
	%>

    </select></td>
  </tr>
  </table>
  <p align="center"><input type="submit" value="Remove Speciality" name="B1"></p>
</form>