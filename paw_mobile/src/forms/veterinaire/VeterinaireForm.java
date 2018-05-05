/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.veterinaire;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.util.Resources;
import util.MenuManager;

/**
 *
 * @author gmehd
 */
public class VeterinaireForm {
    Form f;
    Resources theme;
    Button btnajout,btnaff;

    public VeterinaireForm(Resources theme) {
        this.theme = theme;
        f = new Form("home");

        btnaff=new Button("Affichage");

        f.add(btnaff);

            

//        });
        btnaff.addActionListener((e)->{
        AfficheVeterinaire a = new AfficheVeterinaire(theme);
        a.getF().show();
        });
    }

    public Form getF() {
        MenuManager.createMenu(f, theme);
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
