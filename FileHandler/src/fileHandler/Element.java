package fileHandler;

public class Element {
    
    private String empName;
    private String empDate;
    private String empTask;
    private String empStartTime;
    private String empEndTime;
    private String empComment;
    
    public void SetInfo(String nName, String nDate, String nTask,
            String nStartTime, String nEndTime, String nComment) {
        empName = nName;
        empDate = nDate;
        empTask = nTask;
        empStartTime = nStartTime;
        empEndTime = nEndTime;
        empComment = nComment;
    }
    
    public String GetName() {
        return empName;
    }
    
    public String GetDate() {
        return empDate;  
    }
    
    public String GetTask() {  
        return empTask;        
    }
    
    public String GetStartTime() {
        return empStartTime; 
    }
    
    public String GetEndTime() {
        return empEndTime;  
    }
    
    public String GetComment() { 
        return empComment;   
    }
    
    public Element Clone() {
        Element clonedElement;
        
        clonedElement = new Element();
        clonedElement.SetInfo(empName, empDate, empTask, empStartTime, 
                              empEndTime, empComment);
        
        return clonedElement;   
    }
    
    public String GetKey() { 
        return empName;
    }   
}
