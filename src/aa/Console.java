package aa;

public class Console {
	
	public static void main(String[] args) {
		
	}

	public static void WriteLine(String string) {
		System.out.println(string);
		
	}

	public static void WriteLine(String string, String val) {
		 
		string=string.replaceAll("{0}", val);
		WriteLine( string);
	}

	public static void WriteLine(String string, int length) {
		WriteLine(string,String.valueOf( length));
		
	}

	public static void WriteLine(String string, int id, int id2, float score) {
		string=string.replaceAll("{0}", String.valueOf(id));
		string=string.replaceAll("{1}", String.valueOf(id2));
		string=string.replaceAll("{2}", String.valueOf(score));
		WriteLine( string);
		
	}

}
