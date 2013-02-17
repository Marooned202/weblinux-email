<%@ page import="java.util.Collection,
                 java.util.Iterator,
                 ir.ac.tums.mail.db.entities.Unit"%>&nbsp;
<form method="POST" action="updateremoveuserlist.do">
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#008000" width="600" id="AutoNumber1" height="112">
    <tr>
      <td width="43" height="28" bgcolor="#C0C0C0">
      </td>
      <td width="249" height="19" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="276" height="19" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="43" height="82" rowspan="3" bgcolor="#C0C0C0">&nbsp;</td>
      <td width="249" height="26" bgcolor="#C0C0C0">
      <p align="right"><input type="checkbox" name="sb1" value="1"></td>
      <td width="276" height="26" bgcolor="#C0C0C0">Username:
      <input type="text" name="tt1" size="20"></td>
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="249" height="26" bgcolor="#C0C0C0">
      <p align="right"><input type="checkbox" name="sb2" value="2"></td>
      <td width="276" height="26" bgcolor="#C0C0C0">First Name:
      <input type="text" name="tt2" size="20"></td>
      <td width="173" height="26" bgcolor="#C0C0C0">&nbsp;</td>
    </tr>
    <tr>
      <td width="249" height="26" bgcolor="#C0C0C0">
      <p align="right"><input type="checkbox" name="sb3" value="3"></td>
      <td width="276" height="26" bgcolor="#C0C0C0">Last Name:
      <input type="text" name="tt3" size="20"></td>
      <td width="173" height="26" bgcolor="#C0C0C0">
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
    </table>



  <p align="center">
  <input type="submit" value="Submit" name="B1"></p>
</form>
<p>&nbsp;</p>