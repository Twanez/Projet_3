package fr.projet_3.jeu;

import org.apache.log4j.Logger;

import fr.projet_3.Menu;

/**
 * <b>La classe Jeu représente un jeu de devinette de nombre.</b>
 *
 */
public abstract class Jeu implements Cloneable {
	private static Logger logger = Logger.getLogger(Menu.class);
	protected int nbChiffres = Menu.paramNombre(4, "NOMBRE_CHIFFRES");
	protected String nbMystere;
	
	public Object clone()
	{
		Object o = null;
		try {
			o = super.clone();
		} catch (CloneNotSupportedException cnse) {
			logger.fatal("Impossible de cloner une instance de la classe Jeu.");
		}
		return o;
	}
	
	/**
	 * Initialise le nombre mystère.
	 * 
	 * @param pNbMystere
	 * 		Le nombre à affecter à nbMystere.
	 */
	public void initialisation(String pNbMystere)
	{
		nbMystere = pNbMystere;
	}
	
	/**
	 * Compare l'entier passé en paramètre avec le nombre mystère, et renvoie true en cas d'égalité.
	 * 
	 * @param entree
	 * 		Nombre à comparer.
	 * 
	 * @return Un booléen exprimant l'égalité (true) ou l'inégalité (false) des deux nombres.
	 */
	public boolean estNbMystere(String entree)
	{
		return (entree.equals(nbMystere));
	}
	
	/**
	 * Retourne le nombre de chiffres maximum contenus dans le nombre mystère.
	 * 
	 * @return Un entier exprimant le nombre de chiffres maximum contenus dans le nombre mystère.
	 */
	public int getNbChiffres()
	{
		return nbChiffres;
	}
	
	/**
	 * Retourne le nombre mystère.
	 * 
	 * @return Une chaîne de caractères correspondant au nombre mystère.
	 */
	public String getNbMystere()
	{
		return nbMystere;
	}
	
	/**
	 * Retourne une combinaison aléatoire qui vérifie les critères d'acceptabilité d'un nombre mystère.
	 * 
	 * @return Une chaîne de caractères correspondant un nombre mystère possible.
	 */
	public abstract String combinaisonAlea();
	
	/**
	 * Retourne la valeur true si la chaîne passée en paramètre vérifie les critères d'acceptabilité d'un nombre mystère.
	 * 
	 * @param entree
	 * 		Chaîne de caractères à vérifier.
	 * 
	 * @return Un booléen indiquant l'acceptabilité de la chaîne de caractères.
	 */
	public abstract boolean entreeValide(String entree);
	
	/**
	 * Affiche les informations relatives au résultat d'un essai. Retourne true si le résultat est le bon, false sinon.
	 * 
	 * @param entree
	 * 		La chaîne de caractères à tester.
	 * 
	 * @return Un booléen exprimant l'égalité (true) ou l'inégalité (false) des deux nombres.
	 */
	public abstract boolean resultat(String entree);
	
	/**
	 * Retourne la proposition de l'ordinateur pour tenter de trouver le nombre mystère.
	 * 
	 * @return Une chaîne de caractères correspondant à la proposition faite par l'ordinateur.
	 */
	public abstract String propositionOrdi();
}
