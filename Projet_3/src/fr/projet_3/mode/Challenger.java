package fr.projet_3.mode;

public class Challenger extends Mode {

	protected void initialisation() {
		jeu.initialisation(ajouterZeros(jeu.combinaisonAlea()));
		if (modeDev)
			System.out.println("*** Combinaison secrète : " + jeu.getNbMystere() + " ***\n");
	}

	protected boolean tour(int nbToursEcoules) {
		if (nbToursEcoules > nbTours)
			return true;
		afficheNbEssais(nbToursEcoules, false);
		entree = recupEntree("\nProposition : ");
		return jeu.resultat(entree);
	}

	protected void finPartie(int nbToursEcoules) {
		if(jeu.estNbMystere(entree))
			System.out.println("\n\nBravo ! Le nombre mystère était bien " + entree + ". Trouvé en " + nbToursEcoules + " essais.");
		else
			System.out.println("\n\nDommage ! Le nombre mystère était " + jeu.getNbMystere() + ".");
	}

}
