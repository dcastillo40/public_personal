package registerSystem;

public class Element {
    //Description: Manages  a customer element
    
    private long entryTime;
    private long startOfServiceTime;
    private long endOfServiceTime;
    
    public void SetEnter(long nEntryTime) {
        //Description:  Sets the enter time of the customer.
        //Preconditions:
        //  Valid time must be recorded.
        //Postconditions:
        //  Entry time will be set.
        
        entryTime = nEntryTime;
    }
    
    public void SetStartServe(long nStartOfServiceTime) {
        //Description:  Sets the start service time of the customer.
        //Preconditions:
        //  Valid time must be recorded.
        //Postconditions:
        //  Start service time will be set.
        
        startOfServiceTime = nStartOfServiceTime;
    }
    
    public void SetEndServe(long nEndOfServiceTime) {
        //Description:  Sets the end service time of the customer.
        //Preconditions:
        //  Valid time must be recorded.
        //Postconditions:
        //  End service time will be set.
        
        endOfServiceTime = nEndOfServiceTime;
    }
    
    public long GetWait( ) {
        //Description:  Will return the wait time of the customer.
        //Preconditions:
        //  The start service time and entry time must be recorded.
        //Postconditions:
        //  The wait time will be returned.
        
        long waitTime;
        
        waitTime = (startOfServiceTime - entryTime)/1000;
        
        return waitTime;
    }
    
    public long GetServe( ) {
        //Description:  Will return the service time of the customer.
        //Preconditions:
        //  The start service time and end service time must be recorded.
        //Postconditions:
        //  The service time will be returned.
        
        long serveTime;
        
        serveTime = (endOfServiceTime - startOfServiceTime)/1000;
        
        return serveTime;
    }
    
    public Element Clone( ) {
        //  Description: Take the data from this instance and create a new 
        //  element with this element's data.
        //Preconditions:
        //  None.
        //Postconditions:
        //  Will create a clone of the current element.
        
        Element clonedElement;
        
        clonedElement = new Element( );
        clonedElement.SetEnter(entryTime);
        clonedElement.SetStartServe(startOfServiceTime);
        clonedElement.SetEndServe(endOfServiceTime);
        
        return clonedElement;
    }
}
