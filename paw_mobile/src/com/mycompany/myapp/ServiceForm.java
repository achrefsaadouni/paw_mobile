/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import forms.annonceAmine.Ajout;
import forms.annonceDressage.AjoutDressageForm;
import forms.annonceGarde.AjoutGardeForm;
import forms.annonceperdu.Ajout2;
import util.MenuManager;

/**
 *
 * @author achref
 */
public class ServiceForm {
    
      public Form f;
    public Container c1;
    public Container c2;
    public Resources theme;

    public ServiceForm(Resources theme) {

        this.theme = theme;
        this.f = new Form("Service", new BoxLayout(BoxLayout.Y_AXIS));
        c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
    }

    public void affiche() {

            Button b = new Button("Dressage",theme.getImage("lost.png"));
            b.setUIID("WhiteButton2");
            b.addActionListener((evt) -> {
                AjoutDressageForm ad = new AjoutDressageForm(theme);
                ad.getDressageForm().show();

            });
 
              Button b1 = new Button("Garde",theme.getImage("doglove.jpg"));
            b1.setUIID("WhiteButton2");
            b1.addActionListener((evt) -> {
                AjoutGardeForm ag = new AjoutGardeForm(theme);
                ag.getGardeForm().show();
            });
       
        f.add(b);
         f.add(b1); 
        
        MenuManager.createMenu(f, theme);
        f.show();
    }

    
}
