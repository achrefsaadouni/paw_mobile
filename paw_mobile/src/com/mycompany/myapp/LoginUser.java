/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entity.Utilisateur;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import static com.mycompany.myapp.MyApplication.membre;
import forms.boutique.BoutiqueForm;
import forms.boutique.PanierForm;
import java.util.ArrayList;
import java.util.Iterator;
import service.ServiceUtilisateur;
import util.FacebookLogin;

/**
 *
 * @author AYOUB
 */
class LoginUser {
    Form form ;
    Resources theme;

    public LoginUser(Resources theme) {
        this.theme = theme;
    }
    
    void init() {
        theme = UIManager.initFirstTheme("/theme_2_1");
        UIBuilder ui = new UIBuilder();
        Form form = ui.createContainer(theme, "GUI 1").getComponentForm();
        form.setUIID("Logins");
        form.setTitle("Paw");
        TextField username = (TextField) ui.findByName("username", form.getContentPane());
        TextField pswd = (TextField) ui.findByName("password", form.getContentPane());
        Button connexion = (Button) ui.findByName("connexion", form.getContentPane());
        Button connexionfacebook = (Button) ui.findByName("Facebook", form.getContentPane());
        Button sinscrire = (Button) ui.findByName("signup", form.getContentPane());
        //        form.add(connexionfacebook);
        connexionfacebook.addActionListener(lll->{
            System.out.println("houniiiiii");
            FacebookLogin.signIn();
        });
        connexion.addActionListener(l -> {
            ServiceUtilisateur service = new ServiceUtilisateur();
            //service.getList2().toString();
            ArrayList<Utilisateur>  liste = service.getList2();
            liste.size();
            int x =0;
            for (Iterator<Utilisateur> iterator = liste.iterator(); iterator.hasNext();) {
                Utilisateur next = iterator.next();
                if (next.getUsername().equals(username.getText()) 
                        //&& next.getPassword()== password.getText()
                        )
                {
                   membre=next ;
                   x=1;
                }
            }
            if (x==1)
            {
                this.theme = UIManager.initFirstTheme("/theme");
               MenuForm mf = new MenuForm(theme);
               mf.affiche();
            }
            else if (x==0)
            {
                    if(Dialog.show("Invalid Credentials",
                                    "Username ou mot de passe erroné", 
                                    "Ok", 
                                    null))
                    {
                       
                    }
            }

            System.out.println(membre);
        });
        
        
        sinscrire.addActionListener(ll -> {
            SignupFb s = new SignupFb();
            s.init();
            s.start();
        });
        
       
         
         form.show();
    }

    void start() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
