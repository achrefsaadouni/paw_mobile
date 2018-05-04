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
import Entity.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author sana
 */
public class ServiceUtilisateur {

    public void ajoutUtilisateur(Utilisateur ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/paw_web/web/app_dev.php/api/Inscription?nom=" + ta.getNom() + "&prenom=" + ta.getPrenom()+ "&adresse=" + ta.getAddresse()+ "&email=" + ta.getEmail()+ "&sexe=" + ta.getSexe()+ "&username=" + ta.getUsername()+ "&numero=" + ta.getNumero()+ "&avatar=" + ta.getAvatar()+ "&password=" + ta.getPassword();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void MAJUtilisateur(Utilisateur ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/paw_web/web/app_dev.php/api/MiseAjourUtilisateur?nom=" + ta.getNom() + "&prenom=" + ta.getPrenom()+ "&adresse=" + ta.getAddresse()+ "&email=" + ta.getEmail()+ "&sexe=" + ta.getSexe()+ "&username=" + ta.getUsername()+ "&numero=" + ta.getNumero()+ "&avatar=" + ta.getAvatar()+ "&password=" + ta.getPassword()+"&id=" + Utilisateur.membre.getId();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Utilisateur> getListUtilisateur(String json) {

        ArrayList<Utilisateur> listUsers = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> etudiants = j.parseJSON(new CharArrayReader(json.toCharArray()));  
            List<Map<String, Object>> list = (List<Map<String, Object>>) etudiants.get("root");
            for (Map<String, Object> obj : list) {
                Utilisateur e = new Utilisateur();
                float id = Float.parseFloat(obj.get("id").toString());
                float numero = Float.parseFloat(obj.get("numero").toString());
                e.setId((int) id);
                e.setNumero((int) numero);
                e.setAdresse(obj.get("addresse").toString());
                e.setPrenom(obj.get("prenom").toString());
                e.setNom(obj.get("nom").toString());
                e.setSexe(obj.get("sexe").toString());
                e.setEmail(obj.get("email").toString());
                e.setAvatar(obj.get("avatar").toString());
                e.setUsername(obj.get("username").toString());
                e.setPassword(obj.get("password").toString());
                listUsers.add(e);

            }

        } catch (IOException ex) {
        }
        return listUsers;

    }
    
    
    ArrayList<Utilisateur> listUtilisateurs = new ArrayList<>();
    
    public ArrayList<Utilisateur> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/api/Connexion");   
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUtilisateur ser = new ServiceUtilisateur();
                listUtilisateurs = ser.getListUtilisateur(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listUtilisateurs;
    }

}
