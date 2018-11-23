/*Social, School, Gym Events*/
/**   This class models the Social, School and Gym Events.
 *	  This class extends to the Event class.
 *	  Author:  Ruchika Chona
 *   
 *	  Methods:  default constructor
 *              initial constructors
 *				getOurDate: OurDate
 *				getOurTime: OurTime
 * * 			toString: String - displays values of date to String

 *              
 */

public class Other_Events extends Events {

	public Other_Events() {
		super();
	}
	
	public Other_Events(OurDate date, OurTime time, String description){
		
		super(date, time, description);
	}
	
public OurDate getOurDate(){
		
		return date;
		
	}
	
	public OurTime getOurTime(){
		return time;
	}
	
	public String toString(){
		return date.toString() + " " + time.toString() + " " + description;
	}
	

}
