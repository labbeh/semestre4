/******************************************************************************
                                Les alarmes
*******************************************************************************/
/*-----------------------------------------------------------------------------
Contenu du fichier :
      1. quelques initialisations
      2. définition du prototype Alarme
      3. fonctions pour l'affichage de la liste des alarmes
      4. handlers pour l'ajout et la suppression d'alarmes
      5. handler pour terminer l'étape d'association d'une alarme à un capteur
-------------------------------------------------------------------------------*/

//------------------ 1.quelques initialisations -------------------------------
// constantes pour décrire l'état d'une alarme
var deconnecte = 0;
var vert = 1;
var rouge = 2;

//---------------- 2.définition du prototype Alarme ---------------------------
// constructeur
// Lorsqu'on crée une alarme, aucun capteur ne lui est associé. Il est donc
// déconnecté.
function Alarme(leNom, leSeuil) {
  this.nom = leNom;    // le nom de l'alarme
  this.seuil = leSeuil;// le seuil à partir de laquelle l'alarme passe devient rouge
  this.capteur = null; // le capteur associé à l'alarme
  this.etat = deconnecte; // l'état de l'alarme parmi déconnecté (si pas de capteur), vert et rouge
  this.visu = undefined; // le composant HTML représentant l'alarme
}

// La méthode actualiser() permet de mettre à jour l'état de l'alarme et
// éventuellement la vue associée.
Alarme.prototype.actualiser = function() {
  // mise à jour de l'état
  if (this.capteur == null) {
    this.etat = deconnecte;
  }
  else if (Number(this.capteur.valeur) < Number(this.seuil)) {
    this.etat = vert;
  }
  else {
    this.etat = rouge;
  }
  // mise à jour de la vue
  if (this.visu !== undefined) {
    this.visu.setAttribute("class", this.getClasseCSS());
  }
}

// La méthode associerCapteur() permet d'associer un capteur à l'alarme.
// Le capteur doit s'enregistrer auprès de l'alarme pour être prévenu des
// changements de valeur du capteur.
// Enfin, il faut actualiser l'alarme
Alarme.prototype.associerCapteur = function(leCapteur) {
  this.capteur = leCapteur;
  leCapteur.associerUneAlarme(this);
  this.actualiser();
}

// La méthode getClasseCSS() permet de déterminer la classe à associer à la vue
// représentant l'alarme en fonction de l'état de l'alarme
Alarme.prototype.getClasseCSS = function() {
  if (this.etat == deconnecte) {
    return "deconnecte";
  }
  if (this.etat == vert) {
    return "vert";
  }
  return "rouge";
}

//----------- 3.fonction pour l'affichage de la liste des alarmes -------------

// La méthode afficherUneAlarme() permet de rajouter une alarme au tableau
// listant les alarmes.
function afficherNouvelleAlarme(tableauAlarmes, alarme) {
  var nomAlarme = alarme.nom;
  // Création de la ligne avec ses différentes cellules
  var nouvelleLigne = tableauAlarmes.insertRow();
  var cellNomAlarme = nouvelleLigne.insertCell();
  var cellSeuilAlarme = nouvelleLigne.insertCell();
  var cellCapteurAlarme = nouvelleLigne.insertCell();
  var cellEtatAlarme = nouvelleLigne.insertCell();
  var cellSuppressionAlarme = nouvelleLigne.insertCell();
  // définition de la cellule contenant le nom de l'alarme. C'est sur cette
  // cellule qu'il faudra cliquer pour terminer l'association d'un Capteur
  // à cette alarme.
  var texteNomAlarme = document.createTextNode(alarme.nom);
  cellNomAlarme.appendChild(texteNomAlarme);
  cellNomAlarme.setAttribute("nomAlarme", nomAlarme);
  cellNomAlarme.addEventListener("click", terminerAssociation, true);
  // définition de la cellule contenant le seuil de l'alarme
  var texteSeuilAlarme = document.createTextNode(alarme.seuil);
  cellSeuilAlarme.appendChild(texteSeuilAlarme);
  // définition de la cellule contenant le nom du capteur associé à l'alarme
  var texteCapteurAlarme = undefined;
  if (alarme.capteur == undefined) {
    texteCapteurAlarme = document.createTextNode("Aucun capteur associé");
  }
  else {
    texteCapteurAlarme = document.createTextNode(alarme.capteur.nom);
  }
  cellCapteurAlarme.appendChild(texteCapteurAlarme);
  // définition de la cellule représentant l'état de l'alarme
  var texteEtatAlarme = document.createTextNode("\u25cf");
  cellEtatAlarme.appendChild(texteEtatAlarme);
  cellEtatAlarme.setAttribute("class", alarme.getClasseCSS());
  alarme.visu = cellEtatAlarme;// C'est cette cellule qui affiche l'état
  // définition de la cellule permettant de supprimer l'alarme
  var texteSuppressionAlarme = document.createTextNode("\u2704");
  cellSuppressionAlarme.appendChild(texteSuppressionAlarme);
  cellSuppressionAlarme.setAttribute("nomAlarme", nomAlarme);
  cellSuppressionAlarme.addEventListener("click", supprimerAlarme, true);
}

// Cette fonction permet d'effacer le tableau listant les alarmes sauf les 2
// lignes de titre.
function effacerTableauAlarmes() {
  var tableauAlarmes = document.getElementById("tableauAlarmes");
  while (tableauAlarmes.rows.length > 2) {
    tableauAlarmes.deleteRow(2);
  }
}

// fonction affichant le tableau listant toutes les alarmes
function afficherTableauAlarmes() {
  var tableauAlarmes = document.getElementById("tableauAlarmes");
  effacerTableauAlarmes();
  for (var alarme of alarmes) {
    afficherNouvelleAlarme(tableauAlarmes, alarme);
  }
}

//--------- 4. handlers liés à l'ajout et la suppressin d'alarmes -------------

// handler associé au bouton d'ajout d'alarme' :
// on récupère les infos, on crée l'alarme et on la stocke, puis on
// redessine le tableau des alarmes.
function ajouterAlarme(event) {
  var nomAlarme = document.getElementById("nomNouvelleAlarme").value;
  var seuilAlarme = document.getElementById("seuilNouvelleAlarme").value;
  var nouvelleAlarme = new Alarme(nomAlarme, seuilAlarme);
  alarmes[alarmes.length] = nouvelleAlarme;
  var tableauAlarmes = document.getElementById("tableauAlarmes");
  afficherNouvelleAlarme(tableauAlarmes, nouvelleAlarme);
}

// handler associé au bouton de suppression d'une alarme : on supprime l'alarme,
// puis on redessine le tableau des alarmes.
function supprimerAlarme(event) {
  var nomAlarme = this.getAttribute("nomAlarme");
  var indiceAlarme = -1;
  for (var i = 0; i < nomAlarme.length; i++) {
    if (alarmes[i].nom === nomAlarme) {
      indiceAlarme = i;
      break;
    }
  }
  alarmes.splice(indiceAlarme, 1);
  afficherTableauAlarmes();
}

//---- 5. handler pour finaliser l'association d'une alarme à un capteur ------

// handler associé à la cellule affichant le nom de l'alarme et qui sert à terminer
// l'association (capteur, alarme) : le capteur choisi a été préalablement stocké
// dans la variable capteurAAssocier ; il reste donc à stocker le capteur en
// question dans l'alarme' courante, et à redessiner le tableau.
function terminerAssociation(event) {
  if (capteurAAssocier === null) {
      message = "Association impossible : pas de capteur sélectionné";
  }
  else {
      var nomAlarme = this.getAttribute("nomAlarme");
      console.log("nom de l'alarme " + nomAlarme);
      var alarme = rechercherParNom(alarmes, nomAlarme);
      var capteur = rechercherParNom(capteursSimules,capteurAAssocier);
      if(capteur === undefined) {
        capteur = rechercherParNom(capteursReels,capteurAAssocier);
      }
      alarme.associerCapteur(capteur);
      message = "Association faite entre le capteur " + capteurAAssocier + " et l'alarme " + nomAlarme;
      capteurAAssocier = null;
      document.body.style.cursor="";
      afficherTableauAlarmes();
  }
  var divEtat = document.getElementById("etat");
  divEtat.removeChild(divEtat.firstChild);
  var messageHTML = document.createTextNode(message);
  divEtat.appendChild(messageHTML);
}
