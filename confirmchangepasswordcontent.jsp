<h2 align="center"> Password Changed Succesfully </h2>
<p align="center">

<% int ut = ((Integer) request.getAttribute("usertype")).intValue();

    if (ut == 2) {
        %>
        <a href = "headadminhome.jsp">
        <%
    }
    if (ut == 1) {
        %>
        <a href = "adminhome.do">
        <%
    } else {
        %>
         <a href = "userhome.do">
        <%
    }
        %>

 Return to main page </a></p>