<%@ page import="java.util.Collection,
                 java.util.Iterator,
                 ir.ac.tums.mail.db.entities.Unit"%>&nbsp;<form method="POST" action="searchresults.do">
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#008000" width="600" id="AutoNumber1" height="112">
    <tr>
      <td width="43" height="44" bgcolor="#C0C0C0">
      <input type="radio" name="stype" value="1" style="float: right" checked></td>
      <td width="249" height="44" bgcolor="#C0C0C0">&nbsp;All expired user accounts</td>
      <td width="276" height="44" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="173" height="44" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="19" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="19" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="276" height="19" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="173" height="19" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="28" bgcolor="#C0C0C0">
      <input type="radio" name="stype" value="2" style="float: right"></td>
      <td width="249" height="19" bgcolor="#C0C0C0">&nbsp;Search By:</td>
      <td width="276" height="19" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="82" rowspan="3" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="26" bgcolor="#999999">
      <p align="right"><input type="checkbox" name="sb1" value="1"></td>
      <td width="276" height="26" bgcolor="#999999">Username:
      <input type="text" name="tt1" size="20"></td>
      <td width="173" height="26" bgcolor="#999999">&nbsp;</td>
    </tr>
    <tr>
      <td width="249" height="26" bgcolor="#C0C0C0">
      <p align="right"><input type="checkbox" name="sb2" value="2"></td>
      <td width="276" height="26" bgcolor="#C0C0C0">First Name:
      <input type="text" name="tt2" size="20"></td>
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="249" height="26" bgcolor="#999999">
      <p align="right"><input type="checkbox" name="sb3" value="3"></td>
      <td width="276" height="26" bgcolor="#999999">Last Name:
      <input type="text" name="tt3" size="20"></td>
      <td width="173" height="26" bgcolor="#999999">
      </td>
    </tr>
    <tr>
      <td width="43" height="29" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="26" bgcolor="#C0C0C0">
      <p align="right"><input type="checkbox" name="sb4" value="4"></td>
      <td width="276" height="26" bgcolor="#C0C0C0">Old Email:
      <input type="text" name="tt4" size="20"></td>
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="11" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="1" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="276" height="1" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="173" height="1" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="29" bgcolor="#C0C0C0">
      <input type="radio" name="stype" value="3" style="float: right"></td>
      <td width="249" height="19" bgcolor="#C0C0C0">&nbsp;Search by department or 
      unit</td>
      <td width="276" height="19" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="173" height="29" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="28" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="12" bgcolor="#999999">
      <p align="right">&nbsp;</td>
      <td width="449" height="28" bgcolor="#999999" colspan="2">
      <select size="1" name="unit">

    <%
	  	Collection col = (Collection) request.getAttribute("unitList");
		if (col != null) {
		  Iterator unitIter = col.iterator();
		  while (unitIter.hasNext()) {
		    Unit unit = (Unit) unitIter.next();
	%>
       <option value="<%= unit.getUnitID()%>"><%= unit.getName()%></option>
    <%
		  }
		}
	%>

    </select></select></td>
      </tr>
    <tr>
      <td width="43" height="11" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="1" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="276" height="1" bgcolor="#FFFFFF">&nbsp;</td>
      <td width="173" height="1" bgcolor="#FFFFFF">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="11" bgcolor="#C0C0C0">
      <input type="radio" name="stype" value="4" style="float: right"></td>
      <td width="249" height="1" bgcolor="#C0C0C0">&nbsp;All users that will expire 
      until:</td>
      <td width="276" height="1" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="173" height="1" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="11" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="26" bgcolor="#999999">
      <p align="right">&nbsp;</td>
      <td width="276" height="26" bgcolor="#999999">Year:
      <select size="1" name="year">
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
      <td width="173" height="26" bgcolor="#999999">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="11" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="26" bgcolor="#C0C0C0">
      <p align="right">&nbsp;</td>
      <td width="276" height="26" bgcolor="#C0C0C0">Month:
      <select size="1" name="month">
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
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="11" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="26" bgcolor="#999999">
      <p align="right">&nbsp;</td>
      <td width="276" height="26" bgcolor="#999999">Day:
      <select size="1" name="day">
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
      <td width="173" height="26" bgcolor="#999999">&nbsp;</td>
    </tr>

    <tr>
      <td width="43" height="19" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="698" height="1" bgcolor="#C0C0C0" colspan="3">
      <p align="center">&nbsp;</td>
    </tr>
  </table>



  <p align="center">
  <input type="submit" value="Submit" name="B1"></p>
</form>
<p>&nbsp;</p>