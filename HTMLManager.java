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
   
   public String toString(){
      String str = "";
      
      int initialSize = this.tags.size(); //to remember the original size 
      
      for(int i = 0; i < initialSize; i++){ //loop each item in the queue
         HTMLTag currentTag = this.tags.remove();
         
         //convert the string, then trim the spaces 
         str += currentTag.toString().trim(); 
         
         this.tags.add(currentTag); //put it back at the end of the queue
      }
      return str;
   }
          
}






/*
# PROGRAM OUTPUT

Paste the output from JGrasp here.
Altering output will earn you an automatic zero for the assignment.

*/