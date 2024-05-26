Gestion de fichiers JSON
************************

Le projet GestionJSON contient 1 classe Categorie contenant une collection de Produits.
Le fichier "produits.json" dans "resource" permet de voir plusieurs categories contenant chacune plusieurs produits.
Le fichier GestionJSON.java contient :
 - 1 fonction de lecture en 1 seule fois d'une liste de categories de produits
 - 1 fonction de lecture de lecture d'un seul objet mais de type parametre
 - 1 fonction d'ecriture directe d'une liste de categories de produits
 - 1 fonction d'eciture manuelle du fichier JSON

Decompresser le fichier 7z dans un repertoire temporaire.

Etapes pour créer le projet :
1. Creer un projet Java vide "GestionJSON"
2. Faire un clic droit dessus et import/General/Archive File"
3. Selectionner "GestionJSON.jar" => Finish
4. Clic "Yes to All" pour effacer d'eventuels anciens fichiers
5. Glisser le dossier "com" dans "src"
Vous pouvez alors executer TestJSON.java (la librairie gson est deja incluse dans le repertoire "resource" et incluse dans le ClassPath)

Pour intégrer la classe GestionJSON.java dans votre projet :
1. Copier la classe GestionJSON.java dans votre projet en choisissant "copier" si demande
2. Créer un nouveau dossier (clic-droit sur votre projet et "New/Folder") nomme "resource" à la racine de votre projet. C'est une convention pour l'inclusion de bibliotheques
4. Copier le jar "gson-2.10.1.jar" dans le dossier "resource" par "glisser-deposer" du repertoire temporaire en choisissant "copier" si demande
5. Integrer gson-2.10.1.jar dans le ClassPath des librairies du BuildPath par "clic-droit sur votre projet/Build Path/Configure Build Path/onglet Libraries/ClassPath" et "Ajout d'un JAR" et selectionner gson dans le dossier resource du projet.
6. Modifier les classes Categorie et Produit pour les adapter a votre projet, ainsi que les imports.
7. Creer une classe TestGestionJSON ou reprenez celle du projet en l'adaptant a votre projet.
8. Valider en testant la classe precedente.