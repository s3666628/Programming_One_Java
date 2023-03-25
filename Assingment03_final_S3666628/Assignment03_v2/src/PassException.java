public class PassException extends Exception // extend the standard Exception class
{
   public PassException(String message) //constructor - takes in message as param
   {
      super(message); //from the standard Exception Class 
   }

   public PassException() //default constructor as this is good practice + might not want to pass in message
   {

      super();
   }
}
