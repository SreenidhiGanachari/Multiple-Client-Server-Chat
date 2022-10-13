importjava.io.BufferedReader;
importjava.io.IOException;
importjava.io.InputStreamReader;
importjava.io.PrintWriter;
importjava.net.Socket;
importjava.util.*;

publicclass MultipleClient1 {
	publicstaticvoidmain(String[]args) {
		try(Socket socket=new Socket("localhost",1356)){
		BufferedReaderinput=newBufferedReader(newInputStreamReader(socket.getInputStream()));
		PrintWriteroutput=newPrintWriter(socket.getOutputStream(),true);
		Scanner scanner=newScanner(System.in);
		String userInput;
		String response;
		String clientName="empty";
		ClientThreadclientThread=newClientThread(socket);
		clientThread.start();
		do {
			if(clientName.equals("empty")) {
				System.out.println("Enter your name:");
				userInput=scanner.nextLine();
				clientName=userInput;
				output.println(userInput);
				if(userInput.equals("exit")) {
					break;
				}
			}
			else {
				String message=("("+clientName+")"+"message:");
				System.out.println(message);
				userInput=scanner.nextLine();
				output.println(message+""+userInput);
				if(userInput.equals("exit")) {
					break;
				}
			}
		}
		while(!userInput.equals("exit"));
		}catch(Exception e) {
			System.out.println("Exception occured in client main:"+e.getStackTrace());
		}
	}
}
classClientThreadextendsThread{
	private Socket socket;
	privateBufferedReaderinput;
	publicClientThread(Socket s)throwsIOException{
		this.socket=s;
		this.input=newBufferedReader (newInputStreamReader(socket.getInputStream()));
		
	}
	publicvoidrun() {
		try {
			while(true) {
				String response=input.readLine();
				System.out.println(response);
			}
		}
		catch(IOExceptione) {
			e.printStackTrace();
		}finally {
			try {
				input.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}

