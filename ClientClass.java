import java.net.*;
import java.io.*;

class ClientClass {
	public static void main(String args[]) throws Exception {
		Socket client = new Socket("localhost",9001);
		boolean state=true;
        System.out.println("Connected...");

		try {
			BufferedReader br = null, cr=null;
			PrintStream pr = null;
			String str="";  

			//Get input and Output
	        br = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        cr = new BufferedReader(new InputStreamReader(System.in));

	        pr = new PrintStream(client.getOutputStream());
	        
			while(!str.equals("exit") && !str.equals("quit")){
				str = cr.readLine();
				pr.println(str);
				str=br.readLine();
				System.out.println("Meassage from server: "+str);
			}
			br.close();
			pr.close();
			client.close();
		}
		catch (Exception e) {
			System.out.println("Exception in client read/write: " + e.toString()); 
		}
        
		while (state) {
		}
	}
}
