/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.AnnonceAccouplement;
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
 * @author gmehd
 */
public class ServiceAccouplement {
    
    public void ajoutAnnonceAccouplement(AnnonceAccouplement aa) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/paw_web/web/app_dev.php/ajouterApi?type="+aa.getType()+"&age="+aa.getAge()+"&sex="+aa.getSex()+"&couleur="+aa.getCouleur()+"&messageComplementaire="+aa.getMessage_complementaire()+"&race="+aa.getRace()+"&typePoil="+aa.getType_poil()+"&lieu_trouve="+aa.getLieu()+"&vaccin="+aa.getVaccin()+"&dossier="+aa.getDossier()+"&images="+aa.getImages();
        con.setUrl(Url);

            con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
    
    public ArrayList<AnnonceAccouplement> getListAnAcc(String json) {

        ArrayList<AnnonceAccouplement> listAnnonceAccouplement = new ArrayList<>();

        try {
            //System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> annoncesAcc = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(annoncesAcc);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) annoncesAcc.get("root");

            for (Map<String, Object> obj : list) {
                AnnonceAccouplement e = new AnnonceAccouplement();

                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                
                //System.out.println(id);
                e.setId((int)id);
                e.setAge((int) age);
                e.setDossier(obj.get("dossier").toString());
                e.setCouleur(obj.get("couleur").toString());
                e.setLieu(obj.get("lieuTrouve").toString());
                e.setType(obj.get("type").toString());
                e.setMessage_complementaire(obj.get("messageComplementaire").toString());
                e.setSex(obj.get("sex").toString());
                e.setRace(obj.get("race").toString());
                e.setImages(obj.get("images").toString());
                e.setType_poil(obj.get("typePoil").toString());
                e.setVaccin(obj.get("vaccin").toString());
                //System.out.println();
                //System.out.println(e);
                listAnnonceAccouplement.add(e);

            }

        } catch (IOException ex) {
        }
        System.out.println(listAnnonceAccouplement);
        return listAnnonceAccouplement;

    }
    
    
    ArrayList<AnnonceAccouplement> listAnnAcc = new ArrayList<>();
    
    public ArrayList<AnnonceAccouplement> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/listerMesAnnonce2");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAccouplement ser = new ServiceAccouplement();
                listAnnAcc = ser.getListAnAcc(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnAcc;
    }
    
    
    
//    ArrayList<AnnonceAccouplement> list = new ArrayList<>();
//    
//     public ArrayList<AnnonceAccouplement> getList3(int id){       
//        ConnectionRequest con = new ConnectionRequest( );
//        con.setUrl("http://localhost/paw_web/web/app_dev.php/listerAnnoncePerdu1/"+id);  
//        con.addResponseListener(new ActionListener<NetworkEvent>() {
//            @Override
//            public void actionPerformed(NetworkEvent evt) {
//                ServiceAccouplement ser = new ServiceAccouplement();
//                list = ser.getListAnAcc(new String(con.getResponseData()));
//            }
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//        return list;
//    }
//    
//    public void modifier(String id  ,String lieuTrouve) {
//        ConnectionRequest con = new ConnectionRequest();
//            String Url = ("http://localhost/paw_web/web/app_dev.php/modifierAcc?id="+id+"&lieuTrouve="+lieuTrouve);
//            con.setUrl(Url);
//            con.addResponseListener((e) -> {
//            String str = new String(con.getResponseData());
//            System.out.println(str);
//
//        });
//        NetworkManager.getInstance().addToQueueAndWait(con);
//    }
}
