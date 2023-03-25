/*
 * class Appointment
 * 
 * A representation of a Appointment for a patient attending the 
 * Child Health Clinic.
 * 
 * You will need to implement the various elements of this class as desccribed
 * in Stage 1 of the Assignment 1 Specification.
 * 
 */
public class Appointment
{

   // write your code for the requirements described in Stage 1 here
   // ...
   // Part A - Declare private instance variables

   private String appointmentID;
   private String clinicName;
   private String appointmentTime;
   private String appointmentStatus;
   private String roomNumber;
   private String patientName;
   private String consultationNotes;
   private boolean bulkBilling;
   private int consultationFee;

   // Part B - Provide a constructor for the class
   public Appointment(String appointmentID, String appointmentTime,
                      String patientName, boolean bulkBilling)

   {
      this.appointmentID = appointmentID;
      this.appointmentTime = appointmentTime;
      this.patientName = patientName;
      this.bulkBilling = bulkBilling;

      this.appointmentStatus = "SCH";
      this.clinicName = ClinicDetails.getClinicName(appointmentID);

   }

   // Part C - Implement accessors for the appointmentID, clinicName,
   // appointmentTime, appointmentStatus, patientName
   public String getAppoinmentID()
   {
      // System.out.print("test");
      return appointmentID;
   }

   public String getClinicName()
   {
      return clinicName;
   }

   public String getAppointmentTime()
   {
      return appointmentTime;
   }

   public String getPatientName()
   {
      return patientName;
   }

   // Part D - Implement public method - checkIn()

   public boolean checkIn()

   {
      if (!appointmentStatus.equals("SCH")) // check whether status not equal to SCH
                                            // (scheduled)
      {
         return false;
      }

      else
      {
         appointmentStatus = "CHK"; // if appointment status = something other than
                                    // not SCH - update it to CHK
         return true;
      }

   }

   // Part E - Implement public method - callPatient

   public boolean callPatient(String roomNumber)

   {
      if (!appointmentStatus.equals("CHK")) // check whether status not equal to SCH
                                            // (scheduled)
      {
         return false;
      }

      else
      {
         appointmentStatus = "CLD"; // if appointment status = something other than
                                    // not SCH - update it to CHK
         this.roomNumber = roomNumber;
         return true;
      }

   }
   // Part F - Implement a method public int recordConsultation (String notes),
   // which records details of a consultation that has been delivered to a patient at
   // the clinic.

   public int recordConsultation(String notes)

   {
      if (!appointmentStatus.equals("CLD")) // check whether status not equal to CLD
                                            // (scheduled)
      {
         System.out.println("Appointment Status !=CLD");
         return -1;
      }

      else
      {
         System.out.println("Appointment Status - got the else part");
         this.consultationNotes = notes;

         if (this.bulkBilling == true)
         {
            appointmentStatus = "BBL";
            return 0;
         }
         else
         {
            this.consultationFee = ClinicDetails.getClinicFee(appointmentID);
            appointmentStatus = "PPE";
            return consultationFee;
         }

      }

   }

   // Part G - Implement a print method,
   public void printDetails()

   {
      System.out.print("Appointment ID: " + this.appointmentID + ", ");
      System.out.print("Clinic : " + this.getClinicName() + "\n");

      System.out.print("Appointment Time: " + this.getAppointmentTime() + ", ");
      System.out.print("Appointment Status: " + this.appointmentStatus + "\n");

      System.out.print("Room: " + this.roomNumber + ", ");
      System.out.print("Patient: " + this.getPatientName() + ", ");
      System.out.print("Bulk Billing: " + this.bulkBilling + "\n");

      System.out.print("Consultation Notes: " + this.consultationNotes + "\n");

      System.out.print("Consultation fee: " + "$" + this.consultationFee + "\n");

   }
   // print summary for method used by menu item "C"

   public void printSummary()

   {
      System.out.println("*** Summary Information ***\n");
      System.out.println("Appointment ID: " + this.appointmentID + ", ");
      System.out.println("Appointment Time: " + this.getAppointmentTime() + ", ");
      System.out.println("Patient: " + this.getPatientName() + ", ");

   }

}
