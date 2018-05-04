/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.LigneAchat;
import Entity.Panier;
import Entity.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author achref
 */
public class ServiceAchat {

    private String code;
    private String reponse;

    public String generateCode() {
        final String str = null;
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/code");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                setCode(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }

    public String Payer(String status) {
        String id = "";
        String nbr = "";
        ConnectionRequest con = new ConnectionRequest();
        for (LigneAchat ligne : Panier.getPanier()) {
            id += String.valueOf(ligne.getProduit().getId_produit()) + ",";
            nbr += String.valueOf(ligne.getNbr_produit()) + ",";
        }
        id = id.substring(0, id.length() - 1);
        nbr = nbr.substring(0, nbr.length() - 1);
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/payer?id=" + id + "&nbr=" + nbr+"&user="+Utilisateur.membre.getId()+"&status="+status);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                setReponse(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return reponse;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }
}
