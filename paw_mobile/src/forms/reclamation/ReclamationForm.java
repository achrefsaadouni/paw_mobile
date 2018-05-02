/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.reclamation;

import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author AYOUB
 */
public class ReclamationForm {
    Form currentform;
    Resources theme;
    private UIBuilder uib;

    public ReclamationForm() {
        currentform = new Form("Reclamation", new BoxLayout(BoxLayout.Y_AXIS));    

    }
    
    public void afficher() {
        this.theme = UIManager.initNamedTheme("/theme_1", "Theme 1");
        initReclamation();
        currentform.show();
    }    

    private void initReclamation() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
