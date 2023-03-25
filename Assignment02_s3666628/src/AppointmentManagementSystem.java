import java.util.Scanner;
import java.util.StringTokenizer;

public class AppointmentManagementSystem
{

   // declare Appointment array and object count
   private static Appointment[] appointments = new Appointment[20];
   private static int appointmentCount = 0;

   // shared Scanner which can be used by all helper methods below
   private static Scanner input = new Scanner(System.in);

   public static void main(String[] args)
   {

      String selection;

      do
      {

         // display menu options
         System.out.println("   ***** Appointment Management System Menu *****");
         System.out.println();
         System.out.println("A. Add New Appointment");
         System.out.println("B. List Appointments");
         System.out.println("C. View Appointment Summary");
         System.out.println("D. Check In Patient");
         System.out.println("E. Call Patient");
         System.out.println("F. Record Consultation");
         System.out.println("X. Exit the program");
         System.out.println();

         // prompt user to enter selection
         System.out.print("Enter selection: ");
         selection = input.nextLine();

         System.out.println();

         // validate selection input length
         if (selection.length() != 1)
         {
            System.out.println("Error - invalid selection!");
         }
         else
         {

            // process user's selection
            switch (selection.toUpperCase())
            {
               case "A":
                  addNewAppointment();
                  break;

               case "B":
                  displayAllAppointments();
                  break;

               case "C":
                  viewAppointmentSummary();
                  break;

               case "D":
                  checkInPatient();
                  break;

               case "E":
                  callPatient();
                  break;

               case "F":
                  recordConsultation();
                  break;

               case "X":
                  System.out.println("Exiting the program...");
                  break;

               default:
                  System.out.println("Error - invalid selection!");
            }
         }
         System.out.println();

      } while (!selection.equalsIgnoreCase("X"));
   }

   /*
    * void addNewAppointment() helper method which implements the functionality for
    * adding a new Appointment to this AppointmentManagementSystem.
    * 
    * (implements Stage 2 Requirement A)
    */
   private static void addNewAppointment()
   {
      System.out.println("*** Add New Appointment Feature ***");
      System.out.println();

      // code for Stage 2 Requirement A) should go in here
      // ...
      // STAGE 2 - PART A
      String appointmentID, appointmentTime, patientName, bulkBillingString;
      boolean appointmentValidity;

      System.out.print("Please enter appointment ID: ");
      appointmentID = input.nextLine();

      appointmentValidity = ClinicDetails.isValidAppointmentID(appointmentID);
      System.out.println(appointmentValidity);

      if (appointmentValidity == false)
      {
         System.out.println();
         System.out.print("Error - this is not a valid appointment ID");
         System.out.println();
      }
      else
      {
         // now we have a valid appointment ID we can get the rest of the information
         System.out.println();
         System.out.print("This is a valid appointment ID: " + appointmentID);
         System.out.println();
         System.out.print("Please enter Appointment Time: ");
         appointmentTime = input.nextLine();
         System.out.print("Please enter Patient Name: ");
         patientName = input.nextLine();
         System.out.print("Please enter Bulk Billing Status: ");
         bulkBillingString = input.nextLine();
         boolean bulkBilling = Boolean.parseBoolean(bulkBillingString); // this was
                                                                        // implemented
                                                                        // as
                                                                        // input.nextboolean
                                                                        // was
                                                                        // causing
                                                                        // problem

         // we now have all the information we need to construct a new appointment
         // object
         // so we create a new object and add it the appointments array
         appointments[appointmentCount] =
                  new Appointment(appointmentID, appointmentTime, patientName,
                                  bulkBilling);
         // increment the appointments counter
         appointmentCount++;

         System.out.println();
         System.out.print("Printing out what has been captured: ");
         System.out.println();

         for (int i = 0; i < appointmentCount; i++)
         {
            appointments[i].printDetails();
            System.out.println();
         }

      }

   }

   /*
    * void displayAllAppointments() helper method which implements the functionality
    * for displaying all Appointment details stored in this
    * AppointmentManagementSystem.
    * 
    * (implements Stage 2 Requirement B)
    */
   private static void displayAllAppointments()
   {
      System.out.println("*** Display All Appointments Feature ***");
      System.out.println();

      // code for Stage 2 Requirement B) should go in here
      // ...

      System.out.println();
      // System.out.print("Printing out what has been captured: ");
      System.out.println();

      for (int i = 0; i < appointmentCount; i++)
      {
         appointments[i].printDetails();
         System.out.println();
      }

   }

   /*
    * TIP: You may want to consider implementing a helper method at this point which
    * handles locating and returning the Application object with the specified
    * appointment ID from the applications array, as this will avoid having to repeat
    * your search code for every feature which incorporates a search below.
    * 
    * Note that this is not a strict requirement and repeating search code in each of
    * the features below that require it is also perfectly acceptable.
    */
   // helper method to find appoint id
   public static Appointment findAppointId(String appID)

   {
      Appointment app = null;

      for (int i = 0; i < appointmentCount; i++)
      {
         if (appointments[i].getAppoinmentID().equals(appID))

         {
            System.out.println("Found a match");

            return appointments[i];
         }
      }
      return app;
   }

   /*
    * void viewAppointmentSummary() helper method which implements the functionality
    * for viewing the information for a specified Appointment in this
    * AppointmentManagementSystem.
    * 
    * (implements Stage 2 Requirement C)
    */
   private static void viewAppointmentSummary()
   {
      System.out.println("*** View Appointment Summary Feature ***");
      System.out.println();

      // code for Stage 2 Requirement C) should go in here
      // ...

      String appointmentID;
      System.out
               .println("Find Appointment Summary - Please enter valid Appoint ID: ");
      appointmentID = input.nextLine();
      Appointment result = findAppointId(appointmentID); // call helper method to
                                                         // check whether it is valid
                                                         // appointment

      if (result == null)
      {
         System.out
                  .println("Error -   Could not find a match for the specified appoint id " +
                           appointmentID);
      }

      else
      {
         System.out.println("here is the result ");
         result.printSummary();
      }
   }

   /*
    * void checkInPatient() helper method which implements the functionality for
    * checking in a patient who has a pending Appointment in this
    * AppointmentManagementSystem.
    * 
    * (implements Stage 2 Requirement D)
    */
   private static void checkInPatient()
   {

      System.out.println("*** Check In Patient Feature ***");
      System.out.println();

      // code for Stage 2 Requirement D) should go in here
      // ...
      String appointmentID;
      // String checkInResult;
      System.out.println("Please enter check-in Appoint ID: ");
      appointmentID = input.nextLine();
      Appointment result = findAppointId(appointmentID); // added line 1

      if (result == null)
      {
         System.out
                  .println("Error -   Could not find a match for the specified appoint id " +
                           appointmentID);
      }

      else
      {

         if (result.checkIn() == false)
         {
            System.out
                     .println("Error - the patient has been already been checked in for this appointment");
         }

         else
         {
            System.out.println("Success - Patient " + result.getPatientName() +
                               " has been checked In for appointment ID: " +
                               result.getAppoinmentID());
         }

      }

   }

   /*
    * void callPatient() helper method which implements the functionality for calling
    * a patient to the consultation room for their Appointment in this
    * AppointmentManagementSystem.
    * 
    * (implements Stage 2 Requirement E)
    */
   private static void callPatient()
   {
      System.out.println("*** Call Patient Feature ***");
      System.out.println();

      // code for Stage 2 Requirement E) should go in here
      // ...

      String appointmentID;
      String roomNumber;
      boolean resultCallPatient;

      System.out
               .println("Please enter Appoint ID for appointment which patient is being called for: ");
      appointmentID = input.nextLine();
      Appointment result = findAppointId(appointmentID); // call the helper method to
                                                         // find appointment

      if (result == null)
      {
         System.out
                  .println("Error -   Could not find a match for the specified appoint id " +
                           appointmentID);
      }

      else

      {
         System.out
                  .println("Please enter Room Number the patient being called to: ");
         roomNumber = input.nextLine();
         resultCallPatient = result.callPatient(roomNumber);

         if (resultCallPatient == false)

         {
            System.out
                     .println("Error! - the patient cannot be called for this appointment: ");
         }

         else

         {
            System.out.println("Success: - the patient " + result.getPatientName() +
                               " has been called for the appointment " +
                               result.getAppoinmentID());
         }
      }

   }

   /*
    * void recordConsultation() helper method which implements the functionality for
    * recording details of consultation delivered to a patient in this
    * AppointmentManagementSystem.
    * 
    * (implements Stage 2 Requirement F)
    */
   private static void recordConsultation()
   {

      System.out.println("*** Record Consultation Feature ***");
      System.out.println();
      // code for Stage 2 Requirement F) should go in here
      // ...
      String appointmentID;
      String consultationNotes;
      String allNotes = null;
      int checkConsultation;

      System.out
               .println("Please enter Appoint ID for appointment which patient is being called for: ");
      appointmentID = input.nextLine();
      // call the helper method to find the appointment
      Appointment result = findAppointId(appointmentID); // call the helper method to
                                                         // find appointment
      // if no appointment found - get out of method
      if (result == null)
      {
         System.out
                  .println("Error -   Could not find a match for the specified appoint id " +
                           appointmentID);
      }

      else
      {
         System.out.println("Please enter consultation notes for the patient: ");
         System.out.println("Empty line to finish the input of Consultation Notes");

         consultationNotes = input.nextLine();
         System.out.println("consultation notes length = " +
                            consultationNotes.length());

         // loop to get consultation notes. Loop ends when consultationNotes =0
         // (return + return)
         while (consultationNotes.length() > 0)
         {

            consultationNotes = input.nextLine();
            allNotes += consultationNotes + "\n";

         }
         // for testing purposes
         // System.out.println(allNotes);
         // String testNotes = "test notes test ajajkas";

         // call the public method recordConsultation method - pass in the notes
         checkConsultation = result.recordConsultation(allNotes);

         // note = recordConsultation returns an integer
         if (checkConsultation == -1)
         {
            System.out
                     .println("Error! Cannot update the tracking history for this appointment");
         }
         else
         {
            System.out
                     .println("Success. The Consultation Notes have been recorded. The Consultation Fee is: " +
                              checkConsultation);
         }

      }

   }

}
