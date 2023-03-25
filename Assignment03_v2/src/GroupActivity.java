import java.util.Scanner;

public class GroupActivity extends Attraction
{
   //define the instance variables for the class
   private String activityDate;
   private int maxGroupSize;
   private String tourGuides;
   // new scanner instance needed for the class to take keyboard input
   private static final Scanner sc = new Scanner(System.in);

   // Constructor for sub class
   public GroupActivity(String attractionID, String description, int passFee,
                        String activityDate, int maxGroupSize, String tourGuides)
   {
      super(attractionID, description, passFee); //get the instance variables from the super class
      this.setActivityDate(activityDate);
      this.setMaxGroupSize(maxGroupSize);
      this.setTourGuides(tourGuides); // GroupActivity(grroupAttractionID,
                                      // groupAttractDesc, groupPassFee,
                                      // activityDate, maxGroupSize, groupTourGuide);

   }

   public boolean increaseGroupSize(int places, String tourGuides)
   {

      // Method allows increase in max group size
      // Method option to add more tour guides as string


      // String stringPlaces;

      {

         if (places <= 0) //check the number of places cannot be less than 1
            // if method called with < 1 return false
         {

            System.out
                     .println("Error! Number of places must be at least 1. Request to increase Group Size has been rejected ");
            return false;
         }
         else
         {
            this.maxGroupSize += places; //update the group size with the number of places
            System.out
                     .println("Max group size has been increased and is now set to: " +
                              getMaxGroupSize());
            // now check if user wants to specify new tour guide


            if (tourGuides.length() > 0) //if string > 0 then this will evalate to True
               // if false then we skip this as it is optional
            {
               this.tourGuides += tourGuides + ", ";

               System.out.println("You have successfully added " + tourGuides +
                                  " to the tour guides list");
               return true;

            }

         }

      }
      return false;

   }

   @override
   // Allows the user to purchase passes for Group Activities
   // by overriding super class method sellPasses
   public int sellPasses(int numPasses) throws PassException

   {
      int projectedGroupSize = super.getPassesSold() + numPasses; // work out projected group size 
      if (projectedGroupSize > getMaxGroupSize()) //check if user is selling more passes than the group size
      {
         //exception is thrown to calling method if they are selling too many passes
         //give user information about the current grp size and projected group size so they can fix issue
         throw new PassException(
                                 "Error! - purchase sale not completed. Projected Group Size is: " +
                                          projectedGroupSize +
                                          " but Maximum Group Size is currently set to: " +
                                          getMaxGroupSize());

         // return -1;
      }

      else if (numPasses == 0) // we can't allow user to sell 0 passes
      {
         throw new PassException("Error! - cannot sell 0 group passes");

      }
      //
      // else if (projectedGroupSize == super.getPassesSold()) {
      // throw new PassException("Error! All Passes have been sold");
      //
      //
      // }

      else // everything good - let user know
      {
         System.out.println("Success! - purchase sale completed. " + numPasses +
                            " have been sold");
         return super.sellPasses(numPasses);
      }

   }

   @override
   // Overriding Print Details to include sub Class instance variables
   public void printDetails()

   { // call the super class print details first
      super.printDetails();
      // now add the sub class instance variables that we want to print out
      System.out.println("Override Print Details ");
      System.out.printf("%s %s\n", "Activity Date:", activityDate);
      System.out.printf("%s %d\n", "Maximum Group Size:", maxGroupSize);
      System.out.printf("%s %s\n", "Tour Guides:", tourGuides);

   }

   // Setters and Getters for instance variables

   public String getActivityDate()
   {
      return activityDate;
   }

   public void setActivityDate(String activityDate)
   {
      this.activityDate = activityDate;
   }

   public int getMaxGroupSize()
   {
      return maxGroupSize;
   }

   public void setMaxGroupSize(int maxGroupSize)
   {
      this.maxGroupSize = maxGroupSize;
   }

   public String getTourGuides()
   {
      return tourGuides;
   }

   public void setTourGuides(String tourGuides)
   {
      this.tourGuides = tourGuides;
   }

}
