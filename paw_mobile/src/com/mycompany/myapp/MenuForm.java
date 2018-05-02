/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import forms.boutique.BoutiqueForm;
import forms.boutique.PanierForm;

/**
 *
 * @author achref
 */
public class MenuForm {
    
    Form menu;
    Resources theme;

    public MenuForm(Resources theme) {
        this.theme = theme;
    }
    
    public void affiche()
    {
        menu = new Form ("menu");
              menu.getToolbar().addCommandToOverflowMenu("Panier", null, (e)
                -> {
                PanierForm bf = new PanierForm(theme);
        bf.affichePanier();
        }
        );
         menu.getToolbar().addCommandToOverflowMenu("Boutique", null, (e)
                -> {
                BoutiqueForm bf = new BoutiqueForm();
        bf.afficherBoutique("Vetements");
        }
        );
         
       menu.show();
    }
    
}
