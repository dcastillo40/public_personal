package binary.search.tree;

public class Node {
    private Element data;
    private Node left;
    private Node right;
    
    public void SetData(Element uData) {
        data = uData;   //BST will clone data sent in here...
    }
    
    public Element GetData( ) {
        return data;  //BST clone the data retrieved from here...
    }
    
    public void SetLeft(Node uLeft) {
        left = uLeft;
    }
    
    /*public void ShowData( ) {
        System.out.printf("%-25s%-15s%-15s   %s %s\n", data.GetName( ),
                data.GetOffice( ), data.GetPhone( ), left, right);
    }*/
    
    public void SetRight(Node uRight) {
        right = uRight;
    }
    
    public Node GetLeft( ) {
        return left;
    }
    
    public Node GetRight( ) {
        return right;
    }
}
