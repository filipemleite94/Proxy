import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class webretriever {
	Socket s;
	webserve w;
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
	
	void getResponse() {
		int c;
		try{
			while((c=in.read())!= -1)
				System.out.print((char) c);
		} catch (IOException e){
			System.err.println("IOException in reading from Web server");  
		}
	}
	
	public void close(){
		try {
			in.close(); out.close(); s.close();
		} catch (IOException e){
			System.err.println("IOException in closing connection");
		}
	}
}