-- initialisation de la table utilisateur
INSERT INTO utilisateur (nom, role) VALUES ('simon', 'enseignant');
INSERT INTO utilisateur (nom, role) VALUES ('hatinguais', 'sysres');
INSERT INTO utilisateur (nom, role) VALUES ('lepivert', 'admin');

-- initialisation de la table serveur

INSERT INTO serveur (nom, os) VALUES ('corton', 'linux');
INSERT INTO serveur (nom, os) VALUES ('flower', 'macos');
INSERT INTO serveur (nom, os) VALUES ('door', 'windows');

-- initialisation de la table connexion

INSERT INTO connexion VALUES (1, 2, '2010-12-24 06:45:15', 'simong', 30);
INSERT INTO connexion VALUES (2, 1, '2011-03-12 13:15:15', 'hatin', 20);
INSERT INTO connexion VALUES (3, 1, '2014-11-11 00:00:30', 'lepi', 60);
INSERT INTO connexion VALUES (3, 2, '2000-01-01 00:00:00', 'toto', 60);
INSERT INTO connexion VALUES (1, 1, '2007-10-25 08:45:15', 'simong', 70);

