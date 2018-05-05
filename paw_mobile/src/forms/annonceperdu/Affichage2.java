/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.annonceperdu;

import Entity.AnnoncePerdu;
import com.codename1.components.ImageViewer;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import forms.annonceAmine.Ajout;
import java.util.ArrayList;
import service.ServiceAnnoncePerdu;

/**
 *
 * @author Guideinfo
 */
public class Affichage2 {
    Form f;
    Resources theme;    
   public Affichage2(Resources theme) {
        this.theme=theme;
        f = new Form("Annonces Perdu", new BoxLayout(BoxLayout.Y_AXIS));
     
            ImageViewer imageAnnonceTr=new ImageViewer();
            ServiceAnnoncePerdu ServiceAnnoncePerdu = new ServiceAnnoncePerdu();
              
            ArrayList<AnnoncePerdu> lis = ServiceAnnoncePerdu.getList2();
            for (AnnoncePerdu ed : lis)
            { 
                
                f.add(ajouter(ed));
            }
            
                   
        f.getToolbar().addCommandToRightBar("back", null, (ev)->{Ajout h=new Ajout(theme);
          h.getF().show();
          });
       
    
   }

    public Form getF() { 
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    
     public Container ajouter(AnnoncePerdu at)
            {
                    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150), true);
                     ImageViewer image=new ImageViewer();
          
                     image.setImage(URLImage.createToStorage(placeholder,at.getImages(), "http://localhost/paw_web/web/images/pawLostFound/"+at.getImages()));
            
                    Label x = new Label("Type de l'animal Perdu : ") ;
                    Label y = new Label("Colier : ") ; 
                    Label z = new Label("Agé de : ");
                    Label e = new Label ("Son Sexe  :") ; 
                 Label type = new Label(at.getType()) ;
                 Label colier = new Label(at.getColier()); 
                 Label age = new Label(String.valueOf(at.getAge())); 
                 Label sexe = new Label (at.getSex()) ;  
               
                 Button Details = new Button("Details de cette Annonce");
                 
                 Details.addActionListener(l->{
                 
                      Form f2 = new Form("Les details de l'annonce", new BoxLayout(BoxLayout.Y_AXIS));
                      ImageViewer imageAnnonceTr=new ImageViewer();
                      ServiceAnnoncePerdu ServiceAnnoncePerdu = new ServiceAnnoncePerdu();
            /*  SpanLabel lb = new SpanLabel();
              ArrayList<AnnonceTrouvee> list = ServiceAnnonceTrouvee.getList3(at.getId());
              
              lb.setText(list.toString());
                      
                f2.add(lb);
            */
                        f2.add(ajouter1(at));
                          f2.getToolbar().addCommandToRightBar("back", null, (ev)->{Affichage2 h=new Affichage2(theme);
                           h.getF().show();
                            });
    
                             f2.show();
                 
                 });
                 c1.add(x) ; 
                 c1.add(type) ;
                 c2.add(y) ;
                 c2.add(colier) ;
                 c3.add(z) ; 
                 c3.add(age) ;
                 c4.add(e) ; 
                 c4.add(sexe) ; 
                 c.add(image);
                 c.add(c1);
                 c.add(c2) ; 
                 c.add(c3) ; 
                 c.add(c4) ; 
               
                 c.add(Details) ; 
                 
            
                 return c;
            }
            

     public Container ajouter1(AnnoncePerdu at)
            {
                    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c7 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c8 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c9 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c10 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                 
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150), true);
                     ImageViewer image=new ImageViewer();
          
                     image.setImage(URLImage.createToStorage(placeholder,at.getImages(), "http://localhost/paw_web/web/images/pawLostFound/"+at.getImages()));
            
                    Label x = new Label("Type de l'animal trouvé : ") ;
                    Label y = new Label("Colier : ") ; 
                    Label z = new Label("Agé de : ");
                    Label e = new Label ("Son Sexe  :") ; 
                    Label h = new Label ("Message complementaire  :") ; 
                    Label m = new Label ("Nom du prorietaire:") ; 
                    Label o = new Label ("Prenom du prorietaire :") ; 
                    Label p = new Label ("Addresse du prorietaire : ") ; 
                    Label l = new Label ("Numero du prorieatire : ") ; 
                    Label n = new Label ("Email  : ") ; 
                 Label type = new Label(at.getType()) ;
                 Label colier = new Label(at.getColier()); 
                 Label age = new Label(String.valueOf(at.getAge())); 
                 Label sexe = new Label (at.getSex()) ;  
                 Label msg = new Label (at.getMessage_complementaire()) ; 
                 Label nom = new Label (at.getNomUtilisater()) ; 
                 Label prenom = new Label (at.getPrenomUtilisateur()) ; 
                 Label adresse = new Label (at.getAdresseUtilisateur()) ; 
                 Label numero = new Label (at.getNumeroUtilisateur()) ; 
                 Label email = new Label (at.getEmailUtilisateur()) ; 
                 Button contacter = new Button ("Contacter par email") ;  
                   contacter.addActionListener(ww->{
                   Message msg1 = new Message("Bonjour j'ai trouver votre animal " );
            
                 Display.getInstance().sendMessage(new String[]{at.getEmailUtilisateur()}, "PI", msg1);
                   
                   });
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
                 c6.add(m) ;
                 c6.add(nom) ; 
                 c7.add(o) ; 
                 c7.add(prenom) ;
                 c8.add(p) ; 
                 c8.add(adresse) ;
                 c9.add(l) ; 
                 c9.add(numero) ; 
                 c10.add(n) ;
                 c10.add(email) ; 
                 c.add(image);
                 c.add(c1);
                 c.add(c2) ; 
                 c.add(c3) ; 
                 c.add(c4) ;
                 c.add(c5) ;
                 c.add(c6) ; 
                 c.add(c7) ; 
                 c.add(c8) ;
                 c.add(c9) ; 
                 c.add(c10) ; 
                 c.add(contacter) ; 
                 return c;
            }
            
  
}
