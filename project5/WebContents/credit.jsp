<html>
<head>
	<title>Item</title>
		<meta charset="utf-8">
    	<link rel="stylesheet" type="text/css" href="credit.css">
</head>
<body>
	<p id="name">Please Input Your Credit Card Number</p>
	<% String name = (String)request.getAttribute("Name"); %>
	<% String itemid = (String)request.getAttribute("ItemID"); %>
	<% String price = (String)request.getAttribute("Price"); %>
	<% String getServerName=request.getServerName();%>
	<% String getServerPort=""+request.getServerPort();%>
	<% String getContextPath=request.getContextPath();%>
	<% String link="https://"+getServerName+":"+"8443"+getContextPath+"/confirm";%>
	<table>
	<tr><td>ItemID: </td><td><%=itemid %> </td></tr>
	<tr><td>Name: </td><td><%=name %> </td></tr>
	<tr><td>Price: </td><td><%=price %> </td></tr>
	</table>

	
	<form action=<%=link%> method="post">
		<input type="text" name="Number" placeholder="Input Credit Card Number.."/>
		<input type="SUBMIT" value="SUBMIT" />
	</form>


</body>
</html>