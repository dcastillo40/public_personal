package binary.search.tree;

public class Element {
    private String name;
    private String office;
    private String phone;
    
    public String GetKey( ) {
        return name;
    }
    
    public void Display( ) {
        System.out.printf("%-15s%-15s%-15s\n", 
                name,
                office, 
                phone);
    }
    
    public void Set(String uName, String uOffice, String uPhone) {
        name = uName;
        office = uOffice;
        phone = uPhone;
    }
    
    public String GetName( ) {
        return name;
    }
    
    public String GetOffice( ) {
        return office;
    }
    
    public String GetPhone( ) {
        return phone;
    }
    
    public Element Clone( ) {
        Element temp;
        
        temp = new Element( );
        temp.Set(name, office, phone);
        
        return temp;
    }
}
