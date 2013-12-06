nuit2013
========

Repo Officiel de l'équipe [MasterOfDL](http://www.nuitdelinfo.com/nuitinfo/teams:equipe_1_masterofdl_modl:start) pour la [Nuit de L'informatique](http://www.nuitdelinfo.com/) 2013 les 5 et 6 décembre 2013, de 16h36 à 08h04

*************COPYRIGHT***********

L'application TradeGame est distribuée sous Licence publique générale GNU 3.0, ou GNU General Public License 3.0. 


*************Environnement de notre projet*************

Il est important d’exprimer dans quel contexte nous voulons mettre en place cette application. Tout d’abord il ne s’agit pas vraiment d’une application standalone, mais plus qu’une fonctionnalité qui, couplée a un site d’e-commerce standard pourrait tendre vers la création d’une application Y-comerce. 

En effet, notre principe de TradeGame permet deux choses : 
- Séduire le client via un système de jeu ludique interactif
- Connaître le profil du client qui, en participant au jeu nous permet de déduire ses préférences par rapport aux produits. 

Il est évident que nous n’avons pas mis en place un site de e-commerce standard a coté de notre application, ainsi donc nous vous laisserons imaginer les possibilités de notre innovation en vous proposant l’affichage de notre connaissance du client dans la partie centrale de l’application.  On peut voir qu’au fur et à mesure du jeu nous connaissons de plus en plus finement le client et ainsi donc nous pouvons adapter le reste de l’application par rapport à cette nouvelle connaissance acquise par le jeu. Le tableau de données que nous affichons est juste ici pour montrer que la logique de notre site fonctionne mais ne saurait remplacer un site marchant à part entière.




*************Objectifs de l’application : Trade-Game*************

Nous avons conçu une application qui met en place un jeu d’échange. Il s’agit d’une fonctionnalité complémentaire d’un site marchand normal : de CDiscount à Amazon. L’idée est d’avoir en plus des menus “rubriques” horizontal , un menu latéral où le Trage-Game pourra prendre place. 

Description du Trade-Show

Le trade show est un jeu d’échange de produit entre les utilisateurs connectés et authentifiés a l’application. 

Le trade-show démarre a un instant T et se termine à la fin d’un timer qui se veut long, une semaine par exemple. A l’instant T un Produit est attribué à l’utilisateur, provenant du catalogue de nos produits. Nous verrons par la suite que ce produit pourra être échangé plusieurs fois. 
A la fin du temps imparti, une récompense s’applique sur le TypeDeProduit que l’utilisateur possède a ce moment précis. 

Exemple : L’utilisateur Pierre, démarre une session de de trade Game, le timer indique une semaine. On lui attribue le produit suivant : Clé-USB de 4Go de marque A. Si le timer se termine et qu’il possède ce produit à ce moment là, une récompense lié au produit de Type : Clé-USB lui sera délivré, nous n’avons en l'occurrence pas définit de récompense précise (réduction en %, bon d’achat etc…). 

Chaque jour, le produit courant d’un utilisateur pourra être échangé avec un autre selon un nombre limité de fois : point d’échange, l’application se chargera lorsque l’utilisateur de connecte, de proposer un échange avec un pair. Cette proposition d’échange peut être acceptée ou refusée. Exemple : 

Pierre et Joesph sont deux utilisateurs connectés de mon application, Pierre détient le produit clé-USB 4Go de marque A et Joseph détient le best-seller du moment.  Le système propose l’échange au deux clients : 

- Pierre se voit proposer le livre : il accepte
- Joseph se voit proposer la clé -USB : il accepte

L’échange est effectué et l’on donne le produit Pierre à Joseph et vice versa. De l’échange entre deux utilisateurs nous pourrons déduire des informations : en l'occurrence Pierre préfère l’article livre à l’article clé USB. De cet echange nous mettons en place un tableau de préférence pour chaque utilisateur qui nous servira dans le cadre de notre application normale de vente en ligne. 

Nous mettons donc en place un jeu d’échange où l’utilisateur peut échanger son produit afin d’en obtenir un qui l'intéresse plus afin d’obtenir une récompense associée. Nous pensons ce jeu ludique et ainsi donc séduisant au yeux de l’utilisateur, qui consommera sans doute ses points d’échange journalier, et de notre coté il s’agit d’un bon moyen de connaître les préférences utilisateur afin de lui proposer du contenu pertinent dans le reste de l’application (en plus du jeu de trade game).
