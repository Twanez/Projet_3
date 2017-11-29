package fr.projet_3.jeu;

public class PlusOuMoins extends Jeu {
	StringBuffer solutionMin, solutionMax;
	
	public void initialisation(String pNbMystere)
	{
		super.initialisation(pNbMystere);
		solutionMin = new StringBuffer();
		solutionMax = new StringBuffer();
		for(int i = 0; i < nbChiffres; i++)
		{
			solutionMin.append(0);
			solutionMax.append(9);
		}
	}

	public String combinaisonAlea() {
		return Integer.toString((int) (Math.random() * Math.pow(10, nbChiffres)));
	}

	public boolean entreeValide(String entree) {
	    try {
	    	int nbEntre = Integer.parseInt(entree);
	    	if (nbEntre < 0 || nbEntre > Math.pow(10, nbChiffres))
	    	{
	    		System.out.println(" -> Erreur : un nombre compris entre 0 et " + (int) (Math.pow(10, nbChiffres) - 1) + " est attendu.");
	    		return false;
	    	}
	    } catch (NumberFormatException nfe) {
	    	System.out.println(" -> Erreur : un nombre est attendu.");
	    	return false;
	    }
		return true;
	}

	public boolean resultat(String entree) {
		System.out.print(" -> Réponse : ");
		for(int i = 0; i < nbChiffres; i++)
		{
			if (nbMystere.charAt(i) > entree.charAt(i))
			{
				System.out.print("+");
				solutionMin.setCharAt(i, (char) (entree.charAt(i) + 1));
			}
			else if (nbMystere.charAt(i) < entree.charAt(i))
			{
				System.out.print("-");
				solutionMax.setCharAt(i, (char) (entree.charAt(i) - 1));
			}
			else
			{
				System.out.print("=");
				solutionMin.setCharAt(i, entree.charAt(i));
				solutionMax.setCharAt(i, entree.charAt(i));
			}
		}
		return estNbMystere(entree);
	}

	public String propositionOrdi() {
		String proposition = "";
		for (int i = 0; i < nbChiffres; i++)
		{
			proposition += (int) ( (double) (solutionMax.charAt(i) + solutionMin.charAt(i) - 2 * '0') / 2.0 + Math.random() );
		}
		return proposition;
	}
}
