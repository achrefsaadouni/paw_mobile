/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.FeedBack;

import Entity.Reclamation;
import Entity.RepRec;
import static Entity.Utilisateur.membre;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.table.TableLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import java.util.ArrayList;
import service.ServiceReclamation;
import util.MenuManager;

/**
 *
 * @author AYOUB
 */
public class MesFeedBackForm {
    Form currentform;
    Resources theme;

    public MesFeedBackForm() {
        currentform = new Form("Mes FeedBack", new BoxLayout(BoxLayout.Y_AXIS));
        theme = UIManager.initFirstTheme("/theme_1_ayoub");
        UIBuilder ui = new UIBuilder();
        currentform =ui.createContainer(theme, "MesFBForm").getComponentForm();
        currentform.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

      
    }
    
    public void afficher() {
        ServiceReclamation service = new ServiceReclamation();
        ArrayList<Reclamation> liste = service.getList2(membre.getId());
        if(!liste.isEmpty())
        {
            for (Reclamation item : liste) {
                if(item.getEtat().equals("Traitée")){
                    ServiceReclamation ser = new ServiceReclamation();
                    ArrayList<RepRec> listeRep= ser.getListRep2(item.getId());
                    AddItemInForm(item,listeRep.get(0));
                }
                else{
                    AddItemInForm(item,null);
                }
                
            }
        }
        else{
            currentform.add(new SpanLabel("Vous n'avez pas de feedback"));
        }
        MenuManager.createMenu(currentform, theme);
        currentform.show();
    }    

    private void AddItemInForm(Reclamation item, RepRec rep) {
        Container Kbir = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        Container cDate = new Container(new TableLayout(1, 2));
        cDate.add(new Label(theme.getImage("ic_date_range_white_24dp.png")));
        //cDate.add(new Label(""));
        String ss =""+item.getDate();
        cDate.add(new Label(ss.substring(3)));
        
        Container cObjet = new Container(new TableLayout(1, 3));
        cObjet.add(new Label(theme.getImage("objetAyoub.png")));
        cObjet.add(new Label("Objet: "));
        cObjet.add(new Label(item.getObjet()));
        
        Container cType = new Container(new TableLayout(1, 3));
        cType.add(new Label(theme.getImage("typeAyoub.png")));
        cType.add(new Label("Type: "));
        cType.add(new Label(item.getType()));
        
        Container cText = new Container(new TableLayout(1, 2));
        cText.add(new Label(theme.getImage("textAyoub.png")));
        cText.add(new Label("Description: "));
        
        Container cTextVrai = new Container();
        cTextVrai.add(new SpanLabel("   "+item.getText()));
        
        Container cButton = new Container(new FlowLayout(CENTER, CENTER));
        Button voirRep = new Button("Voir Réponse");
        voirRep.addActionListener(l->{
            Dialog.show("La réponse de Paw", rep.getText(), "Ok", null);
        });
        cButton.add(voirRep);
        if(item.getEtat().equals("Non traitée")){
            voirRep.setVisible(false);
        }
        else{
            voirRep.setVisible(true);
        }
        
        
        Kbir.add(cDate);
        Kbir.add(cType);
        Kbir.add(cObjet);
        Kbir.add(cText);
        Kbir.add(cTextVrai);
        Kbir.add(cButton);
        
        
        
        currentform.add(Kbir);
        
        
    }
}
