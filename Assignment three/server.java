import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
class createSocket extends Thread
{
    static int count=0;
    public Socket s;
    public Socket to;
    public DataInputStream read;
    public DataOutputStream write;
    createSocket(Socket s) throws Exception
    {
        this.s=s;
        this.read=new DataInputStream(s.getInputStream());
    }
    
    public void connectClient(Socket a) throws Exception
    {
        to=a;
        this.write=new DataOutputStream(to.getOutputStream());       
        this.start(); 
    }
    public void run()
    {
        while(true)
    {
        try
        {
         Thread.sleep(400);
         String k=read.readUTF();
         sendMsg(k);
        }
        catch(Exception e)
        {
            System.out.println("Error"+e);
            
        }
    }

    }
    public void sendMsg(String z) throws Exception {
        write.writeUTF(z);        
    }
}
class server
    {
        public static void main(String args[]) throws Exception
        {
            int i=0;
            Scanner sc = new Scanner(System.in);
            ServerSocket ss= new ServerSocket(12345);
            List<createSocket> s = new ArrayList<createSocket>();
            List<Socket> assigned= new ArrayList<Socket>();
            while(true)
            {
            if(i%2!=0)
            {
                s.add(new createSocket(ss.accept()));
                createSocket currNode=s.get(s.size()-1);
                createSocket prevNode=s.get(s.size()-2);
                currNode.connectClient(prevNode.s);               
                prevNode.connectClient(currNode.s);               
                DataOutputStream dout = new DataOutputStream(currNode.s.getOutputStream());
                dout.writeUTF("Connected to Client");
                DataOutputStream dout1 = new DataOutputStream(prevNode.s.getOutputStream());
                dout1.writeUTF("Connected to Client");
                
            }
            else
            {
                s.add(new createSocket(ss.accept()));
                DataOutputStream dout = new DataOutputStream(s.get(s.size()-1).s.getOutputStream());
                dout.writeUTF("No Clients Available Please Wait.....");
            }
            i++;
            if(i==10)
            break;                
            }
            ss.close();

        }
    }