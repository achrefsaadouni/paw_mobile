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
import java.util.List;

/**
 *
 * @author achref
 */
public class DetailAchatForm {
    
    private Form detailachat;
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
    private List<LigneAchat>ligne;
    
    
    
    public DetailAchatForm(Resources theme,List<LigneAchat>ligne) {
            this.theme = UIManager.initFirstTheme("/theme_1");
        this.ligne = ligne;
        this.theme = theme;
        uib = new UIBuilder();
        UIBuilder.registerCustomComponent("SpanLabel", SpanLabel.class);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        detailachat = uib.createContainer(theme, "mesArticles1").getComponentForm();
        detailachat.setTitle("Detail Achat");
    }

    public void init() {

             
            for (LigneAchat l: ligne) {
                initDetails(l);
            }
           
        
    }

    public void initDetails(LigneAchat article) {
        if (!isFirst) {
            libelleLabel = new Label(theme.getImage("shopping-bag (1).png"));
            libelleLabel.setUIID("MyLabel1");
            prixLabel = new Label(theme.getImage("change.png"));
            prixLabel.setUIID("MyLabel1");
            etatLabel = new Label(theme.getImage("approve.png"));
            etatLabel.setUIID("MyLabel1");
            
            imageProd = new ImageViewer();
            venduButton = new Button("                                                  ");
            venduButton.setUIID("WhiteButton2");
            Container deleteButtonContainer = new Container(new FlowLayout(RIGHT));
           
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
            leftContainer.add(deleteButtonContainer);
            leftContainer.add(detailsContainer);
            leftContainer.add(buttonContainer);
            leftDetailsContainer.add(libelleLabel);
            leftDetailsContainer.add(prixLabel);
            leftDetailsContainer.add(etatLabel);
            detailachat.add(itemContainer);
        } else {
            imageProd = (ImageViewer) uib.findByName("ImageProd", detailachat);
            imageProd.setImage(imageProd.getImage().scaled(75, 75));
            libelleLabel = (Label) uib.findByName("Libelle", detailachat);
            prixLabel = (Label) uib.findByName("Prix", detailachat);
            etatLabel = (Label) uib.findByName("Etat", detailachat);
            venduButton = (Button) uib.findByName("Vendu", detailachat);
           
           
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

    }


    public void affiche() {
        init();
        detailachat.show();
    }


    
}
