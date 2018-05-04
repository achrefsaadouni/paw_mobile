/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import static com.codename1.ui.CN.execute;
import static com.codename1.ui.CN.isNativeShareSupported;
import static com.codename1.ui.CN.share;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import forms.FeedBack.AjoutFeedBackForm;
import forms.boutique.BoutiqueForm;
import forms.boutique.PanierForm;
import forms.annonceAmine.Ajout;
import forms.boutique.MesAchatsForm;
import forms.utilisateur.ProfileForm;
import util.MenuManager;

/**
 *
 * @author achref
 */
public class MenuForm {
    
    Form f;
    Resources theme;

    public MenuForm(Resources theme) {
        f = new Form ("Acceuil");
        this.theme = theme;
         
    }
    
    public void affiche()
    { 
       MenuManager.createMenu(f, theme);
       f.show();
    }
    
}
