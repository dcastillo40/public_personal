package registerSystem;

public class CashRegister {
    //Description: Manages  elements of a cash registers
    //Preconditions:
    //   This requires an object named Element is placed within
    //   the package.
    //   This requires that the Element has a Deep Copy method
    //   named Clone.
    
    private Element customer;
    private char status;
    
    public void setStatus(char nStatus) {
        //Description:  Sets the status of the register.
        //Preconditions:
        //  Status must be set.
        //Postconditions:
        //  Status will be set.
        status = nStatus;
    }
    
    public boolean Open( ) { 
        //Description:  Will change a closed register into an open register.
        //Preconditions:
        //  The register must have a status.
        //Postconditions:
        //  The register will be changed to open.
        boolean success;
        
        if(status == 'C'){
            status = 'O';
            success = true;
        }
        else {
            success = false;
        }
        
        return success;
    }
    
    public boolean Close( ) {
        //Description:  Will change a open register into an closed register.
        //Preconditions:
        //  The register must have a status.
        //Postconditions:
        //  The register will be changed to close.
        
        boolean success;
        
        if(status == 'O') {
            status = 'C';
            success = true;
        }
        else {
            success = false;
        }
        
        return success;        
    }
    
    public char GetStatus( ) {
        //Description:  Will return the current status of the register.
        //Preconditions:
        //  The register must have a status.
        //Postconditions:
        //  The register status will be returned.
        
        return status;
    }
    
    public boolean StartCust(Element givenCustomer) {
        //Description:  Will change the status of a register to indicate that
        //  a customer is occupying it and will begin their start of service
        //  time.
        //Preconditions:
        //  A clone method of the element must exist.
        //  A register must be open.
        //Postconditions:
        //  The customer start service time will be set.
        //  Register status will be set to busy.
        
        boolean success;
        
        if(status == 'O') {
            status = 'B';
            customer = givenCustomer.Clone( );
            customer.SetStartServe(System.currentTimeMillis());
            success = true;
        }
        else {
            success = false;
        }
        
        return success;
    }
    
    public Element EndCust( ) {
        //Description:  Will change the status of a register to indicate that
        //  a customer is not occupying it and will record their end of service
        //  time.
        //Preconditions:
        //  A register must be busy.
        //Postconditions:
        //  The customer end service time will be set.
        //  Register status will be set to open.
        
        Element givenCustomer;
        
        if(status == 'B') {
            status = 'O';
            customer.SetEndServe(System.currentTimeMillis());
            givenCustomer = customer;
        }
        else {
            givenCustomer = null;
        }
        
        return givenCustomer;
    }
    
}
