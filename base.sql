-- Active: 1675426184052@@127.0.0.1@5432@enchere

CREATE TABLE Enchere (
    id SERIAL NOT NULL primary key, 
    description varchar(255), 
    duree time(7), 
    dateDebut TIMESTAMP DEFAULT now(), 
    prixMin int4, 
    photoEnchereid int4 NOT NULL, 
    Statueid int4 NOT NULL, 
    Produitid int4 NOT NULL
);
CREATE TABLE photoEnchere (
    id SERIAL NOT NULL,
    photo varchar(255), 
    PRIMARY KEY (id)
);
CREATE TABLE EnchereClient (
    id SERIAL NOT NULL, 
    montant int4, 
    "date" TIMESTAMP DEFAULT now(), 
    idClient int4 NOT NULL, 
    idEnchere int4 NOT NULL, 
    PRIMARY KEY (id)
);
CREATE TABLE Commission (
    id SERIAL NOT NULL, 
    ValeurCommission int4, 
    EnchereClientid int4 NOT NULL, 
    PRIMARY KEY (id)
);
CREATE TABLE Client (
    id SERIAL NOT NULL, 
    Nom varchar(255), 
    Prenom varchar(255), 
    Email varchar(255), 
    Mdp varchar(255), 
    Contact varchar(50), 
    Solde int4, 
    PRIMARY KEY (id)
);
CREATE TABLE Admin (
    id SERIAL NOT NULL, 
    Email varchar(255), 
    Mdp varchar(255), 
    PRIMARY KEY (id)
);
CREATE TABLE Categorie (
    id SERIAL NOT NULL, 
    Nom varchar(255), 
    PRIMARY KEY (id)
);
CREATE TABLE Produit (
    id SERIAL NOT NULL, 
    Nom varchar(255), 
    Categorieid int4 NOT NULL, 
    PRIMARY KEY (id)
);
CREATE TABLE Statue (
    id SERIAL NOT NULL, 
    Statue varchar(255), 
    PRIMARY KEY (id)
);

create table mouvementclient(
    idmouvement serial primary key,
    idclient int,
    idenchere int,
    montant float,
    etat int default 0,
    foreign key (idclient) references client(id),
    foreign key (idenchere) references enchere(id)
);

CREATE TABLE RechargementCompte(
    id SERIAL NOT NULL, 
    DateRechargementCompte date,
    idClient int NOT NULL, 
    Montant int NOT NULL, 
    Etat int NOT NULL
);
ALTER TABLE RechargementCompte ADD CONSTRAINT FKRechargement FOREIGN KEY(idclient) REFERENCES Client (id);

-- drop view historiqueclient;

create or replace view historiqueclient as
select ec.idclient,c.nom,c.prenom,ec.date,e.description,e.prixmin,ec.montant as mise,s.statue
from client c
join enchereclient ec on c.id = ec.idclient
join enchere e on ec.idenchere = e.id
join statue s on e.statueid = s.id; 

create or replace view detailEnchere as
select e.id as idenchere,e.duree,e.datedebut,e.prixmin,e.produitid,p.nom as produit,p.categorieid,c.nom as categorie
from enchere e
join produit p on e.produitid = p.id
join categorie c on p.categorieid = c.id;

ALTER TABLE Enchere ADD CONSTRAINT FKEnchere194561 FOREIGN KEY (photoEnchereid) REFERENCES photoEnchere (id);
ALTER TABLE EnchereClient ADD CONSTRAINT FKEnchereCli753162 FOREIGN KEY (idClient) REFERENCES Client (id);
ALTER TABLE Commission ADD CONSTRAINT FKCommission363073 FOREIGN KEY (EnchereClientid) REFERENCES EnchereClient (id);
ALTER TABLE Produit ADD CONSTRAINT FKProduit762078 FOREIGN KEY (Categorieid) REFERENCES Categorie (id);
ALTER TABLE Enchere ADD CONSTRAINT FKEnchere209032 FOREIGN KEY (Statueid) REFERENCES Statue (id);
ALTER TABLE Enchere ADD CONSTRAINT FKEnchere390744 FOREIGN KEY (Produitid) REFERENCES Produit (id);
ALTER TABLE EnchereClient ADD CONSTRAINT FKEnchereCli201200 FOREIGN KEY (idEnchere) REFERENCES Enchere (id);

-- Photo
insert into photoEnchere(id,photo) values(1,'defaut.jpg');
insert into photoEnchere(id,photo) values(2,'defaut1.jpg');
insert into photoEnchere(id,photo) values(3,'defaut2.jpg');
insert into photoEnchere(id,photo) values(4,'defaut3.jpg');
insert into photoEnchere(id,photo) values(5,'defaut4.jpg');

/*statue*/
insert into Statue(id,Statue) values(1,'A venir');
insert into Statue(id,Statue) values(2,'En cours');
insert into Statue(id,Statue) values(3,'Termine');

/*Categories*/
insert into Categorie(Nom) values('Livres anciens et de collection');
insert into Categorie(Nom) values('Art et antiquites');
insert into Categorie(Nom) values('Materiel de bricolage');
insert into Categorie(Nom) values('Jeux video et consoles ');
insert into Categorie(Nom) values('Monnaies');
insert into Categorie(Nom) values('Vetements et accessoires des dessinees,commics et produits derives');
insert into Categorie(Nom) values('Articles de collection');
insert into Categorie(Nom) values('Jouets et jeux');
insert into Categorie(Nom) values('Bijoux et montres');
insert into Categorie(Nom) values('Gastronomie et boissons');
insert into Categorie(Nom) values('Timbres');


/*Produit*/
insert into Produit(Nom,Categorieid) values('Voiture',1);
insert into Produit(Nom,Categorieid) values('livres',1);
insert into Produit(Nom,Categorieid) values('telephones',1);
insert into Produit(Nom,Categorieid) values('Galerie',2);
insert into Produit(Nom,Categorieid) values('Art',2);
insert into Produit(Nom,Categorieid) values('Materiel',3);
insert into Produit(Nom,Categorieid) values('jeux video',4);
insert into Produit(Nom,Categorieid) values('Console',4);
insert into Produit(Nom,Categorieid) values('Coins',5);
insert into Produit(Nom,Categorieid) values('Vetements',6);
insert into Produit(Nom,Categorieid) values('Plate',7);
insert into Produit(Nom,Categorieid) values('Jouets',8);
insert into Produit(Nom,Categorieid) values('Jeux',8);
insert into Produit(Nom,Categorieid) values('Bijoux',9);
insert into Produit(Nom,Categorieid) values('montres',9);
insert into Produit(Nom,Categorieid) values('Coffee',10);
insert into Produit(Nom,Categorieid) values('Timbres',11);

/*client*/
insert into Client(Nom,Prenom,Email,Mdp,Contact,solde) values('Rakoto','Rabe','Rakoto@gmail.com','rakoto123',0341512345,1000000);
insert into Client(Nom,Prenom,Email,Mdp,Contact,solde) values('Jean','Arthur','Jean@gmail.com','jean456',0331546585,3000000);
insert into Client(Nom,Prenom,Email,Mdp,Contact,solde) values('Rasoa','Elisabeth','Rasoa@gmail.com','rasoa789',0324528621,100000);

/*commission*/
insert into Commission(ValeurCommission,EnchereClientid) values(10000,1);
insert into Commission(ValeurCommission,EnchereClientid) values(2000,2);
insert into Commission(ValeurCommission,EnchereClientid) values(30000,3);

-- Enchere
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Livre vehicules de service ferroviaire allemands','12:00:00','2023-12-14',100000,1,3,2);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Verre','12:00:00','2023-12-13',200000,2,2,5);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Bijoux mode ','15:00:00','2023-12-12',100000,3,1,1);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Montre automatique Rolex Submariner Date 16610 SEL cadran noir','10:00:00','2023-12-15',100000,4,1,8);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('2016 BMW 3-Series','00:15:00','2023-12-16',11900,5,1,1);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Ancien jeux video','12:00:00','2023-12-17',1100,5,1,7);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Tableau','08:00:00','2023-12-18',1900,5,1,5);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Robe','12:00:00','2023-12-19',1190,5,1,10);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Soutient','10:00:00','2023-12-20',1900,5,1,10);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Renault 4','12:00:00','2023-12-21',119000,5,1,1);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Cadillac','12:00:00','2023-12-22',11900000,5,1,1);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Asus Rog','12:00:00','2023-12-23',11900,5,1,6);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Huawei p20 pro','12:00:00','2023-12-24',1190000,5,1,3);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('The begining after the end','12:00:00','2023-12-25',1010900,5,1,2);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Mozzart','12:00:00','2023-12-26',11900,5,1,4);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Bethoven','12:00:00','2023-12-27',25000,5,1,5);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Salon en cuir','12:00:00','2023-12-28',119000,5,1,6);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Elden Ring 4','12:00:00','2023-12-29',9000,5,1,7);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('PS4','12:00:00','2023-12-30',11000,5,1,8);
insert into Enchere(description,duree,dateDebut,prixMin,photoEnchereid,Statueid,Produitid) values('Bon Coins','12:00:00','2023-12-31',10900,5,1,9);

--echere client
insert into EnchereClient(montant,date,idClient,idEnchere) values(300000,'2023-01-01',1,4);
insert into EnchereClient(montant,date,idClient,idEnchere) values(120000,'2023-01-02',2,5);
insert into EnchereClient(montant,date,idClient,idEnchere) values(500000,'2023-01-03',3,7);
insert into EnchereClient(montant,date,idClient,idEnchere) values(100000,'2023-01-04',1,8);
insert into EnchereClient(montant,date,idClient,idEnchere) values(200000,'2023-01-05',2,8);
insert into EnchereClient(montant,date,idClient,idEnchere) values(500000,'2023-01-06',3,8);
insert into EnchereClient(montant,date,idClient,idEnchere) values(300000,'2023-01-07',1,9);
insert into EnchereClient(montant,date,idClient,idEnchere) values(200000,'2023-01-08',2,9);
insert into EnchereClient(montant,date,idClient,idEnchere) values(600000,'2023-01-09',3,9);
insert into EnchereClient(montant,date,idClient,idEnchere) values(700000,'2023-01-10',1,10);
insert into EnchereClient(montant,date,idClient,idEnchere) values(800000,'2023-01-11',2,10);
insert into EnchereClient(montant,date,idClient,idEnchere) values(500000,'2023-01-12',3,10);
insert into EnchereClient(montant,date,idClient,idEnchere) values(800000,'2023-01-13',1,11);
insert into EnchereClient(montant,date,idClient,idEnchere) values(500000,'2023-01-14',2,11);
insert into EnchereClient(montant,date,idClient,idEnchere) values(700000,'2023-01-15',3,11);
insert into EnchereClient(montant,date,idClient,idEnchere) values(450000,'2023-01-16',1,12);
insert into EnchereClient(montant,date,idClient,idEnchere) values(320000,'2023-01-17',2,12);
insert into EnchereClient(montant,date,idClient,idEnchere) values(650000,'2023-01-18',3,12);
insert into EnchereClient(montant,date,idClient,idEnchere) values(80000,'2023-01-19',1,13);
insert into EnchereClient(montant,date,idClient,idEnchere) values(550000,'2023-01-20',2,13);
insert into EnchereClient(montant,date,idClient,idEnchere) values(580000,'2023-01-21',3,13);

/*RechargementCompte*/
insert into RechargementCompte(DateRechargementCompte,idClient,Montant,Etat) values('2023-02-25',1,100000,0);
insert into RechargementCompte(DateRechargementCompte,idClient,Montant,Etat) values('2023-01-30',2,20000,0);
insert into RechargementCompte(DateRechargementCompte,idClient,Montant,Etat) values('2023-02-16',3,230000,0);


/*Admin*/
insert into Admin(Email,Mdp) values('Enchere@gmail.com','mdp123');

-- v_rechargementCompte
create or replace view v_rechargementCompteClient as
select
RechargementCompte.id,
RechargementCompte.idClient,
client.nom,
client.prenom,
client.email,
client.solde,
RechargementCompte.DateRechargementCompte,
RechargementCompte.montant
from
RechargementCompte
join client
on RechargementCompte.idClient = client.id
where RechargementCompte.etat=0;

-- -- v_produitCategorie
create or replace view V_ProduitCategorie as 
select
produit.*,
categorie.nom as nomCategorie
from produit
join categorie
on produit.categorieid = categorie.id ;

-- stat nbre acheteur par produit
create or replace view V_NbreAcheteurProduit as
select
enchere.id as idEnchere,
enchere.description,
enchere.produitid,
V_ProduitCategorie.nom as nomProduit,
V_ProduitCategorie.nomCategorie,
count(distinct(idclient)) as nbAcheteur
from 
enchere
join EnchereClient
on enchere.id = EnchereClient.idEnchere
join V_ProduitCategorie
on enchere.produitid = V_ProduitCategorie.id
where enchere.Statueid>=2
group by 
enchere.id,
V_ProduitCategorie.nom,
V_ProduitCategorie.nomCategorie
order by enchere.id asc;

-- v_enchere
create or replace view V_Enchere as 
select
enchere.id,
enchere.description,
enchere.duree,
enchere.dateDebut,
enchere.prixMin,
enchere.Statueid,
Statue.statue,
EnchereClient.idClient,
EnchereClient.montant,
EnchereClient.date as dateMise
from 
enchere
join Statue
on enchere.Statueid = Statue.id
join EnchereClient
on enchere.id = EnchereClient.idEnchere;



