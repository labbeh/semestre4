window.addEventListener("load", init);

			function init(event){
				let zoneSasie = document.getElementById("inputSaisie");
				zoneSasie.addEventListener("input", convertirMaj);
			}

			function convertirMaj(event){
				let textSaisi = this.value;
				this.value = textSaisi.toUpperCase();
				console.log(textSaisi);
			}