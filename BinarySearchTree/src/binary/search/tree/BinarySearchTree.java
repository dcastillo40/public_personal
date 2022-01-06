package binary.search.tree;

import java.util.Random;

public class BinarySearchTree {

    public static void main(String[] args) {
        BSTreeADT thing = new BSTreeADT( );
        Element temp;
        Random rNum = new Random( );
        int tempNum;
        
        thing.Create( );
        
        temp = new Element( );
        temp.Set("Jack N. Donato", "2-115", "523.786.2455");
        thing.Add(temp);
        temp = new Element( );
        temp.Set("CJ Jackson", "2-117", "231.786.2455");
        thing.Add(temp);
        temp = new Element( );
        temp.Set("KJ Jackson", "2-117", "231.786.2455");
        thing.Add(temp);
        
        for(int cnt = 0; cnt < 10 && !thing.IsFull( ); cnt++) {
            temp = new Element( );
            tempNum = rNum.nextInt(1000);
            System.out.println(tempNum);
            temp.Set("Person" + tempNum, "2-113A", "315.786.2455");
            thing.Add(temp);
        }
        
        System.out.println("*****After all loaded and Jack and CJ*****");
        thing.Display( );
        
        
        
        //temp = thing.Retrieve("Jack N. Donato");
        //if(temp != null)
            //temp.Display( );
        thing.Delete("Jack N. Donato");
        thing.Display( );
        
        
    }

}
