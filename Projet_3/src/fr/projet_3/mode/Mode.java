package fr.projet_3.mode;
import java.util.Scanner;

import fr.projet_3.Menu;
import fr.projet_3.jeu.Jeu;

/**
 * <b>La classe Mode représente un mode de jeu. Elle gère le déroulement du jeu.</b>
 * 
 */
public abstract class Mode {
	protected boolean modeDev;
	protected int nbTours = Menu.paramNombre(10, "NOMBRE_ESSAIS");
	protected Jeu jeu;
	protected String entree;
	
	/**
	 * Lance le jeu passé en paramètres.
	 * 
	 * @param pJeu
	 * 		Jeu à lancer.
	 * 
	 * @param pModeDev
	 * 		Si égal à true, le programme affiche la solution en début de jeu.
	 */
	public void jouer(Jeu pJeu, boolean pModeDev)
	{
		modeDev = pModeDev;
		jeu = pJeu;
		initialisation();
		int i;
		for(i = 1; !tour(i); i++);
		finPartie(i);
	}
	
	/**
	 * Initialise le jeu.
	 */
	protected abstract void initialisation();
	
	/**
	 * Fait se dérouler un tour de jeu.
	 * 
	 * @param nbToursEcoules
	 * 		Numéro du tour actuel.
	 * 
	 * @return Un booléen qui indique si le jeu est terminé (true) ou le contraire (false).
	 */
	protected abstract boolean tour(int nbToursEcoules);
	
	/**
	 * Affiche les résultats de fin de partie.
	 * 
	 * @param nbToursEcoules
	 * 		Nombre de tours écoulés.
	 */
	protected abstract void finPartie(int nbToursEcoules);
	
	/**
	 * Récupère l'entrée de l'utilisateur. Vérifie que celle-ci est valide, sinon redemande l'entrée.
	 * Rajoute des caractères 0 à gauche de la chaîne récupérée afin d'obtenir un nombre de caractères égal au nombre maximal de chiffres du nombre mystère.
	 * 
	 * @param message
	 * 		Chaîne à afficher pour inviter l'utilisateur à rentrer une réponse.
	 * 
	 * @return Une chaîne correspondant à l'entrée de l'utilisateur, agrémentée si nécessaire de 0.
	 */
	protected String recupEntree(String message)
	{
		String entree = "";
		Scanner sc = new Scanner(System.in);
		do
		{
			System.out.print(message);
			entree = ajouterZeros(sc.nextLine());
		} while(!jeu.entreeValide(entree));
		return entree;
	}
	
	/**
	 * Affiche le numéro du tour actuel ainsi que le nombre de tours écoulés.
	 * 
	 * @param nbToursEcoules
	 * 		Numéro du tour actuel.
	 * 
	 * @param duel
	 * 		Si la valeur est false, affiche le nombre d'essais restants.
	 */
	protected void afficheNbEssais(int nbToursEcoules, boolean duel)
	{
		System.out.print("\n\nEssai n°" + nbToursEcoules + ". ");
		if (!duel)
		{
			if (nbToursEcoules == nbTours)
				System.out.print("1 essai restant.");
			else
				System.out.print((nbTours - nbToursEcoules + 1) + " essais restants.");
		}
	}
	
	/**
	 * Ajoute des caractères 0 à la chaîne passée en paramètres, jusqu'à que sa longueur doit égale au nombre maximal de chiffres du nombre mystère.
	 * 
	 * @param nombre
	 * 		Chaîne à agrémenter de 0 si nécessaire.
	 * 
	 * @return La chaîne modifiée.
	 */
	protected String ajouterZeros(String nombre)
	{
		String chaine = "";
		for (int i = 0; i < jeu.getNbChiffres() - nombre.length(); i++)
		{
			chaine += '0';
		}
		return chaine + nombre;
	}
}
