package fileHandler;

import java.util.Scanner;
import java.io.*;

public class FileHandler {
    /*
        Programmer: Darwin Castillo
        Project Description: This program will obtain a decision from the user. 
                             The program will be able to manipulate a file by 
                             adding or subtracing contents from that file. 
                             Also, modifying and displaying the file. The 
                             program will save changes created by the user.
    */
    
    public static void main(String[] args) throws IOException {
        Element empSchedule = new Element();
        ListADT theList = new ListADT();
        theList.Create(10);
        
        
        char userChoice = 'n';
        
        while(userChoice != 'Q') {
            
            userChoice = GetChoice();
            
            PerformChoice(userChoice, theList, empSchedule);
            
        }
        
        System.exit(0);   
    }
    
    public static char GetChoice() {
        char userChoice;
        Scanner kbd = new Scanner(System.in);

        System.out.println("\n(L)oad Schedule\n(S)ave Schedule\n(A)dd Entry\n"
                + "(R)emove Entry\n(D)isplay All Entries\n"
                + "Display All for a (P)erson\nDisplay All for a (T)ask\n"
                + "Display All for a Dat(E)\n(M)odify Entry\n(Q)uit Program");
        
        userChoice = kbd.nextLine().toUpperCase().charAt(0);
        
        return userChoice;
    }
    
    public static void PerformChoice(char userChoice, ListADT theList,
                                     Element empSchedule) throws IOException {
        switch(userChoice) {
            case 'L' : LoadSchedule(theList, empSchedule);
                        break;
            case 'S' : SaveSchedule(theList, empSchedule);
                        break;
            case 'A' : AddEntry(theList, empSchedule);
                        break;
            case 'R' : RemoveEntry(theList, empSchedule);
                        break;
            case 'D' : DisplayAll(theList, empSchedule);
                        break;
            case 'P' : DisplayAllPerson(theList, empSchedule);
                        break;
            case 'T' : DisplayAllTask(theList, empSchedule);
                        break;
            case 'E' : DisplayAllDate(theList, empSchedule);
                        break;
            case 'M' : ModifyEntry(theList, empSchedule);
                        break; 
        }       
    }
    
    public static void LoadSchedule(ListADT theList, Element empSchedule) 
                                    throws IOException {
        String fileName, nName, nDate, nTask, nStartTime, nEndTime, nComment;
        File empFile;
        Scanner empFileSC;

        fileName = GetFileName();
            
        empFile = new File(fileName);
        empFileSC = new Scanner(empFile);
                
        theList.Destroy();
        theList.Create(10);
        
        while(empFileSC.hasNext() && !theList.IsFull()) {
            nName = empFileSC.nextLine();
            nDate = empFileSC.nextLine();
            nTask = empFileSC.nextLine();
            nStartTime = empFileSC.nextLine();
            nEndTime = empFileSC.nextLine();
            nComment = empFileSC.nextLine();
            
            empSchedule = new Element();
            empSchedule.SetInfo(nName, nDate, nTask, nStartTime, nEndTime,
                    nComment);
            
            theList.Add(empSchedule);   
        }
        
        empFileSC.close();
        
        System.out.println("\nFile is loaded.");
    }
    
    public static void SaveSchedule(ListADT theList, Element empSchedule)
                                    throws IOException {
        String fileName;
        
        fileName = GetFileName();
        
        PrintWriter empFilePW; 
                
        empFilePW = new PrintWriter(fileName);
        
        theList.Reset();
        
        while(!theList.AtEnd()) {
            empSchedule = theList.Retrieve();
            
            empFilePW.println(empSchedule.GetName());
            empFilePW.println(empSchedule.GetDate());
            empFilePW.println(empSchedule.GetTask());
            empFilePW.println(empSchedule.GetStartTime());
            empFilePW.println(empSchedule.GetEndTime());
            empFilePW.println(empSchedule.GetComment());
            
            theList.GetNext(); 
        }
        
        empFilePW.close(); 
        
        System.out.println("\nChanges are saved.");
    }
    
    public static String GetFileName() { 
        String fileName;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        
        System.out.println("\nEnter file name: ");
        fileName = keyboard.nextLine();
        
        return fileName;  
    }
    
    public static void AddEntry(ListADT theList, Element empSchedule) {
        String nName, nDate, nTask, nStartTime, nEndTime, nComment;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        
        System.out.println("\nEnter employee name: ");
        nName = keyboard.nextLine();
        System.out.println("Enter date of work: ");
        nDate = keyboard.nextLine();
        System.out.println("Enter task: ");
        nTask = keyboard.nextLine();
        System.out.println("Enter start time: ");
        nStartTime = keyboard.nextLine();
        System.out.println("Enter end time: ");
        nEndTime = keyboard.nextLine();
        System.out.println("Enter comment: ");
        nComment =  keyboard.nextLine();
        System.out.println(" ");
        
        empSchedule = new Element();
        empSchedule.SetInfo(nName, nDate, nTask, nStartTime, nEndTime,
                nComment);
        
        if(theList.Add(empSchedule) == true) {
            System.out.println("Schedule entry was added.");  
        } 
        else {
            System.out.println("Schedule entry was NOT added.");
        } 
    }
    
    public static void RemoveEntry(ListADT theList, Element empSchedule) {
        String empName;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
        
        System.out.println("\nEnter employee name: ");
        empName = keyboard.nextLine();

        if(theList.Delete(empName) == true) {
            System.out.println("Schedule entry was removed.");
        } 
        else { 
            System.out.println("Schedule entry was NOT removed/found.");    
        }        
    }
    
    public static void DisplayAll(ListADT theList, Element empSchedule) {
        theList.Reset();
        
        System.out.format("\n%-20s%-20s%-15s%-15s%-10s%-20s\n", "Employee Name",
                    "Date of Work", "Task", "Start Time", "End Time",
                    "Comment");
        
        while(!theList.AtEnd()) {
            empSchedule = theList.Retrieve();
            
            System.out.format("%-20s%-20s%-15s%-15s%-10s%-20s\n",
                    empSchedule.GetName(), empSchedule.GetDate(),
                    empSchedule.GetTask(), empSchedule.GetStartTime(),
                    empSchedule.GetEndTime(), empSchedule.GetComment());
            
            theList.GetNext();  
        }  
    }
    
    public static void DisplayAllPerson(ListADT theList, Element empSchedule) {
        String empName;
        int cnt;
        Scanner keyboard; 
        
        cnt = 0;
        
        keyboard = new Scanner(System.in);
        
        System.out.println("\nEnter employee name: ");
        empName = keyboard.nextLine();

        System.out.format("\n%-20s%-20s%-15s%-15s%-10s%-20s\n", "Employee Name",
                    "Date of Work", "Task", "Start Time", "End Time",
                    "Comment");
        
        theList.Reset();
        
        while(!theList.AtEnd()) {
            
            empSchedule = theList.Retrieve();
            
            if(empName.equals(empSchedule.GetName())) {   
                System.out.format("%-20s%-20s%-15s%-15s%-10s%-20s\n",
                    empSchedule.GetName(), empSchedule.GetDate(),
                    empSchedule.GetTask(), empSchedule.GetStartTime(),
                    empSchedule.GetEndTime(), empSchedule.GetComment());
                
                cnt++;
            }
            theList.GetNext();  
        }
        
        if(cnt == 0) { 
            System.out.println(empName + " was NOT found.");  
        }  
    }

    public static void DisplayAllTask(ListADT theList, Element empSchedule) {
        String empTask;
        Scanner keyboard;
        int cnt;
        
        keyboard = new Scanner(System.in);
        cnt = 0;
        
        System.out.println("\nEnter task: ");
        empTask = keyboard.nextLine();
        
        System.out.format("\n%-20s%-20s%-15s%-15s%-10s%-20s\n", "Employee Name",
                    "Date of Work", "Task", "Start Time", "End Time",
                    "Comment");
        
        theList.Reset();
        
        while(!theList.AtEnd()) {
            empSchedule = theList.Retrieve();
            
            if(empTask.equals(empSchedule.GetTask())) {
                System.out.format("%-20s%-20s%-15s%-15s%-10s%-20s\n",
                    empSchedule.GetName(), empSchedule.GetDate(),
                    empSchedule.GetTask(), empSchedule.GetStartTime(),
                    empSchedule.GetEndTime(), empSchedule.GetComment());
                
                cnt++;
            }
            theList.GetNext(); 
        }
        
        if(cnt == 0) {
            System.out.println(empTask + " was NOT found.");   
        }  
    }
    
    public static void DisplayAllDate(ListADT theList, Element empSchedule) {
        String empDate;
        Scanner keyboard;
        int cnt;
        
        keyboard = new Scanner(System.in);
        cnt = 0;
        
        System.out.println("\nEnter date: ");
        empDate = keyboard.nextLine();
        
        System.out.format("\n%-20s%-20s%-15s%-15s%-10s%-20s\n", "Employee Name",
                    "Date of Work", "Task", "Start Time", "End Time",
                    "Comment");
        
        theList.Reset();
        
        while(!theList.AtEnd()) {
            empSchedule = theList.Retrieve();
            
            if(empDate.equals(empSchedule.GetDate())) {
                System.out.format("%-20s%-20s%-15s%-15s%-10s%-20s\n",
                    empSchedule.GetName(), empSchedule.GetDate(),
                    empSchedule.GetTask(), empSchedule.GetStartTime(),
                    empSchedule.GetEndTime(), empSchedule.GetComment());
                
                cnt++;
            }
            theList.GetNext();  
        }
        
        if(cnt == 0) {
            System.out.println(empDate + " was NOT found.");   
        } 
    }

    public static void ModifyEntry(ListADT theList, Element empSchedule) {
        String empName, nName, nDate, nTask, nStartTime, nEndTime, nComment;
        Scanner keyboard;
        
        keyboard = new Scanner(System.in);
     
        System.out.println("\nEnter employee name: ");
        empName = keyboard.nextLine();
        
        if(theList.Search(empName) == true) {
            empSchedule = theList.Retrieve();
            
            System.out.println("\nENTER NEW INFORMATION\n");
            System.out.println("Enter employee name: ");
            nName = keyboard.nextLine();
            System.out.println("Enter date of work: ");
            nDate = keyboard.nextLine();
            System.out.println("Enter task: ");
            nTask = keyboard.nextLine();
            System.out.println("Enter start time: ");
            nStartTime = keyboard.nextLine();
            System.out.println("Enter end time: ");
            nEndTime = keyboard.nextLine();
            System.out.println("Enter comment: ");
            nComment =  keyboard.nextLine();
            System.out.println(" ");
            
            theList.Delete(empName);
            empSchedule = new Element();
            empSchedule.SetInfo(nName, nDate, nTask, nStartTime, nEndTime,
                nComment);
            theList.Add(empSchedule);
            
            System.out.println(empName + "'s schedule entry was modified.");  
        } 
        else {
            System.out.println(empName + " was NOT found.");
        } 
    }  
}

