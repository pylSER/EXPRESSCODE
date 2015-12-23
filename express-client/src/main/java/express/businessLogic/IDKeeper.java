package express.businessLogic;
//12
public class IDKeeper {

	private static String id;
	private static String orgID;
	private static String city;
	
	public IDKeeper(){
		id=null;
		orgID=null;
	}
	
	public static String getID(){
		return id;
	}
	
	public static void setID(String i){
		id=i;
	}
	
	public static String getOrgID(){
		return orgID;
	}
	
	public static void setOrgID(String org){
		orgID=org;
	}
	
	public static String getCity(){
		return city;
	}
	
	public static void setCity(String c){
		city=c;
	}
}
