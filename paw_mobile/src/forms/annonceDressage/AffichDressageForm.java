package forms.annonceDressage;

import Entity.AnnonceDressage;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import java.util.ArrayList;
import service.ServiceAnnonceDressage;

public class AffichDressageForm {
    Form form;
    Resources theme;    
    public AffichDressageForm(Resources theme) {
        this.theme=theme;
        form = new Form("Toutes les  annonces de dressage", new BoxLayout(BoxLayout.Y_AXIS));
        ImageViewer imageAnnonceTr=new ImageViewer();
        ServiceAnnonceDressage ServiceAnnonceDressage = new ServiceAnnonceDressage();
        ArrayList<AnnonceDressage> lis = ServiceAnnonceDressage.getList();
        for (AnnonceDressage ed : lis)
        { 
           form.add(Ajouter(ed));
        }
        form.getToolbar().addCommandToRightBar("back", null, (ev)->{AjoutDressageForm h=new AjoutDressageForm(theme);
        h.getDressageForm().show();
        });
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }

    private Container Ajouter(AnnonceDressage ed) {
        
        Container c  = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        
        Label type1 = new Label("Type de votre animal : ");
        Label type2 = new Label(ed.getType()) ;
        
        Label race1 = new Label("Sa race : ");
        Label race2 = new Label(ed.getRace()) ;
        
        Label age1 = new Label("Son age : ");
        Label age2 = new Label(String.valueOf(ed.getAge())) ;
        
        Label sexe1 = new Label("Son sexe : ");
        Label sexe2 = new Label(ed.getSex()) ;
        
        Label typeTr1 = new Label("Son type de dressage : ");
        Label typeTr2 = new Label(ed.getTypeTr()) ;
  
        Button Details = new Button("Details de cette Annonce");

        Details.addActionListener(l->{
                 
              Form f2 = new Form("Detaille de l'annonce", new BoxLayout(BoxLayout.Y_AXIS));
              ImageViewer imageAnnonceTr=new ImageViewer();
              ServiceAnnonceDressage serviceAnnonceDressage = new ServiceAnnonceDressage();
              f2.add(ajouter1(ed));
              f2.show();
                  
        });
        
        c1.add(type1);
        c1.add(type2);
        
        c2.add(race1);
        c2.add(race2);
        
        c3.add(age1);
        c3.add(age2);
        
        c4.add(sexe1);
        c4.add(sexe2);
        
        c5.add(typeTr1);
        c5.add(typeTr2);
        
        c.add(c1);
        c.add(c2);
        c.add(c3);
        c.add(c4);
        c.add(c5);
        c.add(Details);
            
        return c;
    }

    private Component ajouter1(AnnonceDressage ed) {
        
        Container c = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container c1 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c2 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c4 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c5 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
        Container c6 = new Container(new BoxLayout(BoxLayout.X_AXIS)) ; 
                  
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(150, 150), true);
        ImageViewer image=new ImageViewer();
          
        image.setImage(URLImage.createToStorage(placeholder,ed.getImages(), "http://localhost/paw_web/web/images/pawLostFound/"+ed.getImages()));
            
        Label type1 = new Label("Type de l'animal trouvé : ") ;
        Label race1 = new Label("Race : ") ; 
        Label age1 = new Label("Agé de : ");
        Label sexe1 = new Label ("Son Sexe  :") ; 
        Label msg1 = new Label ("Message complementaire  :") ; 
        Label typeTr1 = new Label ("Type de dressage  :") ; 
        
        Label type2 = new Label(ed.getType()) ;
        Label race2 = new Label(ed.getRace()); 
        Label age2 = new Label(String.valueOf(ed.getAge())); 
        Label sexe2 = new Label (ed.getSex()) ;  
        Label msg2 = new Label (ed.getMessage_complementaire()) ;       
        Label typeTr2 = new Label (ed.getTypeTr()) ;
        
        c1.add(type1); 
        c1.add(type2);
        c2.add(race1);
        c2.add(race2);
        c3.add(age1); 
        c3.add(age2);
        c4.add(sexe1); 
        c4.add(sexe2); 
        c5.add(msg1); 
        c5.add(msg2);
        c6.add(typeTr1); 
        c6.add(typeTr2); 
        
        c.add(image);
        c.add(c1);
        c.add(c2); 
        c.add(c3); 
        c.add(c4);
        c.add(c5); 
        c.add(c6); 
        return c;
    }
}