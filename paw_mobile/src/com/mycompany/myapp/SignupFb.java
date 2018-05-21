/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import Entity.Utilisateur;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;
import service.ServiceUtilisateur;


/**
 *
 * @author Elhraiech Nizar
 */
public class SignupFb {

    private Form current;
    private Resources theme;
    private String s;
    String fileNameInServer ="";

    public void init() {
        theme = UIManager.initFirstTheme("/theme_2_1");

        // Enable Toolbar on all Forms by default
        Toolbar.setGlobalToolbar(true);

        // Pro only feature, uncomment if you have a pro subscription
        // Log.bindCrashProtection(true);
    }
    long dtest;
    Form form;
    TextField nom;
    TextField prenom;
    TextField email;
    TextField pswd;
    TextField adresse;
    TextField username;
    ComboBox<String> sexe;
    TextField numero;
    Button choisirImage;

    public void start() {
        UIBuilder ui = new UIBuilder();
        form = ui.createContainer(theme, "Signup").getComponentForm();
        //form1.setUIID("Logins");

        Validator v = new Validator();

        nom = (TextField) ui.findByName("nom", form.getContentPane());
        prenom = (TextField) ui.findByName("prenom", form.getContentPane());
        email = (TextField) ui.findByName("email", form.getContentPane());
        adresse = (TextField) ui.findByName("addresse", form.getContentPane());
        sexe = (ComboBox<String>) ui.findByName("sexe", form.getContentPane());
        numero = (TextField) ui.findByName("numero", form.getContentPane());
        username = (TextField) ui.findByName("username", form.getContentPane());
        pswd = (TextField) ui.findByName("mdp", form.getContentPane());
        choisirImage = (Button) ui.findByName("choisirImage", form.getContentPane());
        
        choisirImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try{
                    FileUploader fu = new FileUploader("http://localhost/paw_web/web/images/pawUsers");
                    
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if(v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }
                            String filePath = ((String)v.getSource()).substring(7);
                            System.out.println(filePath);
                            try {
                                fileNameInServer = fu.upload(filePath);
                            } 
                            catch (Exception ex) {
                                ex.printStackTrace();
                            } 
                        }
                    }, Display.GALLERY_IMAGE);  
                }
                catch(Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        v.addConstraint(email, RegexConstraint.validEmail("You must enter a valid email address to receive confirmation"));
        Button valider = (Button) ui.findByName("sinscrire", form.getContentPane());
        v.addConstraint(pswd, new LengthConstraint(8));
        valider.addActionListener(l -> {
            if (nom.getText().equals("") || prenom.equals("") || email.equals("") || pswd.equals("")|| username.equals("")|| adresse.equals("")) {
                Dialog.show("Confirmation", "Veuillez saisir tout les champs", "Ok", null);
                return;
            } 
            else if (!v.isValid()) {
                Dialog.show("Confirmation", "mot de pass ou email invalide", "Ok", null);
            } else {
                ServiceUtilisateur userservice = new ServiceUtilisateur();
                Utilisateur newuser = new Utilisateur(nom.getText(), prenom.getText(), adresse.getText(), email.getText(), sexe.getSelectedItem(), Integer.parseInt(numero.getText()), fileNameInServer, pswd.getText(), username.getText());
                userservice.ajoutUtilisateur(newuser);      
                Utilisateur.membre=newuser;
                this.theme = UIManager.initFirstTheme("/theme_1");
               MenuForm mf = new MenuForm(theme);
               mf.affiche();
            }
        });
        form.getToolbar().addCommandToLeftBar("", theme.getImage("back-arrow.png"), (vzr) -> {
            LoginUser lg = new LoginUser(theme);
            lg.init();
        });
        form.show();
    }

}
