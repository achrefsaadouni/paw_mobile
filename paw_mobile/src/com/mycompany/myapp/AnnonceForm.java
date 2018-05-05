/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import forms.accouplement.AjoutAccouplement;
import forms.annonceAmine.Ajout;
import forms.annonceperdu.Ajout2;
import util.MenuManager;

/**
 *
 * @author achref
 */
public class AnnonceForm {

    public Form f;
    public Container c1;
    public Container c2;
    public Resources theme;

    public AnnonceForm(Resources theme) {

        this.theme = theme;
        this.f = new Form("Annonce", new BoxLayout(BoxLayout.Y_AXIS));
        c1 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        c2 = new Container(new BoxLayout(BoxLayout.X_AXIS));
    }

    public void affiche() {

            Button b = new Button("Trouvée",theme.getImage("lost.png"));
            b.setUIID("WhiteButton2");
            b.addActionListener((evt) -> {
                Ajout ajout = new Ajout(theme);
                ajout.getF().show();

            });
            Button b2 = new Button("Perdu",theme.getImage("lost.png"));
            b2.setUIID("WhiteButton2");
            b2.addActionListener((evt) -> {
                Ajout2 ajout = new Ajout2(theme);
                ajout.getF().show();

            });
            
              Button b1 = new Button("Accouplement",theme.getImage("doglove.jpg"));
            b1.setUIID("WhiteButton2");
            b1.addActionListener((evt) -> {
                AjoutAccouplement ac = new AjoutAccouplement(theme);
                ac.getF().show();
            });
       
        f.add(b);
         f.add(b1);
        f.add(b2) ; 
        
        MenuManager.createMenu(f, theme);
        f.show();
    }

}
