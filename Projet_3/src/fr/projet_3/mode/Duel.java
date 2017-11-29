package fr.projet_3.mode;

import fr.projet_3.jeu.*;

public class Duel extends Challenger {
	protected Jeu jeuOrdi;
	protected String entreeOrdi;

	@Override
	protected void initialisation() {
		jeuOrdi = (Jeu) jeu.clone();
		super.initialisation();
		jeuOrdi.initialisation(recupEntree("Nombre à faire deviner par l'ordinateur : "));
	}

	@Override
	protected boolean tour(int nbToursEcoules) {
		afficheNbEssais(nbToursEcoules, true);
		
		System.out.println("À vous :");
		entree = recupEntree("\nProposition : ");
		jeu.resultat(entree);
		
		entreeOrdi = jeuOrdi.propositionOrdi();
		System.out.println("\n\nÀ l'ordinateur" + "\nProposition : " + entreeOrdi);
		jeuOrdi.resultat(entreeOrdi);
		
		return (jeu.estNbMystere(entree) || jeuOrdi.estNbMystere(entreeOrdi));
	}

	@Override
	protected void finPartie(int nbToursEcoules) {
		if (jeu.estNbMystere(entree) && jeuOrdi.estNbMystere(entreeOrdi))
			System.out.println("\n\nEgalité ! Le nombre mystère était bien " + entree + ". Trouvé en " + nbToursEcoules + " essais.");
		else if (jeu.estNbMystere(entree))
			System.out.println("\n\nBravo ! Le nombre mystère était bien " + entree + ". Trouvé en " + nbToursEcoules + " essais.");
		else
			System.out.println("\n\nDommage ! L'ordinateur a trouvé votre nombre en " + nbToursEcoules + " essais. Le nombre mystère était " + jeu.getNbMystere() + ".");
	}
}
