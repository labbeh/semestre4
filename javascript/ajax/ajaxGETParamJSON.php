<?PHP

function test_input($data) {
 $data = trim($data);
 $data = stripslashes($data);
 $data = htmlentities($data);
 return $data;
}

$objbase = '{"val":0,"carre":0,"cube":0}';

if (isset($_GET['nb'])) {
 $nb = $_GET['nb'];
 if (empty($nb)) {
  $obj = $objbase;
 }
 else {
  $nb = test_input($nb);
  //echo $nb."<br/>";
  $carre = $nb * $nb;
  $cube = $carre*$nb;
  $obj = '{"val":'.$nb.',"carre":'.$carre.',"cube":'.$cube.'}';
 }
}
else {
 $obj = $objbase;
}

echo $obj;
?>
