/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.Panier;
import Entity.Produit;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.list.ListModel;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author esprit
 */
public class DetailsForm {

    private Resources theme;
    private Form detailsForm;
    private UIBuilder uib;
    private ImageViewer imageProd;
    private Label libelle;
    private Label prix;
    private Label etat;
    private SpanLabel description;
    private Button acheter;
    private Produit article;

    public DetailsForm(Resources theme, Produit article) {
        this.theme = theme;
        this.article = article;
        uib = new UIBuilder();
        UIBuilder.registerCustomComponent("SpanLabel", SpanLabel.class);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        detailsForm = uib.createContainer(this.theme, "articleDetail").getComponentForm();
        libelle = (Label) uib.findByName("Libelle", detailsForm);
        prix = (Label) uib.findByName("Prix", detailsForm);
        etat = (Label) uib.findByName("Etat", detailsForm);
        description = (SpanLabel) uib.findByName("Description", detailsForm);
        imageProd = (ImageViewer) uib.findByName("imageArticle", detailsForm);
        acheter = (Button) uib.findByName("Acheter", detailsForm);
        imageProd = (ImageViewer) uib.findByName("imageArticle", detailsForm);
        detailsForm.setTitle("Details");
        detailsForm.getToolbar().addCommandToLeftBar("Retour", theme.getImage("back-command.png"),
                (e2) -> {
                    BoutiqueForm bf = new BoutiqueForm();
                    bf.afficherBoutique(article.getType());
                }
        );

    }

    public void afficherDetail() {

        initdetail(theme, article);
        this.detailsForm.show();
    }

    public void initdetail(Resources theme, Produit article) {
        libelle.setText(article.getLibelle());
        prix.setText(String.valueOf(article.getPrix()));
        if (article.getQuantite() == 0) {
            etat.setText("Hors Stock");
            acheter.setEnabled(false);
        } else {
            etat.setText("En Stock");
        }
        description.setText(article.getDescription());
        ListModel<Image> listImage = new DefaultListModel<>();
        Image imageSecondaire;
        EncodedImage ph = EncodedImage.createFromImage(theme.getImage("duke-no-logos.png"), true);
        imageSecondaire = URLImage.createToStorage(ph, article.getImage1(), "http://localhost/paw_web/web/images/pawBoutique/" + article.getImage1());
        imageSecondaire.scaled(200, 200);
        listImage.addItem(imageSecondaire);
            
        
        ph = EncodedImage.createFromImage(theme.getImage("duke-no-logos.png"), true);
        imageSecondaire = URLImage.createToStorage(ph, article.getImage2(), "http://localhost/paw_web/web/images/pawBoutique/" + article.getImage2());
        imageSecondaire.scaled(200, 200);
        listImage.addItem(imageSecondaire);

        imageProd.setImage(imageSecondaire);
        imageProd.setImageList(listImage);
        acheter.addActionListener(e -> {
           
            Panier.addProduit(article);
        });

    }

    public Form getDetailsForm() {
        return detailsForm;
    }

    public void setDetailsForm(Form DetailsForm) {
        this.detailsForm = DetailsForm;
    }

    public Resources getTheme() {
        return theme;
    }

    public void setTheme(Resources theme) {
        this.theme = theme;
    }

}
