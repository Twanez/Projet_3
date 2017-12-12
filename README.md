# Utilisation du programme

Pour compiler le programme en mode console, placez-vous dans le dossier Projet_3 et exécutez la ligne de commande suivante :

javac -d bin -cp log4j-1.2.17.jar src/fr/projet_3/*.java src/fr/projet_3/*/*.java



Pour lancer le programme, exécutez la commande suivante dans le même répertoire :

java bin/fr/projet_3/Main



Le menu :

Une fois le programme lancé en mode console, la navigation dans le menu s'effectue en entrant un chiffre correspondant à l'un des choix proposés.



Les jeux :

L'objectif du challenger est de deviner un nombre correspondant à une combinaison de chiffres. Les combinaisons possibles, ainsi que les informations données par le programme à chaque essai, varient en fonction des jeux.

Le nombre d'essais ainsi que le nombre de chiffres peuvent être modifiés dans le fichier de configuration.

- Au Mastermind, les chiffres (aussi appelés couleurs) de la combinaison sont compris entre 0 et 6 non inclus. Ce nombre peut être modifié dans le fichier de configuration. À chaque essai, l'ordinateur compare la tentative avec la combinaison secrète, et indique le nombre de chiffres bien placés, ainsi que le nombre de chiffres présents et mal placés.

- Au Plus ou Moins, les chiffres de la combinaison sont compris entre 0 et 9. Pour chaque chiffre de chaque tentative du challenger, l'ordinateur indique si le chiffre de la combinaison secrète est supérieur, inférieur ou égal.




Les modes :

- Challenger : le programme génère une combinaison aléatoire à faire deviner à l'utilisateur. Celui-ci devra, à chaque essai, entrer une combinaison valide en guise d'essai.

- Défenseur : l'utilisateur doit choisir une combinaison secrète valide à faire deviner par l'ordinateur.

- Duel : l'utilisateur et l'ordinateur génèrent une combinaison secrète chacun. Tour à tour, chacun doit essayer de deviner la combinaison mystère de l'autre. À noter qu'il s'agit du seul mode de jeu où l'égalité est possible.



Exécuter l'application avec le mode développeur :

Pour lancer le programme avec le mode développeur, il existe deux possibilités :
- mettre la valeur de MODE_DEVELOPPEUR à 1 dans le fichier config.properties
- ou bien lancer le programme avec cette commande :
java bin/fr/projet_3/Main -d

Le mode développeur permet d'afficher la combinaison secrète au début de chaque jeu.
