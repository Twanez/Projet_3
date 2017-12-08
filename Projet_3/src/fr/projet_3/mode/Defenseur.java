package fr.projet_3.mode;

public class Defenseur extends Mode {

	protected void initialisation() {
		jeu.initialisation(recupEntree("Nombre Mystère : "));
	}

	protected boolean tour(int nbToursEcoules) {
		if (nbToursEcoules > nbTours)
			return true;
		afficheNbEssais(nbToursEcoules, false);
		entree = jeu.propositionOrdi();
		System.out.println("\nProposition : " + entree);
		return jeu.resultat(entree);
	}

	protected void finPartie(int nbToursEcoules) {
		if(jeu.estNbMystere(entree))
			System.out.println("\n\nL'ordinateur a trouvé le nombre mystère (" + entree + ") en " + nbToursEcoules + " essais.");
		else
			System.out.println("\n\nL'ordinateur n'a pas trouvé le nombre mystère. (" + jeu.getNbMystere() + ")");
	}

}
