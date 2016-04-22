import java.net.*;
import java.util.StringTokenizer;
import java.io.*;
import java.util.ArrayList;

//Classe que faz o recebimento de pedidos e encaminhamento de respostas.
public class webserve {
	Socket s;
	webserve w;
	OutputStream out;
	BufferedReader in;
	String resource;

	//Construtor
	public webserve(Socket s) throws IOException {
			this.s = s;
			out=s.getOutputStream();
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	}
	
	//Recebe a path, procura o GET e pega o termo seguinte a ele
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
	
	//Recebe a resposta em um array de inteiros e encaminha um texto
	void returnResponse(ArrayList<Integer> valores) {
		int count;
		try{
			for(count=0; count<valores.size(); count++)
				out.write(valores.get(count));
		} catch (IOException e){
			System.err.println("IOException in reading in Web server");
		}
	}
	
	//Fecha os sockets e as streams
	public void close(){
		try {
			in.close(); out.close(); s.close();
		} catch (IOException e){
			System.err.println("IOException in closing connection");
		}
	}
}