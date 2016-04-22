import java.net.*;
import java.util.StringTokenizer;
import java.io.*;

public class webserve {
	Socket s;
	webserve w;
	OutputStream out;
	BufferedReader in;
	String resource;

	public webserve(Socket s) throws IOException {
			this.s = s;
			out=s.getOutputStream();
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	void getRequest(){
		try{
			String message;
			
			while ((message=in.readLine())!=null){
				if (message.equals(""))
					break;
				System.err.println(message);
				StringTokenizer t = new StringTokenizer(message);
				String token = t.nextToken();
				if (token.equals("GET"))
					resource=t.nextToken();
			}
		}catch (IOException e){
			System.err.println("Error in receiving web request");
		}
	}
	
	void returnResponse() {
		int c;
		try{
			FileInputStream f = new FileInputStream("."+resource);
			while((c=f.read())!=-1)
				out.write(c);
			f.close();
		} catch (IOException e){
			System.err.println("IOException in reading in Web server");
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