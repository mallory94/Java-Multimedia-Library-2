package appli;

public class Appli {

	public static void main(String[] args) {
		  Class c = null;
		try {
			c = Class.forName(ChargeurDeClasse.class.getName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	      String innerClassName = c.getName();
	      System.out.println("The fully qualified name of the inner class is: " + innerClassName);

	}

}
