import java.util.*;

public class HTMLManager {
   private Queue<HTMLTag> tags; //internal field to remember the tags
  
   public HTMLManager(Queue<HTMLTag> html){
      
      if (html == null){ //null checker
         throw new IllegalArgumentException("Invalid Queue(Can't be null)"); 
      }
      
      //copy of the values into the new Queue
      this.tags = new LinkedList<>(html);
   }
   
   //returns the queue of html tag being managed
   public Queue<HTMLTag> getTags(){
      return new LinkedList<>(this.tags);
   }
  
}
