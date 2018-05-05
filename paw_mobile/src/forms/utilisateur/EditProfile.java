/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.utilisateur;

import Entity.Utilisateur;
import static Entity.Utilisateur.membre;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import forms.FeedBack.MesFeedBackForm;
import forms.boutique.MesAchatsForm;
import util.MenuManager;

/**
 *
 * @author AYOUB
 */
public class EditProfile {
    Form currentform ;
    Resources theme;
    public EditProfile() {
        currentform = new Form("rien", new BoxLayout(BoxLayout.Y_AXIS));
        theme = UIManager.initFirstTheme("/theme_2_1");
        
        UIBuilder uib = new UIBuilder();
        UIBuilder.registerCustomComponent("SpanLabel", SpanLabel.class);
        UIBuilder.registerCustomComponent("ImageViewer", ImageViewer.class);
        currentform = uib.createContainer(theme, "pr").getComponentForm();
        
    }
    
    public void affiche() {
        UIBuilder ui = new UIBuilder();
        Container container = (Container) ui.findByName("container", currentform.getContentPane());
        
        Container cTitre = new Container(new TableLayout(1, 3));
        
        cTitre.add(new Label("    "));
        cTitre.add(new Label(theme.getImage("detailsAyoub.png")));
        Button maj = new Button("Mise à jour infos");
        maj.addActionListener(l->{
            ProfileForm pf = new ProfileForm();
            pf.affiche();
        });
        cTitre.add(maj);
        
        Container cNom = new Container(new TableLayout(1, 2));
        Label nom = new Label();
        nom.setText("Nom: "+Utilisateur.membre.getNom());
        cNom.add(new Label(theme.getImage("nomAyoub.png")));
        cNom.add(nom);
        
        Container cUsername = new Container(new TableLayout(1, 2));
        Label username = new Label();
        username.setText("Username: "+Utilisateur.membre.getUsername());
        cUsername.add(new Label(theme.getImage("usernameAyoub.png")));
        cUsername.add(username);
        
        Container cEmail = new Container(new TableLayout(1, 2));
        Label email = new Label();
        email.setText(Utilisateur.membre.getEmail());
        cEmail.add(new Label(theme.getImage("emailAyoub.png")));
        cEmail.add(email);
        
        container.add(cTitre);
        container.add(cNom);
        container.add(cUsername);
        container.add(cEmail);
        
        Button MesFeeds = new Button("Mes FeedBack");
        MesFeeds.addActionListener(l->{
            MesFeedBackForm rf = new MesFeedBackForm();
            rf.afficher();
        });
        container.add(MesFeeds);
        
        Button MesFeeds1 = new Button("Mes Achats");
        MesFeeds1.addActionListener(l->{
            this.theme = UIManager.initFirstTheme("/theme_1");
            MesAchatsForm rf = new MesAchatsForm(this.theme);
            rf.affiche();
        });
        container.add(MesFeeds1);
        
        
        
        Container cAvatar = (Container) ui.findByName("cAvatar", currentform.getContentPane());
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(250, 250), true);
        ImageViewer i = new ImageViewer();
        i.setImage(URLImage.createToStorage(placeholder, membre.getAvatar(), "http://localhost/web/web/images/pawUsers/" + membre.getAvatar()));
        i.setHeight(50);
        i.setWidth(50);
        cAvatar.add(i);
        
        MenuManager.createMenu(currentform, theme);

        currentform.show();
    }
    
}
