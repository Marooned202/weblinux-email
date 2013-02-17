<%@ taglib uri="/tags/struts-logic" prefix="logic" %>
<%@ taglib uri='/WEB-INF/struts-template.tld' prefix='template' %>
<%@ taglib uri="/tags/struts-bean" prefix="bean" %>
<%@ page import = "java.util.*" %>

<logic:present name="formNotFilled">
	<h3 align="center"><font color="#FF0000"> Please enter a valid username and password</font></h3>
</logic:present>
<logic:present name="authError">
	<h3 align="center"><font color="#FF0000"> Username or password wrong</font></h3>
</logic:present>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<!-- saved from url=(0036)http://accounts.tums.ac.ir/logon.jsp -->
<HTML><HEAD><TITLE>Login Page</TITLE>
<META http-equiv=Content-Language content=en-us>
<META content="Microsoft FrontPage 5.0" name=GENERATOR>
<META content=FrontPage.Editor.Document name=ProgId>
<META http-equiv=Content-Type content="text/html; charset=windows-1252">
<STYLE>.class1 {
	FONT-SIZE: 10pt; FONT-FAMILY: Arial
}
.FOOTER {
	FONT-SIZE: 8pt; FONT-FAMILY: Arial, Helvetica, sans-serif
}
</STYLE>
</HEAD>
<BODY bgColor=#fef4e3 leftMargin=0 topMargin=0>
<TABLE style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 
cellPadding=0 width="100%" border=0>
  <TBODY>
  <TR>
    <TD width="100%" height=100>&nbsp;</TD></TR>
  <TR>
    <TD align=middle width="100%">
      <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 
      cellPadding=0 width=360 border=0>
        <TBODY>
        <TR>
          <TD width=150>
          <IMG height=63 src="design/Login%20Page_files/pic28.jpg" 
            width=150 border=0></TD>
          <TD width=210>
          <IMG height=63 src="design/Login%20Page_files/pic27b.jpg" 
            width=210 border=0></TD></TR>
        <TR>
          <TD vAlign=top align=middle width=150 
          background="design/Login%20Page_files/pic30.jpg">
            <TABLE style="BORDER-COLLAPSE: collapse" borderColor=#111111 
            cellSpacing=0 cellPadding=0 width=125 border=0>
              <FORM method="POST" action="login.do">
              <TBODY>
              <TR>
                <TD width="100%"><FONT face=Arial color=#ffffff 
                  size=4>username</FONT></TD></TR>
              <TR>
                <TD width="100%"><INPUT 
                  style="BORDER-RIGHT: #0e52a5 1px solid; PADDING-RIGHT: 4px; BORDER-TOP: #0e52a5 1px solid; PADDING-LEFT: 4px; PADDING-BOTTOM: 1px; BORDER-LEFT: #0e52a5 1px solid; WIDTH: 125px; PADDING-TOP: 1px; BORDER-BOTTOM: #0e52a5 1px solid; HEIGHT: 20px" 
                  size=15 name=username></TD></TR>
              <TR>
                <TD width="100%">&nbsp;</TD></TR>
              <TR>
                <TD width="100%"><FONT face=Arial color=#ffffff 
                  size=4>password</FONT></TD></TR>
              <TR>
                <TD width="100%"><INPUT 
                  style="BORDER-RIGHT: #0e52a5 1px solid; PADDING-RIGHT: 4px; BORDER-TOP: #0e52a5 1px solid; PADDING-LEFT: 4px; PADDING-BOTTOM: 1px; BORDER-LEFT: #0e52a5 1px solid; WIDTH: 125px; PADDING-TOP: 1px; BORDER-BOTTOM: #0e52a5 1px solid; HEIGHT: 20px" 
                  type=password size=15 name=password></TD></TR>
              <TR>
                <TD width="100%">&nbsp;</TD></TR>
              <TR>
                <TD width="100%"><INPUT style="BORDER-RIGHT: #1165cc 1px solid; PADDING-RIGHT: 4px; BORDER-TOP: #1165cc 1px solid; PADDING-LEFT: 4px; PADDING-BOTTOM: 1px; BORDER-LEFT: #1165cc 1px solid; WIDTH: 55px; COLOR: #1165cc; PADDING-TOP: 1px; BORDER-BOTTOM: #1165cc 1px solid; HEIGHT: 20px; BACKGROUND-COLOR: #ffffff" type=submit value=Login name=B1> 
<INPUT style="BORDER-RIGHT: #1165cc 1px solid; PADDING-RIGHT: 4px; BORDER-TOP: #1165cc 1px solid; PADDING-LEFT: 4px; PADDING-BOTTOM: 1px; BORDER-LEFT: #1165cc 1px solid; WIDTH: 55px; COLOR: #1165cc; PADDING-TOP: 1px; BORDER-BOTTOM: #1165cc 1px solid; HEIGHT: 20px; BACKGROUND-COLOR: #ffffff" type=reset value=Clear name=B2></FORM></TD></TR></TBODY></TABLE></TD>
          <TD vAlign=top width=210 background="design/Login%20Page_files/pic29.jpg" 
          height=210>
            <P class=class1 style="MARGIN: 3px 15px 10px 10px">
            <span lang="en-gb"><b>TUMS Email Administrator</b></span>
            <span lang="en-gb">is an administration software for managing email 
            accounts</span></P>
            <P class=class1 style="MARGIN: 3px 15px 10px 10px"><BR>Please Enter your username and password in special 
            area to use this software.</P></TD></TR>
        <TR>
          <TD align=middle colSpan=2>
            <P class=FOOTER>© 2004 TUMS Company. 
            All rights 
reserved.</P></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></BODY></HTML>