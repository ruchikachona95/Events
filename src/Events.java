/**		This class models the abstract Events class.
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
 * 					 readValues(Scanner, char) - reads values of date and time from the keyboard or file. 
 **/

import java.util.*;

public abstract class Events {
	
protected OurTime time;
protected OurDate date;
protected String description;

public Events(){
	
	date = new OurDate();
	time = new OurTime();
	description = new String();
	
}
	
	public Events(OurDate date, OurTime time, String description){
		this.date = date;
		this.time = time;
		this.description = description;
	
	}
	
	public abstract OurDate getOurDate();
	public abstract OurTime getOurTime();
	public abstract String toString();
	
	
	//reads date and time
	public void readDateAndTime(Scanner in, char d){ 
        date.readDate(in, d);
        time.readTime(in, d);
    }
	
	//read description
	public void readValues(Scanner in, char d){
        if(d == 'k'){
            date.readDate(in, d);
            time.readTime(in, d);
            System.out.println("Enter description: ");
            description = in.next();
        }
        else if(d == 'f'){
            date.readDate(in, d);
            time.readTime(in, d);
            description = in.next();
        }
    }
}
