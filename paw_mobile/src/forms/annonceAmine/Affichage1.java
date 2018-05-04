/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.annonceAmine;

import Entity.AnnonceTrouvee;
import forms.annonceAmine.Ajout;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.List;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.util.ArrayList;
import service.ServiceAnnonceTrouvee;

/**
 *
 * @author Guideinfo
 */
public class Affichage1 {
    Form f;
    Resources theme;    
   public Affichage1(Resources theme) {
        this.theme=theme;
        f = new Form("Toutes les  annonces Trouveés", new BoxLayout(BoxLayout.Y_AXIS));
     
            ImageViewer imageAnnonceTr=new ImageViewer();
            ServiceAnnonceTrouvee ServiceAnnonceTrouvee = new ServiceAnnonceTrouvee();
              
            ArrayList<AnnonceTrouvee> lis = ServiceAnnonceTrouvee.getList2();
            for (AnnonceTrouvee ed : lis)
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
    
     public Container ajouter(AnnonceTrouvee at)
            {
                    Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
                    Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                    EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150), true);
                     ImageViewer image=new ImageViewer();
          
                     image.setImage(URLImage.createToStorage(placeholder,at.getImages(), "http://localhost/paw_web/web/images/pawLostFound/"+at.getImages()));
            
                    Label x = new Label("Type de l'animal trouvé : ") ;
                    Label y = new Label("Colier : ") ; 
                    Label z = new Label("Agé de : ");
                    Label e = new Label ("Son Sexe  :") ; 
                 Label type = new Label(at.getType()) ;
                 Label colier = new Label(at.getColier()); 
                 Label age = new Label(String.valueOf(at.getAge())); 
                 Label sexe = new Label (at.getSex()) ;  
                 Button Details = new Button("Details de cette Annonce");
                 
                 Details.addActionListener(l->{
                 
                      Form f2 = new Form("CI joint tous les detais de l'annonce", new BoxLayout(BoxLayout.Y_AXIS));
                      ImageViewer imageAnnonceTr=new ImageViewer();
                      ServiceAnnonceTrouvee ServiceAnnonceTrouvee = new ServiceAnnonceTrouvee();
            /*  SpanLabel lb = new SpanLabel();
              ArrayList<AnnonceTrouvee> list = ServiceAnnonceTrouvee.getList3(at.getId());
              
              lb.setText(list.toString());
                      
                f2.add(lb);
            */
                        f2.add(ajouter1(at));
    
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
            

     public Container ajouter1(AnnonceTrouvee at)
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
                
                 Label type = new Label(at.getType()) ;
                 Label colier = new Label(at.getColier()); 
                 Label age = new Label(String.valueOf(at.getAge())); 
                 Label sexe = new Label (at.getSex()) ;  
                 Label msg = new Label (at.getMessage_complementaire()) ;  
               
                
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
            
  
}
