/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.LigneAchat;
import Entity.Panier;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author achref
 */
public class PanierForm {

    private Form monPanier;
    private UIBuilder uib;
    private Resources theme;
    private Container itemContainer;
    private Container rightContainer;
    private Container leftContainer;
    private Container detailsContainer;
    private Container leftDetailsContainer;
    private Container rightDetailsContainer;
    private Container buttonContainer;
    private Button deleteButton;
    private Label libelleLabel;
    private Label prixLabel;
    private Label etatLabel;
    private Button venduButton;
    private Button boosterButton;
    private ImageViewer imageProd;
    private boolean isFirst = true;

    public PanierForm(Resources theme) {
            this.theme = UIManager.initFirstTheme("/theme_1");
            if (Panier.getPanier().isEmpty()) {
            monPanier = new Form ("Panier",new BoxLayout(BoxLayout.Y_AXIS));
            Label vide = new Label ("Votre Panier est Vide");
            monPanier.add(vide);
            Button payer = new Button("Retour Au Boutique", theme.getImage("boutiqueItems.png"));
            payer.setUIID("BlueButton");
            monPanier.add(payer);
            payer.addActionListener(l -> {
                BoutiqueForm bf = new BoutiqueForm();
                bf.afficherBoutique("Laisse, Collier et Harnais");
            });
        } else {
        this.theme = theme;
        uib = new UIBuilder();
        UIBuilder.registerCustomComponent("SpanLabel", SpanLabel.class);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        monPanier = uib.createContainer(theme, "mesArticles").getComponentForm();
        monPanier.setTitle("Panier");}
    }

    public void initPanier() {

             if (!Panier.getPanier().isEmpty()) {
            for (LigneAchat ligne : Panier.getPanier()) {
                initDetails(ligne);
            }

            Button payer = new Button("payer", theme.getImage("forward.png"));
            payer.setUIID("BlueButton");
            monPanier.add(payer);
            payer.addActionListener(l -> {
               PayerForm pf = new PayerForm(theme);
               pf.show();
            });}
        
    }

    public void initDetails(LigneAchat article) {
        if (!isFirst) {
            libelleLabel = new Label(theme.getImage("shopping-bag (1).png"));
            libelleLabel.setUIID("MyLabel1");
            prixLabel = new Label(theme.getImage("change.png"));
            prixLabel.setUIID("MyLabel1");
            etatLabel = new Label(theme.getImage("approve.png"));
            etatLabel.setUIID("MyLabel1");
            deleteButton = new Button(theme.getImage("cancel (4).png"));
            deleteButton.setUIID("WhiteButton");
            imageProd = new ImageViewer();
            venduButton = new Button("Plus", theme.getImage("plus.png"));
            venduButton.setUIID("BlueButton");
            boosterButton = new Button("Moins", theme.getImage("cancel (2).png"));
            boosterButton.setUIID("RedButton");
            Container deleteButtonContainer = new Container(new FlowLayout(RIGHT));
            deleteButtonContainer.add(deleteButton);

            itemContainer = new Container(BoxLayout.x());
            itemContainer.setUIID("MyArticleContainer");
            rightContainer = new Container(new GridLayout(1, 1));
            rightContainer.setUIID("ImageAchatContainer");
            rightContainer.add(imageProd);
            leftContainer = new Container(BoxLayout.y());
            leftContainer.setUIID("MyColdGreenTab");
            itemContainer.add(rightContainer);
            itemContainer.add(leftContainer);
            detailsContainer = new Container(new GridLayout(1, 2));
            leftDetailsContainer = new Container(BoxLayout.y());
            rightDetailsContainer = new Container(BoxLayout.y());
            detailsContainer.add(leftDetailsContainer);
            detailsContainer.add(rightDetailsContainer);
            buttonContainer = new Container(BoxLayout.x());
            buttonContainer.add(venduButton);
            buttonContainer.add(boosterButton);
            leftContainer.add(deleteButtonContainer);
            leftContainer.add(detailsContainer);
            leftContainer.add(buttonContainer);
            leftDetailsContainer.add(libelleLabel);
            leftDetailsContainer.add(prixLabel);
            leftDetailsContainer.add(etatLabel);
            monPanier.add(itemContainer);
        } else {
            imageProd = (ImageViewer) uib.findByName("ImageProd", monPanier);
            imageProd.setImage(imageProd.getImage().scaled(75, 75));
            libelleLabel = (Label) uib.findByName("Libelle", monPanier);
            prixLabel = (Label) uib.findByName("Prix", monPanier);
            etatLabel = (Label) uib.findByName("Etat", monPanier);
            venduButton = (Button) uib.findByName("Vendu", monPanier);
            boosterButton = (Button) uib.findByName("Booster", monPanier);
            deleteButton = (Button) uib.findByName("Delete", monPanier);
            isFirst = false;
        }
        EncodedImage ph = EncodedImage.createFromImage(theme.getImage("duke-no-logos.png").scaled(75, 75), true);
        Image image = URLImage.createToStorage(ph, article.getProduit().getImage1(), "http://localhost/paw_web/web/images/pawBoutique/" + article.getProduit().getImage1());
        image.scaled(75, 75);
        imageProd.setImage(image);
        if (article.getProduit().getLibelle().length() > 10) {
            libelleLabel.setText(article.getProduit().getLibelle().substring(0, 10) + "...");
        } else {
            libelleLabel.setText(article.getProduit().getLibelle());
        }
        prixLabel.setText(String.valueOf(article.getProduit().getPrix()));
        etatLabel.setText(String.valueOf(article.getNbr_produit()));
        venduButton.addActionListener((evt) -> {
            Panier.plus(article.getProduit());
            refresh();
        });
        boosterButton.addActionListener((evt) -> {
            Panier.minus(article.getProduit());
            refresh();
        });
        deleteButton.addActionListener((evt) -> {
            Panier.deleteProduit(article);
            refresh();
        });

    }

    public void refresh() {
        PanierForm pf = new PanierForm(theme);
        pf.affichePanier();
    }

    public void affichePanier() {
        initPanier();
        monPanier.show();
    }

}
