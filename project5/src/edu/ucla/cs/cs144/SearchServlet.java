package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ucla.cs.cs144.AuctionSearchClient;

public class SearchServlet extends HttpServlet implements Servlet {
       
    public SearchServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String q = request.getParameter("q");
		Integer numResultsToSkip = Integer.parseInt(request.getParameter("numResultsToSkip"));
		Integer numResultsToReturn = Integer.parseInt(request.getParameter("numResultsToReturn"));
        SearchResult[] basicResult = AuctionSearchClient.basicSearch(q,numResultsToSkip,numResultsToReturn);
        SearchResult[] basicResultnext = AuctionSearchClient.basicSearch(q,(numResultsToSkip+numResultsToReturn),numResultsToReturn);
        
        request.setAttribute("q", basicResult);
        request.setAttribute("qNext", basicResultnext);
        request.getRequestDispatcher("search.jsp").forward(request, response);
    }
}
