/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.messaging.Message;
import com.codename1.ui.Display;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author vinga
 */
public class Mail {

    public static boolean send(String recepteur,String code) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/paw_web/web/app_dev.php/mobile/mail?recepteur="+recepteur+"&code="+code);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return false;

    }
}
