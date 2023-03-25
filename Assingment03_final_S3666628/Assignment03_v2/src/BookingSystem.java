/*
 * class BookingSystem
 * 
 * This code is a partially complete application for the booking system which 
 * implements a fully functional menu for CPT121 Assignment 3 in SP1 2018.
 * 
 * You are expected to work off this sample implementation of the BookingSystem
 * class - you *DO NOT* need to implement your own version of this program menu.
 * 
 * Note that the required program features as described in Stages 2 and 4 of the
 * Assignment 3 specification should be implemented in the corresponding helper 
 * methods further * down in the class.
 * 
 */

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BookingSystem
{
   // Declare the array of Tour references in which the Tour and
   // ScheduledTour objects that the user adds will be stored in and
   // the corresponding tour count.
   //
   // Note that this array and the corresponding counter will be
   // accessible both from within the main method as well as from
   // within any helper methods you may decide to implement.

   // array to store the attraction and group activity objects

   private static final Attraction[] attractionList = new Attraction[100];
   private static int attractionCount = 0;

   // Declaring a shared scanner just in case you choose to
   // implement some helper methods in your application class
   // that need access to one.

   private static final Scanner sc = new Scanner(System.in);

   public static void main(String[] args) throws PassException, IOException
   {
      // load attraction data from file
      // load group activity data from file - had to create 2 methods for these as
      // could not get them working in one method
      loadAttractionData();
      loadAGroupData();

      // variables required to process user's menu selection
      String input;
      char selection = '\0';

      // keep repeating the menu until the user chooses to exit
      do
      {
         // display menu options
         System.out.println("******* Pass Sales System Menu *******");
         System.out.println("");
         System.out.println("A - Add New Attraction");
         System.out.println("B - Display Attraction Summary");
         System.out.println("C - Sell Passes");
         System.out.println("D - Refund Passes");
         System.out.println("E - Add New Group Activity");
         System.out.println("F - Update Maximum Group Size");
         System.out.println("X - Exit Program");
         System.out.println();

         // prompt the user to enter their selection
         System.out.print("Enter your selection: ");
         input = sc.nextLine();

         System.out.println();

         // check to see if the user failed to enter exactly one character
         // for their menu selection

         if (input.length() != 1)
         {
            System.out.println("Error - selection must be a single character!");

         }
         else
         {
            // extract the user's menu selection as a char value and
            // convert it to upper case so that the menu becomes
            // case-insensitive

            selection = Character.toUpperCase(input.charAt(0));

            // process the user's selection
            switch (selection)
            {
               case 'A':

                  // call addNewAttraction() helper method
                  addNewAttraction();

                  break;

               case 'B':

                  // call displayAttractionSummary() helper method
                  displayAttractionSummary();
                  break;

               case 'C':

                  // call sellPasses() helper method
                  sellPasses();
                  break;

               case 'D':

                  // call refundPasses() helper method
                  refundPasses();
                  break;

               case 'E':

                  // call addNewScheduledTour() helper method
                  addNewGroupActivity();
                  break;

               case 'F':

                  // call updateMaxTourGroupSize() helper method
                  updateMaximumGroupSize();
                  break;

               case 'X':
                  // added call to savedata method to save the attraction and group
                  // activity objects to text file
                  System.out.println("Saving Attractions...");
                  saveData(); // call save data
                  System.out.println("Booking system shutting down - goodbye...");
                  break;

               default:

                  // default case - handles invalid selections
                  System.out.println("Error - invalid selection!");

            }
         }
         System.out.println();

      } while (selection != 'X');

   }

   /*
    * addNewAttraction()
    * 
    * This method will add a new attraction by capturing user input. The attractionID
    * must be unique so this method will also ensure that the attraction id has not
    * already been allocated.
    */
   private static final void addNewAttraction()
   {
      // Implement your code for Stage 2 Requirement A) here
      // ...

      String attractionID, attractDesc, StringPassFee;
      int passFee;

      System.out.println("Add New Attraction Feature Selected!");
      System.out.println("Please enter details of the new attraction below");
      System.out.print("Please enter Attraction ID: ");

      // call helper method to check whether attraction id has already been used

      attractionID = sc.nextLine().toUpperCase();// force the attraction ID to be
                                                 // upper case as we don't want case
                                                 // sensitive IDs
      boolean dupResult = checkAttrDuplicate(attractionID); // call the check
                                                            // duplicate helper
                                                            // method which returns
                                                            // boolean

      if (dupResult == true) // if duplicate then let user know
         System.out.println("Error! - the Attraction ID: " + attractionID +
                            " has already been used");

      else
      // if not duplicate we can carry on and get rest of user input
      {
         System.out.print("Please enter Attraction Description: ");
         attractDesc = sc.nextLine();

         System.out.print("Please enter Pass Fee: ");
         StringPassFee = sc.nextLine();
         passFee = Integer.parseInt(StringPassFee);
         // now create an Attraction object by calling the constructor and add to
         // array
         attractionList[attractionCount] =
                  new Attraction(attractionID, attractDesc, passFee);

         attractionCount++; // update the counter so that next record get inserted at
                            // correct index location in array

      }
   }

   /*
    * displayAttractionSummary() This method prints a summary of all Attractions it
    * iterates over the attraction array and prints out each attraction to the screen
    * using FOR loop
    */
   private static final void displayAttractionSummary()
   {
      // Implement your code for Stage 2 Requirement B) here
      // ...

      System.out.println("Display Attraction Summary Feature Selected!");

      // this loops through all of the attractions and prints them out
      for (int i = 0; i < attractionCount; i++)
      {
         attractionList[i].printDetails();
         System.out.println();
      }

   }

   // helper method to find Attraction ID

   /*
    * findAttractionId() this method will iterate over the attractionList array and
    * let caller know whether the passed in AttID has been found if Attraction has
    * been found the attraction object will be returned to the caller if not found
    * will return null
    */

   public static Attraction findAttractionId(String attID)

   {
      Attraction app = null;

      for (int i = 0; i < attractionCount; i++)
      {
         if (attractionList[i].getAttractionID().equals(attID))

         {
            System.out.println("Found a matching Attraction!");

            return attractionList[i];
         }
      }
      return app;
   }

   // helper method to check for duplicate attraction IDs

   /*
    * checkAttrDuplicate() this method will iterate over the attractionList array and
    * let caller know whether the passed in AttID has already been used if it is has
    * will return true if not found will return false
    */

   public static boolean checkAttrDuplicate(String attID)

   {

      for (int i = 0; i < attractionCount; i++)
      {
         if (attractionList[i].getAttractionID().equals(attID))

         {
            System.out.println("Error! - Attraction ID: " + attID +
                               " is a duplicate Attraction ID!");

            return true;
         }
      }
      return false;
   }

   /*
    * sellPasses() Sells passes based on user input user has to enter valid
    * attraction id - must already exist if matching attraction id is located then
    * user enters number of passes to be sold
    */
   private static final void sellPasses()
   {

      // Implement your code for Stage 2 Requirement C) here
      // ...

      String attractionID, stringPassesSold;

      // get user input for
      System.out.println("Sell Passes Feature Selected!");
      System.out.println("Please enter Attraction ID you wish to sell passes for: ");

      attractionID = sc.nextLine().toUpperCase(); // make all IDS upper case
      // call the helper method to find the right attraction id to update
      Attraction result = findAttractionId(attractionID);

      if (result == null) // check whether attraction id exists
      {
         System.out
                  .println("Error -   Could not find a match for the specified Attraction ID: " +
                           attractionID);

      }
      // Note - this was added to prevent the user from attempting to sell more
      // passes than the max
      // group size
      else if (result.getPassesSold() == ((GroupActivity) result).getMaxGroupSize())
      {
         System.out
                  .println("All Passes have been sold - you need to increase group size to sell more passes");
      }

      // At this point we know that 1 - matching ID has been found, 2 - not all
      // passes have been sold
      // From here we just need to prevent user from attempting to sell 0 passes
      else
      {
         System.out.println("A match has been found for Atrraction ID: " +
                            attractionID);

         boolean continueInput = true; // do while loop stops when this is false

         do
         {
            try
            // get user input - if they enter number > 0 processing continues, if not
            // caught as exception
            {

               System.out.println("Please enter the number of passes to be sold: ");
               stringPassesSold = sc.nextLine();
               int passesSold = Integer.parseInt(stringPassesSold);
               System.out.println("Sell Passes Try Block");
               result.sellPasses(passesSold);

               continueInput = false; // this stops the loop running
            }
            catch (PassException e)
            {
               // error message is retrieved from method being called
               System.out.println(e.getMessage());
            }
         } while (continueInput == true);

      }

   }

   /*
    * refundPasses() method allows the user to refund passes for specified (by user
    * input) attraction id
    */
   private static final void refundPasses()
   {
      // Implement your code for Stage 2 Requirement D) here
      // ...

      String attractionID, stringNumPassesRefund;
      int numPassesRefund;
      // get user input
      System.out.println("Refund Passes Feature Selected!");
      System.out
               .println("Please enter Attraction ID you wish to REFUND passes for: ");
      attractionID = sc.nextLine().toUpperCase(); // force to upper case - makes it
                                                  // consistent

      Attraction result = findAttractionId(attractionID); // check whether attraction
                                                          // id exists
      // if it does not exist then processing refund stops

      if (result == null)
      {
         System.out
                  .println("Error -   Could not find a match for the specified appoint id " +
                           attractionID);
      }

      else
      // we have valid attraction id - now we can carry on processing refund
      {
         // get user input for number of passes to refund
         System.out
                  .println("Please enter Number of Passes you wish to REFUND for: " +
                           attractionID);
         stringNumPassesRefund = sc.nextLine();
         numPassesRefund = Integer.parseInt(stringNumPassesRefund);

         int resultRefundPasses = result.refundPasses(numPassesRefund); // call the
                                                                        // refund
                                                                        // passes
                                                                        // method and
                                                                        // save what
                                                                        // it returns

         // now decided what to do with information returned from method callto
         // refundPasses
         if (resultRefundPasses == -1) // means refund has been rejected - end
                                       // processing
         {
            System.out.println("Error - refund request was for refunding " +
                               numPassesRefund + " passes for Attraction ID: " +
                               attractionID + " was rejected");

         }

         else
         // all good - refund can proceed - let user know
         {
            System.out.println("Success -" + numPassesRefund +
                               " passes have been refunded for Attraction ID: " +
                               attractionID);
         }

      }

   }

   /*
    * addNewGroupActivity() allows the user to add a new group activity this is a
    * sub-class of the Attraction object so does share some of the instance variables
    * from the super class the additional GroupActivity instance variables are
    * obtained as user input then the constructor is called and the object is added
    * to the attractionList array
    */
   private static void addNewGroupActivity()
   {

      // Implement your code for Stage 4 Requirement A) here
      // ...

      System.out.println("Add New Group Activity Feature Selected!");

      // variables to capture user input
      String grroupAttractionID, groupAttractDesc, stringGroupPassFee, activityDate, stringMaxGroupSize, groupTourGuide;
      int groupPassFee, maxGroupSize;

      System.out.print("Please enter Attraction ID: ");
      grroupAttractionID = sc.nextLine().toUpperCase();

      // check whether Attraction ID has been used already by calling the helper
      // method
      // if we get a true - then we don't carry on with the addNewGroupActivity
      // method
      boolean dupResult = checkAttrDuplicate(grroupAttractionID);
      if (dupResult == true)
         System.out.println("Error! - the Attraction ID: " + grroupAttractionID +
                            " has already been used");
      // this means it's a not a duplicate group Attraction ID so we can carry on
      // with the processing
      else
      {

         System.out.print("Please enter Attraction Description: ");
         groupAttractDesc = sc.nextLine();
         System.out.print("Please enter Pass Fee: ");
         stringGroupPassFee = sc.nextLine();
         groupPassFee = Integer.parseInt(stringGroupPassFee);// convert string to
                                                             // integer
         // now ask for additional input for the sub class (Group Activity) instance
         // variables
         // we need this information to pass to the Group Activity constructor

         System.out.print("Please enter Acitivy Date: ");
         activityDate = sc.nextLine();
         System.out.print("Please enter Maximum Group Size: ");
         stringMaxGroupSize = sc.nextLine();
         maxGroupSize = Integer.parseInt(stringMaxGroupSize);// convert string to
                                                             // integer
         System.out.print("Please enter Tour Guide: ");
         groupTourGuide = sc.nextLine();
         groupTourGuide = groupTourGuide + ", ";

         // call the constructor for the GroupActivity subclass and insert new object
         // into the next free point in the array

         attractionList[attractionCount] =
                  new GroupActivity(grroupAttractionID, groupAttractDesc,
                                    groupPassFee, activityDate, maxGroupSize,
                                    groupTourGuide);
         attractionCount++; // increment the counter so next created object goes into
                            // next index point in array
      }

   }

   /*
    * updateMaximumTourGroupSize() Allows the user to update maximum tour group size
    * this needs to be updated if user wants to sell more passes than the current
    * group size
    */
   private static void updateMaximumGroupSize()
   {
      // Implement your code for Stage 4 Requirement B) here
      // ...
      // capture the user input using these variables
      String groupAttractID, stringNewNumberPlaces, tourGuide;
      boolean resultIncreaseGrp;
      int newNumberPlaces, resultNewMax;

      System.out.println("Update Maximum Group Size Feature Selected!");

      System.out
               .print("Please enter Attraction ID of the attraction for which you want to update the maximum group size: ");
      groupAttractID = sc.nextLine().toUpperCase();
      // check whether the attraction exists if it doesn't then end of processing

      Attraction result = findAttractionId(groupAttractID);

      if (result == null)
      {
         System.out
                  .println("Error -   Could not find a match for the specified appoint id " +
                           groupAttractID);
      }

      else
      // we have a valid attraction (Group Activity) ID so carry on getting user
      // input
      {
         if (result instanceof GroupActivity) // we need to check that we are
                                              // operating on the right type of
                                              // object
         {
            System.out
                     .print("Please enter number of additional places to be added: ");
            stringNewNumberPlaces = sc.nextLine();
            newNumberPlaces = Integer.parseInt(stringNewNumberPlaces);// convert
                                                                      // string to
                                                                      // integer
            System.out
                     .print("Please enter new tour guide name or press Enter if you do not want to add more tour guides: ");
            tourGuide = sc.nextLine();
            // cast result to type GroupActivity so that we can invoke the
            // increaseGroupSize method
            resultIncreaseGrp = ((GroupActivity) result)
                     .increaseGroupSize(newNumberPlaces, tourGuide); // call the
                                                                     // method to
                                                                     // increase
                                                                     // group size
            resultNewMax = ((GroupActivity) result).getMaxGroupSize(); // capture the
                                                                       // result of
                                                                       // the method
                                                                       // call
            if (resultIncreaseGrp == true) // call has been successful - let user
                                           // know
            {

               System.out
                        .print("Success! - Maximum Group Size has been increased to: " +
                               resultNewMax);
            }

            else
            // call has failed - let user know
            {

               System.out
                        .print("Error! - Maximum Group Size could not be increased");

            }

         }
         else if (result instanceof Attraction) // user is trying to perform
                                                // operation on wrong object type
         {
            System.out.println("Error - this is Attraction not Group Activity");
            System.out
                     .println("Error - Maximum Group Size can only be changed for Group Activities");
         }

         else
         {

            System.out.println("Uknown Object Type");
         }
      }

   }

   /*
    * saveData() This method saves the objects in the attractionList array and writes
    * them to a text file we can then read from the text file when app starts (see
    * calls to load methods in the main method)
    */
   private static void saveData()

   {

      // try / catch block for saving data
      try
      {
         // open file for writing
         PrintWriter pw = new PrintWriter("attribute.txt"); // if file doesn't exist
                                                            // it should be created

         // iterate over array to see what data is stored
         // use the attractionCount as this tells us how many records are in the
         // array
         for (int i = 0; i < attractionCount; i++)
         {
            // need to handle Group Activities different - so use instance of to
            // determine which type of object we are dealing with
            if (attractionList[i] instanceof GroupActivity)
            // write each piece Group Activity information to file

            {
               System.out.println("Writing Group Activity Data");
               pw.println("GroupActivity");
               pw.println(attractionList[i].getAttractionID());
               pw.println(attractionList[i].getDescription());
               pw.println(attractionList[i].getPassFee());
               pw.println(((GroupActivity) attractionList[i]).getActivityDate());
               pw.println(((GroupActivity) attractionList[i]).getMaxGroupSize());
               pw.println(((GroupActivity) attractionList[i]).getTourGuides());
            }

            else
            // if not instance of Group Activity object is Attraction as only 2 types
            // of object.
            // write each piece standard Attraction information to file

            {
               System.out.println("Writing Standard Attraction Data");
               pw.println("Attraction");
               pw.println(attractionList[i].getAttractionID());
               pw.println(attractionList[i].getDescription());
               pw.println(attractionList[i].getPassFee());
            }

         }

         pw.close(); // close the file once finished processing
         System.out.println("Closing File"); // let user know file is closed

      }
      catch (FileNotFoundException e)
      {

         // The FileNotFoundException is a little misleading as it is thrown when
         // the file cannot be opened for writing - by default a PrintWriter will
         // create a new file when it doesn't already exist!

         e.printStackTrace();
      }

   }

   /*
    * loadAttractionData() This method loads the Attraction objects from a text file
    * and adds them to the array by calling the constructor NOTE - had to load
    * Attraction and Group Activity in 2 seperate method as could not get them
    * working in the same method
    */

   private static void loadAttractionData()
   {

      System.out
               .println("Loading Attraction Accounts in system in previous execution run: ");

      //
      // //variables for Attraction
      String AttractionID = null, description, stringPassFee1;
      int passFee;
      Attraction att;

      try
      {
         Scanner fileScanner = new Scanner(new FileReader("attribute.txt"));
         //
         // // repeat while there are still lines to be read from the file
         while (fileScanner.hasNextLine())
         {
            // // read details for next object from file
            //
            if (fileScanner.nextLine().equals("Attraction"))
            {
               //
               AttractionID = fileScanner.nextLine();
               System.out.println("Attraction ID: [" + AttractionID + "]");
               description = fileScanner.nextLine();
               System.out.println("Description: [" + description + "]");
               stringPassFee1 = fileScanner.nextLine();
               System.out.println("Pass Fee: [" + stringPassFee1 + "]");
               //
               passFee = Integer.parseInt(stringPassFee1);
               //
               // // store newly reconstructed Account object into the array
               att = new Attraction(AttractionID, description, passFee);

               if (fileScanner.hasNextLine())
                  fileScanner.nextLine();

               attractionList[attractionCount] = att;
               attractionCount++;
               System.out.println();

            }

         }

      }

      catch (InputMismatchException e)
      {
         System.out.print(e.getMessage()); // try to find out specific reason.
      }

      catch (FileNotFoundException e)
      {
         System.out.println("Cannot open file for reading!");
      }
   }

   /*
    * loadAGroupData() This method loads the GroupActivity objects from a text file
    * and adds them to the array by calling the constructor NOTE - had to load
    * Attraction and Group Activity in 2 separate methods as could not get them
    * working in the same method
    */

   private static void loadAGroupData()
   {
      System.out
               .println("Loading Group Activities in system in previous execution run: ");

      //
      // //variables for Attraction
      String AttractionID = null, description, stringPassFee1;
      String activityDate, stringMaxGroupSize, tourGuides;
      GroupActivity grp;

      try
      {
         Scanner fileScanner = new Scanner(new FileReader("attribute.txt"));
         //
         // // repeat while there are still lines to be read from the file
         while (fileScanner.hasNextLine())
         {
            // // read details for next object from file
            //
            if (fileScanner.nextLine().equals("GroupActivity"))
            {
               //
               AttractionID = fileScanner.nextLine();
               System.out.println("Attraction ID: [" + AttractionID + "]");
               description = fileScanner.nextLine();
               System.out.println("Description: [" + description + "]");
               stringPassFee1 = fileScanner.nextLine();
               System.out.println("Pass Fee: [" + stringPassFee1 + "]");
               //

               activityDate = fileScanner.nextLine();
               System.out.println("Activity Date: [" + activityDate + "]");

               stringMaxGroupSize = fileScanner.nextLine();
               System.out.println("Max Group Size [" + stringMaxGroupSize + "]");

               tourGuides = fileScanner.nextLine();
               System.out.println("Tour Guides: [" + tourGuides + "]");

               int GrpPassFee = Integer.parseInt(stringPassFee1);
               int GroupSize = Integer.parseInt(stringMaxGroupSize);

               // // store newly reconstructed Account object into the array
               grp =
                        new GroupActivity(AttractionID, description, GrpPassFee,
                                          activityDate, GroupSize, tourGuides);

               if (fileScanner.hasNextLine())
                  fileScanner.nextLine();

               attractionList[attractionCount] = grp;
               attractionCount++;
               System.out.println();

            }

         }

      }

      catch (InputMismatchException e)
      {
         System.out.print(e.getMessage()); // try to find out specific reason.
      }

      catch (FileNotFoundException e)
      {
         System.out.println("Cannot open file for reading!");
      }
   }

}
