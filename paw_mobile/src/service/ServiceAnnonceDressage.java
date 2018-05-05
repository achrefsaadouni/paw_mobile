/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.AnnonceDressage;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.File;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class ServiceAnnonceDressage {
    ArrayList<AnnonceDressage> listAnnonceDressage = new ArrayList<>();
    
    public void ajoutAnnonceDressage(AnnonceDressage A){       
        ConnectionRequest con = new ConnectionRequest();
//                   localhost/Paw_WEB/web/app_dev.php/AjouterAnnonceDressage2?          age=2             &type=chien          &sexe=male          &race=berger         &couleur=red               &typeTr=Advanced         &message=rien 
        String url ="http://localhost/Paw_WEB/web/app_dev.php/AjouterAnnonceDressage2?"+"age="+A.getAge()+"&type="+A.getType()+"&sexe="+A.getSex()+"&race="+A.getRace()+"&couleur="+A.getCouleur()+"&typeTr="+A.getTypeTr()+"&message="+A.getMessage_complementaire()+"&userId="+A.getId_utilisateur();//+"&images="+A.getImages();  
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);       
    }
    
    
    public ArrayList<AnnonceDressage> getListDressage(String json) {

        ArrayList<AnnonceDressage> listAnnonceDressages = new ArrayList<>();
        
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> annonceTr = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(annonceTr);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) annonceTr.get("root");

            for (Map<String, Object> obj : list) {
                AnnonceDressage e = new AnnonceDressage();

                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                
                System.out.println(id);
                e.setId((int)id);
                e.setAge((int) age);
                e.setCouleur(obj.get("couleur").toString());
                e.setTypeTr(obj.get("typetr").toString());
                e.setType(obj.get("type").toString());
                e.setMessage_complementaire(obj.get("messageComplementaire").toString());
                e.setSex(obj.get("sex").toString());
                e.setRace(obj.get("race").toString());
//                e.setImages(obj.get("images").toString());
                //System.out.println();
                System.out.println(e);
                listAnnonceDressages.add(e);

            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(listAnnonceDressages);
        return listAnnonceDressages;

    }
    
    ArrayList<AnnonceDressage> listTr = new ArrayList<>();
    
    public ArrayList<AnnonceDressage> getList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/getListDressage");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonceDressage ser = new ServiceAnnonceDressage();
                listTr = ser.getListDressage(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTr;
    }
    
}
