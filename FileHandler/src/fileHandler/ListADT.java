package fileHandler;

public class ListADT {
    //This manages a list of elements.
    
    //This is written by the UTILITY programmer
    
    //Preconditions:
    //  This requires an object named Element is placed within
    //      the package.
    //  This requires that the Element has a Deep Copy method
    //      named Clone.
    //  This requires a "key" (string) be identified in the Element class.
    //  This requires that an Element method named GetKey returns a string of
    //      the key's value.
    
    //Shhhhhh...  This is implemented CURRENTLY via a subarray of Elements.
    private Element [ ] data;
    private int numItems;  //logical size
    private int loc;  //current element
    
    /*METHODS:
        Create - done.
        Destroy - done.
        Search - done.
        NOPE-modify - not doing
        NOPE-sort - not doing
        NOPE-compare list - not doing
        Add - done.
        Delete (remove) - done.
        IsFull - done.
        IsEmpty - done.
        NOPE-copy - not doing
        NOPE-display - not doing
        NOPE-replace - not doing
        NOPE-save - not doing
        
        Reset - done.
        Retrieve - done.
        GetNext - done.
        AtEnd - done.
    */
    
    public void Destroy( ) {
        //Description:  Sets the list to an unusable state
        //                  and eliminates all data in an existing list.
        //Preconditions:
        //  List must exist (created).
        //Postconditions:
        //  List will be in an unusable state.
        //  No data shall remain.
        for(int cnt = 0; cnt < data.length; cnt++)
            data[cnt] = null;
        data = null;
        loc = -1;
        numItems = -1;
    }
    
    public boolean Delete(String searchValue) {
        //Description:  Removes a specified item from the list.
        //Preconditions:
        //  The list must exist (created).
        //  Must be given a search value.
        //Postconditions:
        //  When the search value is found is found in the list:
        //      The matching element will be removed.
        //      A true value is returned.
        //  When the search value is NOT found in the list:
        //      The current location will be meaningless.
        //      The data in the list shall remain unchanged.
        //      A false value will be returned. 
        boolean found;
        
        found = Search(searchValue);
        
        if(found) {
            numItems--;
            data[loc] = data[numItems];
            data[numItems] = null;//safe
        }
        
        return found;
    }
    
    public boolean AtEnd( ) {
        //Description:  Tests to see if the current location is at the end of 
        //                  the list.
        //Preconditions:
        //  The list must exist (created).
        //Postconditions:
        //  When current location is at end, returns true.
        //  When current location is NOT at end, returns false.
        boolean end;
        
        end = (numItems == loc);
        
        return end;
    }
    
    
    
    public void GetNext( ) {
        //Description:  Positions the current location to the next item 
        //                  in the list.
        //Preconditions:
        //  The list must exist (created).
        //  Must not be at end of list.
        //Postconditions:
        //  The current location reflects the next item in the list.
       loc++;
    }
    
    
    
    public void Reset( ) {
        //Description:  Positions the current location at the first element.
        //Preconditions:
        //  The list must exist (created).
        //Postconditions:
        //  The current location will reflect the first element.
        loc = 0;
    }
    
    public Element Retrieve( ) {
        //Description:  Provides the Element from the current location.
        //Preconditions:
        //  The list must exist (created).
        //  The current location "should be less than numItems."   MAYBE WORKS?
        //Postconditions:
        //  Returns a copy of the current element.
        //  The list remains unchanged.
        Element elementToReturn;
        
        elementToReturn = data[loc].Clone( );
        
        return elementToReturn;
    }
    
    public boolean Search(String searchValue) {
        //Description:  Searches the list and identifies if the given item 
        //                  is found and its location within the list.
        //Preconditions:
        //  The list must exist (created).
        //  Must be given a search value.
        //Postconditions:
        //  When the search value is found is found in the list:
        //      The list will have the current (correct) location.
        //      A true value is returned.
        //  When the search value is NOT found in the list:
        //      A false value will be returned.
        //      The current location will be meaningless.
        boolean found;
        
        //Try to find the item
        Reset( );//loc = 0;
        while(!AtEnd( ) && //loc < numItems &&  //not at logical end of list
                searchValue.equals(data[loc].GetKey( )) == false) //not found
            GetNext( );//loc++;
        
        //communicate found or not...  Set found to something
        if(!AtEnd( )) //loc < numItems) && searchValue.equals(data[loc].GetKey( )))
            found = true;
        else
            found = false;
            
        return found;
    }
    
    public boolean IsEmpty( ) {
        //Description:  Identifies if the list contains no data.
        //Preconditions:
        //  The list exists (created).
        //Postconditions:
        //  When the list has NO data, returns a true value.
        //  When the list contains some data, returns a false value.
        boolean empty;// = (numItems == 0);
        
        /*
        if(numItems == 0)
            empty = true;
        else
            empty = false;
        */
        empty = (numItems == 0);
        
        return empty;
    }
    
    public boolean IsFull( ) {
        //Description:  Identifies if the list has any capacity remaining.
        //Preconditions:
        //  List must exist (created).
        //Postconditions:
        //  When the list is full, returns true.
        //  When the list is not full, returns false.
        boolean full;
        
        if(numItems == data.length)
            full = true;
        else
            full = false;
        
        return full;
    }
    
    public void Create(int capacity) {
        //Description:  Allocates memory for a list of a given capacity.
        //Preconditions:
        //  capacity must be properly set.
        //  capacity must be > 0 (positive).
        //Postconditions:
        //  List will exist.
        //  List will be empty.
        //  List will have the given capacity.
        
        data = new Element[capacity];
        numItems = 0;
        loc = 0;
    }
    
    public boolean Add(Element uItem) {
        //Desc:  Adds a given element to the list.
        //Preconditions:
        //  Given element must exist
        //  List must be created.
        //  NOPE - List must not be full. ???
        //Postconditions:
        //  When the list is not full
        //      Given element will be included in the list.
        //      Returns a true value.
        //  When the list is full
        //      The list shall remain unchanged.
        //      Returns a false value.
        boolean wasAdded;
        
        if(IsFull( ) == false) {
            data[numItems] = uItem.Clone( );
            numItems++;
            //System.out.println("Data added.");
            wasAdded = true;
        }
        else
            //System.out.println("Data NOT added!");
            wasAdded = false;
        
        //Debug( );
        return wasAdded;
    }

}
