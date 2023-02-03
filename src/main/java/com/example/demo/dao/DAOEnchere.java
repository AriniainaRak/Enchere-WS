package com.example.demo.dao;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Client;
import com.example.demo.model.DetailEnchere;
import com.example.demo.model.Enchere;
import com.example.demo.model.Historiqueclient;
import com.example.demo.model.V_Enchere;

@Repository
public class DAOEnchere {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public int insertEnchere(String description, Time duree, int prix, Date date, int idClient) {
        Enchere e = new Enchere();
        e.setDescription(description);
        e.setDuree(duree);
        e.setPrixMin(prix);
        e.setIdClient(idClient);
        e.setDateDebut(date);

        String sql = "";
        sql += "insert into enchere(description,duree,datedebut,prixmin,photoenchereid,statueid,produitid,idclient) values ('"
                + e.getDescription() + "','" + e.getDuree() + "','" + e.getDateDebut() + "','"
                + e.getPrixMin() + "','" + e.getPhotoenchereid() + "','" + e.getStatueid() + "','"
                + e.getProduitid() + "','" + e.getIdClient() + "');";

        return jdbcTemplate.update(sql);
    }

    public List<V_Enchere> enchereCompteClient(int idClient) {
        String sql = "";
        sql += "SELECT ";
        sql += "v_enchere.id, ";
        sql += "v_enchere.description,";
        sql += "v_enchere.duree,";
        sql += "v_enchere.dateDebut,";
        sql += "v_enchere.prixmin,";
        sql += "v_enchere.idClientAuteur,";
        sql += "v_enchere.statue,";
        sql += "v_enchere.acheteur,";
        sql += "v_enchere.montant,";
        sql += "v_enchere.dateMise";
        sql += " FROM v_enchere ";
        sql += "JOIN (";
        sql += "SELECT idEnchere, MAX(montant) AS max_montant ";
        sql += "FROM EnchereClient ";
        sql += "GROUP BY idEnchere";
        sql += ") max_enchere_client ";
        sql += "ON ";
        sql += "v_enchere.id = max_enchere_client.idEnchere AND v_enchere.montant = max_enchere_client.max_montant";
        sql += "  where idclientauteur = " + idClient + ";";
        System.out.println();
        System.out.println();
        System.out.println(sql);
        return jdbcTemplate.query(
                sql,
                BeanPropertyRowMapper.newInstance(V_Enchere.class));

    }

    public int insertRechargeCompte(int idClient, int montant) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime now = LocalDateTime.now();
        return jdbcTemplate
                .update("insert into rechargementCompte(dateRechargementCompte,idClient,montant,etat) values ('"
                        + now + "','"
                        + idClient + "','" + montant + "','0');");
    }

    public List<DetailEnchere> searchEnchere(String produit, String categorie) {
        return jdbcTemplate.query(
                "select * from detailenchere where produit like '%" + produit + "%' and categorie like '%" + categorie
                        + "%' ",
                BeanPropertyRowMapper.newInstance(DetailEnchere.class));
    }

    public List<Client> getClient(int idClient) {
        return jdbcTemplate.query(
                "select * from client where id = '" + idClient + "';",
                BeanPropertyRowMapper.newInstance(Client.class));

    }

    public int insertClient(String nom, String prenom, String contact, String email, String mdp, int solde) {
        Client c = new Client();
        c.setContact(contact);
        c.setEmail(email);
        c.setMdp(mdp);
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setSolde(solde);
        return jdbcTemplate
                .update("insert into client(nom,prenom,email,mdp,contact,solde) values ('" + c.getNom()
                        + "','" + c.getPrenom() + "', '" + c.getEmail() + "','" + c.getMdp()
                        + "','" + c.getContact() + "','"
                        + c.getSolde() + "')");
    }

    public void reencherir(int idEnchere, int montant, int idClient) throws Exception {
        List<Client> liste = getClient(idClient);
        Client c = liste.get(0);
        String verif = "select sum(montant) from mouvementclient where idclient =" + c.getId() + " and etat = 0";
        System.out.println(verif);
        int result = jdbcTemplate.queryForObject(verif, Integer.class);
        if (result != 0) {
            int solde = c.getSolde() - result;
            c.setSolde(solde);
            if (c.getSolde() < montant) {
                throw new Exception("solde insuffisant");
            } else {
                String sql = "insert into enchereclient(montant,idclient,idenchere) values (" + montant + ","
                        + c.getId() + "," + idEnchere + ")";
                String mouvement = "insert into mouvementclient(idclient,idenchere,montant) values(" + c.getId() + ","
                        + idEnchere + "," + montant + ")";
                System.out.println(mouvement);
                jdbcTemplate.update(sql);
                jdbcTemplate.update(mouvement);
            }
        } else {
            String sql = "insert into enchereclient(montant,idclient,idenchere) values (" + montant + ","
                    + c.getId() + "," + idEnchere + ")";
            String mouvement = "insert into mouvementclient(idclient,idenchere,montant) values(" + c.getId() + ","
                    + idEnchere + "," + montant + ")";
            System.out.println(mouvement);
            jdbcTemplate.update(sql);
            jdbcTemplate.update(mouvement);
        }
    }

    public List<DetailEnchere> allEnchere() {
        return jdbcTemplate.query("select*from detailenchere",
                BeanPropertyRowMapper.newInstance(DetailEnchere.class));
    }

    public List<DetailEnchere> detailEnchere(int id) {
        return jdbcTemplate.query("select*from detailenchere where idEnchere =" + id,
                BeanPropertyRowMapper.newInstance(DetailEnchere.class));
    }

    public List<Historiqueclient> historique(int idClient) {
        return jdbcTemplate.query("select*from historiqueclient where idclient = '" + idClient + "'",
                BeanPropertyRowMapper.newInstance(Historiqueclient.class));
    }

    public List<Client> login(String email, String mdp) {
        return jdbcTemplate.query(
                "select*from client where email = '" + email + "' and mdp = '" + mdp + "';",
                BeanPropertyRowMapper.newInstance(Client.class));

    }
}
