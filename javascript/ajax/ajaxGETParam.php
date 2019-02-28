<?PHP

if (isset($_GET['nom'])) {
 $nom = $_GET['nom'];
 if (empty($nom)) {
  $nom = "inconnu";
 }
}
else {
 $nom = "inconnu";
}

echo "Le nom qui a &eacute;t&eacute; transmis est ".$nom;
?>