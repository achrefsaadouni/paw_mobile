/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.annonceAmine;

import forms.annonceAmine.Affichage1;
import Entity.AnnonceTrouvee;
import Entity.Utilisateur;
import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.OnOffSwitch;
import com.codename1.components.ToastBar;
import com.codename1.io.File;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.ComponentGroup;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.ImageIO;
import com.codename1.ui.util.Resources;
import com.codename1.util.Base64;
//import static com.codename1.ui.CN.openGallery; 
//import com.codename1.io.FileSystemStorage;
//import java.util.Random;
import static com.codename1.ui.CN.openGallery;
import static com.codename1.ui.CN1Constants.GALLERY_IMAGE;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.URLImage;
import java.util.Random;
import java.io.OutputStream ; 

//import static com.codename1.ui.Display.GALLERY_IMAGE;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import rest.file.uploader.tn.FileUploader;
import service.ServiceAnnonceTrouvee;
import util.MenuManager;

/**
 *
 * @author Guideinfo
 */
public class Ajout {
    
    Form f;
    TextField colier;
    TextField lieu_trouvee;
    TextField age;
    TextField couleur;
    TextField sexe;
    TextField race;
    TextField message;
    TextField type;
     String filePath;
      String fileName;
      String imgname ;  
    private CheckBox chat;
    private CheckBox chien;
    private CheckBox cheval;
    private CheckBox chevre;
     private CheckBox male;
    private CheckBox female;
    Resources theme;
    Button btnajout,btnaff,choisirImage,mesAnnonces;
     private Label label;
     private Label label2;
     private Container c1;
    private Container c2;
   private  String fileNameInServer ="";
    public Ajout(Resources theme) {
        this.theme = theme;
        f = new Form("Ajout Annonce Trouvee");
        colier = new TextField("","Porte t'il un Colier",30,TextArea.ANY);
        lieu_trouvee = new TextField("","Lieu trouvé",30,TextArea.ANY);
        age = new TextField("","Age",30,TextArea.ANY);
        couleur = new TextField("","Couleur",30,TextArea.ANY);
        label = new Label("Sexe");
        
        c1 = new Container();
        c1.add(label);
       
        f.add(c1) ; 
        
         ComboBox<Map<String, Object>> combo = new ComboBox<> (
                 "Chien","Chat","Cheval","Rongeur")
         ;
          ComboBox<Map<String, Object>> combo1 = new ComboBox<> (
                 "female","male")
         ;

       c1.add(combo1);
        race = new TextField("","Race",30,TextArea.ANY);
        message = new TextField("","Saisisez Un message Complaimentaire",30,TextArea.ANY);
        c2 = new Container() ; 
        label2 = new Label("Veuillez saisir le type de l'animal :");
       
        c2.add(label2) ; 
        c2.add(combo);
        f.add(c2) ; 
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        btnajout = new Button("Valider");
        choisirImage = new Button("Choisir image");
        c3.add(btnajout) ; 
        c3.add(choisirImage) ;           
        f.add(colier);
        f.add(lieu_trouvee);
        f.add(age);
        f.add(couleur);
        f.add(race);
        f.add(message);
        f.add(c3) ; 
  
        
     
        
        
        choisirImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                                try{
                    FileUploader fu = new FileUploader("http://localhost/paw_web/web/images");
                    
                    //Upload
                    
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if(v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }
                            
                            String filePath = ((String)v.getSource()).substring(7);
                            System.out.println(filePath);
                            
                            try {
                                fileNameInServer = fu.upload(filePath);
                           
                              
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                             
                          
                        }
                    }, Display.GALLERY_IMAGE);
                    
                    
                }catch(Exception ex){
                    ex.printStackTrace();
                }

                            
                         }
        });
        
        
        
        
        
        
            btnajout.addActionListener((e) -> {
            ServiceAnnonceTrouvee ser = new ServiceAnnonceTrouvee();
            String sexe="" ; 
            switch(combo1.getModel().getSelectedIndex()){
                case 0 : sexe="male"; 
                case 1 : sexe ="female" ; 
            }
            String animal="";
            switch(combo.getModel().getSelectedIndex()){
                case 0 : animal="Chien";
                case 1 : animal="Chat";
                case 2 : animal="Cheval" ; 
                case 3 : animal="Rongeur";
            }
            System.out.println(combo.getModel().getSelectedIndex());
               if(Integer.parseInt(age.getText())>0 && !colier.getText().equals("") && !lieu_trouvee.getText().equals("") && !couleur.getText().equals("")&& !race.getText().equals("") && !message.getText().equals(""))
                {
            AnnonceTrouvee t = new AnnonceTrouvee(colier.getText(),lieu_trouvee.getText(),0,Integer.valueOf(age.getText()),couleur.getText(),sexe,race.getText(),message.getText(),animal,fileNameInServer,Utilisateur.membre.getId());
            System.out.println(t.getImages());
            System.out.println(fileNameInServer);
            ser.ajoutAnnonceTrouvee(t);
                }
               
  else
                    Dialog.show("Erreur", "L'age est negatif ou bien vous avez un champs vide", "OK", "");
                    

        });
        
        
            
            
            
        Button avatar = new Button("");
        avatar.setUIID("InputAvatar");
        Image defaultAvatar = FontImage.createMaterial(FontImage.MATERIAL_CAMERA, "InputAvatarImage", 8);
        Image circleMaskImage = Image.createImage(250, 200);
        defaultAvatar = defaultAvatar.scaled(circleMaskImage.getWidth(), circleMaskImage.getHeight());
        defaultAvatar = ((FontImage) defaultAvatar).toEncodedImage();
        Object circleMask = circleMaskImage.createMask();
        defaultAvatar = defaultAvatar.applyMask(circleMask);
        avatar.setIcon(defaultAvatar);

        
        
        avatar.addActionListener(e -> {
            if (Dialog.show("Camera or Gallery", "Would you like to use the camera or the gallery for the picture?", "Camera", "Gallery")) {
                String pic = Capture.capturePhoto();
                if (pic != null) {
                    try {
                        Image img = Image.createImage(pic).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                        avatar.setIcon(img.applyMask(circleMask));
                        Random randomGenerator = new Random();

                        int randomInt = randomGenerator.nextInt(19999999);
                        String devisnamee = String.valueOf(randomInt) + ".jpg";
                        imgname = devisnamee;
                        System.out.println(imgname);
                        System.out.println(FileSystemStorage.getInstance().getAppHomePath());
                        String imageFile = "http://localhost/paw_web/web/images/" + devisnamee;
                        try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile);) {
                            ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_PNG, 1);
                        } catch (IOException err) {
                            Log.e(err);
                        }
                    } catch (IOException err) {
                        ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                        Log.e(err);
                    }
                }
            } else {
                openGallery(ee -> {
                    if (ee.getSource() != null) {
                        try {
                            Image img = Image.createImage((String) ee.getSource()).fill(circleMaskImage.getWidth(), circleMaskImage.getHeight());
                            avatar.setIcon(img.applyMask(circleMask));
                        } catch (IOException err) {
                            ToastBar.showErrorMessage("An error occured while loading the image: " + err);
                            Log.e(err);
                        }
                    }
                }, GALLERY_IMAGE);
            }
        });
        f.add(avatar) ;
  
        
        f.getToolbar().addCommandToOverflowMenu("Affichers Toutes Les Annonces", null, (e)
                -> {
        Affichage1 a=new Affichage1(theme);
        a.getF().show();
        }
        );
        
        
           f.getToolbar().addCommandToOverflowMenu("Affichers Mes Annonces", null, (e)
                -> {
         Form f9 = new Form("Mes Annonces" , new BoxLayout(BoxLayout.Y_AXIS)) ; 
         
        
          
         ImageViewer imageAnnonceTr=new ImageViewer();
            ServiceAnnonceTrouvee ServiceAnnonceTrouvee = new ServiceAnnonceTrouvee();
              
            ArrayList<AnnonceTrouvee> lis = ServiceAnnonceTrouvee.getList2();
            for (AnnonceTrouvee ed : lis)
            { 
           Button supprimer =new Button("Supprimer" ) ;
           Button modifier = new Button("Modifier") ;
                
                f9.add(afficherMesAnnonces(ed));
                
                f9.add(modifier) ; 
                f9.add(supprimer) ; 
                modifier.addActionListener(jj->{
            Dialog dlg = new Dialog(" edit") ;
            Button confirm = new Button("confirm edit") ;
            Button exit = new Button ("Exit") ; 
         Container cc2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ;
         Container cc3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ;
         Container cc4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ;
         Container cc5 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ;
         Container cc6 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ;
         Container cc7 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ;
            Label lieu69= new Label ("Lieux Trouvés :") ; 
            Label sexe69= new Label ("Sexe : ") ; 
            Label race69 = new Label ("Race :") ; 
            Label couleur69=new Label ("Couleur : ") ; 
            Label msg69=new Label("Message Comp : ") ; 
            Label colier69 = new Label("Colier :") ; 
            TextField  lieuxkl = new TextField(ed.getLieu_trouve()) ; 
            TextField sexe12 = new TextField(ed.getSex()) ; 
            TextField race12 = new TextField(ed.getRace()) ; 
            TextField couleur12 = new TextField(ed.getCouleur()) ; 
            TextField msg12 = new TextField(ed.getMessage_complementaire()) ;
            TextField colier12 = new TextField(ed.getColier()) ;
             lieuxkl.setText(ed.getLieu_trouve());
            sexe12.setText(ed.getSex()) ;
            race12.setText(ed.getRace());
            couleur12.setText(ed.getCouleur()) ;
            msg12.setText(ed.getMessage_complementaire());
            colier12.setText(ed.getColier()) ; 
            cc2.add(lieu69) ; 
            cc2.add(lieuxkl) ; 
            dlg.add(cc2) ;
            cc3.add(sexe69) ;
            cc3.add(sexe12) ; 
            dlg.add(cc3) ;
            cc4.add(race69) ;
            cc4.add(race12) ; 
            dlg.add(cc4) ;
            cc5.add(couleur69) ;
            cc5.add(couleur12) ; 
            dlg.add(cc5) ; 
            cc6.add(msg69) ;
            cc6.add(msg12) ;
            dlg.add(cc6) ; 
            cc7.add(colier69) ; 
            cc7.add(colier12) ; 
            dlg.add(cc7) ; 
            dlg.add(confirm);
            dlg.add(exit) ; 
            confirm.addActionListener(ty->{
            
              ServiceAnnonceTrouvee ann = new ServiceAnnonceTrouvee() ; 

              ann.modifier(ed.getId(),lieuxkl.getText(),sexe12.getText(), race12.getText(),couleur12.getText(),msg12.getText(),colier12.getText());
            
            
            });
            
            
            exit.addActionListener(rr->{
            
            f9.show();
            });
            
            
            
            
            dlg.show();
            
                
                });
                
          
            
           supprimer.addActionListener(kl->{
           ServiceAnnonceTrouvee ann = new ServiceAnnonceTrouvee() ; 
           ann.supprimer1(ed.getId());
           
           });
           
               
                
                
              }
          
    
            
          
           
            
        
            
            MenuManager.createMenu(f9, theme);
            f9.show(); 
        }
        );
        
        
        
        MenuManager.createMenu(f, theme);

    }
    
    
    
     public Container afficherMesAnnonces(AnnonceTrouvee at)
            {
                    
                    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                  
                     EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150), true);
                     ImageViewer image=new ImageViewer();
          
                     image.setImage(URLImage.createToStorage(placeholder,at.getImages(), "http://localhost/paw_web/web/images/pawLostFound/"+at.getImages()));
            
                  
                    Label x = new Label("Type de l'animal trouvé : ") ;
                    Label y = new Label("Colier : ") ; 
                    Label z = new Label("Agé de : ");
                    Label e = new Label ("Son Sexe  :") ; 
                    Label h = new Label ("Message complementaire  :") ; 
                    Label i = new Label ("Nom propriétaire  :") ;
                 Label type = new Label(at.getType()) ;
                 Label colier = new Label(at.getColier()); 
                 Label age = new Label(String.valueOf(at.getAge())); 
                 Label sexe = new Label (at.getSex()) ;  
                 Label msg = new Label (at.getMessage_complementaire()) ;  
                 Label nom = new Label ();
                
                 c1.add(x) ; 
                 c1.add(type) ;
                 c2.add(y) ;
                 c2.add(colier) ;
                 c3.add(z) ; 
                 c3.add(age) ;
                 c4.add(e) ; 
                 c4.add(sexe) ; 
                 c5.add(h) ; 
                 c5.add(msg) ;
                 
                 c.add(image);
                 c.add(c1);
                 c.add(c2) ; 
                 c.add(c3) ; 
                 c.add(c4) ;
                 c.add(c5) ; 
                 
            
                 return c;
            }
            

    
     
     
     
     
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public TextField getColier() {
        return colier;
    }

    public void setColier(TextField colier) {
        this.colier = colier;
    }

    public TextField getLieu_trouvee() {
        return lieu_trouvee;
    }

    public void setLieu_trouvee(TextField lieu_trouvee) {
        this.lieu_trouvee = lieu_trouvee;
    }

    public TextField getAge() {
        return age;
    }

    public void setAge(TextField age) {
        this.age = age;
    }

    public TextField getCouleur() {
        return couleur;
    }

    public void setCouleur(TextField couleur) {
        this.couleur = couleur;
    }

    public TextField getSexe() {
        return sexe;
    }

    public void setSexe(TextField sexe) {
        this.sexe = sexe;
    }

    public TextField getRace() {
        return race;
    }

    public void setRace(TextField race) {
        this.race = race;
    }

    public TextField getMessage() {
        return message;
    }

    public void setMessage(TextField message) {
        this.message = message;
    }

    public TextField getType() {
        return type;
    }

    public void setType(TextField type) {
        this.type = type;
    }

    public Button getBtnajout() {
        return btnajout;
    }

    public void setBtnajout(Button btnajout) {
        this.btnajout = btnajout;
    }

   protected Resources getResources() {
        Resources res = null;
        return res;
    }
    

    
}
