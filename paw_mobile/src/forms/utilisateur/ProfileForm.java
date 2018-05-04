/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.utilisateur;

import Entity.Utilisateur;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import service.ServiceUtilisateur;

/**
 *
 * @author AYOUB
 */
public class ProfileForm {
    Form currentform;
    Resources theme;

    public ProfileForm() {
        currentform = new Form("rien", new BoxLayout(BoxLayout.Y_AXIS));
        theme = UIManager.initFirstTheme("/theme_2_1");
        
        UIBuilder uib = new UIBuilder();
        
        currentform = uib.createContainer(theme, "EditProfil").getComponentForm();
    }
    
    
    public void affiche() { 
        UIBuilder ui = new UIBuilder();
        
        TextField nom = (TextField) ui.findByName("nom", currentform.getContentPane());
        nom.setText(Utilisateur.membre.getNom());
        TextField prenom = (TextField) ui.findByName("prenom", currentform.getContentPane());
        prenom.setText(Utilisateur.membre.getPrenom());
        TextField username = (TextField) ui.findByName("username", currentform.getContentPane());
        username.setText(Utilisateur.membre.getUsername());
        TextField email = (TextField) ui.findByName("email", currentform.getContentPane());
        email.setText(Utilisateur.membre.getEmail());
        TextField addresse = (TextField) ui.findByName("addresse", currentform.getContentPane());
        addresse.setText(Utilisateur.membre.getAddresse());
        TextField numero = (TextField) ui.findByName("numero", currentform.getContentPane());
        numero.setText(""+Utilisateur.membre.getNumero());
        TextField avatar = (TextField) ui.findByName("avatar", currentform.getContentPane());
        avatar.setText(Utilisateur.membre.getAvatar());
        TextField pswd = (TextField) ui.findByName("mdp", currentform.getContentPane());
        pswd.setText(Utilisateur.membre.getPassword());
        ComboBox<String> sexe = (ComboBox<String>) ui.findByName("sexe", currentform.getContentPane());
        sexe.setSelectedItem(Utilisateur.membre.getSexe());
        
        Button edit = (Button) ui.findByName("edit", currentform.getContentPane());
        
        edit.addActionListener(l->
        {
            if(!IsEmailUnique(email.getText())){
                if(Dialog.show("Email déjà existant",
                                    "Veuillez choisir une autre adresse email", 
                                    "Ok", 
                                    null))
                    {}
            }
            else if (!IsUsernameUnique(username.getText())){
                if(Dialog.show("Username déjà existant",
                                   "Veuillez choisir un autre nom d'utilisateur", 
                                   "Ok", 
                                   null))
                   {}
            }
            else if (email.getText().equals("")||nom.getText().equals("")||prenom.getText().equals("")||
                     username.getText().equals("")||pswd.getText().equals("")||addresse.getText().equals(""))
            {
                if(Dialog.show("Informations manquantes",
                                   "Veuillez remplir tout les champs", 
                                   "Ok", 
                                   null))
                   {}
            }
            else if (!email.getText().equals(Utilisateur.membre.getEmail())||!sexe.getSelectedItem().equals(Utilisateur.membre.getSexe())||!nom.getText().equals(Utilisateur.membre.getNom())||!prenom.getText().equals(Utilisateur.membre.getPrenom())||
                     !username.getText().equals(Utilisateur.membre.getUsername())||!pswd.getText().equals(Utilisateur.membre.getPassword())||!addresse.getText().equals(Utilisateur.membre.getAddresse()))
            {
                ServiceUtilisateur userservice = new ServiceUtilisateur();
                Utilisateur newuser = new Utilisateur(nom.getText(), prenom.getText(), addresse.getText(), email.getText(), sexe.getSelectedItem(), Integer.parseInt(numero.getText()), avatar.getText(), pswd.getText(), username.getText());
                userservice.MAJUtilisateur(newuser);
                newuser.setId(Utilisateur.membre.getId());
                Utilisateur.membre=newuser;
                if(Dialog.show("Informations mises à jour",
                                   "Vos données ont étés mises à jour", 
                                   "Ok", 
                                   null))
                   {}
            }
            else{
                if(Dialog.show("Aucun changement",
                                   "Il n'y a eu aucun changement dans vos données", 
                                   "Ok", 
                                   null))
                   {}
            }
        }
        );
        currentform.show();
    }
    
    public Boolean IsUsernameUnique(String x){
        if(x.equals(Utilisateur.membre.getUsername()))
            return true;
        ServiceUtilisateur se = new ServiceUtilisateur();
        ArrayList<Utilisateur> liste = se.getList2();
        int h=0;
        for (Utilisateur user : liste) {
            if (user.getUsername().equals(x))
                h++;
        }
        if (h==0)
            return true;
        else
            return false;
    }
    public Boolean IsEmailUnique(String x){
        if(x.equals(Utilisateur.membre.getEmail()))
            return true;    
        ServiceUtilisateur se = new ServiceUtilisateur();
        ArrayList<Utilisateur> liste = se.getList2();
                int h=0;
        for (Utilisateur user : liste) {
            if (user.getEmail().equals(x))
                h++;
        }
        if (h==0)
            return true;
        else
            return false;
    }
    
}
