/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.utilisateur;

import Entity.Utilisateur;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.MenuForm;
import com.mycompany.myapp.SignupFb;
import java.util.ArrayList;
import java.util.Iterator;
import service.ServiceUtilisateur;
import util.FacebookLogin;

/**
 *
 * @author AYOUB
 */
public class EditProfile {
    Form form ;
    Resources theme;
    public EditProfile(Resources theme) {
        this.theme = theme;
    }
    
    void init() {
        theme = UIManager.initFirstTheme("/theme_2_1");
        UIBuilder ui = new UIBuilder();
        Form form = ui.createContainer(theme, "profile").getComponentForm();
        //form.setUIID("Logins");
        form.setTitle("Paw");
        
       
         
         form.show();
    }
    
}
