<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>

<%@ page import="java.util.*,
                 ir.ac.tums.mail.db.entities.User,
                 ir.ac.tums.mail.db.DBInfo"%>


<table border="1" cellpadding="0" cellspacing="0" width="100%" id="AutoNumber1" height="37" style="border-left-style: none; border-right: .75pt solid navy; border-top: .75pt solid navy; border-bottom: .75pt solid navy; background-color: white" fpstyle="23,011111100">
  <tr>
    <td width="7%" height="17" style="font-weight: bold; color: white; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: navy" align="center">
    #</td>
    <td width="25%" height="17" style="font-weight: bold; color: white; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: navy" align="center">
    Username</td>
    <td width="28%" height="17" style="font-weight: bold; color: white; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: navy" align="center">
    Name</td>
    <td width="20%" height="17" style="font-weight: bold; color: white; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: navy" align="center">
    Update</td>
    <td width="20%" height="17" style="font-weight: bold; color: white; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: navy" align="center">
    remove</td>
  </tr>


    <%
        int i,SP,stype;
        int day=0, month=0, year=0, unit=0;
        String added = new String ("");

        stype = Integer.parseInt((String)request.getParameter("stype"));
        if (request.getParameter("SP") == null) {
            i = 0;
            SP = 1;
        }
        else {
            SP = Integer.parseInt (request.getParameter("SP"));
            i = SP-1;
        }
        if (stype == 4)
        {
            day = ((Integer) request.getAttribute("day")).intValue();
            month = ((Integer) request.getAttribute("month")).intValue();
            year = ((Integer) request.getAttribute("year")).intValue();
        } else if (stype == 3)
        {
            unit = ((Integer) request.getAttribute("unit")).intValue();     
        } else if (stype == 2)
        {
            if (request.getAttribute("sb1")!=null)
            {
                added += "&sb1=1&";
                String tt1 = (String) request.getAttribute("tt1");
                added += "tt1=";
                added += tt1;
            }
            if (request.getAttribute("sb2")!=null)
            {
                added += "&sb2=2&";
                String tt2 = (String) request.getAttribute("tt2");
                added += "tt2=";
                added += tt2;
            }
            if (request.getAttribute("sb3")!=null)
            {
                added += "&sb3=3&";
                String tt3 = (String) request.getAttribute("tt3");
                added += "tt3=";
                added += tt3;
            }
            if (request.getAttribute("sb4")!=null)
            {
                added += "&sb4=4&";
                String tt4 = (String) request.getAttribute("tt4");
                added += "tt4=";
                added += tt4;
            }
        }
	  	Collection col = (Collection) request.getAttribute("userList");
		if (col != null) {
		  Iterator userIter = col.iterator();
		  while (userIter.hasNext()) {
		    i++;
		    User user = (User) userIter.next();
	%>

  <tr>
    <td width="7%" height="19" style="font-weight: normal; color: black; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: white" align="center">
    <p align="center">  <%=i%>  </td>
    <td width="25%" height="19" style="font-weight: normal; color: black; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: white" align="center">
    <p align="center">  <%=user.getUsername()%>  </td>
    <td width="28%" height="19" style="font-weight: normal; color: black; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: white" align="center">
    <p align="center"><%=user.getFirstname()%> &nbsp;  <%=user.getLastname()%> </td>
    <td width="20%" height="19" style="font-weight: normal; color: black; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: white" align="center">
    <p align="center"><a href="updateuserform.do?UID=<%=user.getUserID()%>">update</a></td>
    <td width="20%" height="19" style="font-weight: normal; color: black; border-left: .75pt solid navy; border-right-style: none; border-top-style: none; border-bottom: .75pt solid navy; background-color: white" align="center">
    <p align="center"><a href="removeuser.do?UID=<%=user.getUserID()%>">
    remove</a></td>
  </tr>

    <%
		  }
		}
	%>
</table>


<center><table border="0" width="60%" cellspacing="0" cellpadding="0">
    <tr>
      <td width="20%"></td>

      <td width="29%">
	  <logic:present name="hasPrev">
        <p align="right"><font face="Tahoma" size="2"><a href="searchresults.do?SP=<%=SP-DBInfo.PAGE_SIZE%>&stype=<%=stype%>&day=<%=day%>&month=<%=month%>&year=<%=year%>&unit=<%=unit%><%=added%>" class="two">&lt;&lt;
        Prev</a></font>
	  </logic:present>
	  </td>
      <td width="5%"></td>
      <td width="26%">
	  <logic:present name="hasNext">

        <p align="left"><font face="Tahoma" size="2"><a href="searchresults.do?SP=<%=SP+DBInfo.PAGE_SIZE%>&stype=<%=stype%>&day=<%=day%>&month=<%=month%>&year=<%=year%>&unit=<%=unit%><%=added%>" class="two">Next
        &gt;&gt;</a></font>
	  </logic:present>
	  </td>
      <td width="20%"></td>
    </tr>
  </table></center>