package fr.projet_3.jeu;

import fr.projet_3.Menu;

public class Mastermind extends Jeu {
	int nbCouleurs = Menu.paramNombre(6, "NOMBRE_COULEURS"), minPlaces = nbChiffres, minPresents = nbChiffres;
	int tabCouleurs[][] = new int[nbCouleurs][2];
	String propGauche = "", propDroite = "";
	StringBuffer coulGauche = new StringBuffer(""), coulDroite = new StringBuffer("");
	
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
		
		if (propDroite != "")
		{
			if (coulGauche.indexOf("-") == -1 && coulDroite.indexOf("-") == -1) {
				
			} else {
				int nbTours = propDroite.charAt(0) - '0';
				minPlaces = Math.min(minPlaces, places);
				minPresents = Math.min(minPresents, presents);
				tabCouleurs[nbTours][0] = places;
				tabCouleurs[nbTours][1] = presents;
				
				if (nbTours > nbChiffres/2 && tabCouleurs[0][0] == -1)
				{
					tabCouleurs[0][0] = minPlaces;
					tabCouleurs[0][1] = minPresents;
					for (int i = 0; i < tabCouleurs[0][0]; i++)
						coulGauche.setCharAt(coulGauche.indexOf("-"), '0');
					
					for (int i = 0; i < tabCouleurs[0][1]; i++)
						coulDroite.setCharAt(coulDroite.indexOf("-"), '0');
							
					for (int i = 1; i <= nbTours; i++)
					{
						tabCouleurs[i][0] -= tabCouleurs[0][0];
						tabCouleurs[i][1] -= tabCouleurs[0][1];
					}
					for (int i = 1; i <= nbTours; i++)
					{
						for (int j = 0; j < tabCouleurs[i][0]; j++)
							coulDroite.setCharAt(coulDroite.indexOf("-"), (char) ('0' + i));
						
						for (int j = 0; j < tabCouleurs[i][1]; j++)
							coulGauche.setCharAt(coulGauche.indexOf("-"), (char) ('0' + i));
					}
				} else if (nbTours > nbChiffres/2) {
					tabCouleurs[nbTours][0] -= tabCouleurs[0][0];
					tabCouleurs[nbTours][1] -= tabCouleurs[0][1];
					
					for (int i = 0; i < tabCouleurs[nbTours][0]; i++)
						coulDroite.setCharAt(coulDroite.indexOf("-"), (char) ('0' + nbTours));
					
					for (int i = 0; i < tabCouleurs[nbTours][1]; i++)
						coulGauche.setCharAt(coulGauche.indexOf("-"), (char) ('0' + nbTours));
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
		if (propDroite == "") {
			tabCouleurs[0][0] = -1;
			for (int i = 0; i < nbChiffres/2; i++) {
				propGauche += '0';
				coulGauche.append('-');
			}
			for (int i = nbChiffres/2; i < nbChiffres; i++) {
				propDroite += '1';
				coulDroite.append('-');
			}
		} else {
			if (coulGauche.indexOf("-") == -1 && coulDroite.indexOf("-") == -1) {
				
			} else {
				char chiffre = propDroite.charAt(0);
				propDroite = "";
				for (int i = nbChiffres/2; i < nbChiffres; i++) {
					propDroite += (char) (chiffre + 1);
				}
			}
		}
		return propGauche + propDroite;
	}

}
