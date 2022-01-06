package numberBaseConverter;

import java.util.Scanner;


public class NumberBaseConverter {

    /*
        Programmer: Darwin Castillo
        Project Description: This program will obtain a decision from the user. 
                             The program will also obtain a number and a base 
                             to convert the number to from the user. The 
                             converted number will be displayed to the user
                             when the number is equal to or less than 0.
     */
    public static void main(String[] args) {
        int  userNumber, userBase;
        char userChoice;
        
        userChoice = 'Y';
        
        do{
            userNumber = getUserNumber( );
            userBase = getUserBase( );
            calculateRemainder(userNumber, userBase);
            userChoice = getUserChoice( );
        }while(userChoice != 'N');
        
    }
    
    public static int getUserNumber( ) {
        int userNumber;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        
        do { 
            System.out.println("\nEnter a positive number: ");
            userNumber = keyboard.nextInt( );  
            
            if(userNumber <= 0) {
                System.out.println("Not a positive number.");
            }
        }while(userNumber <= 0);
        
        return userNumber;
    }
    
    public static int getUserBase( ) {
        int userBase;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        
        do { 
            System.out.println("\nEnter a base to use (2-16): ");
            userBase = keyboard.nextInt( ); 
            
            if(userBase < 2 || userBase > 16) {
                System.out.println("Not a base that can be used.");
            }
        }while(userBase < 2 || userBase > 16);
        
        return userBase;
    }
    
    public static char getUserChoice( ) {
        char userChoice;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        
        do { 
            System.out.println("\n\nDid you want to convert another number? ");
            userChoice = keyboard.nextLine( ).toUpperCase( ).charAt(0); 
            
            if(userChoice != 'N'  && userChoice != 'Y') {
                System.out.println("Not a correct option.");
            }
        }while(userChoice != 'N' && userChoice != 'Y');
        
        return userChoice;
    }
    
    public static void calculateRemainder(int userNumber, int userBase) {
        int remainder;
        
        if(userNumber != 0) {
            remainder = userNumber % userBase;
            
            userNumber = userNumber / userBase;
            
            calculateRemainder(userNumber, userBase);
            
            if(remainder == 10){
                System.out.print('A');
            }
            else {
                if(remainder == 11){
                    System.out.print('B');
                }
                else {
                    if(remainder == 12){
                        System.out.print('C');
                    }
                    else {
                        if(remainder == 13){
                            System.out.print('D');
                        }
                        else {
                            if(remainder == 14){
                                System.out.print('E');
                            }
                            else {
                                if(remainder == 15){
                                    System.out.print('F');
                                }
                                else {
                                    System.out.print(remainder);
                                }
                            }
                        }
                    }
                }  
            }
            
        }
    
    }
    
}
