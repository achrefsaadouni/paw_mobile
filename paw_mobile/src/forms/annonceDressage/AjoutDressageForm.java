/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.annonceDressage;

import Entity.AnnonceDressage;
import Entity.Utilisateur;
import com.codename1.ui.util.Resources;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import java.util.Map;
import rest.file.uploader.tn.FileUploader;
import service.ServiceAnnonceDressage;
import util.MenuManager;

/**
 *
 * @author Lenovo
 */
public class AjoutDressageForm  {
    Form dressageForm;
    Resources theme;
    
    
    TextField type;
    TextField race;
    TextField couleur;
    TextField sexe;
    TextField age;
    TextField typeTr;
    TextField dateTr;
    TextField message;
   
    String filePath;
    String fileName;
    String imgname ; 
    private String fileNameInServer = "";
    
    private CheckBox chat;
    private CheckBox chien;
    private CheckBox Oiseaux;
    private CheckBox cheval;
    
    private CheckBox male;
    private CheckBox female;
    
    Button ajoutBtn,affichBtn,choisirImage;//,mesAnnonces;
     private final Label label;
     private final Label label2;
     private final Container c1;
     private final Container c2;
     
     public  AjoutDressageForm(Resources theme){
        this.theme = theme;
        this.dressageForm = new Form("Ajouter Annonce Dressage");
        race = new TextField("","Race",30,TextArea.ANY);
        age = new TextField("","age",30,TextArea.ANY);
        couleur = new TextField("","couleur",30,TextArea.ANY);
        dateTr = new TextField("","Date de Dressage",30,TextArea.ANY);
        message = new TextField("","message complementaire",30,TextArea.ANY);
        
        label = new Label("Sexe");
        c1 = new Container();
        c1.add(label);
        
        ComboBox<Map<String, Object>> comboType = new ComboBox<> (
                 "Chien","Chat","Cheval","Oiseaux");
        ComboBox<Map<String, Object>> comboSexe = new ComboBox<> (
                 "female","male");
        ComboBox<Map<String, Object>> comboTypeTr = new ComboBox<> (
                 "Dressage de debutant","Dressage Avancé","Dressage des chiot");

       c1.add(comboSexe);
       
       c2 = new Container() ; 
       label2 = new Label("Choisir le type de votre animal");
       c2.add(label2) ; 
       c2.add(comboType);
       Label label3 = new Label("Choisir le type de dressage");
       c2.add(label3);
       c2.add(comboTypeTr);
        
       Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
       ajoutBtn = new Button("Valider");
//       choisirImage = new Button("Choisir image");
       affichBtn=new Button("Mes Annonces");
       c3.add(ajoutBtn);
//       c3.add(choisirImage);
       c3.add(affichBtn);
       
       dressageForm.add(c2); 
       dressageForm.add(race); 
       dressageForm.add(c1) ;
       dressageForm.add(age); 
       dressageForm.add(couleur); 
       dressageForm.add(dateTr); 
       dressageForm.add(message);
       dressageForm.add(c3);
       
       ajoutBtn.addActionListener((e) -> {
            ServiceAnnonceDressage ser = new ServiceAnnonceDressage();
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
            
            String typeTr="";
            switch(comboTypeTr.getModel().getSelectedIndex()){
                case 0 : typeTr="Debutant";
                case 1 : typeTr="Avancé";
                case 2 : typeTr="Puppy" ; 
                
            }
            int userId = Utilisateur.membre.getId();  
            AnnonceDressage t = new AnnonceDressage(typeTr,0,Integer.valueOf(age.getText()),couleur.getText(),sexe,race.getText(),message.getText(),animal,Utilisateur.membre.getId());
            System.out.println(t.getImages());
            
            ser.ajoutAnnonceDressage(t);
            

        });
       
      affichBtn.addActionListener((e) -> {
          AffichDressageForm rf = new AffichDressageForm(theme);
          rf.getForm().show();
      });   
    }
     

    public Form getDressageForm() {
        MenuManager.createMenu(dressageForm, theme);
        return dressageForm;
    }

    public void setDressageForm(Form DressageForm) {
        this.dressageForm = DressageForm;
    }
    
}
