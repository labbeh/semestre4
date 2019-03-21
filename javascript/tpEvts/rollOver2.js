window.addEventListener("load", init);

function init(event){
	var lien = document.getElementById("lien");
	lien.addEventListener("mouseover", sourisOn);
	lien.addEventListener("mouseout", sourisOut);
}

obj.sourisOn = function (event){
	/*console.log("souris On");
	console.log(this);
	this.src = "help1.jpg";*/
	var image = this.getElementsByTagName("img")[0];
	image.src = "help1.jpg";
}

function sourisOut(event){
	/*console.log("souris Out");
	console.log(this);
	this.src = "help2.jpg";*/
	event.target.src = "help2.jpg"
}