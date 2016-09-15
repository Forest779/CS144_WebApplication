<html>
<head>
	<title>Search</title>
	<script type="text/javascript" src="autosuggest.js"></script>
	<link rel="stylesheet" type="text/css" href="autosuggest.css" />
	<link rel="stylesheet" type="text/css" href="search.css" />   
	<script type="text/javascript">
    	window.onload = function () {
			var begin = new AutoSuggestControl(document.getElementById("pbox"));  
      	}
	</script> 
</head>
<body>
<!--Defination of varibles ############################################################################-->

	<%@ page import="edu.ucla.cs.cs144.SearchResult" %>
	<% int skip = Integer.parseInt(request.getParameter("numResultsToSkip")); %>
	<% String key = request.getParameter("q"); %>
	<% SearchResult[] r = (SearchResult[])request.getAttribute("q"); %>
	<% SearchResult[] rNext = (SearchResult[])request.getAttribute("qNext"); %>
	<% int returnNum = 10; %>
	<% int skipNum = 10; %>

<!--Search another keyword ############################################################################-->

	<form action="search" method="get">
		<input type="text" id="pbox" name="q" autocomplete="off" placeholder="Search Keywords.."/>
		<input type="hidden" name="numResultsToSkip" value="0" />
		<input type="hidden" name="numResultsToReturn" value=
			<% out.println("\""+returnNum+"\""); %>
		/>
		<input type="submit" value="SEARCH"/>
	</form>
	<br/>
	<br/>
	<br/>

<!--Show result ############################################################################-->
<!--If nothing match the keyword #####################################################################-->

	<% if(r.length==0){ %>
		<p id="sorry">Sorry,we don't find any item with given keywords.</p>
	<% } else{ %>
	<table>
	<tr>
	<th> No.</th>
	<th> ItemID</th>
	<th> Name</th>
	</tr>
	<% for(int i=0;i<r.length;i++) { 
		String link="item?id="+r[i].getItemId(); %>
	   	<tr>
	   	<td><%=skip+i+1%></td>
		<td><a href= <%=link %>><%=r[i].getItemId()%></a></td>
		<td><%=r[i].getName()%></td>
		</tr>
	<%}%>
	</table>
	 <%}%>



<!--Next Button ############################################################################-->

	<% if(rNext.length!=0 ) { %>
		<form action="search" method="get">
			<input type="hidden" name="q" value=
				<% out.println("\""+key+"\""); %>
			/>
			<input type="hidden" name="numResultsToSkip" value=
				<% out.println((skip+skipNum)); %>
			/>
			<input type="hidden" name="numResultsToReturn" value=10 />
			<input type="submit" name="Next"/ value="Next">
		</form>
	<% } %>

<!--Previous Button ############################################################################-->

	<% if(skip>0) { %>
		<form action="search" method="get">
			<input type="hidden" name="q" value=
				<% out.println("\""+key+"\""); %>
			/>
			<input type="hidden" name="numResultsToSkip" value=
				<% out.println((skip-skipNum)); %>
			/>
			<input type="hidden" name="numResultsToReturn" value=10 />
			<input type="submit" name="Previous"/ value="Previous">
		</form>
	<% } %>
</body>
</html>