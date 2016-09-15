package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import edu.ucla.cs.cs144.AuctionSearchClient;

public class ConfirmServlet extends HttpServlet implements Servlet {
       
    public ConfirmServlet() {}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        HttpSession session = request.getSession(true);
        String name=(String)session.getAttribute("Name");
        String itemId=(String)session.getAttribute("ItemID");
        String price=(String)session.getAttribute("Price");
        String number = (String)request.getParameter("Number"); 
    
        request.setAttribute("Name", name);
        request.setAttribute("ItemID", itemId);
        request.setAttribute("Price", price);
        request.setAttribute("Number", number);
        request.getRequestDispatcher("confirm.jsp").forward(request, response);
    }
}
