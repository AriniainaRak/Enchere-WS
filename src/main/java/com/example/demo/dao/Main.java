package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Client;

public class Main {
    public static void main(String[] args) throws Exception{
        System.out.println("hello lessy eh");
        DAOEnchere dao = new DAOEnchere();
        List<Client> client = dao.getClient(1);
        System.out.println(client.size());
        // dao.reencherir(4, 150000, 2);
    }
}
