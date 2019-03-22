/*-----------------------------------------------------------------------------
Contenu du fichier :
    I. Capteurs Simulés
      1. définition du prototype CapteurSimule
      2. handlers associés aux boutons +/- d'un capteur simulé
      3. fonction pour l'affichage de la liste des capteurs simulés
      4. handler gérant l'ajout d'un capteur simulé
   II. Code partagé pour les capteurs simulés et les capteurs réels
      5. handler gérant la première phase de l'association capteur-alarme
  III. Capteurs Réels
      6. définition du prototype CapteurRéel
      7. fonction pour l'affichage de la liste des capteurs réels
      8. handler gérant l'ajout d'un capteur réel
   IV. Le reste
      9. fonctions pour récupérer, via ajax, la liste des capteurs disponibles
     10. fonctions pour la mise à jour, via ajax, de la valeur des capteurs
     11. handler appelé automatiquement pour la mise à jour des capteurs réels
     12. fonctions d'initialisation
-------------------------------------------------------------------------------*/


//-------------- 1. définition du prototype CapteurSimule ---------------------

/***********************************************
             Les capteurs simulés
************************************************/
function CapteurSimule(leNom, laValeur) {
  /* un capteur simulé est défini par son nom et sa valeur. Par ailleurs, il
     mémorise également, dans une propriété de type tableau "alarmesAssociees"
     la liste des alarmes qui ont été associées à ce capteur.
     L'élément du DOM dans lequel la valeur du capteur est associée doit être
     mémorisé dans la propriété elementAffichageValeur dans la fonction
     afficherUnCapteurSimulé().
  */
  this.nom = leNom;
  this.valeur = laValeur;
  this.alarmesAssociees = new Array();
  this.elementAffichageValeur = undefined;
}

// méthode à appeler pour augmenter la valeur d'un capteur simulé de 1
CapteurSimule.prototype.augmenter = function() {
  this.valeur++;
  if (this.elementAffichageValeur != undefined) {
    this.elementAffichageValeur.data = this.valeur;
  }
  for (var i = 0; i < this.alarmesAssociees.length;i++) {
    this.alarmesAssociees[i].actualiser();
  }
}

// méthode à appeler pour diminuer la valeur d'un capteur simulé de 1
CapteurSimule.prototype.diminuer = function() {
  this.valeur--;
  if (this.elementAffichageValeur != undefined) {
    this.elementAffichageValeur.data = this.valeur;
  }
  for (var i = 0; i < this.alarmesAssociees.length;i++) {
    this.alarmesAssociees[i].actualiser();
  }
}

// méthode appelée depuis le prototype Alarme pour mémoriser le fait qu'une
// nouvelle alarme est associée au capteur
CapteurSimule.prototype.associerUneAlarme = function(uneAlarme) {
  this.alarmesAssociees[this.alarmesAssociees.length] = uneAlarme;
}

//--------- 2. handlers associés aux boutons +/- d'un capteur simulé ----------

/* handler à associer à chaque bouton "+" de capteur simulé. Ce handler doit
commencer par récupérer le capteur simulé auquel le bouton est rattaché dans le
tableau 'capteursSimules'. Il faut ensuite augmenter la valeur du capteur
gràace la ma méthode augmenter().
*/
function augmentation(event) {
  let nomCapteur = event.target.id.substring(12, event.target.id.length);

  let capteur = rechercherParNom(capteursSimules, nomCapteur);
  capteur.augmenter();

  let ligne = document.getElementById("ligne"+capteur.nom);
  valeurAffiche = ligne.getElementsByTagName("th")[1];
  //valeurAffiche.createTextNode(capteur.valeur);
  console.log(capteur.valeur);
}

/* handler à associer à chaque bouton "-" de capteur simulé. Ce handler doit
commencer par récupérer le capteur simulé auquel le bouton est rattaché dans le
tableau 'capteursSimules'. Il faut ensuite diminuer la valeur du capteur
grâce à la méthode diminuer().
*/
function diminution(event) {
  let nomCapteur = event.target.id.substring(11, event.target.id.length);

  let capteur = rechercherParNom(capteursSimules, nomCapteur);
  capteur.diminuer();

  console.log(capteur.valeur);
}

//------ 3. fonctions pour l'affichage de la liste des capteurs simulés -------

/*
  Cette fonction prend en paramètre un objet représentant le tableau html des
  capteurs simulés et un capteur simulé à afficher à la fin du tableau. elle
  crée alors la nouvelle ligne et l'ajoute au tableau. Aucun retour de la
  méthode n'est attendu.
  Comme cela est mentionné dans le constructeur, elle doit également mémoriser
  dans la propriété elementAffichageValeur du capteur le noeud texte du DOM
  servant à afficher la valeur du capteur.
  Par ailleurs, pour faciliter l'écriture des différents handlers associés
  aux boutons "+", "-" et "Associer une alarme", il est souhaitable d'associer,
  à chacun des éléments du DOM correspondant à ces boutons, un attribut "nomCapteur" ayant
  pour valeur le nom du capteur.
*/
function afficherNouveauCapteurSimule(tableau, capteur) {
  // <tr><th>Nom</th><th>Valeur courante</th><th>Réglages</th><th>Associer une alarme</th></tr>
  let ligne = document.createElement("tr");
  ligne.setAttribute("id", "ligne"+capteur.nom);

  let nom = document.createElement("th");
  let valeur = document.createElement("th");
  let reglages = document.createElement("th");
  let alarme = document.createElement("th");

  nom.appendChild(document.createTextNode(capteur.nom));
  valeur.appendChild(document.createTextNode(capteur.valeur));

  let btnAugmenter = document.createElement("input");
  btnAugmenter.setAttribute("type", "button");
  btnAugmenter.setAttribute("value", "+");
  btnAugmenter.setAttribute("id", "btnAugmenter"+capteur.nom);
  btnAugmenter.addEventListener("click", augmentation);

  let btnDiminuer = document.createElement("input");
  btnDiminuer.setAttribute("type", "button");
  btnDiminuer.setAttribute("value", "-");
  btnDiminuer.setAttribute("id", "btnDiminuer"+capteur.nom);
  btnDiminuer.addEventListener("click", diminution);

  reglages.appendChild(btnAugmenter);
  reglages.appendChild(btnDiminuer);

  // <input type="button" value="ajouter" id="bAjoutCapteurSimule"/>
  let btnAjouterAlarme = document.createElement("input");
  btnAjouterAlarme.setAttribute("type", "button");
  btnAjouterAlarme.setAttribute("value", "Ajouter alarme");
  btnAjouterAlarme.setAttribute("id", "btnAjouterAlarme"+capteur.nom);

  alarme.appendChild(btnAjouterAlarme);

  ligne.appendChild(nom);
  ligne.appendChild(valeur);
  ligne.appendChild(reglages);
  ligne.appendChild(alarme);

  tableau.appendChild(ligne);
}

//--------------- 4. handler gérant l'ajout d'un capteur simulé ---------------

/* Il s'agit du handler associé au bouton "Ajouter un capteur simulé" dans la
   fonction init(). Il doit récupérer le nom et la valeur saisie pour le nouveau
   capteur. Il doit ensuite vérifier que le nom n'est pas "" et qu'il n'y a pas déjà un
   capteur simulé de même nom (la valeur ne doit pas être vérifiée).
   Si le nom du capteur n'est pas correct, un message doit être affiché dans le
   <div id="etat"/> de la page page. Sinon, il faut créer l'objet représentant le capteur en
   question, l'ajouter au tableau javascript capteursSimules, puis modifier
   l'affichage du tableau html des capteurs simulés.
*/
function ajouterCapteurSimule(event) {
  let nom = document.getElementById("nomNouveauCapteurSimule").value;
  let chercherNom = rechercherParNom(capteursSimules, nom);

  if(nom == "" || chercherNom != undefined){
    let etat = document.getElementById("etat");
    let pEtat = document.createElement("p");

    let texte = document.createTextNode("Erreur: vérifiez le nom");
    pEtat.appendChild(texte);

    etat.appendChild(pEtat);
  }
  else{
    let valeur = document.getElementById("valeurNouveauCapteurSimule").value;
    let nvCapteur = new CapteurSimule(nom, valeur);
    capteursSimules.push(nvCapteur);
    afficherNouveauCapteurSimule(document.getElementById("tableauCapteursSimules"), nvCapteur);
  }
}

//---- 5. handler gérant la première phase de l'association capteur-alarme ----

/* handler à associer à chaque bouton "Associer une alarme" d'un capteur (simulé
   ou réel). Ce handler doit mémoriser dans la variable capteurAAssocier le nom
   du capteur auquel est associé le bouton sur lequel on vient de cliquer, puis
   il doit modifier le texte du <div id="etat"/> du fichier surveillance.html
   pour faire figurer le message "association en cours pour le capteur XXX", où
   XXX correspond au capteur associé au bouton sur lequel on vient de cliquer.
*/
function association(event) {
  //TODO
}

// -------------- 6. définition du prototype CapteurRéel ----------------------

/***********************************************
             Les capteurs réels
************************************************/
function CapteurReel(leNom) {
  /* un capteur réel est défini uniquement par son nom. Par ailleurs, il
     mémorise également, dans une propriété de type tableau "alarmesAssociees"
     la liste des alarmes qui ont été associées à ce capteur.
     Enfin, pour que la mise à jour de la valeur d'un capteur puisse modifier
     l'affichage, il faut également stocker dans la variable d'instance
     elementAffichageValeur l'élément du dom affichant la valeur du capteur
     (ceci doit être fait dans la fonction afficherUnCapteurRéel).
  */
  this.nom = leNom;
  this.alarmesAssociees = new Array();
  this.elementAffichageValeur = undefined;
}

// méthode appelée depuis le prototype Alarme pour mémoriser le fait qu'une
// nouvelle alarme est associée au capteur
CapteurReel.prototype.associerUneAlarme = function(uneAlarme) {
  this.alarmesAssociees[this.alarmesAssociees.length] = uneAlarme;
}

// méthode permettant de modifier la valeur d'un capteur en mettant à jour
// l'affichage et l'état des différentes alarmes associées au capteur.
CapteurReel.prototype.setValeur = function(nouvelleValeur) {
  this.valeur = nouvelleValeur;
  if (this.elementAffichageValeur != undefined) {
    this.elementAffichageValeur.data = this.valeur;
  }
  for (var i = 0; i < this.alarmesAssociees.length;i++) {
    this.alarmesAssociees[i].actualiser();
  }
}

//------ 7. fonction pour l'affichage de la liste des capteurs réels -------

/*
  Cette fonction prend en paramètre un objet représentant le tableau html des
  capteurs réels et un capteur réel à afficher à la fin du tableau. elle
  crée alors la nouvelle ligne et l'ajoute au tableau. Aucun retour de la
  méthode n'est attendu.
  Comme cela est mentionné dans le constructeur, elle doit également mémoriser
  dans la propriété elementAffichageValeur du capteur le noeud texte du DOM
  servant à afficher la valeur du capteur.
  Par ailleurs, pour faciliter l'écriture du handler associé au bouton
  "Associer une alarme", il est souhaitable d'associer à ce bouton un attribut
  "nomCapteur" ayant pour valeur le nom du capteur.
*/
function afficherUnCapteurReel(tableau, capteur) {
  //TODO
}

//--------------- 8. handler gérant l'ajout d'un capteur réel ---------------

/* Il s'agit du handler associé au bouton "Ajouter un capteur réel" dans la
   fonction init(). Il doit récupérer le nom saisi pour le nouveau
   capteur. Il doit ensuite vérifier que le capteur en question n'a pas déjà
   été ajouté.
   Si le capteur existe déjà, un message doit être affiché dans le
   <div id="etat"/> de la page page. Sinon, il faut créer l'objet représentant le capteur en
   question, l'ajouter au tableau javascript capteursReels, puis mettre à jour
   l'affichage du tableau des capteurs réels grâce à la fonction précédente.
*/
function ajouterCapteurReel(event) {
  //TODO
}

/***********************************************
             AJAX
************************************************/

// - 9. fonctions pour récupérer, via ajax, la liste des capteurs disponibles -

/*
  Cette fonction doit initialiser et renvoyer l'objet Xhr permettant d'interroger
  le serveur pour obtenir la liste des capteurs disponibles. Elle doit notamment
  permettre que la fonction recupererListeCapteurs() soit appelée automatiquement
  lorsque la réponse est reçue.
*/
function preparerXHRlisteCapteurs() {
  let xmlhttp;
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");

  xmlhttp.addEventListener("readystatechange",demanderListeCapteurs(xmlhttp));

  return xmlhttp;
}

/*
  Cette fonction doit être appelée pour initialiser la liste des capteurs.
  Elle prend un paramètre Xhr et émet vers le serveur la requête permettant
  d'obtenir la liste des capteurs.
*/
function demanderListeCapteurs(xmlhttp) {
  xmlhttp.open("GET","https://mermet.users.greyc.fr/IUT/capteurs.php?capteur=liste",true);
  xmlhttp.send(null);
  recupererListeCapteurs(xmlhttp);

  return xmlhttp;
}

/*
  Cette fonction, qui prend en paramètre un objet Xhr, est la fonction appelée
  lorsque la liste des capteurs, en provenance du serveur, est reçue sous la
  forme d'un tableau JSON. Elle doit alors rajouter une option par capteur à
  la balise <select id="nomNouveauCapteurRéel"/> du fichier html.
*/
function recupererListeCapteurs(xmlhttp) {
  if (xmlhttp.readyState==4 && xmlhttp.status==200){
    let reponse = xmlhttp.responseText;
    let parseJson = JSON.parse(reponse);

    let nvCapteur = document.getElementById("nomNouveauCapteurRéel");
    nvCapteur.appendChild(document.createElement())
  }
}

// -- 10. fonctions pour la mise à jour, via ajax, de la valeur des capteurs --

/*
  Cette fonction doit initialiser et renvoyer l'objet Xhr permettant d'interroger
  le serveur pour obtenir la valeur d'un capteur. Elle doit Notamment
  permettre que la fonction recupererValeurCapteur() soit appelée automatiquement
  lorsque la réponse est reçue.
*/
function preparerXHRvaleurCapteur() {
  //TODO
}

/*
  Cette fonction doit être appelée pour demander la nouvelle valeur d'un capteur.
  Elle prend un paramètre Xhr ainsi que le capteur. Elle émet vers le serveur la
  requête permettant d'obtenir la nouvelle valeur pour le capteur en question en
  passant en paramètre le nom du capteur en question.
*/
function demanderValeurCapteur(xmlhttp, capteur) {
  //TODO
}

/*
  Cette fonction, qui prend en paramètre un objet Xhr, est la fonction appelée
  lorsque la valeur d'un capteur, en provenance du serveur, est reçue. Elle doit
  alors modifier la valeur de l'objet représentant le capteur grâce la méthode
  setValeur().
*/
function recupererValeurCapteur(xmlhttp) {
  //TODO
  
}

/***********************************************
                   Le Reste
************************************************/

// -- 11. handler appelé automatiquement pour la mise à jour des capteurs réels

/* Cette fonction est appelée automatiquement toutes les secondes grâce au
   setInterval(...) de la fonction init(). Elle doit parcourir l'ensemble des
   capteurs réels créés (et contenu dans le tableau javascript capteursReels)
   et pour chacun, émettre une requête ajax */
function majCapteursReels(event) {
  //TODO
  
}

// ---------------------- 12. fonctions d'initialisation ----------------------

/*
  Cette fonction, sans paramètre ni valeur de retour, est appelée une fois, au
  démarrage, via la fonction init(). Elle doit permettre d'initialiser la
  balise <select> donnant la liste des capteurs réels disponibles grâce à une
  requête Ajax vers le serveur.
*/
function initListeCapteursReels() {
  //TODO
}

/* fonction d'initialisation appelée au chargement de la page. Cette fonction
   associe déjà les handlers d'ajouts de nouveaux éléments aux boutons
   adéquats.
*/
function init() {
  var boutonAjouterAlarme = document.getElementById("bAjoutAlarme");
  boutonAjouterAlarme.addEventListener("click", ajouterAlarme, true);
  var boutonAjouterCapteurSimule = document.getElementById("bAjoutCapteurSimule");
  boutonAjouterCapteurSimule.addEventListener("click", ajouterCapteurSimule, true);
  var boutonAjouterCapteurReel = document.getElementById("bAjoutCapteurReel");
  boutonAjouterCapteurReel.addEventListener("click", ajouterCapteurReel, true);
  initListeCapteursReels();
  setInterval(majCapteursReels, 1000);
}
