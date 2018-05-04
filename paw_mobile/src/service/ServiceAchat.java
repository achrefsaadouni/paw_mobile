/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.Achat;
import Entity.LigneAchat;
import Entity.Panier;
import Entity.Produit;
import Entity.Utilisateur;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.Format;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author achref
 */
public class ServiceAchat {

    private String code;
    private String reponse;
    private List<Achat> listachats;
    private List<LigneAchat> ligneachats;

    public ServiceAchat() {
        this.listachats = new ArrayList<>();
    }

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
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/payer?id=" + id + "&nbr=" + nbr + "&user=" + Utilisateur.membre.getId() + "&status=" + status);
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

    public ArrayList<Achat> getmesAchats(String json) {

        ArrayList<Achat> achats = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> p = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) p.get("root");
            for (Map<String, Object> obj : list) {
                float id = Float.parseFloat(obj.get("idAchat").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());

                Date date = new Date(((Double) ((Map<String, Object>) obj.get("dateAchat")).get("timestamp")).longValue() * 1000);
                Achat e = new Achat((int) id, convertTime(date.getTime()), prix, obj.get("etat").toString());
                e.setList(getlignes(e.getId_achat()));
                achats.add(e);
            }

        } catch (Exception ex) {
        }

        return achats;

    }

    public List<Achat> mesAchats() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/consultermesAchat?user=" + Utilisateur.membre.getId());
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                listachats = getmesAchats(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listachats;
    }

    public String convertTime(long time) {
        Date date = new Date(time);
        Format format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return format.format(date);
    }

    public List<LigneAchat> getlignes(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/consulterAchat?id=" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ligneachats = getmesLigneAchats(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return ligneachats;
    }

    public ArrayList<LigneAchat> getmesLigneAchats(String json) {

        ArrayList<LigneAchat> achats = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();

            Map<String, Object> p = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) p.get("root");
            for (Map<String, Object> obj : list) {
                float id = Float.parseFloat(obj.get("idLigne").toString());
                float nbr = Float.parseFloat(obj.get("nbrProduit").toString());
                String pro = obj.get("idProduit").toString();
                List<String> t = StringUtil.tokenize(pro, ',');
                Produit produit = new Produit();
                produit.setImage1(t.get(1).substring(8));
                produit.setPrix(Float.parseFloat(t.get(4).substring(6)));
                produit.setLibelle(t.get(3).substring(9));
                LigneAchat e = new LigneAchat((int) id, (int) nbr, produit);
                achats.add(e);
            }

        } catch (Exception ex) {
        }

        return achats;

    }

}
