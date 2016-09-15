<%@ page import="edu.ucla.cs.cs144.Item" %>
<html>
<head>
	<title>Item</title>
		<meta charset="utf-8">
    	<link rel="stylesheet" type="text/css" href="item.css">
    	<script type="text/javascript" src="https://maps.googleapis.com/maps/api/js?sensor=false"></script>
    	<script type="text/javascript" src="map.js"></script>
</head>
<body>
	<% Item item = (Item)request.getAttribute("Item"); %>
	<% String[] category = item.getCategory(); %>
	<% String[][] bids = item.getBid(); %>
	<% String location= item.getLocation();%>
	

	<script type="text/javascript">
	add = '<%= location %>';
	</script>

<!--Search another Id ############################################################################-->

	<form action="item" method="get">
		<input type="text" name="id" placeholder="Search ItemId.."/>
		<input type="SUBMIT" value="SEARCH" />
	</form>

<!--Show result ############################################################################-->
	<div id="basic-information">
	<p id="itemID">ItemID: <%=item.getItemID() %> </p>
	<p id="name"><%=item.getName() %> </p>
	<table>
	<tr>
	<td>Category:</td>
	<td>
	<%
	for(int i=0;i<category.length;i++) { %> 
		<%=category[i] %>
	<% } %>
	</td>
	</tr>
	<tr><td>Currently: </td><td><%=item.getCurrently() %> </td></tr>
	<tr><td>Buy Price: </td><td><%=item.getBuy_Price() %> </td></tr>
	<tr><td>First Bid: </td><td><%=item.getFirst_Bid() %> </td></tr>
	
	<tr><td>Location: </td><td><%=item.getLocation() %> </td></tr>
	<tr><td>Latitude: </td><td><%=item.getLatitude() %> </td></tr>
	<tr><td>Longitude: </td><td><%=item.getLongitude() %> </td></tr>
	<tr><td>Country: </td><td><%=item.getCountry() %> </td></tr>
	<tr><td>Started: </td><td><%=item.getStarted() %> </td></tr>
	<tr><td>Ends: </td><td><%=item.getEnds() %> </td></tr>
	<tr><td>Seller: </td><td><%=item.getUserID() %> </td></tr>
	<tr><td>Seller Rating: </td><td><%=item.getRating() %> </td></tr>
	<tr><td>Number of Bids: </td><td><%=item.getNumber_of_Bids() %> </td></tr>
	</table>
	</div>
	<div id="map-canvas"></div>
	
	<div id="bids-information">
	<% if(!(item.getBuy_Price().equals(""))){%>
	<form action="credit" method="get">
		<input type="SUBMIT" value="Buy Now!" />
	</form>
	<%}%>

	<p id="description">Description</p> 
	<blockquote> <%=item.getDescription() %> </blockquote>
	
	<%if (bids.length>0){%>
	<p id="bid-history">Bid Histroy</p>
	<table>
  		<tr>
  			<th>No.</th>
   			<th>Bidder Name</th>
    		<th>Bidder Rating</th>
    		<th>Location</th>
    		<th>Country</th>
    		<th>Time</th>
    		<th>Amount</th>
  		</tr>
	<%for(int i=0;i<bids.length;i++) { %>
	<tr>
	<td><%=i+1 %></td>

	<td><%=bids[i][0] %></td>
	<td><%=bids[i][1] %></td>
	<td><%=bids[i][2] %></td>
	<td><%=bids[i][3] %></td>
	<td><%=bids[i][4] %></td>
	<td><%=bids[i][5] %></td>
	</tr>
	<% }%>
	</table>
	<% }%>
	</div>


<!--If no Id matches ############################################################################-->

	<% if(item==null) { %>
		The itemId doesn't exsit!
	<% } %>
</body>
</html>