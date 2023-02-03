package com.example.demo.controller;

import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.DAOEnchere;
import com.example.demo.model.Client;
import com.example.demo.model.MyToken;
import com.example.demo.model.V_Enchere;

@Controller
public class PagesController {

    @Autowired
    DAOEnchere daoEnchere;

    @RequestMapping(value = "/encheres", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public int insertEnchere(@RequestParam(value = "description") String description,
            @RequestParam(value = "duree") String dureeString,
            @RequestParam(value = "prix") int prix,
            @RequestParam(value = "date") String dateString,
            @RequestParam(value = "idClient") int idClient) {
        int result = 0;

        Time duree = null;
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
        
        Date date = null;
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = format.parse(dateString);
            duree = new Time(timeFormat.parse(dureeString).getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }

        result = daoEnchere.insertEnchere(description, duree, prix, date, idClient);

        return result;
    }

    @RequestMapping(value = "/enchere", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public List<V_Enchere> enchereCompteClient(@RequestParam int idClient) {
        // System.out.println("Voir liste enchere idClient = "+idClient);
        return daoEnchere.enchereCompteClient(idClient);
    }

    @RequestMapping(value = "/clients/{idClient}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public List<Client> getClient(@PathVariable int idClient) {
        return daoEnchere.getClient(idClient);
    }

    @RequestMapping(value = "/recharge", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public int recharger(@RequestParam(value = "idClient") int idClient, @RequestParam(value = "montant") int montant) {
        int result = 0;

        result = daoEnchere.insertRechargeCompte(idClient, montant);

        return result;
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public HashMap<String, Object> login(@RequestParam(value = "email", defaultValue = "") String email,
            @RequestParam(value = "mdp", defaultValue = "") String mdp) {
        HashMap<String, Object> result = new HashMap<String, Object>();

        List<Client> listLogin = daoEnchere.login(email, mdp);

        int checkLogin = listLogin.size();

        if (checkLogin != 0) {
            checkLogin = listLogin.get(0).getId();
            result.put("id", checkLogin);
            result.put("message", "Correct");
            MyToken myToken = new MyToken();
            String tokenSociete = myToken.generateToken(checkLogin);
            result.put("token", tokenSociete);
            Date dateExpiDate = myToken.expirationdateToken(tokenSociete);

            System.out.println();
            System.out.println();
            System.out.println("today's date : " + new Date(System.currentTimeMillis()));
            System.out.println("expi date : " + dateExpiDate);
            System.out.println();
            System.out.println();

            result.put("dateExpiration", dateExpiDate);
        }

        else {
            result.put("message", "Not Correct");
        }

        return result;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    @CrossOrigin
    public int insertClient(@RequestParam(value = "nom") String nom,
            @RequestParam(value = "prenom") String prenom,
            @RequestParam(value = "email") String email,
            @RequestParam(value = "contact") String contact,
            @RequestParam(value = "mdp") String mdp,
            @RequestParam(value = "solde") int solde) {
        int result = 0;

        result = daoEnchere.insertClient(nom, prenom, contact, email, mdp, solde);

        return result;
    }

    @RequestMapping(value = "/greeting/{name}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public String greeting(@PathVariable String name) {
        return "hello " + name;
    }
}
