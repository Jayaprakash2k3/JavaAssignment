import java.io.*;
import java.net.*;
import java.util.Scanner;
class Writer extends Thread
{


    Scanner sc= new Scanner(System.in);
    DataOutputStream dout;
    Writer(Socket s) throws Exception
    {
        dout= new DataOutputStream(s.getOutputStream());
        this.start();    }
    public void run()
        {
            while(true)
            try{
                System.out.print("You: ");   
                String z=sc.nextLine();
                dout.writeUTF(z);     

               }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

}
class Reader extends Thread
{

    DataInputStream din;
    Reader(Socket s) throws Exception
    {
        din= new DataInputStream(s.getInputStream());     
        this.start();   
    }
    public void run()
        {
            while(true)
            try{
                String q=din.readUTF();
                System.out.println("\b\b\b\b\b"+"Stranger: "+q);
                Thread.sleep(400);
               }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

}
class client
    {
        public static void main(String args[]) throws Exception
        {
        Socket s= new Socket("localhost",12345);
        Reader r = new Reader(s);
        Writer w = new Writer(s);

            }

    }