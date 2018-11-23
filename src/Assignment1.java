/** 	This class models the main.
 * 		Author: Ruchika Chona
 * 	
 * 		Data fields: input: Scanner - is an InputStream
 * 					 events: ArrayList<Events> - contains an Events ArrayList.
 * 					 main(String[]):void - contains both option menu and opt menu
 * 					 option: - int values 1-7 for options in the menu
 * 					 input: Scanner - is an InputStream
 * 					 option1: boolean - set as false    
 * 					 option5: boolean - set as false
 * 					 exception handling: do- while loop, try- catch loop
 * 		Methods:	 eventExists(OurDate, OurTime): boolean - checking to see if the event exists
 * 					 atIndex(Events): int - comparing the 1st and 2nd date.
 * 					 addEvent(Events): boolean - adding a new event to the list
 * 					 deleteEvent(Events): void - deletes events.
 * 					 openFile(): void - opens file named Events.
 * 					 readFile(): void - reads from file named Events.
 * 					 closeFile(): void - closes file named Events.
 * 					 sortEvents(): void - sort Events.
 *
 */


import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.InputMismatchException;

public class Assignment1 {

	private static Scanner input = new Scanner(System.in);
	private static ArrayList<Events> events = new ArrayList<Events>();

	public static void main(String[] args) {
		int option = 0;
		Scanner input = new Scanner(System.in);
		boolean option1 = false;
		boolean option5 = false;

		do {

			try{

				System.out.println("1 ... Add an event from keyboard"); 
				System.out.println("2 ... Display events of a day"); 
				System.out.println("3 ... Display events of a week"); 
				System.out.println("4 ... Delete an event"); 
				System.out.println("5 ... Add events from a file"); 
				System.out.println("6 ... Display all events"); 
				System.out.println("7 ... Quit"); 

				option = input.nextInt();

				if(option ==1){
					int opt = 0;

					System.out.println("1 ... Meeting Event");
					System.out.println("2 ... School Event"); 
					System.out.println("3 ... Work Event"); 
					System.out.println("4 ... Gym Event"); 
					System.out.println("5 ... Social Event"); 

					System.out.println("Enter your event type: ");
					opt = input.nextInt();

					if(opt == 1){
						Meeting m = new Meeting();
						m.readValues(input, 'k');
						m.readLocation(input, 'k');

						option1 = addEvent(m);
					}

					else if(opt == 3){
						Work w = new Work();
						w.readValues(input, 'k');
						w.readNameAndShift(input, 'k');

						option1 = addEvent(w);
					}

					else if(opt == 2 || opt == 4 || opt == 5){
						Other_Events o = new Other_Events();
						o.readValues(input, 'k');

						option1 = addEvent(o);

					}

					else System.out.println("Invalid entry. Please try again.");

				}

				else if (option == 2){

					if(option1 || option5){
						OurDate date = new OurDate();

						System.out.println("Enter date to display:");
						date.readDate(input, 'k');

						sortEvents();
						System.out.println("Your events on " + date.toString() + " are:");
						for(int i = 0; i < events.size(); i++){
							if(date.isEqual(events.get(i).getOurDate()))
								System.out.println("     " + events.get(i).toString());
						}
					}
					else
						System.out.println("Please select option 1 or option  5 first");
				}

				else if (option == 3){

					if(option1 || option5){
						OurDate date1 = new OurDate();
						OurDate date2;

						System.out.println("Enter start date to display:");
						date1.readDate(input, 'k');
						date2 = new OurDate(date1);

						for(int i = 0; i < 6; i++){
							date2.addOne();
						}

						sortEvents();
						System.out.println("Your activities for the week starting from " + date1.toString() + " until " + date2.toString() + " are:");
						for(int i = 0; i < events.size(); i++){
							if((date2.isGreater(events.get(i).getOurDate()) || date2.isEqual(events.get(i).getOurDate())) && (events.get(i).getOurDate().isGreater(date1) || events.get(i).getOurDate().isEqual(date1)))
								System.out.println("     " + events.get(i).toString());
						}
					}
					else
						System.out.println("Please select option 1 or option  5 first");

				}

				else if (option == 4){

					if(option1 || option5){
						int eventType = 0;
						
						System.out.println("Enter the type, date, and time of the event to be deleted");
						System.out.println("Enter type (1 - Meeting, 2 - School, 3 - Work, 4 - Gym, 5 - Social):");
						eventType = input.nextInt();
						
						if(eventType == 1){
                            Meeting m = new Meeting();
                            m.readDateAndTime(input, 'k');
                            deleteEvent(m);
                        }

                        else if(eventType == 3){
                            Work w = new Work();
                            w.readDateAndTime(input, 'k');
                            deleteEvent(w);
                        }

                        else if(eventType == 2 || eventType == 4 || eventType == 5){
                            Other_Events o = new Other_Events();
                            o.readDateAndTime(input, 'k');
                            deleteEvent(o);
                        }
						else
							System.out.println("Invalid event... please try again. \nMain Menu:");
					}
					else
						System.out.println("Please select option 1 or option  5 first");

				}

				else if (option == 5){

					if(!option5){
                        openFile();
                        readFile();
                        closeFile();
                        option5 = true;
                    }
                    else{
                        System.out.println("Option 5 has already been selected.");
                    }

				}

				else if (option == 6){

					if(option1 || option5){
						sortEvents();
						System.out.println("LIST OF EVENTS");
						System.out.println("**************");

						for(int i = 0; i < events.size(); i++)
							System.out.println("     " + events.get(i).toString());
					}
					else
						System.out.println("Please select option 1 or option 5 first");

				}
				else if (option == 7){
					continue;

				}

			}catch(InputMismatchException inputMismatch){
				System.out.println("Error... Please try again. \nMain menu:");
				input.next();

			}

		}while (option != 7);
		System.out.println("Goodbye, Have a nice day!");
	}

	public static boolean eventExists(OurDate date, OurTime time){
        for(int i = 0; i < events.size(); i++){
            if(date.isEqual(events.get(i).getOurDate()) && time.isEqual(events.get(i).getOurTime()))
                return true;
        }
        return false;
    } 

    public static int atIndex(OurDate date, OurTime time){
        for(int i = 0; i < events.size(); i++){
            if(date.isEqual(events.get(i).getOurDate()) && time.isEqual(events.get(i).getOurTime()))
                return i;
        }
        return 0;
    } 

    public static boolean addEvent(Events e){
        if(eventExists(e.getOurDate(), e.getOurTime()))
            System.out.println("An event already exists for this date & time..... cannot enter");
        else{
            events.add(e);
            return true;
        }
        return false;
    } 

    public static void deleteEvent(Events e){
        if(eventExists(e.getOurDate(), e.getOurTime())){
            events.remove(atIndex(e.getOurDate(), e.getOurTime()));
            System.out.println("Event deleted");
        }
        else
            System.out.println("Error... event does not exist");

    }

	public static void openFile() {
		// TODO Auto-generated method stub
		try{
			input = new Scanner(Paths.get("Events.txt"));
		}catch (IOException e)
		{
			System.err.println("Error opening file. Terminating.");
		}
	}

	public static void readFile() {
		int eventType;

		try{
			while(input.hasNext())
			{
				eventType = input.nextInt();
				
				if(eventType == 1){
                    Meeting m = new Meeting();
                    m.readValues(input, 'f');
                    m.readLocation(input, 'f');
                    events.add(m);
                }

                else if(eventType == 3){
                    Work w = new Work();
                    w.readValues(input, 'f');
                    w.readNameAndShift(input, 'f');
                    events.add(w);
                }

                else if(eventType == 2 || eventType == 4 || eventType == 5){
                    Other_Events o = new Other_Events();
                    o.readValues(input, 'f');
                    events.add(o);
                }
			}

		}
		catch (NoSuchElementException elementEx){
			System.err.println("File improperly formed. Terminating.");
		}
		catch (IllegalStateException stateException){
			System.err.println("Error reading from file. Terminating.");
		}

	}

	public static void closeFile() {
		// TODO Auto-generated method stub
		if(input != null)
			input.close();
	}

	public static void sortEvents(){ 
		Events temp;
		OurDate date1, date2;
		OurTime time1, time2;

		for(int i = 1; i < events.size(); i++){
			for(int j = i; j > 0; j--){
				date1 = events.get(j-1).getOurDate();
				date2 = events.get(j).getOurDate();
				if(date1.isGreater(date2)){
					temp = events.get(j);
					events.set(j, events.get(j-1));
					events.set(j-1, temp);
				}
			}
		}

		for(int i = 1; i < events.size(); i++){
			for(int j = i; j > 0; j--){
				date1 = events.get(j-1).getOurDate();
				date2 = events.get(j).getOurDate();
				time1 = events.get(j-1).getOurTime();
				time2 = events.get(j).getOurTime();
				if(time1.isGreater(time2) && date1.isEqual(date2)){
					temp = events.get(j);
					events.set(j, events.get(j-1));
					events.set(j-1, temp);
				}
			}
		}
	}

}
