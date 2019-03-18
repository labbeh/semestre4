drop table connexion;
drop table utilisateur;
drop table serveur;

-- modification du type de ids
create table serveur (
	ids serial primary key,
	nom varchar(50),
	os varchar(7) check (os in ('windows','linux','macos'))
);

-- modification du type de idu
create table utilisateur (
	idu serial primary key,
	nom varchar(20),
	role varchar(10) 
		check (role in ('etudiant','enseignant','sysres','admin'))
);

create table connexion (
	idu int references utilisateur(idu),
	ids int references serveur(ids),
	datec timestamp,
	login varchar(10) NOT NULL,
	duree int check (duree > 0),
	primary key (ids,idu,datec)
);

