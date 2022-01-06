package registerSystem;

import java.util.Scanner;
import java.io.*;

public class RegisterSystem {
    
    /*
        Programmer: Darwin Castillo
        Project Description: This program will obtain a decision from the user. 
                             The program will be able to add and remove a
                             customer from a queue. The program will record 
                             the wait and service times of a customer into a
                             file. Also, the averages will be calculated and 
                             displayed to the user.
     */

    public static void main(String[] args) throws IOException {
        QueueADT theQueue;
        CashRegister [ ] registers;
        String userChoice;
        char storeStatus;
        
        theQueue = new QueueADT( );
        theQueue.Create(5);
        
        registers = new CashRegister[3];
        
        for(int cnt = 0; cnt < registers.length; cnt++) {
            registers[cnt] = new CashRegister( );
            registers[cnt].setStatus('C');
        }
         
        storeStatus = 'C';
        
        do {
            userChoice = getUserChoice( );
            storeStatus = performChoice(userChoice, theQueue, registers, 
                                        storeStatus);
        }while(!userChoice.equals("EP"));
        
        checkCustomers(userChoice, registers, theQueue, storeStatus);
        
        computeAverages( );
        
    }
    
    public static String getUserChoice( ) {
        //Description:  Sets the user choice from keyboard input.
        //Preconditions:
        //  User input must be validated.
        //Postconditions:
        //  A user option will be set.
        
        String userChoice;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        userChoice = "E";
        
        while(!(userChoice.equals("CS") || userChoice.equals("EP") || 
               userChoice.equals("CE") || userChoice.equals("SS") ||
               userChoice.equals("ES") || userChoice.equals("OR") ||
               userChoice.equals("CR"))){
            System.out.println("CS - change store status\n"
                               + "EP - end processing\n"
                               + "CE - customer enters\n"
                               + "SS - customer start service\n"
                               + "ES - customer ends service\n"
                               + "OR - open a register\n"
                               + "CR - close register\n");
            
            userChoice = keyboard.nextLine().toUpperCase();
            
            if(!(userChoice.equals("CS") || userChoice.equals("EP") || 
               userChoice.equals("CE") || userChoice.equals("SS") ||
               userChoice.equals("ES") || userChoice.equals("OR") ||
               userChoice.equals("CR"))) {
                System.out.println("Please enter a correct option");
            }
        
        }
  
        return userChoice; 
    } 
    
    public static char performChoice(String userChoice, QueueADT theQueue, 
                                    CashRegister [ ] registers, 
                                    char storeStatus) throws IOException  {
        //Description: Completes the task based on the input given 
        // from the user.
        //Preconditions:
        //  User input must be given and match a option provided.
        //Postconditions:
        //  The program will complete the option given by the user. 
        
        if(userChoice.equals("CE")) {
            customerEnter(theQueue, storeStatus);
        }
        else {
            if(userChoice.equals("SS")) {
                customerStart(theQueue, registers);
            }
            else {
                if(userChoice.equals("ES")) {
                    customerEnd(registers);
                }
                else {
                    if(userChoice.equals("OR")) {
                        registerOpen(registers);
                    }
                    else {
                        if(userChoice.equals("CR")) {
                            registerClose(registers);
                        }
                        else {
                            if(userChoice.equals("CS")) {
                                storeStatus = openCloseStore(storeStatus);
                            }
                        }
                    }
                }
            }
        }
         
        return storeStatus;
    }
    
    public static void customerEnter(QueueADT theQueue, char storeStatus) {
        //Description: Adds a customer to the queue ands sets the arrival time.
        //Preconditions:
        //  The queue must be created.
        //  The queue must not be full.
        //  The store must be set to open.
        //Postconditions:
        //  Customer will be added to the queue.
        //  The time entered will be recorded. 
        
        Element customer;
        
        customer = new Element();
        
        if(storeStatus == 'O' && !theQueue.IsFull()) {
            customer.SetEnter(System.currentTimeMillis());
            theQueue.Enqueue(customer);
            System.out.println("Customer is waiting.\n");
        }
        else {
            System.out.println("Store is not open or is full.\n");
        }

    }
    
    public static void customerStart(QueueADT theQueue, 
                       CashRegister [ ] registers) {
        //Description: Will remove the customer from the queue and set their
        //  start of service.
        //Preconditions:
        //  Registers are created.
        //  At least one register is open.
        //  The queue is not empty.
        //Postconditions:
        //  The customer will be removed from the queue and moved into an open
        //  register. 
        //  The customer start service time will be recorded.
        
        Element customer;
        char serveOption;
        Scanner keyboard;

        keyboard = new Scanner(System.in);
        serveOption = 'Y';

        for(int cnt = 0; cnt <= 2; cnt++) {
            if(registers[cnt].GetStatus() == 'O' && !theQueue.IsEmpty()) {
                System.out.println("Register " + cnt + " is open, do you want "
                                   + "to serve the customer here?\n");
                
                serveOption = keyboard.nextLine().toUpperCase().charAt(0);
            
                if(serveOption == 'Y') {
                    customer = theQueue.Dequeue();
                    registers[cnt].StartCust(customer);
                    System.out.println("Customer is being served.\n");
                }  
            }
            
            else {
                System.out.println("Register " +cnt+ " is unavailable or there "
                                    + "are no customers to serve.\n");
            }
        }
    }
    
    public static void customerEnd(CashRegister [ ] registers) 
                                                           throws IOException {
        //Description: The customer will be removed from the register and 
        //  their end of service time will be recorded. Also, their wait time
        //  and service time will be recorded.
        //Preconditions:
        //  Their is a customer on a register.
        //Postconditions:
        //  The program will remove customer from a busy register.
        //  Wait time and Service time will be stored into a file.
        
        Element customer;
        char endOption;
        Scanner keyboard;
        File waitFile;
        FileWriter waitFileFW;
        PrintWriter custTimePW;
                
        waitFile = new File("CustomerTimes");
        waitFileFW = new FileWriter(waitFile, true);
        custTimePW = new PrintWriter(waitFileFW);
        
        keyboard = new Scanner(System.in);
        endOption = 'Y';
        
        for(int cnt = 0; cnt <= 2; cnt++) {
            if(registers[cnt].GetStatus() == 'B') {
                System.out.println("Register " + cnt + " is busy, do you want "
                                    + "to end service?\n");
                
                endOption = keyboard.nextLine().toUpperCase().charAt(0);
            
                if(endOption == 'Y') {
                    customer = registers[cnt].EndCust();
                    custTimePW.println(customer.GetWait());
                    custTimePW.println(customer.GetServe());
                    System.out.println("Customer has been served.\n");
                }
            }
            
             else {
                System.out.println("No customers at Register " + cnt);
            }
        }
        
        custTimePW.close();
    }
    
    public static void registerOpen(CashRegister [ ] registers) { 
        //Description: Opens a register that is closed.
        //Preconditions:
        //  Register is closed.
        //Postconditions:
        //  A closed register will be set to open.
        
        char openOption;
        Scanner keyboard;

        keyboard = new Scanner(System.in);
        openOption = 'Y';
        
        for(int cnt = 0; cnt <= 2; cnt++) {
            if(registers[cnt].GetStatus() == 'C') {
                System.out.println("Register " +cnt+ " is closed, do you want "
                                   + "to open?\n");
                
                openOption = keyboard.nextLine().toUpperCase().charAt(0);
                
                if(openOption == 'Y') {
                registers[cnt].Open();
                System.out.println("Register was opened\n");
                }
            }
            
            else {
                System.out.println("Register" + cnt + "is open\n");
            }
        }
    }
    
    public static void registerClose(CashRegister [ ] registers) { 
        //Description: Closes a register that is open.
        //Preconditions:
        //  A Register is opened.
        //Postconditions:
        //  An open register will be set to close.
        
        char closeOption;
        Scanner keyboard;

        keyboard = new Scanner(System.in);
        closeOption = 'Y';
        
        for(int cnt = 0; cnt <= 2; cnt++) { 
            if(registers[cnt].GetStatus( ) == 'O') {
                System.out.println("Register " + cnt + " is open, do you want "
                                   + "to close?\n");
                
                closeOption = keyboard.nextLine().toUpperCase().charAt(0);
            
                if(closeOption == 'Y') {
                    registers[cnt].Close();
                    System.out.println("Register was closed\n");
                }
                
                else {
                    System.out.println("Register" + cnt + "is closed.\n");
                }
            }
        }
    }
    
    public static char openCloseStore(char storeStatus) { 
        //Description: Will reverse the status of the store.
        //Preconditions:
        //  The store must be opened or closed.
        //Postconditions:
        //  Store will be set to open from close.
        //  Store will be set to close from open.
        
        if(storeStatus == 'O') {
            storeStatus = 'C';
            System.out.println("Store is closed.\n");
        }
        else {
            storeStatus = 'O';
            System.out.println("Store is open.\n");
        }
        
        return storeStatus;
    }
    
    public static void checkCustomers(String userChoice, 
                                      CashRegister [ ] registers, 
                                      QueueADT theQueue, char storeStatus) 
                                      throws IOException {
        //Description: Will check to see if there are any customers being
        //  served or in the queue and remove them. Also, will close any 
        // register that is open.
        //Preconditions:
        //  Register must be created.
        //  The queue must be created.
        //  The store must have a status set.
        //Postconditions:
        //  Any remaining customers in the queue will be served.
        //  An open register will be set to close.
        //  Any customers in the queue will be removed.
        //  The store will be closed if open.
        
        Element customer;
        File waitFile;
        FileWriter waitFileFW;
        PrintWriter custTimePW;
                
        waitFile = new File("CustomerTimes");
        waitFileFW = new FileWriter(waitFile, true);
        custTimePW = new PrintWriter(waitFileFW);
        
            for(int cnt = 0; cnt <= 2; cnt++) {
                if(registers[cnt].GetStatus() == 'B') {
                    customer = registers[cnt].EndCust();
                    custTimePW.println(customer.GetWait());
                    custTimePW.println(customer.GetServe());
                    System.out.println("Register " + cnt + " is was busy,"
                                       + " customer has been served.");
                }
                
                if(registers[cnt].GetStatus() == 'O') {
                     registers[cnt].Close();
                     System.out.println("Register " + cnt + " is closed.\n");
                }
            }

            custTimePW.close();
            
            while(!theQueue.IsEmpty()) {
                customer = theQueue.Dequeue();
                System.out.println("Customers were sent home\n");
            }
            
            if(storeStatus == 'O') {
            storeStatus = 'C';
            System.out.println("Store is closed.\n");
            }
    }
    
    public static void computeAverages( ) throws IOException {
        //Description: Will calculate the average wait and serve time and 
        //  display it to the user.
        //Preconditions:
        //  At least one customer must be served.
        //Postconditions:
        //  Wait average will be calculated and displayed.
        //  Serve average will be calculated and diplayed.
        
        long [ ] averages;
        File waitFile;
        Scanner waitFileSC;
        int avgCnt;
        
        avgCnt = 0;
        
        averages = new long[2];
        
        waitFile = new File("CustomerTimes");
        waitFileSC = new Scanner(waitFile);
        
        while(waitFileSC.hasNext()) {
            averages[0] = averages[0] + waitFileSC.nextLong();
            averages[1] = averages[1] + waitFileSC.nextLong();
            avgCnt++;
        }

        if(avgCnt > 0) {
            averages[0] = averages[0] / avgCnt;
            averages[1] = averages[1] / avgCnt;
        
            System.out.printf("Wait Average: " + averages[0] +"\nServe "
                    + "Average: " + averages[1] + "\n\n");
        
        }
        else {
            System.out.println("There were no customers.\n");
        }
        
    }
  
}
