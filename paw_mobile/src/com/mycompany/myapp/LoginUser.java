/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entity.Utilisateur;
import static Entity.Utilisateur.membre;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
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

        connexionfacebook.addActionListener(lll->{
            FacebookLogin.signIn();
        });
        connexion.addActionListener(l -> {
            ServiceUtilisateur service = new ServiceUtilisateur();
            Utilisateur uuu = new Utilisateur();
            
            

            if (service.getUser(username.getText(), pswd.getText())!=null)
            {
                membre=service.getUser(username.getText(), pswd.getText());
               this.theme = UIManager.initFirstTheme("/theme_1");
               MenuForm mf = new MenuForm(theme);
               mf.affiche();
            }
            else
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
