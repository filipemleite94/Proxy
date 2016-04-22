import java.net.*;
import java.util.ArrayList;
import java.io.*;

public class proxy {
	public static void main(String[] args) {
		try{
			//Aberto o socket de servidor;
			ServerSocket s = new ServerSocket(8080);
			
			//Laço infinito de recebimento e encaminhamento de pedidos e recebimento e encaminhamento de respostas.
			for(;;){
				webserve w = new webserve(s.accept());
				w.getRequest();
				webretriever wr= new webretriever("www.site.com.br", 80);
				wr.Request(w.resource);
				w.returnResponse(wr.getResponse());
				w.close();
				wr.close();
			}
		} catch(IOException e){
			System.out.println("Error in conection");
		}

	}

}
