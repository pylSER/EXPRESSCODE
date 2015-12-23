package express.rmi;

public class Server {
	public static void main(String[] args){
		try{
	        System.out.println("Try start server...");
	        RMIServer.init();
	        System.out.println("Server is now running!");
	    }catch (ServerException e) {
	    	e.printStackTrace();
	        System.out.println("Server starts fail!"); 
	        
	    }
	}
}
