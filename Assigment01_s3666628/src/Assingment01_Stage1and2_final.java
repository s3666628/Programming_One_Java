import java.util.Scanner;
import java.lang.Math;

public class Assingment01_Stage1and2_final
{

   public static void main(String[] args)
   {

      Scanner input = new Scanner(System.in);
      final double SECTOR_RATE_1_2 = 2.5;
      final double SECTOR_RATE_3_4 = 2.0;
      final double SECTOR_RATE_5_7 = 1.9;
      final double SECTOR_RATE_8_10 = 1.8;
      // Get user input and assign to variables

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

      // Display Output to user

      System.out.print("\n");
      System.out.printf("Customer Details:\n");
      System.out.print("\n");

      final int padding = 80;

      int namePadding = padding - ("Name: ").length();
      // System.out.println(namePadding);
      String nameFormat = "%" + namePadding + "s%n";
      // System.out.println(nameFormat);

      int emailPadding = padding - ("Email Address: ").length();
      // System.out.println(emailPadding);
      String emailFormat = "%" + emailPadding + "s%n";
      // System.out.println(emailFormat);

      int mobilePadding = padding - ("Mobile Phone Number: ").length();
      String mobileFormat = "%" + mobilePadding + "s%n";

      int addressPadding = padding - ("Home Address: ").length();
      String addressFormat = "%" + addressPadding + "s%n";

      int creditPadding = padding - ("Card details: ").length();
      String creditFormat = "%" + creditPadding + "s%n";

      int expiryPadding = padding - ("Expiry Date: ").length();
      String expirtyFormat = "%" + expiryPadding + "s%n";

      int tripDatePadding = padding - ("Date: ").length();
      String tripDateFormat = "%" + tripDatePadding + "s%n";

      int sectorsPadding = padding - ("Sectors Travelled: ").length();
      String sectorsFormat = "%" + sectorsPadding + "s%n";

      int basicTripFarePadding = padding - ("Basic Trip Fare: ").length() - 4;
      String basicTripFareFormat = "%" + basicTripFarePadding + "s%.2f%n";

      int bookingSurchargePadding = padding - ("Booking Surcharge: ").length() - 4;
      String bookingSurchargeFormat = "%" + bookingSurchargePadding + "s%.2f%n";

      int adjustedFarePadding = padding - ("Adjusted Trip Fare: ").length() - 4;
      String adjustedFareFormat = "%" + adjustedFarePadding + "s%.2f%n";

      System.out.printf("%s" + nameFormat, "Name: ", customerName);
      System.out.printf("%s" + emailFormat, "Email Address: ", email);
      System.out.printf("%s" + mobileFormat, "Mobile Phone Number: ", mobilePhone);
      System.out.printf("%s" + addressFormat, "Home Address: ", homeAddress);
      System.out.print("\n");

      System.out.printf("%s" + creditFormat, "Card details: ", creditCardNum);
      System.out.printf("%s" + expirtyFormat, "Expiry Date: ", creditCardExpiry);

      System.out.print("\n");
      System.out.print("Trip Details:\n");
      System.out.print("\n");

      System.out.printf("%s" + tripDateFormat, "Date: ", tripDate);
      System.out.printf("%s" + sectorsFormat, "Sectors Travelled: ",
                        sectorsTravelled);

      if (sectorsTravelled <= 2)
      {
         basicTripFare = sectorsTravelled * SECTOR_RATE_1_2;
         System.out.printf("%s" + basicTripFareFormat, "Basic Trip Fare: ", "$",
                           basicTripFare);

         System.out.println("SECTOR_RATE_1_2");
      }
      else if (sectorsTravelled <= 4)
      {
         basicTripFare = sectorsTravelled * SECTOR_RATE_3_4;
         // System.out.printf("basic fare is %s%.2f%n", "$",basicTripFare);
         System.out.printf("%s" + basicTripFareFormat, "Basic Trip Fare: ", "$",
                           basicTripFare);
         System.out.println("SECTOR_RATE_3_4");
      }
      else if (sectorsTravelled <= 7)
      {
         basicTripFare = sectorsTravelled * SECTOR_RATE_5_7;
         // System.out.printf("basic fare is %s%.2f%n", "$",basicTripFare);
         System.out.printf("%s" + basicTripFareFormat, "Basic Trip Fare: ", "$",
                           basicTripFare);
         System.out.println("SECTOR_RATE_5_7");
      }

      else
      {
         basicTripFare = sectorsTravelled * SECTOR_RATE_8_10;

         // System.out.printf("basic fare is %s%.2f%n", "$",basicTripFare);
         System.out.printf("%s" + basicTripFareFormat, "Basic Trip Fare: ", "$",
                           basicTripFare);
         System.out.println("SECTOR_RATE_8_10");
      }

      // work out which surcharges to apply
      double bookingSurcharge;
      double adjustedFare;

      if (bookingTypeString.equals("S"))
      {
         bookingSurcharge = 0;
         // System.out.printf("Surcharges %s%.2f%n", "$",bookingSurcharge);
         System.out.printf("%s" + bookingSurchargeFormat, "Booking Surcharge: ", "$",
                           bookingSurcharge);

      }
      else if (bookingTypeString.equals("RS") && basicTripFare * 0.2 > 2)

      {
         bookingSurcharge = basicTripFare * 0.2;
         // System.out.printf("Surcharges %s%.2f%n", "$",bookingSurcharge);
         System.out.printf("%s" + bookingSurchargeFormat, "Booking Surcharge: ", "$",
                           bookingSurcharge);

      }

      else if (bookingTypeString.equals("RS") && basicTripFare * 0.2 <= 2)

      {
         bookingSurcharge = 2.0;
         // System.out.printf("Surcharges %s%.2f%n", "$",bookingSurcharge);
         System.out.printf("%s" + bookingSurchargeFormat, "Booking Surcharge: ", "$",
                           bookingSurcharge);

      }

      else if (bookingTypeString.equals("FC") && basicTripFare * 0.5 > 4)
      {
         bookingSurcharge = basicTripFare * 0.5;
         // System.out.printf("Surcharges %s%.2f%n", "$",bookingSurcharge);
         System.out.printf("%s" + bookingSurchargeFormat, "Booking Surcharge: ", "$",
                           bookingSurcharge);
      }

      else

      {
         bookingSurcharge = 4.0;
         // System.out.printf("Surcharges %s%.2f%n", "$",bookingSurcharge);
         System.out.printf("%s" + bookingSurchargeFormat, "Booking Surcharge: ", "$",
                           bookingSurcharge);

      }

      if (bookingSurcharge == 0)
      {
         adjustedFare = basicTripFare;
      }
      else
      {
         adjustedFare = bookingSurcharge + basicTripFare;
      }

      // adjustedFare = bookingSurcharge * basicTripFare;

      // System.out.printf("Adjusted Trip Fare: %s%.2f%n", "$",adjustedFare);
      System.out.printf("%s" + adjustedFareFormat, "Adjusted Trip Fare: ", "$",
                        adjustedFare);

      // System.out.printf("%57s%3.2f","$",basicTripFare);

      // basicTripFare

   }

}
