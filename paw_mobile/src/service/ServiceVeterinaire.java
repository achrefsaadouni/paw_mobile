/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package service;

/**
 *
 * @author gmehd
 */

import Entity.Veterinaire;
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

public class ServiceVeterinaire {
    public ArrayList<Veterinaire> getListVets(String json) {

        ArrayList<Veterinaire> listEtudiants = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(etudiants);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Veterinaire v = new Veterinaire();

                // System.out.println(obj.get("id"));
                float id = Float.parseFloat(obj.get("id").toString());
                float numero = Float.parseFloat(obj.get("numero").toString()) ;
                float longitude = Float.parseFloat(obj.get("longitude").toString()) ;
                float latitude = Float.parseFloat(obj.get("latitude").toString()) ;
                System.out.println(id);
                v.setId((int) id);
                
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                
                v.setNom(obj.get("nom").toString());
                v.setPrenom(obj.get("prenom").toString());
                v.setAdresse(obj.get("adresse").toString());
                v.setRegion(obj.get("region").toString());
                v.setImages(obj.get("images").toString());
                v.setEmail(obj.get("email").toString());
                v.setLatitude(latitude);
                v.setLongitude(longitude);
                v.setNumero((int) numero);
                
                System.out.println(v);
                listEtudiants.add(v);

            }

        } catch (IOException ex) {
        }
        System.out.println(listEtudiants);
        return listEtudiants;

    }
    
    
    ArrayList<Veterinaire> listVets = new ArrayList<>();
    
    public ArrayList<Veterinaire> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/listerVeterinaire");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceVeterinaire ser = new ServiceVeterinaire();
                listVets = ser.getListVets(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listVets;
    }
    
    public void rating(int valeur, int id_utilisateur, int id_veterinaire){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/rate?valeur="+valeur+"&id_utilisateur="+id_utilisateur+"&id_veterinaire="+id_veterinaire);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                String s = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

    }
}
