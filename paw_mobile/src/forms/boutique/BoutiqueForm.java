/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.Produit;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.List;
import service.ServiceProduit;

/**
 *
 * @author achref
 */
public class BoutiqueForm {

    Form boutiqueForm;
    Resources theme;
    private UIBuilder uib;

    public Form getBoutiqueForm() {
        return boutiqueForm;
    }

    public void setBoutiqueForm(Form boutiqueForm) {
        this.boutiqueForm = boutiqueForm;
    }

    public void afficherBoutique(String type) {
        this.theme = UIManager.initNamedTheme("/theme_1", "Theme 1");
        boutiqueForm.removeAll();
        initboutique(type);
        boutiqueForm.show();
    }

    public void initboutique(String type) {
        ServiceProduit sp = new ServiceProduit();
        List<Produit> liste = sp.getList2(type);
        for (Produit article : liste) {
            Label libelle = new Label(theme.getImage("shopping-store.png"));
            // libelle.setUIID("Libelle");
            libelle.setUIID("MyLabel");
            Label prix = new Label(theme.getImage("coins (1).png"));
            // prix.setUIID("prix");
            prix.setUIID("MyLabel");
            Label etat = new Label(theme.getImage("package.png"));
            etat.setUIID("MyLabel1");
            URLImage i;
            String url = "http://localhost/paw_web/web/images/pawBoutique/" + article.getImage1();
            EncodedImage enc;
            enc = EncodedImage.createFromImage(theme.getImage("round.png"), false);
            i = URLImage.createToStorage(enc, article.getImage1(), url);
            ImageViewer image = new ImageViewer(i);
            String lib = article.getLibelle();
            lib = (lib.length() > 20) ? lib.substring(0, 19) + "..." : lib;
            libelle.setText(lib);
            prix.setText(String.valueOf(article.getPrix()) + "TND");

            Button acheter = new Button("Acheter");
            acheter.setIcon(theme.getImage("shopping-bag.png"));
            acheter.setUIID("TransparentButtonWhite");
            Button moreDetail = new Button(theme.getImage("more (1).png"));
            moreDetail.setUIID("WhiteButton2");
            moreDetail.addActionListener((evt) -> {
                afficherDetail(article);
            });
            Container ctnr = new Container(new BorderLayout());
            ctnr.setUIID("BoutiqueItem");
            Container ctnrLibelle = new Container(new FlowLayout(LEFT, TOP));
            ctnrLibelle.add(libelle);
            Container ctnrPrix = new Container(new FlowLayout(RIGHT, TOP));
            ctnrPrix.add(prix);
            Container ctnrLibellePrix = Container.encloseIn(new GridLayout(1, 2), ctnrLibelle, ctnrPrix);
            ctnrLibellePrix.setUIID("BoutiquePurpleTab");
            if (article.getQuantite() == 0) {
                etat.setText("Hors Stock");
                acheter.setEnabled(false);
            } else {
                etat.setText("En Stock");
            }
            Container ctnrImage = Container.encloseIn(new FlowLayout(), image);
            ctnrImage.setUIID("ImageContainer");

            Container ctnrAcheter = new Container(new FlowLayout(LEFT, TOP));
            ctnrAcheter.add(acheter);
            Container ctnrMore = new Container(new FlowLayout(RIGHT, CENTER));
            ctnrMore.add(moreDetail);
            Container ctnrButtons = Container.encloseIn(new GridLayout(1, 2), ctnrAcheter, ctnrMore);
            ctnrButtons.setUIID("BoutiquePurpleTab");
            
            Container CtnrSouth = Container.encloseIn(BoxLayout.y(), etat, ctnrButtons);
            CtnrSouth.setUIID("WhiteTab");

            ctnr.add(BorderLayout.CENTER, ctnrImage);
            ctnr.add(BorderLayout.NORTH, ctnrLibellePrix);
            ctnr.add(BorderLayout.SOUTH, CtnrSouth);
            ctnr.setLeadComponent(moreDetail);
            boutiqueForm.add(ctnr);
            //this.show();
        }

    }

    public BoutiqueForm() {

        boutiqueForm = new Form("Boutique", new BoxLayout(BoxLayout.Y_AXIS));
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Laisse, Collier et Harnais", null, (e)
                -> {
            afficherBoutique("Laisse, Collier et Harnais");
        }
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Lits et Couvertures", null, (e)
                -> {
                afficherBoutique("Lits et Couvertures");
        }
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Shampoings et Conditionneurs", null, (e)
                -> {
                afficherBoutique("Shampoings et Conditionneurs");
        }
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Vetements", null, (e)
                -> {
                afficherBoutique("Vetements");
        }   
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Bols", null, (e)
                -> {
                afficherBoutique("Bols");
        }
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Cadeaux", null, (e)
                -> {
                afficherBoutique("Cadeaux");
        }
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Gâteries Et Nourritures", null, (e)
                -> {
                afficherBoutique("Gâteries Et Nourritures");
        }
        );
        boutiqueForm.getToolbar().addCommandToOverflowMenu("Jouets", null, (e)
                -> {
                afficherBoutique("Jouets");
        }
        );
         boutiqueForm.getToolbar().addCommandToOverflowMenu("Panier", null, (e)
                -> {
                PanierForm bf = new PanierForm(theme);
        bf.affichePanier();
        }
        );

    }

    public void afficherDetail(Produit produit) {
            DetailsForm df = new DetailsForm(this.theme, produit);
            df.afficherDetail();
    }
}
