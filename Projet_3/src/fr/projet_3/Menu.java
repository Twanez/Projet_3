package fr.projet_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;
import fr.projet_3.jeu.*;
import fr.projet_3.mode.*;
import org.apache.log4j.Logger;

/**
 * <b>La classe Menu est la classe qui gère et contrôle le choix du jeu et du mode de jeu par l'utilisateur.</b>
 */
public class Menu {
	private static Logger logger = Logger.getLogger(Menu.class);
	private Jeu jeu;
	private Mode mode;
	
	/**
	 * Lance le menu pour choisir le jeu.
	 * 
	 * @param modeDev
	 * 		Si égal à true, le programme affiche la solution en début de jeu.
	 */
	public void menu(boolean modeDev)
	{
		char choix;
		modeDev = (modeDev || paramNombre(0, "MODE_DEVELOPPEUR") == 1);
		System.out.println("\n\n*** Projet 3 ***\n");
		do {
			choix = question("Choisissez le jeu :\n"
								+ "1. Plus ou Moins\n"
								+ "2. Mastermind\n"
								+ "0. Quitter\n\n",
							 "012",
					 		 "\nErreur : Réponse invalide. Veillez choisir une réponse entre 0 et 2.\n\n");
			switch(choix)
			{
				case '0':
					System.out.println("\nBye !");
					return;
				case '1':
					jeu = new PlusOuMoins();
					break;
				case '2':
					jeu = new Mastermind();
					break;
			}
			System.out.println("\n");
			choix = question("Choisissez le mode de jeu :\n\n"
								+ "1. Challenger\n"
								+ "2. Défenseur\n"
								+ "3. Duel\n"
								+ "4. Retour\n"
								+ "0. Quitter\n\n",
							 "01234",
							 "\nErreur : Réponse invalide. Veuillez choisir une réponse entre 0 et 4.\n\n");
			switch(choix)
			{
				case '0':
					System.out.println("\nBye !");
					return;
				case '1':
					mode = new Challenger();
					break;
				case '2':
					mode = new Defenseur();
					break;
				case '3':
					mode = new Duel();
					break;
			}
			
			if (choix != '4')
			{
				do {
					mode.jouer(jeu, modeDev);
					choix = question("Rejouer une partie ?\n\n"
										+ "1. Oui !\n"
										+ "2. Changer de jeu\n"
										+ "0. Quitter\n\n",
									 "012",
									 "\nErreur : Réponse invalide. Veuillez choisir une réponse entre 0 et 4.\n\n");
				} while (choix == '1');
			}
			System.out.println("\n");
		} while (choix != '0');
	}
	
	/**
	 * Retourne un paramètre dans le fichier de configuration.
	 * 
	 * @param valDefaut
	 * 		Valeur par défaut, si jamais la récupération du paramètre venait à échouer.
	 * 
	 * @param chaineConfig
	 * 		Nom du paramètre à rechercher.
	 * 
	 * @return Un entier correspondant au paramètre recherché.
	 */
	public static int paramNombre(int valDefaut, String chaineConfig)
	{
		int nombre = valDefaut;
		try (BufferedReader br = new BufferedReader(new FileReader("config.properties"));)
		{
			String ligne;
			do {
				ligne = br.readLine();
				if (ligne != null && ligne.substring(0, ligne.indexOf('=')).equals(chaineConfig))
					nombre = Integer.parseInt(ligne.substring(ligne.indexOf('=') + 1));
			} while (ligne != null);
		} catch (StringIndexOutOfBoundsException e){
		} catch (Exception e) {
			System.out.print("\n");
			logger.warn("Fichier config.properties innaccessible ou corrompu.");
			System.out.print(e.getClass());
			return nombre;
		}
		return nombre;
	}
	
	/**
	 * Pose une question à l'utilisateur, et récupère son choix sous forme d'un caractère compris dans une liste prédéfinie.
	 * Répète la question à l'utilisateur jusqu'à qu'une réponse remplisse les critères d'admissibilité.
	 * 
	 * @param question
	 * 		Chaîne à afficher pour inviter l'utilisateur à rentrer une réponse.
	 * 
	 * @param reponses
	 * 		Chaîne contenant la liste des caractères autorisés en réponse.
	 * 
	 * @param messageErreur
	 * 		Message s'affichant à chaque fois que l'utilisateur choisit un caractère non admissible, ou entre plusieurs caractères.
	 * 
	 * @return Un caractère correspondant au choix de l'utilisateur.
	 */
	private char question(String question, String reponses, String messageErreur)
	{
		String entree;
		Scanner sc = new Scanner(System.in);
		System.out.print(question);
		entree = sc.nextLine();
		while(entree.length() != 1 || reponses.indexOf(entree.charAt(0)) == -1)
		{
			System.out.print(messageErreur);
			System.out.print(question);
			entree = sc.nextLine();
		}
		return entree.charAt(0);
	}
}
