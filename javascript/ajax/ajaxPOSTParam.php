<?PHP

function verifParam($nomparam) {
	if (isset($_POST[$nomparam])) {
		$val = $_POST[$nomparam];
 		if (empty($val)) {
  			$val = "inconnu";
 		}
	}
	else {
 		$val = "inconnu";
	}	
	return $val;
}

$nom = verifParam('nom');
$age = verifParam('age');

echo "Le nom qui a &eacute;t&eacute; transmis est ".$nom;
echo "<br/>";
echo "L'age qui a &eacute;t&eacute; transmis est ".$age;
?>
