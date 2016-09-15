package edu.ucla.cs.cs144;

/* 
 * This is item object
 */

 public class Item {
 	private String ItemID=null;
 	private String Name=null;
 	private String[] Category=null;
 	private String Currently=null;
 	private String Buy_Price=null;
 	private String First_Bid=null;
 	private String Number_of_Bids;
 	private String[][] Bids=null;
 	private String Location=null;
 	private String Latitude=null;
 	private String Longitude=null;
 	private String Country=null;
 	private String Started=null;
 	private	String Ends=null;
 	private String UserID=null;
 	private String Rating=null;
 	private String Description=null;
	
 	public Item() {}
	
 	public String getItemID()
 	{
 		return ItemID;
 	}
     
 	public String getName()
 	{
 		return Name;
 	}
     
 	public String[] getCategory()
 	{
 		return Category;
 	}
     
    public String getCategory(int i)
    {
        return Category[i];
    }
    
 	public String getCurrently()
 	{
 		return Currently;
 	}
     
 	public String getBuy_Price()
 	{
 		return Buy_Price;
 	}
     
 	public String getFirst_Bid()
 	{
 		return First_Bid;
 	}
     
 	public String getNumber_of_Bids()
 	{
 		return Number_of_Bids;
 	}
     
 	public String[][] getBid()
 	{
 		return Bids;
 	}
     
    public String[] getBid(int i)
    {
         return Bids[i];
    }
     
 	public String getLocation()
 	{
 		return Location;
 	}
     
 	public String getLatitude()
 	{
 		return Latitude;
 	}
     
 	public String getLongitude()
 	{
 		return Longitude;
 	}
     
 	public String getCountry()
 	{
 		return Country;
 	}
     
 	public String getStarted()
 	{
 		return Started;
 	}
     
 	public String getEnds()
 	{
 		return Ends;
 	}
     
 	public String getUserID()
 	{
 		return UserID;
 	}
     
 	public String getRating()
 	{
 		return Rating;
 	}
     
 	public String getDescription()
 	{
 		return Description;
 	}

    public void setItemID(String s)
    {
        ItemID=s;
    }
     
    public void setName(String s)
    {
        Name=s;
    }
     
    public void setCategory(String[] s)
    {
        Category=s;
    }
     
//    public void setCategory(int i,String s)
//    {
//        Category[i]=s;
//    }
    
    public void setCurrently(String s)
    {
        Currently=s;
    }
     
    public void setBuy_Price(String s)
    {
        Buy_Price=s;
    }
     
    public void setFirst_Bid(String s)
    {
        First_Bid=s;
    }
     
    public void setNumber_of_Bids(String s)
    {
        Number_of_Bids=s;
    }
     
    public void setBid(String[][] s)
    {
        Bids=s;
    }
     
//    public void setBid(int i,String[] s)
//    {
//        Bid[i]=s;
//    }
     
    public void setLocation(String s)
    {
        Location=s;
    }
     
    public void setLatitude(String s)
    {
        Latitude=s;
    }
     
    public void setLongitude(String s)
    {
        Longitude=s;
    }
     
    public void setCountry(String s)
    {
        Country=s;
    }
     
    public void setStarted(String s)
    {
        Started=s;
    }
     
    public void setEnds(String s)
    {
        Ends=s;
    }
     
    public void setUserID(String s)
    {
        UserID=s;
    }
     
    public void setRating(String s)
    {
        Rating=s;
    }
     
    public void setDescription(String s)
    {
        Description=s;
    }
 }
