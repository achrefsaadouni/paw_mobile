/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.AnnonceTrouvee;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.codename1.util.StringUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Guideinfo
 */
public class ServiceAnnonceTrouvee {
    
      public void ajoutAnnonceTrouvee(AnnonceTrouvee at) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/paw_web/web/app_dev.php/AddAnnonceTrouver/new?"+ "&colier=" + at.getColier() + "&couleur=" + at.getCouleur()+ "&lieu=" + at.getLieu_trouve()+ "&message=" + at.getMessage_complementaire()+ "&race=" + at.getRace()+ "&sexe=" + at.getSex()+ "&age=" + at.getAge()+ "&type=" + at.getType()+"&image=" + at.getImages()+"&user="+at.getId_utilisateur();
        con.setUrl(Url);


        NetworkManager.getInstance().addToQueue(con);
    }
      
      
      
     
    public ArrayList<AnnonceTrouvee> getListAnTr(String json) {

        ArrayList<AnnonceTrouvee> listAnnonceTrouvee = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> annoncesTr = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(annoncesTr);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesTr.get("root");

            for (Map<String, Object> obj : list) {
                AnnonceTrouvee e = new AnnonceTrouvee();

                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                
                System.out.println(id);
                System.out.println(obj.get("utilisateur"));
                String partsUsr=obj.get("utilisateur").toString();
                List<String> userPart=StringUtil.tokenize(partsUsr, ",");
                System.err.println(userPart.get(0).substring(5));
                e.setNomUtilisater(userPart.get(0).substring(5));
                e.setPrenomUtilisateur(userPart.get(1).substring(8));
                e.setAdresseUtilisateur(userPart.get(2).substring(10));
                e.setNumeroUtilisateur(userPart.get(3).substring(8));
                e.setEmailUtilisateur(userPart.get(21).substring(7));
                e.setId((int)id);
                e.setAge((int) age);
                e.setColier(obj.get("colier").toString());
                e.setCouleur(obj.get("couleur").toString());
                e.setLieu_trouve(obj.get("lieuTrouve").toString());
                e.setType(obj.get("type").toString());
                e.setMessage_complementaire(obj.get("messageComplementaire").toString());
                e.setSex(obj.get("sex").toString());
                e.setRace(obj.get("race").toString());
                e.setImages(obj.get("images").toString());
                //System.out.println();
                System.out.println(e);
                listAnnonceTrouvee.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnonceTrouvee);
        return listAnnonceTrouvee;

    }
    
       ArrayList<AnnonceTrouvee> listAnnTr = new ArrayList<>();
    
   
       
    public ArrayList<AnnonceTrouvee> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/getAllAnnTR");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonceTrouvee ser = new ServiceAnnonceTrouvee();
                listAnnTr = ser.getListAnTr(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnTr;
    }
    
    
    ArrayList<AnnonceTrouvee> list = new ArrayList<>();
    
     public ArrayList<AnnonceTrouvee> getList3(int id){       
        ConnectionRequest con = new ConnectionRequest( );
        con.setUrl("http://localhost/paw_web/web/app_dev.php/listerAnnoncePerdu1/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonceTrouvee ser = new ServiceAnnonceTrouvee();
                list = ser.getListAnTr(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return list;
    }
    
   public void modifier(int id  , String lieuxT , String sexe,String race , String couleur ,String msg, String colier) {
        ConnectionRequest con = new ConnectionRequest();
            String Url = ("http://localhost/paw_web/web/app_dev.php/modifierAnnonceTrouver3?id="+id+"&lieu="+lieuxT+"&sexe="+sexe+"&race="+race+"&couleur="+couleur+"&message="+msg+"&colier"+colier);
            con.setUrl(Url);
            con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   

public void supprimer1(int id ) {
        ConnectionRequest con = new ConnectionRequest();
            String Url = ("http://localhost/paw_web/web/app_dev.php/supprimerAnnonceTrouver_1?id="+id);
            con.setUrl(Url);
            con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   


    
    
    
    
}
