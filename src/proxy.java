import java.net.*;

public class proxy {

	public static void main(String[] args) {
		try{
			ServerSocket s = new ServerSocket(8080);
			for(;;){
				WebServe w = new WebServe(s.accept())
				w.requisicao();
				
			}
		}

	}

}
