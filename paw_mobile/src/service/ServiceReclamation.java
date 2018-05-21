/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import Entity.Reclamation;
import Entity.RepRec;
import Entity.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceReclamation {

    public void ajoutReclamation(Reclamation ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/paw_web/web/app_dev.php/api/AjoutFeedBack?objet=" + ta.getObjet() + "&text=" + ta.getText()+ "&type=" + ta.getType()+ "&utilisateur=" + Utilisateur.membre.getId();
        con.setUrl(Url);
        System.out.println(Url);
        System.out.println(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Reclamation> getListReclamation(String json) {

        ArrayList<Reclamation> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                Reclamation e = new Reclamation();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setEtat(obj.get("etat").toString());
                e.setObjet(obj.get("objet").toString());
                e.setText(obj.get("text").toString());
                e.setType(obj.get("type").toString());
                Date date = new Date(((Double) ((Map<String, Object>) obj.get("date")).get("timestamp")).longValue() * 1000);
                e.setDate(date);
                //System.out.println(date);
                
                String chaine = obj.get("idUtilisateur").toString();
                String x = chaine.substring(chaine.indexOf("id=")+3, chaine.length()-2);
                String y = x.substring(0, x.indexOf(","));
                e.setUtilisateur((int)Float.parseFloat(y));
                listUsers.add(e);

            }

        } catch (IOException ex) {
        }
        return listUsers;

    }
    
    public ArrayList<RepRec> getListRepReclamation(String json) {

        ArrayList<RepRec> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");

            for (Map<String, Object> obj : list) {
                RepRec e = new RepRec();

                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setText(obj.get("text").toString());

                listUsers.add(e);

            }

        } catch (IOException ex) {
        }
        return listUsers;

    }
    
    
    ArrayList<Reclamation> listReclamations = new ArrayList<>();
    
    ArrayList<RepRec> listRepReclamations = new ArrayList<>();
    
    public ArrayList<Reclamation> getList2(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/api/Reclamation/"+id);  
                //System.out.println(Url);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReclamation ser = new ServiceReclamation();
                listReclamations = ser.getListReclamation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReclamations;
    }
    public ArrayList<RepRec> getListRep2(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/api/RepRec/"+id);  
        System.out.println("http://localhost/paw_web/web/app_dev.php/api/RepRec/"+id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceReclamation ser = new ServiceReclamation();
                listRepReclamations = ser.getListRepReclamation(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listRepReclamations;
    }

    
    

}
