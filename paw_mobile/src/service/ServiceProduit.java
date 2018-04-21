/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Produit;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author achref
 */
public class ServiceProduit {
 /*

    public ArrayList<Produit> getListTask(String json) {

        ArrayList<Produit> produits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> p = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) p.get("root");
            for (Map<String, Object> obj : list) {
                Produit e = new Produit(obj.get("libelle").toString(),obj.get("type").toString());

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setEtat(obj.get("state").toString());
                e.setNom(obj.get("name").toString());
                System.out.println(e);
                produits.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(produits);
        return produits;

    }
    */

}
