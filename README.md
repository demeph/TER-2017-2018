# TER-2017-2018

Cette repesitorie correspond au projet de recherche *Automatiser la production logicielle de controleur d'automates*

## Organisation

 Au total 4 branches differents qui correspond au different etape du projet.

- **master** : branche contenant la bibliographie
- **sprint_2** : branche contenant le source code en Java modelisant application concrete avec librairie Lejos EV3
- **sprint_3** : branche contenant les transformations ATL sur la modele de bases
- **sprint_5** : branche contenant le code source pour une application mobile sous android qui controle l'application concrete de sprint_2



## Branche sprint_2

Contient deux projets d'Eclipse different :

- TER-AELOS-2017-2018
- TER-Communication



### TER-AELOS-2017-2018

​	Projet Eclipse qui contient le code source pour le brick du robot Lego EV3. Ce projet contient 4 programmes principales :

- ***MainBeta*** : programme principale qui permet de modeliser un systeme de porte automatique avec une porte  une battante. Les buttons du brick EV3 permet controler different action.
  - bouton haut : commande pour ouvrir  la porte
  - bouton bas : commande pour fermer la porte
  - bouton gauche : commande pout mettre en urgence la porte
  - bouton droite : commande pour reprendre apres l'etat urgence suivant l'action qui etait avant etat urgence
- ***MainBeta2moteurs*** : programme principale similaire au programme principale *MainBeta*, avec la difference que ce programme principale permet de modeliser un systeme de porte automatique avec une porte deux battants.
- ***MainCommunicationBluetooth*** : programme principale permettant de realiser la communication avec l'application mobile sous Android en utilisant le reseau sans-fil Bluetooth.
- ***MainCommunicationWithPC*** : programme principale permettant de realiser la communication avec l'application java trouvant dans le dossier *TER-Communication* via un reseau filaire.



### TER-Communication

​	Projet Eclipse contenant une application avec un interface graphique sous Java qui permet de communiquer avec le brick de Lego EV3 via un reseau filaire (cable USB de base d'un robot Lego EV3). Cette application fonctionnent avec le programme *MainCommunicationWithPC*.

- ***TelecommandeUI*** : application avec interface graphique qui modelise une telecommande. Cette telecommande possede deux boutons : ouverture de la porte et fermeture de la porte.
  - L'ordre de fonctionnement est ceci : d'abord il faut lancer *MainCommunicationWithPC* qui sert comme un serveur et puis il faut lancer *TelecommandeUI*.



