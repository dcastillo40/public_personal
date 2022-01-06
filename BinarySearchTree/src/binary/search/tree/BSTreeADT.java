package binary.search.tree;

public class BSTreeADT {

    private Node theRoot;
    //Need an element class
    //Need a clone method
    //Need a display method
    //Need save method
    //Need a GetKey method
    
    //Node current;  //fabulous idea, great thinking - nope...

    //constructor
    /*
    create - done
    destroy
    add - done
    remove
    isfull - done
    isempty - done
    search - not QUITE doing
    retrieve - done
    
    OTHERS - Display??? a traversal that uses ELEMENT display
           - Save??? a traversal thet uses ELEMENT save
    
     */
    
    private Node RemoveFoundNode(Node nodeToRemove) {
        Element largestSmall;
        System.out.println("Found some node  ABOUT TO REMOVE");
        nodeToRemove.GetData( ).Display( );
        
        //no children
        if(nodeToRemove.GetLeft( ) == null && nodeToRemove.GetRight( ) == null) {
            nodeToRemove.SetData(null);  //safe
            nodeToRemove = null;
        }
        else //one child on left
            if(nodeToRemove.GetRight( ) == null) {
                nodeToRemove.SetData(null);  //safe
                nodeToRemove = nodeToRemove.GetLeft( );
            }
            else //one child on right
                if(nodeToRemove.GetLeft( ) == null) {
                    nodeToRemove.SetData(null);
                    nodeToRemove = nodeToRemove.GetRight( );
                }
                else {   //two children
                    largestSmall = GetLargestSmallerElement(nodeToRemove.GetLeft( ));
                    nodeToRemove.SetData(largestSmall);
                    nodeToRemove.SetLeft(DeleteNode(largestSmall.GetKey( ), 
                            nodeToRemove.GetLeft( )));
                }

        return nodeToRemove;
    }
    
    private Element GetLargestSmallerElement(Node possibleNode) {
        Element result;
        if(possibleNode.GetRight( ) == null)  //no or one child on left - as large as I can get???
            result = possibleNode.GetData( );
        else
            result = GetLargestSmallerElement(possibleNode.GetRight( ));
        return result;
    }
    
    public boolean Delete(String sValue) {
        boolean wasDeleted;
        
        wasDeleted = Retrieve(sValue) != null;  //found? so it WILL be deleted???
        
        if(wasDeleted == true)
            if(theRoot.GetData( ).GetKey( ).equals(sValue)) {
                //System.out.println("Found at ROOT ");
                //theRoot.GetData( ).Display( );
                theRoot = RemoveFoundNode(theRoot);
            }
            else
                if(theRoot.GetData( ).GetKey( ).compareTo(sValue) > 0)
                    theRoot.SetLeft(DeleteNode(sValue, theRoot.GetLeft( )));
                else
                    theRoot.SetRight(DeleteNode(sValue, theRoot.GetRight( )));
        return wasDeleted;
    }
    
    private Node DeleteNode(String sValue, Node anotherRoot) {
        if(anotherRoot.GetData( ).GetKey( ).equals(sValue)) {
                //System.out.println("Found at ROOT ");
                //theRoot.GetData( ).Display( );
                anotherRoot = RemoveFoundNode(anotherRoot);
            }
            else
                if(anotherRoot.GetData( ).GetKey( ).compareTo(sValue) > 0)
                    anotherRoot.SetLeft(DeleteNode(sValue, anotherRoot.GetLeft( )));
                else
                    anotherRoot.SetRight(DeleteNode(sValue, anotherRoot.GetRight( )));
        
        return anotherRoot;
    }
    
    public Element Retrieve(String sValue) {
        Element foundItem;
        
        if(theRoot == null)
            foundItem = null;
        else
            if(theRoot.GetData( ).GetKey( ).equals(sValue))
                foundItem = theRoot.GetData( ).Clone( );
            else
                if(theRoot.GetData( ).GetKey( ).compareTo(sValue) < 0)
                    foundItem = RetrieveNode(sValue, theRoot.GetRight( ));
                else
                    foundItem = RetrieveNode(sValue, theRoot.GetLeft( ));
        
        return foundItem;
    }
    
    public Element RetrieveNode(String sValue, Node anotherRoot) {
        Element foundItem;
        
        if(anotherRoot == null)
            foundItem = null;
        else
            if(anotherRoot.GetData( ).GetKey( ).equals(sValue))
                foundItem = anotherRoot.GetData( ).Clone( );
            else
                if(anotherRoot.GetData( ).GetKey( ).compareTo(sValue) < 0)
                    foundItem = RetrieveNode(sValue, anotherRoot.GetRight( ));
                else
                    foundItem = RetrieveNode(sValue, anotherRoot.GetLeft( ));
        
        return foundItem;
    }
    
    public boolean Add(Element uItem) {
        boolean wasAdded;

        if (IsFull()) {
            wasAdded = false;
        } else {
            wasAdded = true;
            theRoot = AddNode(uItem, theRoot);
        }
        
        //System.out.println("root is now " + theRoot);

        return wasAdded;
    }
    
    public void Display( ) {
        if(theRoot != null) {
             theRoot.GetData( ).Display( );
             DisplayNode(theRoot.GetRight( ));
             /*System.out.printf("%-15s%-15s%-15s\n", 
                theRoot.GetData( ).GetName( ),
                theRoot.GetData( ).GetOffice( ), 
                theRoot.GetData( ).GetPhone( ));*/
             DisplayNode(theRoot.GetLeft( ));
        }
    }
    
    private void DisplayNode(Node anotherRoot) {
        if(anotherRoot != null) {
             DisplayNode(anotherRoot.GetRight( ));
             /*System.out.printf("%-15s%-15s%-15s\n", 
                anotherRoot.GetData( ).GetName( ),
                anotherRoot.GetData( ).GetOffice( ), 
                anotherRoot.GetData( ).GetPhone( ));*/
             anotherRoot.GetData( ).Display( );
             DisplayNode(anotherRoot.GetLeft( ));
        }
    }
    
    /*public void Debug( ) {
        System.out.println("Root:  " + theRoot + "\nLeft:  " +
                theRoot.GetLeft( ) + "\nRight:  " + theRoot.GetRight( ));
    }*/

    private Node AddNode(Element uItem, Node anotherRoot) {
        if (anotherRoot != null) {
            if (uItem.GetName().compareToIgnoreCase(
                    anotherRoot.GetData().GetName()) < 0) {
                anotherRoot.SetLeft(AddNode(uItem, anotherRoot.GetLeft()));
            } else {
                anotherRoot.SetRight(AddNode(uItem, anotherRoot.GetRight( )));
            }
        } else {
            anotherRoot = new Node( );
            anotherRoot.SetData(uItem.Clone( ));
            anotherRoot.SetLeft(null);
            anotherRoot.SetRight(null);
            //anotherRoot.ShowData( );
        }
        
        return anotherRoot;
    }

    public void Create() {
        theRoot = null;
    }

    public boolean IsEmpty() {
        boolean empty;

        empty = (theRoot == null);

        return empty;
    }

    public boolean IsFull() {
        Node nTest;
        Element eTest;
        boolean full;

        nTest = null;
        eTest = null;
        full = false;
        try {
            nTest = new Node();
            eTest = new Element();
        } catch (Exception e) {
            full = true;
        }
        if (nTest == null || eTest == null) {
            full = true;
        }

        nTest = null;
        eTest = null;

        return full;
    }
}
