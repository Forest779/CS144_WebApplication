<%@ page import="java.util.*" %>
<html>
<head>
	<title>Item</title>
		<meta charset="utf-8">
    	<link rel="stylesheet" type="text/css" href="credit.css">
</head>
<body>
	<p id="name">Thank you. Your order has been placed.</p>
	<% String name = (String)request.getAttribute("Name"); %>
	<% String itemid = (String)request.getAttribute("ItemID"); %>
	<% String price = (String)request.getAttribute("Price"); %>
	<% String number = (String)request.getAttribute("Number"); %>

	<% String getServerName=request.getServerName();%>
	<% String getServerPort=""+request.getServerPort();%>
	<% String getContextPath=request.getContextPath();%>
	<% String link="http://"+getServerName+":"+"1448"+getContextPath+"/keywordSearch.html";%>

	<% Date date = new Date();%>
	<table>
	<tr><td>ItemID: </td><td><%=itemid %> </td></tr>
	<tr><td>Name: </td><td><%=name %> </td></tr>
	<tr><td>Price: </td><td><%=price %> </td></tr>
	<tr><td>Credit Card: </td><td><%=number %> </td></tr>
	<tr><td>Time: </td><td><%=date.toString() %> </td></tr>
	</table>

	<p id="name">Continue and search other items!</p>
	<a id="a" href=<%=link%>>
	CONTINUE
	</a>


</body>
</html>