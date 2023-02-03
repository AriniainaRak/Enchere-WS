package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dao.DAOEnchere;
import com.example.demo.dao.DAOProduit;
import com.example.demo.model.DetailEnchere;
import com.example.demo.model.Historiqueclient;

@Controller
public class EnchereController {

    @Autowired
    DAOEnchere daoEnchere;

    @Autowired
    DAOProduit daoproduit;


    @GetMapping("/enchere/search")
    @ResponseBody
    @CrossOrigin
    public List<DetailEnchere> listEnchere(Model model, @RequestParam(value = "critere", defaultValue = "") String critere,
            @RequestParam(value = "categ", defaultValue = "") String categ) {
        List<DetailEnchere> liste = null;
        if (critere == "" && categ == "") {
            liste = daoEnchere.allEnchere();
        } else {
            liste = daoEnchere.searchEnchere(critere, categ);
        }
         model.addAttribute("resultat", liste);
        return liste;
    }

    @GetMapping("/enchere/encherir")
    @ResponseBody
    @CrossOrigin
    public void encherir(@RequestParam(value = "idEnchere") String idEnchere,
            @RequestParam(value = "montant") String montant,
            @RequestParam(value = "idClient") String idClient) throws Exception{
                int idE = Integer.parseInt(idEnchere);
                int m = Integer.parseInt(montant);
                int idC = Integer.parseInt(idClient);
        daoEnchere.reencherir(idE, m, idC);
    }

    @GetMapping("/enchere/historique")
    @ResponseBody
    @CrossOrigin
    public List<Historiqueclient> historiqueClient(@RequestParam(value = "idClient") String idClient) {
                int idC = Integer.parseInt(idClient);
        return daoEnchere.historique(idC);
    }

    @GetMapping("/enchere/liste")
    @ResponseBody
    @CrossOrigin
    public List<DetailEnchere> listeEnchere() {
        return daoEnchere.allEnchere();
    }

    @GetMapping("/enchere/detailenchere")
    @ResponseBody
    @CrossOrigin
    public List<DetailEnchere> detailenchere(@RequestParam(value = "idEnchere") String idEnchere) {
                int id = Integer.parseInt(idEnchere);
        return daoEnchere.detailEnchere(id);
    }

}
