import java.net.*;
import java.io.*;
import java.util.ArrayList;

public class webretriever {
	Socket s;
	webretriever w;
	OutputStream out;
	InputStream in;

	public webretriever(String server, int port) throws IOException, UnknownHostException {
			this.s=new Socket (server, port);
			out=s.getOutputStream();
			in=s.getInputStream();
	}
	
	void Request(String path){
		try{
			String mensage= "GET " + path + "\n\n";
			out.write(mensage.getBytes());
			out.flush();
		}catch (IOException e){
			System.err.println("Error in HTTP request");
		}
	}

	ArrayList<Integer> getResponse(){
		int c;
		ArrayList<Integer> valores= new ArrayList<Integer>();
		try{
			while((c=in.read())!= -1)
				valores.add(c);
		} catch (IOException e){
			System.err.println("IOException in reading from Web server");  
		}
		return valores;
	}
	
	public void close(){
		try {
			in.close(); out.close(); s.close();
		} catch (IOException e){
			System.err.println("IOException in closing connection");
		}
	}
}