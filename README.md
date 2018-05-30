# TER-2017-2018

Cette repesitorie correspond au projet de recherche *Automatiser la production logicielle de controleur d'automates*

## Organisation

 Au total 4 branches differents qui correspond au different etape du projet.

- **master** : branche contenant la bibliographie
- **sprint_2** : branche contenant le source code en Java modelisant application concrete avec librairie Lejos EV3
- **sprint_3** : branche contenant les transformations ATL sur la modele de bases
- **sprint_5** : branche contenant le code source pour une application mobile sous android qui controle l'application concrete de sprint_2



## Branche sprint_3

​	Le dossier principale est *TER-Transformation * contenant 3 dossiers qui permet a realiser les transformation des modeles via ATL.

- ***ModeleBase*** : dossier contient les fichiers representant diagramme de classe et les diagrammes etats-transitions importantes. Dans ce dossier on trouve des fichiers qui sont cree a partir du plugin Papyrus, qui cree automatiquement les fichiers avec les extensions suivantes :

  - .di : fichier correspondant au Papyrus Model pour modifier ou creer les diagrammes via un intefrace graphique
  - .notation : extension de *.di* pour visualiser les diagrammes des etats-transitions
  - .uml : le fichier XMI qui permet represente les diagrammes UML

- ***ModeleSortie*** : dossier contient les modeles  obtenus via les transformations

- ***Transformation*** : les regles *ATL* permettant transfromer les modeles

  - ***transformation_1.atl***  : transformation prenant en entree deux entrees : modele de base contenant diagramme de classe et de l'autre cote un diagramme d'etats transitions. Cette regle ATL est uilise plusieurs foix pour integrer plusieurs etats-transitions.

    - transformation_1.atl : 

      - mode ATL : from
      - ModeleBase/modelBase.uml et ModeleBase/stateChartePorte.uml -> ModeleSortie/transfoModele1.uml (ajout premiere enum de la porte)
      - ModeleSortie/transfoModele1.uml et ModeleBase/stateCharteControleur.uml -> ModeleSortie/transfoModele2.uml (ajout premiere enum de la controleur)
      - ModeleSortie/transfoModele2.uml et ModeleBase/stateCharteMoteur.uml -> ModeleSortie/transfoModele3.uml (ajout premiere enum de la moteur)

    - transformation_2.ATL :  deplacement des enumerations dans la partie Model

      - mode ATL : refining
      - ModeleSortie/transfoModele3.uml -> ModeleSortie/transfoModele4.uml

    - transformation_3_clean.atl :  efface les traces des enum restees dans le fichier  ModeleSortie/transfoModele4.uml

      - mode ATL : refining
      -  ModeleSortie/transfoModele4.uml ->  ModeleSortie/transfoModele5.uml

    - transformation_4_addEnumAttribute.atl : avant lancement de cette tranformation il faut intervenir et il faudra rajouter des stereotypes aux classes et use case aux enum.

      - Mode ATL : refining
      - Classe et enumeration Porte

        - class *Porte* :
          - stereotype : BehaviorDoor
        - enumeration *enumPorte*
          - use case : BehaviorDoor
      - Classe et enumeration ControleurDePorte 
        - classe ControleurDePorte
          - stereotype : BehaviorCTRL
        - enumeration enumControleurDePorte
          - use case : BehaviorCTRL
      - Classe et enumeration Moteur
        - classe Moteur
          - stereotype : BehaviorMotor
        - enumeration enumMoteur
          - use case : BehaviorMotor

      Une fois stereotype et use case ajouter, on lance la regle ATL  :

      ModeleSortie/transfoModele5.uml ->  ModeleSortie/transfoModele6.uml
