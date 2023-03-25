/* Assignment 01 - by Philip Beeby (RMIT Student ID - s3666628)
 * This is the answer to Part 3
 * */

import java.util.Scanner;

public class Assignmnt01_Stage3
{

   public static void main(String[] args)
   {
      // TODO Auto-generated method stub
      Scanner input = new Scanner(System.in);
      String selection = "D";
      double totalKioskPirce = 0.0;
      String kioskPuchases = "";

      double totalOfAllFares = 0.0;
      String allTripDetails = "";
      // Constant amounts for the sector prices

      final double SECTOR_RATE_1_2 = 2.5;
      final double SECTOR_RATE_3_4 = 2.0;
      final double SECTOR_RATE_5_7 = 1.9;
      final double SECTOR_RATE_8_10 = 1.8;
      
      // This section allows user to enter personal information
      System.out.print("*** Regional Passenger Train Invoicing System ***\n");
      System.out.print("\n");

      System.out.print("Enter Account Number: ");
      String accountNumber = input.nextLine();

      System.out.print("Enter Customer Name: ");
      String customerName = input.nextLine();

      System.out.print("Enter Customer Email Address: ");
      String email = input.nextLine();

      System.out.print("Enter Customer Mobile Phone No: ");
      String mobilePhone = input.nextLine();

      System.out.print("Enter Customer Home Address: ");
      String homeAddress = input.nextLine();
      System.out.print("\n");

      System.out.print("Enter Credit Card No: ");
      String creditCardNum = input.nextLine();

      System.out.print("Enter Expiry Date: ");
      String creditCardExpiry = input.nextLine();

      System.out.print("Enter Security Code: ");
      String securityCode = input.nextLine();

      // This is the start of the Do While Loop for the menu
      // loop ends when users enter 'X'
      Scanner sc = new Scanner(System.in);

      do
      {
         System.out.print("\n");
         System.out.print("*** Regional Passenger Train Entry Menu ***\n");
         System.out.print("---------------------------------------------\n");
         System.out.print("\n");
         System.out.print("A - Record Trip\n");
         System.out.print("B - Record Kiosk Purchase\n");
         System.out.print("X - Exit\n");
         System.out.print("\n");
         System.out.print("Enter your selection: ");

         String input2 = sc.nextLine();
         if (input2.length() > 1)
         {
            System.out.print("Please enter single character, A, B or X " +
                             input2.length());
         }
         else
            selection = input2;
         selection = selection.toUpperCase();
         {
            switch (selection)

            {
               case "A":
                  // System.out.print("You have selected A: \n");

                  System.out.print("Enter trip date: ");
                  String tripDate = input.nextLine();

                  System.out.print("Enter start sector (1-10): ");
                  String startSectorString = input.nextLine();

                  int startSector = Integer.parseInt(startSectorString);

                  System.out.print("Enter end sector (1-10): ");
                  String endSectorString = input.nextLine();

                  int endSector = Integer.parseInt(endSectorString);

                  System.out.print("Enter booking type (S, RS, FC): ");
                  String bookingTypeString = input.nextLine();

                  int sectorsTravelled = (Math.abs(endSector - startSector)) + 1;
                  double basicTripFare;

                  if (sectorsTravelled <= 2)
                  {
                     basicTripFare = sectorsTravelled * SECTOR_RATE_1_2;
                     //System.out.println("SECTOR_RATE_1_2");
                  }
                  else if (sectorsTravelled <= 4)
                  {
                     basicTripFare = sectorsTravelled * SECTOR_RATE_3_4;
                     //System.out.println("SECTOR_RATE_3_4");
                  }
                  else if (sectorsTravelled <= 7)
                  {
                     basicTripFare = sectorsTravelled * SECTOR_RATE_5_7;
                     // System.out.println("SECTOR_RATE_5_7");
                  }

                  else
                  {
                     basicTripFare = sectorsTravelled * SECTOR_RATE_8_10;
                     // System.out.println("SECTOR_RATE_8_10");
                  }

                  // work out which surcharges to apply
                  double bookingSurcharge;
                  double adjustedFare;
                  String seatType;
                  //This is the logic for determining the Booking Surcharge
                  

                  if (bookingTypeString.equals("S"))
                  {
                     bookingSurcharge = 0;
                     seatType = "Standard";

                  }
                  else if (bookingTypeString.equals("RS") && basicTripFare * 0.2 > 2)

                  {
                     bookingSurcharge = basicTripFare * 0.2;
                     seatType = "Reserved Seat";

                  }

                  else if (bookingTypeString.equals("RS") &&
                           basicTripFare * 0.2 <= 2)

                  {
                     bookingSurcharge = 2.0;
                     seatType = "Reserved Seat";
                  }

                  else if (bookingTypeString.equals("FC") && basicTripFare * 0.5 > 4)
                  {
                     bookingSurcharge = basicTripFare * 0.5;
                     seatType = "First Class";                  }

                  else

                  {
                     bookingSurcharge = 4.0;
                     seatType = "First Class";
                  }
                  //if Booking Surcharge is 0 we can say that adjusted fare will be the same as basic fare
                  //else we have to add them up

                  if (bookingSurcharge == 0)
                  {
                     adjustedFare = basicTripFare;
                  }
                  else
                  {
                     adjustedFare = bookingSurcharge + basicTripFare;
                  }

                  totalOfAllFares += adjustedFare;
                  // Format doubles so they only output $XX.XX
                  String basicTripFareOut = String.format("%1$,.2f", basicTripFare);
                  String bookingSurchargeOut =
                           String.format("%1$,.2f", bookingSurcharge);
                  String adjustedFareOut = String.format("%1$,.2f", adjustedFare);
                  //This is formatted string to output total fare information
                  allTripDetails += " - " + tripDate + " " + seatType + " - " +
                                    sectorsTravelled + " Sectors" + " : " +
                                    "fare: $" + basicTripFareOut + "," +
                                    " surcharge: $" + bookingSurchargeOut + ", " +
                                    "total: $" + adjustedFareOut + "\n";

                  break;

               case "B":
                  //This is for the user to enter details of Kiosk purchases
                  //System.out.print("You have selected B: \n");
                  System.out.print("Enter your trip date: ");
                  String kioskTripDate = sc.nextLine();
                  kioskPuchases = kioskPuchases + "- " + kioskTripDate;
                  System.out.print("Enter description: ");
                  String kioskDescription = sc.nextLine();
                  kioskPuchases = kioskPuchases + " - " + kioskDescription + " ";
                  System.out.print("Enter price: ");
                  String StringKioskPirce = sc.nextLine();
                  kioskPuchases = kioskPuchases + " : $" + StringKioskPirce + "\n";
                  double kioskPrice = Double.parseDouble(StringKioskPirce);
                  totalKioskPirce = totalKioskPirce + kioskPrice;

                  break;
               //This is for leaving the Do While loop
               //Once this is selected final statement will be printed out to terminal   
               case "X":
                  System.out.print("You have selected X: \n");
                  System.out.print("Exiting System\n");
                  break;

               default:
                  System.out.print("Error: Invalid input");

            }

         }
      } while (selection.equals("X") == false);

      // Set up the padding information so the output lines up

      final int padding = 80;

      int namePadding = padding - ("Name: ").length();
      String nameFormat = "%" + namePadding + "s%n";

      int emailPadding = padding - ("Email Address: ").length();
      String emailFormat = "%" + emailPadding + "s%n";

      int mobilePadding = padding - ("Mobile Phone Number: ").length();
      String mobileFormat = "%" + mobilePadding + "s%n";

      int addressPadding = padding - ("Home Address: ").length();
      String addressFormat = "%" + addressPadding + "s%n";

      int creditPadding = padding - ("Card details: ").length();
      String creditFormat = "%" + creditPadding + "s%n";

      int expiryPadding = padding - ("Expiry Date: ").length();
      String expirtyFormat = "%" + expiryPadding + "s%n";

      // Final Output of Invoice Displayed to the User

      // 1 - Output the Customer Details

      System.out.print("\n");
      System.out.printf("Customer Details:\n");
      System.out.print("\n");

      System.out.printf("%s" + nameFormat, "Name: ", customerName);
      System.out.printf("%s" + emailFormat, "Email Address: ", email);
      System.out.printf("%s" + mobileFormat, "Mobile Phone Number: ", mobilePhone);
      System.out.printf("%s" + addressFormat, "Home Address: ", homeAddress);
      System.out.print("\n");
      System.out.printf("%s" + creditFormat, "Card details: ", creditCardNum);
      System.out.printf("%s" + expirtyFormat, "Expiry Date: ", creditCardExpiry);

      // 2 - Output the the Trip Details
      System.out.print("\n");
      System.out.printf("Trip Details:\n");
      System.out.print("\n");

      System.out.print(allTripDetails); // displays all trips as string
      System.out.print("\n");
      String formattedTripFareTotal= String.format("%1$,.2f", totalOfAllFares);
      System.out.print("Trip fare Total: $" + formattedTripFareTotal);
      

      System.out.print("\n");

      // 3 - Output the the Kiosk Purchasess
      System.out.print("\n");
      System.out.print("Kiosk Purchases:\n");
      System.out.print("\n");
      System.out.print(kioskPuchases);
      System.out.print("\n");
      String formattedTotalkioskPrice = String.format("%1$,.2f", totalKioskPirce);
      System.out.print("Kiosk Purchases Total: " + "$" + formattedTotalkioskPrice);
      System.out.print("\n");
      // 3 - Output Combined Total - All Trips + All Kiosk Purchases
      System.out.print("\n");
      double finalInvoice = totalOfAllFares + totalKioskPirce;
      String formattedFinalInvoice = String.format("%1$,.2f", finalInvoice);
      System.out.print("Final Invoice Total: $" +formattedFinalInvoice);
                       

   }

}
