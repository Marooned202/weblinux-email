<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import = "java.util.*,
                   ir.ac.tums.mail.db.entities.User" %>

<%@ page import="java.util.*,
                 ir.ac.tums.mail.db.entities.Unit,
                 ir.ac.tums.mail.db.entities.MailHost,
                 ir.ac.tums.mail.db.entities.Speciality"%>

<logic:present name="formNotFilled">
<h3 align="center"><font color="#FF0000"> Please enter a valid username and password</font></h3>
</logic:present>
<logic:present name="dupError">
<h3 align="center"><font color="#FF0000"> Username exists, Please select another username</font></h3>
</logic:present>

<%
    User user = (User) request.getAttribute("user");
%>

<h2 align="center">Add User swsw</h2>
<form method="POST" action="updateuser.do">

<input type="hidden" name="UID" value="<%=user.getUserID()%>">
<input type="hidden" name="username" value=<%=user.getUsername()%> size="20">

<p>
<table border="0" cellpadding="5" cellspacing="5" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1">
<tr>
<td width="50%" align="right">
<p align="right">Username: </td>
<td width="50%"><%=user.getUsername()%></td>
</tr>
<tr>
<td width="50%" align="right">Name:</td>
<td width="50%"><input type="text" name="name" size="20" value="<%=user.getFirstname()%>"></td>
</tr>
<tr>
<td width="50%" align="right">Last Name: </td>
<td width="50%"><input type="text" name="lastname" size="20" value="<%=user.getLastname()%>"></td>
</tr>
<tr>
<td width="50%" align="right">Type:</td>
<td width="50%"><select size="1" name="type">
<option selected value="0">User</option>
<option value="1">Admin</option>
</select></td>
</tr>
<tr>
<td width="50%" align="right">Old Email: </td>
<td width="50%"><input type="text" name="oldemail" size="20" value="<%=user.getOldemail()%>"></td>
</tr>
<tr>
<td width="50%" align="right">Home Phone:</td>
<td width="50%">
<input type="text" name="homephone" size="20" value="<%=user.getHomephone()%>"></td>
</tr>
<tr>
<td width="50%" align="right">Work Phone:</td>
<td width="50%"><input type="text" name="workphone" size="20" value="<%=user.getWorkphone()%>"></td>
</tr>
<tr>
<td width="50%" align="right">Work Place:</td>
<td width="50%">
<input type="text" name="workplace" size="20" value="<%=user.getWorkplace()%>"></td>
</tr>
<tr>
<td width="50%" align="right">Unit: </td>
<td width="50%"><select size="1" name="unit">

    <%
        Collection col = (Collection) request.getAttribute("unitList");
        if (col != null) {
            Iterator unitIter = col.iterator();
            while (unitIter.hasNext()) {
                Unit unit = (Unit) unitIter.next();

    %>
    <option
       <%
                if (unit.getUnitID() == user.getUnit().getUnitID()) {%>selected
       <%};
       %>
       value="<%= unit.getUnitID()%>"><%= unit.getName()%></option>
    <%
            }
        }
    %>

    </select></td>
    </tr>
    <tr>
    <td width="50%" align="right">Speciality:</td>
    <td width="50%"><select size="1" name="spec">


    <%
        col = (Collection) request.getAttribute("specialityList");
        if (col != null) {
            Iterator specialityIter = col.iterator();
            while (specialityIter.hasNext()) {
                Speciality spec = (Speciality) specialityIter.next();
    %>
    <option
       <%
                if (spec.getSpecialityID() == user.getSpec().getSpecialityID()) {%>selected
       <%};
       %>
       value="<%= spec.getSpecialityID()%>"><%= spec.getName()%></option>
    <%
            }
        }
    %>

    </select></td>
    </tr>
    <tr>
    <td width="50%" align="right">Host:</td>
    <td width="50%"><select size="1" name="host">

    <%
        col = (Collection) request.getAttribute("hostList");
        if (col != null) {
            Iterator hostIter = col.iterator();
            while (hostIter.hasNext()) {
                MailHost host = (MailHost) hostIter.next();
    %>
    <option
       <%
                if (host.getHostID() == user.getHost().getHostID()) {%>selected
       <%};
       %>
       value="<%= host.getHostID()%>"><%= host.getHostID()%></option>
    <%
            }
        }
    %>

    </select></td>
    </tr>
    <tr>
    <td width="50%" align="right">Password:</td>
    <td width="50%">
    <input type="password" name="password" size="20" value="<%=user.getPassword()%>"></td>
    </tr>
    
    <tr>
    <td width="50%" align="right">
    <input type="radio" value="expdate" name="exp">Expiration Date:</td>
    <td width="50%">&nbsp;</td>
  </tr>
  <tr>
    <td width="50%" align="right">Year</td>
    <td width="50%"><select size="1" name="year">
    <option selected>1382</option>
    <option>1383</option>
    <option>1384</option>
    <option>1385</option>
    <option>1386</option>
    <option>1387</option>
    <option>1388</option>
    <option>1389</option>
    <option>1390</option>
    </select></td>
  </tr>
  <tr>
    <td width="50%" align="right">Month</td>
    <td width="50%"><select size="1" name="month">
    <option selected>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
    <option>6</option>
    <option>7</option>
    <option>8</option>
    <option>9</option>
    <option>10</option>
    <option>11</option>
    <option>12</option>
    </select></td>
  </tr>
  <tr>
    <td width="50%" align="right">Day</td>
    <td width="50%"><select size="1" name="day">
    <option selected>1</option>
    <option>2</option>
    <option>3</option>
    <option>4</option>
    <option>5</option>
    <option>6</option>
    <option>7</option>
    <option>8</option>
    <option>9</option>
    <option>10</option>
    <option>11</option>
    <option>12</option>
    <option>13</option>
    <option>14</option>
    <option>15</option>
    <option>16</option>
    <option>17</option>
    <option>18</option>
    <option>19</option>
    <option>20</option>
    <option>21</option>
    <option>22</option>
    <option>23</option>
    <option>24</option>
    <option>25</option>
    <option>26</option>
    <option>27</option>
    <option>28</option>
    <option>29</option>
    <option>30</option>
    <option>31</option>
    </select></td>
  </tr>
  <tr>
    <td width="50%" align="right">
    <input type="radio" checked name="exp" value="expdays">Expire Days:</td>
    <td width="50%"><input type="text" name="expdays" value="<%
        long ex = user.getExpdate().getTime();
        long now = (new java.util.Date()).getTime();
        int diffdays = (int) ((ex-now)/(1000*60*60*24));
        out.print(diffdays);
     %>" size="20"></td>
  </tr>
    
</table>

<p align="center">

<input type="submit" value="Update User" name="B1"></p>
</p>
<p align="center">
<a href = "updateremoveuserlist.do"> Return to update/remove user list </a></p>
</form>