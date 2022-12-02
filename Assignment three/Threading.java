import java.util.Scanner;

class A extends Thread
{
    String s;
    A(String s)
    {
        this.s=s;
        this.start();
    }
    public void run() 
    {
        try
    {
        while(true)
        {
        System.out.println(s);
        Thread.sleep(400);
        }
    }
    catch(Exception e)
    {
        System.out.println(e);
    }
        
    }
}
 public class Threading {
    public static void main(String args[])
    {
        A a= new A("one");
        A b= new A("two");

        
    }
    
}
