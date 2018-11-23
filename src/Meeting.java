/**		This class models the Meeting class that extends to the Events class.
 * 		Author: Ruchika Chona
 * 
 * 		Data fields: location: String - displays location as a String
 * 		Methods:	 default constructor
 * 					 initial constructor
 * 					 getOurDate: OurDate
 * 					 getOurTime: OurTime
 * 					 toString: String - displays values of date to String
 * 					 readLocation(Scanner, char) - reads location from keyboard or text file.
 **/


import java.util.*;

public class Meeting extends Events{
	
	private String location;

	public Meeting(){
		
		super();
		location = new String();
		
	}
	
	public Meeting(OurDate date, OurTime time, String description, String location){
		super(date, time, description);
		this.location = location;
		
		
	}
	
	public OurDate getOurDate(){
		
		return date;
		
	}
	
	public OurTime getOurTime(){
		return time;
	}
	
	public String toString(){
		return date.toString() + " " + time.toString() + " " + description + " at " + location;
	}
	
	public void readLocation(Scanner in, char d){ //exception handling is needed here now
        if(d == 'k'){
            System.out.println("Enter location: ");
            this.location = in.next();
        }
        else if(d == 'f'){
            this.location = in.next();
        }
    }
	
	
	

}
