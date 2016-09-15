package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ucla.cs.cs144.AuctionSearchClient;

public class CreditServlet extends HttpServlet implements Servlet {
       
    public CreditServlet() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        String name=(String)session.getAttribute("Name");
        String itemId=(String)session.getAttribute("ItemID");
        String price=(String)session.getAttribute("Price"); 
    
        request.setAttribute("Name", name);
        request.setAttribute("ItemID", itemId);
        request.setAttribute("Price", price);
        request.getRequestDispatcher("credit.jsp").forward(request, response);
    }
}
