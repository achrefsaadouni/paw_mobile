/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import Entity.AnnonceGarde;
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
 * @author Lenovo
 */
public class ServiceAnnonceGarde {
    ArrayList<AnnonceGarde> listAnnonceGarde = new ArrayList<>();
    
    public void ajoutAnnonceGarde(AnnonceGarde A){       
        ConnectionRequest con = new ConnectionRequest();
//                   http://localhost/Paw_WEB/web/app_dev.php/AjouterAnnonceGarde2?   age=2             &type=chien          &sexe=male          &race=berger         &couleur=red               &dureSit=3                 &todolist=marche             &message=rien                             &userId 
        String url ="http://localhost/Paw_WEB/web/app_dev.php/AjouterAnnonceGarde2?"+"age="+A.getAge()+"&type="+A.getType()+"&sexe="+A.getSex()+"&race="+A.getRace()+"&couleur="+A.getCouleur()+"&dureSit="+A.getDureSit()+"&todolist="+A.getTodolist()+"&message="+A.getMessage_complementaire()+"&user="+A.getId_utilisateur();  
        con.setUrl(url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);       
    }
    
    public ArrayList<AnnonceGarde> getListGarde(String json) {

        ArrayList<AnnonceGarde> listAnnonceGarde = new ArrayList<>();
        
        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> annonceTr = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(annonceTr);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) annonceTr.get("root");

            for (Map<String, Object> obj : list) {
                AnnonceGarde e = new AnnonceGarde();

                float id = Float.parseFloat(obj.get("id").toString());
                float age = Float.parseFloat(obj.get("age").toString());
                float dure = Float.parseFloat(obj.get("dureSit").toString());
                
                System.out.println(id);
                e.setId((int)id);
                e.setAge((int) age);
                e.setCouleur(obj.get("couleur").toString());
                e.setDureSit((int)dure);
                e.setType(obj.get("type").toString());
                e.setMessage_complementaire(obj.get("messageComplementaire").toString());
                e.setSex(obj.get("sex").toString());
                e.setRace(obj.get("race").toString());
//                e.setImages(obj.get("images").toString());
                //System.out.println();
                System.out.println(e);
                listAnnonceGarde.add(e);

            }

        } catch (IOException ex) {
            System.out.println(ex);
        }
        System.out.println(listAnnonceGarde);
        return listAnnonceGarde;

    }
    
    ArrayList<AnnonceGarde> listTr = new ArrayList<>();
    
    public ArrayList<AnnonceGarde> getList(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/getListDressage");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceAnnonceGarde ser = new ServiceAnnonceGarde();
                listTr = ser.getListGarde(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listTr;
    }
    
    
}
