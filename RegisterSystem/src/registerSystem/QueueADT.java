package registerSystem;

public class QueueADT {
    //This manages a queue of elements.
    
    //This is written by the UTILITY programmer
    
    //Preconditions:
    //  This requires an object named Element is placed within
    //      the package.
    //  This requires that the Element has a Deep Copy method
    //      named Clone.
    
    //Shhhhhh...  This CIRCULAR queue is implemented CURRENTLY via a 
    //            "subarray" of Elements.
    private Element [ ] data;
    private int front, back;
    
    /*METHODS:
        Create
        Destroy
        Enqueue (Push (Add))
        Dequeue (Pop (Delete/Retrieve))
        IsFull
        IsEmpty
    */
    
    public void Destroy( ) {
        //Description:  Sets the stack to an unusable state
        //                  and eliminates all data in an existing stack.
        //Preconditions:
        //  Stack must exist (created).
        //Postconditions:
        //  Stack will be in an unusable state.
        //  No data shall remain.
        for(int cnt = 0; cnt < data.length; cnt++)
            data[cnt] = null;
        //while(!IsEmpty( ))
        //    Dequeue( );
        data = null;
        front = -1;
        back = -1;
    }
    
    public Element Dequeue( ) {
        //Description:  Provides the Element from the front of the queue.
        //Preconditions:
        //  The queue must exist (created).
        //Postconditions:
        //  When queue is NOT empty
        //      Returns the front element.
        //      The queue will no longer contain the dequeued element.
        //  When the queue is empty
        //      Returns an empty element (null)
        Element elementToReturn;
        
        if(!IsEmpty( )) {
            elementToReturn = data[front];
            data[front] = null;  //safe - defensive
            front = (front + 1) % data.length;
        }
        else
            elementToReturn = null;
        
        return elementToReturn;
    }
    
    public boolean IsEmpty( ) {
        //Description:  Identifies if the queue contains no data.
        //Preconditions:
        //  The queue exists (created).
        //Postconditions:
        //  When the queue has NO data, returns a true value.
        //  When the queue contains some data, returns a false value.
        boolean empty;
        
        empty = (front == back);
        
        return empty;
    }
    
    public boolean IsFull( ) {
        //Description:  Identifies if the queue has any capacity remaining.
        //Preconditions:
        //  Queue must exist (created).
        //Postconditions:
        //  When the queue is full, returns true.
        //  When the queue is not full, returns false.
        boolean full;
        
        if((back + 1) % data.length == front)
            full = true;
        else
            full = false;
        
        return full;
    }
    
    public void Create(int capacity) {
        //Description:  Allocates memory for a queue of a given capacity.
        //Preconditions:
        //  capacity must be properly set.
        //  capacity must be > 0 (positive).
        //Postconditions:
        //  Queue will exist.
        //  Queue will be empty.
        //  Queue will have the given capacity.
        
        data = new Element[capacity + 1];
        front = back = 0;
    }
    
    public boolean Enqueue(Element uItem) {
        //Desc:  Adds a given element to the queue.
        //Preconditions:
        //  Given element must exist
        //  Queue must be created.
        //Postconditions:
        //  When the queue is not full
        //      Given element will be included in the queue (at the back).
        //      Returns a true value.
        //  When the queue is full
        //      The queue shall remain unchanged.
        //      Returns a false value.
        boolean wasAdded;
        
        if(IsFull( ) == false) {
            data[back] = uItem.Clone( );  //adds the copy
            /*
            back++;                       //advanced back
            if(back == data.length)       //wraps the back to the start of array if needed
                back = 0;
            */
            back = (back + 1) % data.length;
            
            wasAdded = true;
        }
        else
            wasAdded = false;
        
        //Debug( );
        return wasAdded;
    }
}

