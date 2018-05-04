/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.FeedBack;

import Entity.Reclamation;
import Entity.Utilisateur;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.MyApplication;
import service.ServiceReclamation;

/**
 *
 * @author AYOUB
 */
public class AjoutFeedBackForm {
    Form currentform;
    Resources theme;

    ComboBox<String> objet ;
    TextArea text;
    RadioButton reclamation;
    RadioButton remerciment;
    Button envoyer;
    
    
    public AjoutFeedBackForm() {
        
        currentform = new Form("rien", new BoxLayout(BoxLayout.Y_AXIS));
        theme = UIManager.initFirstTheme("/theme_1_ayoub");
        UIBuilder ui = new UIBuilder();
        currentform = ui.createContainer(theme, "ajoutFeedBack").getComponentForm();
        
        
        text = (TextArea) ui.findByName("text", currentform.getContentPane());
        objet = (ComboBox<String>) ui.findByName("objet", currentform.getContentPane());
        reclamation= (RadioButton) ui.findByName("reclamation", currentform.getContentPane());
        remerciment= (RadioButton) ui.findByName("remerciment", currentform.getContentPane());
        envoyer =(Button)ui.findByName("envoyer", currentform.getContentPane());
        
        envoyer.addActionListener(l -> {
                if(objet.getSelectedItem().equals("Service concerné .."))
                {
                    if(Dialog.show("Informations manquantes",
                                    "Veuillez selectionner le service concerné", 
                                    "Ok", 
                                    null))
                    {}
                }
                else if (text.getText().equals(""))
                {
                    if(Dialog.show("Informations manquantes",
                                    "Veuillez ajouter une description", 
                                    "Ok", 
                                    null))
                    {}
                }
                else if(reclamation.isSelected()==false && remerciment.isSelected()==false )
                {
                    if(Dialog.show("Informations manquantes",
                                    "Veuillez selectionner le type", 
                                    "Ok", 
                                    null))
                    {
                       
                    }
                }
                else{
                    String s = "Remerciment";
                    if (reclamation.isSelected())
                    {
                        s = "Reclamation";
                    }
                    Reclamation r = new Reclamation(Utilisateur.membre.getId(), objet.getSelectedItem(), text.getText(), s);
                    ServiceReclamation service = new ServiceReclamation();
                    service.ajoutReclamation(r);
                    objet.setSelectedItem("Service concerné ..");
                    text.setText("");
                    remerciment.setSelected(false);
                    reclamation.setSelected(false);
                    if(Dialog.show("FeedBack envoyé",
                                    "Merci pour votre collaboration", 
                                    "Ok", 
                                    null))
                    {}
                }
            });
    }
    
    public void afficher() {
        currentform.show();
    }    
}
