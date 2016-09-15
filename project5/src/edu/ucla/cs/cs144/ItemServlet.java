package edu.ucla.cs.cs144;

import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import java.text.ParseException;
import java.io.*;
import java.text.*;
import java.util.*;
import edu.ucla.cs.cs144.Item;
import edu.ucla.cs.cs144.AuctionSearchClient;

public class ItemServlet extends HttpServlet implements Servlet {
       
    public ItemServlet() {}
    
    static Element[] getElementsByTagNameNR(Element e, String tagName) {
        Vector< Element > elements = new Vector< Element >();
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
            {
                elements.add( (Element)child );
            }
            child = child.getNextSibling();
        }
        Element[] result = new Element[elements.size()];
        elements.copyInto(result);
        return result;
    }
    
    /* Returns the first subelement of e matching the given tagName, or
     * null if one does not exist. NR means Non-Recursive.
     */
    static Element getElementByTagNameNR(Element e, String tagName) {
        Node child = e.getFirstChild();
        while (child != null) {
            if (child instanceof Element && child.getNodeName().equals(tagName))
                return (Element) child;
            child = child.getNextSibling();
        }
        return null;
    }
    /* Returns the amount (in XXXXX.xx format) denoted by a money-string
     * like $3,453.23. Returns the input if the input is an empty string.
     */
    static String strip(String money) {
        if (money.equals(""))
            return money;
        else {
            double am = 0.0;
            NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
            try { am = nf.parse(money).doubleValue(); }
            catch (ParseException e) {
                System.out.println("This method should work for all " +
                                   "money values you find in our data.");
                System.exit(20);
            }
            nf.setGroupingUsed(false);
            return nf.format(am).substring(1);
        }
    }
    /* Returns the text associated with the given element (which must have
     * type #PCDATA) as child, or "" if it contains no text.
     */
    static String getElementText(Element e) {
        if (e.getChildNodes().getLength() == 1) {
            Text elementText = (Text) e.getFirstChild();
            return elementText.getNodeValue();
        }
        else
            return "";
    }
    
    /* Returns the text (#PCDATA) associated with the first subelement X
     * of e with the given tagName. If no such X exists or X contains no
     * text, "" is returned. NR means Non-Recursive.
     */
    static String getElementTextByTagNameNR(Element e, String tagName) {
        Element elem = getElementByTagNameNR(e, tagName);
        if (elem != null)
            return getElementText(elem);
        else
            return "";
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        /* Non-recursive (NR) version of Node.getElementsByTagName(...)
         */
        HttpSession session = request.getSession(true);
        
        PrintWriter out=response.getWriter();
        String itemId = request.getParameter("id");
        String xml = AuctionSearchClient.getXMLDataForItemId(itemId);
        Item item=new Item();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc=null;
        try{
        builder = factory.newDocumentBuilder();
        InputSource is = new InputSource(new StringReader(xml));
        doc=builder.parse(is);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if(doc==null)
        {
            request.getRequestDispatcher("itemnotfound.html").forward(request, response);
            return;
        }
        Element item_element=doc.getDocumentElement();
        String itemid=item_element.getAttribute("ItemID");
        item.setItemID(itemid);
        Element[] categorylist=getElementsByTagNameNR(item_element,"Category");
        String[] category=new String[categorylist.length];
        for(int i=0;i<category.length;i++)
        {
            category[i]=getElementText(categorylist[i]);
        }
        item.setCategory(category);
        String name=getElementTextByTagNameNR(item_element,"Name");
        item.setName(name);
        String currently=getElementTextByTagNameNR(item_element,"Currently");
        item.setCurrently(currently);
        String buy_price=getElementTextByTagNameNR(item_element,"Buy_Price");
        item.setBuy_Price(buy_price);
        String first_bid=getElementTextByTagNameNR(item_element,"First_Bid");
        item.setFirst_Bid(first_bid);
        String number_of_bids=getElementTextByTagNameNR(item_element,"Number_of_Bids");
        item.setNumber_of_Bids(number_of_bids);
        Element bids=getElementByTagNameNR(item_element,"Bids");
        Element[] bidlist=getElementsByTagNameNR(bids,"Bid");
        String[][] bid=new String[bidlist.length][6];
        for(int i=0;i<bidlist.length;i++)
        {
            Element bidder=getElementByTagNameNR(bidlist[bidlist.length-1-i],"Bidder");

            String bidder_userid=bidder.getAttribute("UserID");
            bid[i][0]=bidder_userid;
            String bidder_rating=bidder.getAttribute("Rating");
            bid[i][1]=bidder_rating;
            String bidderlocation=getElementTextByTagNameNR(bidder,"Location");
            bid[i][2]=bidderlocation;
            String biddercountry=getElementTextByTagNameNR(bidder,"Country");
            bid[i][3]=biddercountry;
            String time=getElementTextByTagNameNR(bidlist[bidlist.length-1-i],"Time");
            bid[i][4]=time;
 
            String amount=getElementTextByTagNameNR(bidlist[bidlist.length-1-i],"Amount");
            bid[i][5]=amount;
        }
        item.setBid(bid);
        Element location=getElementByTagNameNR(item_element,"Location");
        String itemlocation=getElementTextByTagNameNR(item_element,"Location");
        item.setLocation(itemlocation);
        String latitude=null;
        String longitude=null;
        if(location!=null)
        {
            latitude=location.getAttribute("Latitude");
            longitude=location.getAttribute("Longitude");
        }
        item.setLatitude(latitude);
        item.setLongitude(longitude);
        String itemcountry=getElementTextByTagNameNR(item_element,"Country");
        item.setCountry(itemcountry);
        String started=getElementTextByTagNameNR(item_element,"Started");
        item.setStarted(started);
        String ends=getElementTextByTagNameNR(item_element,"Ends");
        item.setEnds(ends);
        Element seller=getElementByTagNameNR(item_element,"Seller");
        
        String seller_userid=seller.getAttribute("UserID");
        item.setUserID(seller_userid);
        String seller_rating=seller.getAttribute("Rating");
        item.setRating(seller_rating);
        String description=getElementTextByTagNameNR(item_element,"Description");
        item.setDescription(description);
        request.setAttribute("Item", item);
        session.setAttribute("ItemID",itemid);
        session.setAttribute("Name",name);
        if(!buy_price.equals(""))session.setAttribute("Price",buy_price);
        request.getRequestDispatcher("item.jsp").forward(request, response);
    }
}
