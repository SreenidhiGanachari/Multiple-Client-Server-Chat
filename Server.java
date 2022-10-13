importjava.io.BufferedReader;
importjava.io.InputStreamReader;
importjava.io.PrintWriter;
importjava.net.ServerSocket;
importjava.net.Socket;
importjava.util.ArrayList;

publicclass MultipleServer1 {
publicstaticvoidmain(String[]args) {
	ArrayList<ServerThread>threadList=newArrayList<>();
	try(ServerSocketserversocket=newServerSocket(1356)) {
		while(true) {
			Socket socket=serversocket.accept();
			ServerThreadserverThread=newServerThread(socket,threadList);
			threadList.add(serverThread);
			serverThread.start();
		}
	}
	catch(Exception e) {
		System.out.println("Error occured in main:"+e.getStackTrace());
	}
}
}
classServerThreadextendsThread{
	private Socket socket;
	privateArrayList<ServerThread>threadList;
	privatePrintWriteroutput;
	
	publicServerThread(Socket socket, ArrayList<ServerThread>threads) {
		this.socket=socket;
		this.threadList=threads;
	}
	publicvoidrun() {
		try {
			BufferedReaderinput=newBufferedReader(newInputStreamReader(socket.getInputStream()));
			output=newPrintWriter(socket.getOutputStream(),true);
			while(true) {
				String outputString=input.readLine();
				if(outputString.equals("exit")) {
					break;
				}
				printToAllClients(outputString);
				System.out.println("Server recieved"+outputString);
			}}
			catch(Exception e) {
				System.out.println("Error occured"+e.getStackTrace());
			}
		}
	privatevoidprintToAllClients(String outputString) {
		for(ServerThreadsT:threadList) {
			sT.output.println(outputString);
		}
	}
}

