  init = function() {
    $("body").contents().wrapAll("<div id='contenu'/>");
    $("#contenu").wrap("<div id='corps'/>");
    $("#corps").append("<div id='source' class='invisible'/>");
    $("body").prepend("<div id='bottom'/>");
    $("#bottom").append("<input type='button' value='afficher le code source' id='code' class='cmd'/>");
    $("#bottom").append("<input type='button' value='masquer le code source' id='nonCode' class='cmd cache'/>");

    var boutonCode = document.getElementById("code");
    boutonCode.onclick = afficherCode;
    var boutonNonCode = document.getElementById("nonCode");
    boutonNonCode.onclick = masquerCode;
    $.get("", traitementCode);
  }

  $(document).ready(init);

  afficherCode = function(event) {
      $("div#source").toggleClass("invisible");
    $(".cmd").toggleClass("cache");
  }
  traitementCode = function(data, status, xhr) {
    var contenu = xhr.responseText;
    contenu = contenu.replace(/  /g,"\xA0\xA0\xa0\xa0");
    var para = contenu.split("\n");
    $.each(para, ajouterLigne);
  }
  ajouterLigne = function(indice, texte) {
    var nouveauPara = $("<p/>").text(texte);
    $("div#source").append(nouveauPara);
  }
  masquerCode = function(event) {
    $("div#source").toggleClass("invisible");
    $(".cmd").toggleClass("cache");
  }
