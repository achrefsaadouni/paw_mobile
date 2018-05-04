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
ArrayList<Produit> listProduits = new ArrayList<>();
    public ArrayList<Produit> getListProduit(String json) {

        ArrayList<Produit> produits = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> p = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) p.get("root");
            for (Map<String, Object> obj : list) {
                float id = Float.parseFloat(obj.get("id").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                float quantite = Float.parseFloat(obj.get("quantite").toString());
                Produit e = new Produit((int) id, obj.get("libelle").toString(), prix,(int)quantite,obj.get("description").toString(),obj.get("image1").toString(),obj.get("image2").toString(),obj.get("type").toString());
                produits.add(e);
            }

        } catch (IOException ex) {
        }
        System.out.println(produits);
        return produits;

    }
    
      
    
    public ArrayList<Produit> getList2(String type){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/boutique/type="+type);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listProduits = getListProduit(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listProduits;
    }
    
    
   

}
