package fr.projet_3.jeu;

import fr.projet_3.Menu;

public class Mastermind extends Jeu {
	int nbCouleurs = Menu.paramNombre(6, "NOMBRE_COULEURS");
	boolean combinaisons[] = new boolean[ (int) Math.pow(nbCouleurs, nbChiffres) ];
	
	@Override
	public String combinaisonAlea() {
		String combinaison = "";
		for (int i = 0; i < nbChiffres; i++)
			combinaison += Integer.toString( (int)(Math.random() * nbCouleurs) );
		return combinaison;
	}

	@Override
	public boolean entreeValide(String entree) {
		try {
	    	int nbEntre = Integer.parseInt(entree);
	    	if (nbEntre < 0 || nbEntre > Math.pow(10, nbChiffres))
	    	{
	    		System.out.println(" -> Erreur : un nombre compris entre 0 et " + (int) (Math.pow(10, nbChiffres) - 1) + " est attendu.");
	    		return false;
	    	}
	    	for (int i = 0; i < nbChiffres; i++)
	    	{
	    		if (entree.charAt(i) >= '0' + nbCouleurs)
	    		{
	    			System.out.println(" -> Erreur : seuls les chiffres allant de 0 à " + (nbCouleurs - 1) + "sont attendus.");
	    			return false;
	    		}
	    	}
	    } catch (NumberFormatException nfe) {
	    	System.out.println(" -> Erreur : un nombre est attendu.");
	    	return false;
	    }
		return true;
	}

	@Override
	public boolean resultat(String entree) {
		char tabNbMystere[] = nbMystere.toCharArray();
		int places = 0, presents = 0;
		for (int i = 0; i < nbChiffres; i++)
		{
			if (entree.charAt(i) == tabNbMystere[i])
			{
				places++;
				tabNbMystere[i] = '\0';
			}
		}
		for (int i = 0; i < nbChiffres; i++)
		{
			if (tabNbMystere[i] != '\0')
			{
				int index = indexTab(entree.charAt(i), tabNbMystere);
				if (index != -1)
				{
					presents++;
					tabNbMystere[index] = ' ';
				}
			}
		}
		
		System.out.print(" -> Réponse : ");
		switch (presents)
		{
			case 0:
				if (places == 0)
					System.out.println("Aucun présent");
				break;
			case 1:
				System.out.print("1 présent");
				break;
			default:
				System.out.print(presents + " présents");
				break;
		}
		if (presents != 0 && places != 0)
			System.out.print(", ");
		switch (places)
		{
			case 0:
				break;
			case 1:
				System.out.print("1 bien placé");
				break;
			default:
				System.out.print(places + " bien placés");
				break;
		}
		
		majCombinaison(entree, places, presents);
		
		return estNbMystere(entree);
	}

	private int indexTab(char element, char tab[])
	{
		for (int i = 0; i < tab.length; i++)
		{
			if (tab[i] == element)
				return i;
		}
		return -1;
	}

	@Override
	public String propositionOrdi() {
		int nbCombiPossibles = 0, numProposition, i;
		for (i = 0; i < combinaisons.length; i++) {
			if (combinaisons[i])
				nbCombiPossibles++;
		}
		numProposition = (int) ( Math.random() * nbCombiPossibles );
		for (i = 0; numProposition >= 0; i++) {
			if (combinaisons[i])
				numProposition--;
		}
		
		return combinaisonNumero(i - 1);
	}
	
	
	public void initialisation(String pNbMystere) {
		super.initialisation(pNbMystere);
		for (int i = 0; i < combinaisons.length; i++)
			combinaisons[i] = true;
	}
	
	private void majCombinaison(String entreeOrdi, int pPlaces, int pPresents) {
		char combi[] = new char[nbChiffres];
		for (int i = 0; i < Math.pow(nbCouleurs, nbChiffres); i++) {
			combi = combinaisonNumero(i).toCharArray();
			int places = 0, presents = 0;
			for (int j = 0; j < nbChiffres; j++)
			{
				if (entreeOrdi.charAt(j) == combi[j])
				{
					places++;
					combi[j] = '\0';
				}
			}
			for (int j = 0; j < nbChiffres; j++)
			{
				if (combi[j] != '\0')
				{
					int index = indexTab(entreeOrdi.charAt(j), combi);
					if (index != -1)
					{
						presents++;
						combi[index] = ' ';
					}
				}
			}
			
			if (places != pPlaces || presents != pPresents)
				combinaisons[i] = false;
		}
	}
	
	private String combinaisonNumero(int numero) {
		return ajouterZeros(Integer.toString(numero, nbCouleurs));
	}
	
	private String ajouterZeros(String nombre)
	{
		String chaine = "";
		for (int i = 0; i < nbChiffres - nombre.length(); i++)
		{
			chaine += '0';
		}
		return chaine + nombre;
	}
	
	

}
