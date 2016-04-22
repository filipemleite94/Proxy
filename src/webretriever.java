import java.net.*;
import java.io.*;
import java.util.ArrayList;

//Encaminha o pedido e recebe a resposta da pagina;
public class webretriever {
	Socket s;
	webretriever w;
	OutputStream out;
	InputStream in;

	//Contrutor
	public webretriever(String server, int port) throws IOException, UnknownHostException {
			this.s=new Socket (server, port);
			out=s.getOutputStream();
			in=s.getInputStream();
	}
	
	//Faz o pedido;
	void Request(String path){
		try{
			String mensage= "GET " + path + "\n\n";
			out.write(mensage.getBytes());
			out.flush();
		}catch (IOException e){
			System.err.println("Error in HTTP request");
		}
	}

	//Recebe a resposta em um array de inteiros
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
	
	//Dá close na Socket e nas streams;
	public void close(){
		try {
			in.close(); out.close(); s.close();
		} catch (IOException e){
			System.err.println("IOException in closing connection");
		}
	}
}