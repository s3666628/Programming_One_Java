/*
 * class Attraction
 * 
 * This code is the implementation of the Attraction class for CPT121 
 * Assignment 3 in SP1, 2018.
 * 
 * You are expected to work off this sample implementation of the Attraction
 * class - you *DO NOT* need to implement your own version of this class.
 * 
 * You may need to amend the functionality of this class to cater for exception
 * handling requirements in Stage 5 and perhaps for file handling functionality
 * in the bonus marks stage.
 * 
 */
public class Attraction
{
   // Step 1: Define instance variables required for a Attraction
   private String attractionID;
   private String description;
   private int passFee;
   private int passesSold;

   // Step 2: Define a constructor that sets up a new Attraction
   public Attraction(String attractionID, String description, int passFee)
   {
      this.attractionID = attractionID;
      this.description = description;
      this.passFee = passFee;
   }

   // Step 3 - Define accessors (getters) for each instance variable
   public String getAttractionID()
   {
      return attractionID;
   }

   public int getPassesSold()
   {
      return passesSold;
   }

   // Added missing accessors (getters)

   public String getDescription()
   {
      return description;
   }

   public int getPassFee()
   {
      return passFee;
   }

   // Step 4: Define operations that can be performed on a Attraction

   // sellPasses()
   //
   // Attempts to record the sale of the specified number of passes
   // for this Attraction and return the pass fees payable.
   //
   // Returns a signal of -1 when the number of passes specified
   // specified is not a positive value.
   //
   public int sellPasses(int numPasses) throws PassException // change to now throw
                                                             // PassException
   {
      // check for an invalid number of passes
      if (numPasses <= 0)
      {
         throw new PassException("Number of Passes sold cannot be 0");
      }
      else
      {
         // update the passes sold figure for this Attraction
         System.out.println("Success! " + numPasses + " have been sold");
         this.passesSold += numPasses;

         // calculate the pass fees payable by the customer
         int passFees = this.calculatePassFees(numPasses);

         // return the pass fees payable to the caller
         return passFees;
      }
   }

   // refundPasses()
   //
   // Processes the refunding of passes previously sold for this
   // Attraction and returns the pass fees amount to be
   // refunded to the customer accordingly.
   //
   // Returns a signal of -1 when the number of passes specified
   // is not a positive value OR is greater than the current
   // passes sold for this Attraction.
   //
   public int refundPasses(int numPasses)
   {
      // check to make sure number of tourists is valid
      if (numPasses <= 0 || numPasses > passesSold)
      {
         return -1;
      }
      else
      {
         // update the total number of bookings for this Attraction
         this.passesSold -= numPasses;

         // calculate the pass fees to be refunded
         int feesRefundable = this.calculatePassFees(numPasses);

         // return the refunded pass fees to the caller
         return feesRefundable;
      }
   }

   // Step 5: Define helper methods that we may need

   // calculatePassFees()
   //
   // Helper method which calculates the pass fees that
   // apply based on the specified number of passes that has
   // been passed in as a parameter and the pass fee for this
   // Attraction
   //
   private int calculatePassFees(int numPasses)
   {
      return numPasses * this.passFee;
   }

   // Helper method which displays the basic details for this Attraction,
   // as well as the total booking fees received for this Attraction.
   public void printDetails()
   {
      System.out.printf("%s %s\n", "Attraction ID:", attractionID);
      System.out.printf("%s %s\n", "Description:", description);
      System.out.printf("%s $%d\n", "Pass Fee:", passFee);
      System.out.printf("%s %d\n", "Passes sold:", passesSold);

      // call the calculatePassFees() method to help figure out the
      // total fees received for the Attraction
      System.out.printf("%s $%d\n", "Total Pass Fees Received:",
                        this.calculatePassFees(passesSold));
   }
}
