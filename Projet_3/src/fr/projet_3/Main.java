package fr.projet_3;


public class Main {
	public static void main(String[] args) {
		boolean modeDev = false;
		
		for (int i = 0; i < args.length; i++)
		{
			if (args[i] == "d")
				modeDev = true;
		}
		Menu m = new Menu();
		m.menu(modeDev);
	}
}
