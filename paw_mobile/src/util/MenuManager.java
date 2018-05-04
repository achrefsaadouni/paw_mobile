/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Entity.Utilisateur;
import com.codename1.components.ImageViewer;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.URLImage;
import com.codename1.ui.util.Resources;
import forms.boutique.BoutiqueForm;

/**
 *
 * @author Meedoch
 */
public class MenuManager {

    public static void createMenu(Form f, Resources theme) {
        String uuid = "sideMenuCommandCenter";
        URLImage i;
        String url = "http://localhost/paw_web/web/images/pawBoutique/" + Utilisateur.membre.getAvatar();
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("user.png"), false);
        i = URLImage.createToStorage(enc, Utilisateur.membre.getAvatar(), url);
        f.getToolbar().addCommandToSideMenu("", i.scaled(150, 150), (evt) -> {

        });
        f.getToolbar().addCommandToSideMenu(Utilisateur.membre.getNom() + " " + Utilisateur.membre.getPrenom(), null, (evt) -> {

        });
        f.getToolbar().addCommandToSideMenu("Evenements", theme.getImage("event.png").scaled(20, 20), (evt) -> {

        });
        f.getToolbar().addCommandToSideMenu("Publications", theme.getImage("publication.png").scaled(20, 20), (evt) -> {

        });
        f.getToolbar().addCommandToSideMenu("Boutique", theme.getImage("boutique.png").scaled(20, 20), (evt) -> {
            BoutiqueForm bf = new BoutiqueForm();
            bf.afficherBoutique("Laisse, Collier et Harnais");
        });
        f.getToolbar().addCommandToSideMenu("Messages", theme.getImage("messages.png").scaled(20, 20), (evt) -> {

        });
        f.getToolbar().addCommandToSideMenu("Profile", theme.getImage("profile.png").scaled(20, 20), (evt) -> {

        });
    }

}
