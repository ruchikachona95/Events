/**		This class models the Work class that extends to the Events class.
 * 		Author: Ruchika Chona
 * 
 * 		Data fields: time: OurTime - protected; object of OurTime 
 * 					 date: OurDate - protected; object of OurDate
 * 					 description: String - protected
 * 		Methods:	 default constructor
 * 					 initial constructor
 * 					 getOurDate: OurDate
 * 					 getOurTime: OurTime
 * 					 toString: String - displays values of date to String
 * 					 readDateAndTime(Scanner, char) - reads date and time from keyboard or text file.
 * 					 readNameAndShift(Scanner, char) - reads values of date and time from the keyboard or file. 
 **/

import java.util.*;

public class Work extends Events {
	
	private String name;
	private int shift;
	
	public Work() {
		super();
		name = new String();
		shift = 0;
		
	}
	
	public Work(OurDate date, OurTime time, String description, String name, int shift){
		super(date, time, description);
		this.name = name;
		this.shift = shift;
	}
	
public OurDate getOurDate(){
		
		return date;
		
	}
	
	public OurTime getOurTime(){
		return time;
	}
	
	public String toString(){
		return date.toString() + " " + time.toString() + " " + description + " at " + name + " for " + shift + " hours";
	}
	
	public void readNameAndShift(Scanner in, char d){
        if(d == 'k'){
            System.out.println("Enter workplace: ");
            this.name = in.next();
            System.out.println("Enter number of hours: ");
            this.shift = in.nextInt();
        }
        else if(d == 'f'){
            this.name = in.next();
            this.shift = in.nextInt();
        }
    }
	

}
