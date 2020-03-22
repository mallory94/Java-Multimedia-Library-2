# Services dispenser


Java application server which hosts services for programmer and provides it to amateurs

---


Vadim Goldaniga

Mallory Gack

---


<p style="text-align: right">


I- Présentation du projet……………………...

Le but du projet était de réaliser un système permettant à des amateurs de se connecter pour pouvoir utiliser des services mise à disposition par des programmeurs.

Les programmeurs doivent s’identifier à l’aide d’un mot de passe et d’un nom d’utilisateur et ont chacun un serveur ftp attribué.

Les amateurs quant à eux ne sont pas encombrés par des mots de passes et identifiants, ils sont tous libres d’utiliser les services comme bon leur semble. Bien que certains services nécessitent des identifiants.

Pour pouvoir tester leurs services, les programmeurs ont aussi un compte amateur.



II- Le travail réalisé…………………………

**Les Services :**



*   Service inversion : Le plus fondamental des services, il permet d’inverser une chaîne de caractère.
*   Service de messagerie interne : Ce service est le seul qui nécessite des identifiants, car ils permet aux amateurs de pouvoir communiquer entre eux grâce à un système type mail. Chaque amateur peut envoyer un message à n’importe quel autre et voir ses messages reçus.
*   Service d’authentification : Il permet aux programmeurs de se connecter lors de leurs sessions de programmation afin de vérifier leur identité. Seul un nom d’utilisateur et un mot de passe sont nécessaire.
*   Service d’Analyse XML : Ce service télécharge et analyse un fichier XML sur le serveur FTP d’un amateur et lui envoit un compte rendu par mail. Les fichiers analysés sont censé représenter des livres. Un fichier modèle .xml est présent dans la solution.
*   Service d’arrêt et de démarrage de service : Celui-ci sert uniquement aux programmeurs. Il permet de contrôler les services de façons à pouvoir facilement les mettre à jour ou les désinstaller si nécessaire. Comme son nom l’indique il permet d’arrêter ou de redémarrer un service, à savoir que tous les services par défauts sont considérés comme démarrés dès le lancement du serveur. Contrairement aux services ajoutés par les programmeurs, qui devront être manuellement démarrés.
*   Service de d’upload de service via serveur ftp : Les programmeurs peuvent fournir un chemin et un nom de fichier propre à leur serveur ftp afin de fournir un service supplémentaire aux amateurs.
*   Service d’ajout de service : C’est un service qui permet aux programmeurs d’ajouter leurs propres services. À noter que ce service utilise celui de téléchargement ftp, car le service à ajouter du programmeur se trouve uniquement dans son serveur ftp personnel.
*   Service d’email : Ce service est utiliser lors de l’analyse de fichier XML une fois le fichier analysé le rapport est envoyé au mail renseigné par le programmeur.

III- Les améliorations possibles……………...

La première chose que nous pouvons ajouter à notre projet est un système d’inscription. Bien que ce ne soit pas le but du projet cela permet de le rendre plus propre car pour l'instant les utilisateurs du système sont inscrits manuellement. Aussi, il nous faudrait faire de la persistance de donnée pour pouvoir garder ses mails ou ses services lors d’un arrêt du serveur. Ces deux ajouts pourraient permettre à notre projet d’être véritablement utilisable (Bien qu’une connexion au réseau serait indispensable). Bien évidemment nous aurions tout simplement pu améliorer notre projet en développant toutes les fonctionnalités en italique mais aussi en réussissant à charger des classes grâce à leur chemin en local. Nous sommes parvenus à développer le code permettant de télécharger une classe via un serveur ftp cependant nous ne sommes pas parvenu à exploiter le ‘.class’ récupérer. Nous avons cru avoir réglé le problème avec un Class.forName() mais en faite nous avions laissé la classe désignée par le Class.forName() dans le projet Eclipse ce nous as trompé. Harcelés par des java.lang.ClassNotFoundException, nous avons perdus beaucoup de temps. Le projet nous a tout de même fait réfléchir à la réflexivité même si certains points restent encore flous.
