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
import com.mycompany.myapp.AnnonceForm;
import com.mycompany.myapp.MenuForm;
import com.mycompany.myapp.ServiceForm;
import forms.FeedBack.AjoutFeedBackForm;
import forms.FeedBack.MesFeedBackForm;
import forms.boutique.BoutiqueForm;
import forms.boutique.PanierForm;
import forms.utilisateur.EditProfile;
import forms.veterinaire.AfficheVeterinaire;

/**
 *
 * @author Meedoch
 */
public class MenuManager {
    Resources theme ;
    public static void createMenu(Form f, Resources theme) {
        
        String uuid = "sideMenuCommandCenter";
        URLImage i;
        String url = "http://localhost/paw_web/web/images/pawUsers/"+Utilisateur.membre.getAvatar();
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("user.png"), false);
        i = URLImage.createToStorage(enc, Utilisateur.membre.getAvatar(), url);
        f.getToolbar().addCommandToSideMenu("", i.scaled(150, 150), (evt) -> {
            EditProfile ep = new EditProfile();
            ep.affiche();
        });

        f.getToolbar().addCommandToSideMenu("Acceuil", theme.getImage("homepage.png").scaled(20, 20), (evt) -> {
            MenuForm mf = new MenuForm(theme);
            mf.affiche();

        });
        f.getToolbar().addCommandToSideMenu("Annonce", theme.getImage("sitting-dog.png").scaled(20, 20), (evt) -> {
            AnnonceForm af= new AnnonceForm(theme);
            af.affiche();
        });
        f.getToolbar().addCommandToSideMenu("Veterinaires", theme.getImage("syringe.png").scaled(20, 20), (evt) -> {
            AfficheVeterinaire av = new AfficheVeterinaire(theme);
            av.getF().show();
        });
        f.getToolbar().addCommandToSideMenu("Boutique", theme.getImage("shop.png").scaled(20, 20), (evt) -> {
            BoutiqueForm bf = new BoutiqueForm();
            bf.afficherBoutique("Laisse, Collier et Harnais");
        });
        f.getToolbar().addCommandToSideMenu("Panier", theme.getImage("boutique.png").scaled(20, 20), (evt) -> {
            PanierForm bf = new PanierForm(theme);
            bf.affichePanier();
        });
        f.getToolbar().addCommandToSideMenu("Services", theme.getImage("dog-training.png").scaled(20, 20), (evt) -> {
            ServiceForm sf = new ServiceForm((theme));
            sf.affiche();
        });
        f.getToolbar().addCommandToSideMenu("FeedBack", theme.getImage("note.png").scaled(20, 20), (evt) -> {
            AjoutFeedBackForm ajfb= new AjoutFeedBackForm();
            ajfb.afficher();
        });
    }

}
