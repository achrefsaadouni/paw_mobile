/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.annonceGarde;

import Entity.AnnonceGarde;
import Entity.Utilisateur;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.Map;
import service.ServiceAnnonceGarde;
import util.MenuManager;

/**
 *
 * @author Lenovo
 */
public class AjoutGardeForm {
    Form gardeForm;
    Resources theme;
    
    
    TextField type;
    TextField race;
    TextField couleur;
    TextField sexe;
    TextField age;
    TextField dureSit;
    TextField todolist;
    TextField message;
   
    String filePath;
    String fileName;
    String imgname ;  
    
    private CheckBox chat;
    private CheckBox chien;
    private CheckBox Oiseaux;
    private CheckBox cheval;
    
    private CheckBox male;
    private CheckBox female;
    
    Button ajoutBtn,affichBtn;//,choisirImage,mesAnnonces;
    private Label label;
    private Label label2;
    private Container c1;
    private Container c2;
    public  AjoutGardeForm(Resources theme){
        this.theme = theme;
        this.gardeForm = new Form("Ajouter Annonce Garde");
        race = new TextField("","Race",30,TextArea.ANY);
        age = new TextField("","age",30,TextArea.ANY);
        couleur = new TextField("","couleur",30,TextArea.ANY);
        dureSit = new TextField("","Durée de garde",30,TextArea.ANY);
        todolist = new TextField("","To do list",30,TextArea.ANY);
        message = new TextField("","message complementaire",30,TextArea.ANY);
        
        label = new Label("Sexe");
        c1 = new Container();
        c1.add(label);
        
        ComboBox<Map<String, Object>> comboType = new ComboBox<> (
                 "Chien","Chat","Cheval","Oiseaux");
        ComboBox<Map<String, Object>> comboSexe = new ComboBox<> (
                 "female","male");

       c1.add(comboSexe);
       
       c2 = new Container() ; 
       label2 = new Label("Choisir le type de votre animal");
       c2.add(label2) ; 
       c2.add(comboType);
        
       Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
       ajoutBtn = new Button("Valider");
       affichBtn=new Button("Affiche");
       c3.add(ajoutBtn);
       c3.add(affichBtn);
       
       gardeForm.add(c2); 
       gardeForm.add(race); 
       gardeForm.add(c1) ;
       gardeForm.add(age); 
       gardeForm.add(couleur); 
       gardeForm.add(dureSit); 
       gardeForm.add(todolist); 
       gardeForm.add(message);
       gardeForm.add(c3); 
       
       ajoutBtn.addActionListener((e) -> {
            ServiceAnnonceGarde ser = new ServiceAnnonceGarde();
            String sexe="" ; 
            switch(comboSexe.getModel().getSelectedIndex()){
                case 0 : sexe="male"; 
                case 1 : sexe ="female" ; 
            }
            String animal="";
            switch(comboType.getModel().getSelectedIndex()){
                case 0 : animal="Chien";
                case 1 : animal="Chat";
                case 2 : animal="Cheval" ; 
                case 3 : animal="Oiseaux";
            }
            System.out.println(comboType.getModel().getSelectedIndex());

            int userId = Utilisateur.membre.getId();  

            AnnonceGarde t = new AnnonceGarde(Integer.valueOf(dureSit.getText()),todolist.getText(),0,Integer.valueOf(age.getText()),couleur.getText(),sexe,race.getText(),message.getText(),animal,Utilisateur.membre.getId());
            System.out.println(t.getImages());
            
            ser.ajoutAnnonceGarde(t);
            

        });
        
       affichBtn.addActionListener((e) -> {
          AffichGardeForm rf = new AffichGardeForm(theme);
          rf.getForm().show();
      }); 
    }
    public Form getGardeForm() {
        MenuManager.createMenu(gardeForm, theme);
        return gardeForm;
    }

    public void setGardeForm(Form GardeForm) {
        this.gardeForm = GardeForm;
    }

}
