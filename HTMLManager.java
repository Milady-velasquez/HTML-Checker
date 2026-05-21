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
   
   public void fixHTML() {
    Queue<HTMLTag> fixedQueue = new LinkedList<>();
    
    // stack to track open tags
    Stack<HTMLTag> openTagsStack = new Stack<>();
    
    //Process every tag in the current manager queue
    int initialSize = this.tags.size();
    for (int i = 0; i < initialSize; i++) {
        HTMLTag current = this.tags.remove();
        
        if (current.isSelfClosing()) {  
        //Self-closing tags go straight to the right queue
            fixedQueue.add(current);
        } 
        else if (current.isOpening()) {
            // Opening tags go to both the queue and the stack
            fixedQueue.add(current);
            openTagsStack.push(current);
        } 
        else if (current.isClosing()) {
            if (!openTagsStack.isEmpty()) {
                HTMLTag topOpen = openTagsStack.peek();
                
                if (topOpen.matches(current)) {
                    // if it is perfect match, Keep the closing tag and pop the open tag
                    fixedQueue.add(current);
                    openTagsStack.pop();
                } else {
                    // if it is Mismatch, Fix it by automatically closing the top open tag
                    HTMLTag correctCloseTag = topOpen.getMatching();
                    fixedQueue.add(correctCloseTag);
                    openTagsStack.pop();
                    
                    // Put the current closing tag back in rotation by backing up the loop
                    this.tags.add(current);
                    initialSize++; 
                }
            }
        }
    }
    
    //Clean up any leftover open tags that were never closed
    while (!openTagsStack.isEmpty()) {
        HTMLTag leftOverOpen = openTagsStack.pop();
        fixedQueue.add(leftOverOpen.getMatching());
    }
    
    //Update the manager's master queue with the fixed version
    this.tags = fixedQueue;
}          
}






/*
# PROGRAM OUTPUT

Paste the output from JGrasp here.
Altering output will earn you an automatic zero for the assignment.

*/