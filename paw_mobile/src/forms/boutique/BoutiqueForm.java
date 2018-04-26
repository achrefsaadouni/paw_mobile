/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.Produit;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import javafx.scene.image.ImageView;

/**
 *
 * @author achref
 */
public class BoutiqueForm{
    Form boutiqueForm;
    Resources theme;

    public Form getBoutiqueForm() {
        return boutiqueForm;
    }

    public void setBoutiqueForm(Form boutiqueForm) {
        this.boutiqueForm = boutiqueForm;
    }
    public void afficherBoutique(Resources theme)
    {
        this.theme = theme;
        initboutique();
        boutiqueForm.show();
    }
    public void initboutique()
    {
        
    }
    
    public Container produit(Produit p )
    {
        String url = "http://localhost/paw_web/web/images/pawBoutique/"+p.getImage1();
        Container c1 = new Container (new BoxLayout(BoxLayout.Y_AXIS));
        URLImage i ;
        EncodedImage enc;
        enc = EncodedImage.createFromImage(theme.getImage("round.png"),false);
        i = URLImage.createToStorage(enc,"test", url);
        ImageViewer image = new ImageViewer(i);
        image.setWidth(150);
        image.setHeight(150);
        c1.add(i);
        Container c2 = new Container (new BoxLayout(BoxLayout.X_AXIS));
        Container c3 = new Container (new BoxLayout(BoxLayout.X_AXIS));
        Container c4 = new Container (new BoxLayout(BoxLayout.X_AXIS));
        Image im1 = theme.getImage("libelle.png");
        Image im2 = theme.getImage("price.png");
        ImageViewer libelle = new ImageViewer(im1);
        ImageViewer prix = new ImageViewer(im2);
        libelle.setWidth(20);
        libelle.setHeight(20);
        prix.setHeight(20);
        prix.setWidth(20);
        Label libelleT = new Label(p.getLibelle());
        Label prixT = new Label(String.valueOf(p.getPrix()));
        c2.add(libelle);c2.add(libelleT);
        c3.add(prix);c3.add(prixT);
        c1.add(c2);
        c1.add(c3);
        Button Detail = new Button("Info");
        Button acheter = new Button("Acheter");
        c4.add(Detail);c4.add(acheter);
        c1.add(c4);
        return c1;
        
    }
}
