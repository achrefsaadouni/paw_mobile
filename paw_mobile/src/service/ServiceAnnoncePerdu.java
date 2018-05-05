/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.AnnoncePerdu;
import Entity.AnnonceTrouvee;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
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
public class ServiceAnnoncePerdu {
    
      public void ajoutAnnoncePerdu(AnnoncePerdu at) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/paw_web/web/app_dev.php/AddAnnonceTrouver/new?"+ "&colier=" + at.getColier() + "&couleur=" + at.getCouleur()+ "&lieu=" + at.getLieu_perdu()+ "&message=" + at.getMessage_complementaire()+ "&race=" + at.getRace()+ "&sexe=" + at.getSex()+ "&age=" + at.getAge()+ "&type=" + at.getType()+"&image=" + at.getImages()+"&user="+at.getId_utilisateur();
        con.setUrl(Url);

            con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      
      
     
    public ArrayList<AnnoncePerdu> getListAnPr(String json) {

        ArrayList<AnnoncePerdu> listAnnoncePerdu = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> annoncesPR = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(annoncesPR);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesPR.get("root");

            for (Map<String, Object> obj : list) {
                AnnoncePerdu e = new AnnoncePerdu();

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
                e.setLieu_perdu(obj.get("lieuPerdu").toString());
                e.setType(obj.get("type").toString());
                e.setMessage_complementaire(obj.get("messageComplementaire").toString());
                e.setSex(obj.get("sex").toString());
                e.setRace(obj.get("race").toString());
                e.setImages(obj.get("images").toString());
                //System.out.println();
                System.out.println(e);
                listAnnoncePerdu.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnoncePerdu);
        return listAnnoncePerdu;

    }
    
       ArrayList<AnnoncePerdu> listAnnPR = new ArrayList<>();
    
   
       
    public ArrayList<AnnoncePerdu> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/getAllAnnPR");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnoncePerdu ser = new ServiceAnnoncePerdu();
                listAnnPR = ser.getListAnPr(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnPR;
    }
    
    
    ArrayList<AnnoncePerdu> list = new ArrayList<>();
    
     public ArrayList<AnnoncePerdu> getList3(int id){       
        ConnectionRequest con = new ConnectionRequest( );
        con.setUrl("http://localhost/paw_web/web/app_dev.php/listerMesAnnonce56/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnoncePerdu ser = new ServiceAnnoncePerdu();
                list = ser.getListAnPr(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return list;
    }
    
   public void modifier(int id  , String lieuxP , String sexe,String race , String couleur ,String msg, String colier) {
        ConnectionRequest con = new ConnectionRequest();
            String Url = ("http://localhost/paw_web/web/app_dev.php/modifierAnnoncePerdu56?id="+id+"&lieu="+lieuxP+"&sexe="+sexe+"&race="+race+"&couleur="+couleur+"&message="+msg+"&colier"+colier);
            con.setUrl(Url);
            con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   

public void supprimer1(int id ) {
        ConnectionRequest con = new ConnectionRequest();
            String Url = ("http://localhost/paw_web/web/app_dev.php/supprimerAnnoncePerdu_56?id="+id);
            con.setUrl(Url);
            con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
   


    
    
}
