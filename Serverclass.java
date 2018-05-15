import java.net.*;
import java.io.*;

class ServerClass {
	@SuppressWarnings("unused")
	public static void main(String args[]) throws Exception {
		ServerSocket server = new ServerSocket(9001);
		boolean state=true;
        System.out.println("Listening...");
        
		while (state) {
			Socket client = server.accept();
	        System.out.println("Connected client");
	        
			RunnableThread workThread = new RunnableThread(client);
			if(state==false) {
				server.close();
				break;
			}
		}
	}
}

class RunnableThread implements Runnable {
	Thread runner;
	Socket client;
	
	public RunnableThread(Socket c) {
		runner = new Thread(this, "client"); // (1) Create a new thread.
		client = c;
        System.out.println("Client Thread Starting...");
		System.out.println(runner.getName());
		runner.start();
	}
	
	public RunnableThread(String threadName) {
		runner = new Thread(this, threadName); // (1) Create a new thread.
		System.out.println(runner.getName());
		runner.start(); // (2) Start the thread.
	}

	public void run() {
		//DataInputStream din = null;
		//DataOutputStream dout = null;
		BufferedReader br = null;
		PrintStream pr = null;
		String str="";  

		//Display info about this particular thread
		System.out.println(Thread.currentThread());
		System.out.println("connected");
		try {
			//dout=new DataOutputStream(client.getOutputStream());  
			//Get input
	        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        pr = new PrintStream(client.getOutputStream());

			while(!str.equals("exit") && !str.equals("quit")){
				str=br.readLine();
				System.out.println("Meassage from client: "+str);
				pr.println(str);
			}
			br.close();
			pr.close();
			client.close();
		}
		catch (Exception e) {
			System.out.println("Exception in client read/write: " + e.toString()); 
		}
	}
}



