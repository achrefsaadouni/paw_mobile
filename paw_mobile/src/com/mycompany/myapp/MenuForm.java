/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Container;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import util.MenuManager;

/**
 *
 * @author achref
 */
public class MenuForm {
    
    Form f;
    Resources theme;

    public MenuForm(Resources theme) {
        f = new Form ("Acceuil",new BoxLayout(BoxLayout.Y_AXIS));
        this.theme = theme;
        Container c1 = new Container (new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer image = new ImageViewer(theme.getImage("logo_menu.png"));
        Label l = new Label("              Bienvenu sur Paw");
        c1.add(l);
        c1.add(image);
        f.add(c1);
        Container c2 = new Container (new BoxLayout(BoxLayout.X_AXIS));
        Label l1 = new Label("Visitez Aussi");
         Button b = new Button(theme.getImage("fa.png"));
            b.setUIID("WhiteButton2");
            b.addActionListener((evt) -> {
         
                 execute("https://www.facebook.com/Boutique-Paw-753236058199700");

            });
             Button b1 = new Button(theme.getImage("inst.jpg"));
            b1.setUIID("WhiteButton2");
            b1.addActionListener((evt) -> {
         
                 execute("https://www.instagram.com/pawzcorporation/?hl=fr");

            });
            
               Button b2 = new Button(theme.getImage("site.png"));
            b2.setUIID("WhiteButton2");
            b2.addActionListener((evt) -> {
         
                 execute("http://localhost/paw_web/web/app_dev.php/index");

            });
            c2.add(l1);
            c2.add(b);
            c2.add(b1);
            c2.add(b2);
            f.add(c2);
    }
    
    public void affiche()
    { 
       MenuManager.createMenu(f, theme);
       f.show();
    }
    
}
