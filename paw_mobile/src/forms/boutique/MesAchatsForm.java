/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.Achat;
import com.codename1.ui.Button;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.mycompany.myapp.MenuForm;
import service.ServiceAchat;
import util.MenuManager;

/**
 *
 * @author achref
 */
public class MesAchatsForm {

    private Resources theme;
    private UIBuilder uib;
    private Form achatsForm;
    private Container achatContainer;
    private Container itemContainer;
    private Container buttonContainer;
    private Container righButtonContainer;
    private Label libelle;
    private Label prix;
    private Label dateAchat;
    private Button consulter;
    private ServiceAchat sa;
    boolean isFirst = true;

    public MesAchatsForm(Resources theme) {
        sa = new ServiceAchat();
        this.theme = theme;
        this.theme = UIManager.initNamedTheme("/theme_1", "Theme 1");
        uib = new UIBuilder();
        if (!sa.mesAchats().isEmpty()) {
            achatsForm = uib.createContainer(this.theme, "AchatsForm").getComponentForm();
            achatsForm.setTitle("Mes Achats");
        } else {
            achatsForm = new Form("Mes Achats", new BoxLayout(BoxLayout.Y_AXIS));
        }

    }

    public void init() {
        if (!sa.mesAchats().isEmpty()) {
            for (Achat achat : sa.mesAchats()) {
                initAchats(achat);
            }
        } else {
            Label titre = new Label("Vous n'avez aucun achat");

            Button payer = new Button("Retour Au Menu", theme.getImage("back.png"));
            payer.setUIID("BlueButton");
            payer.addActionListener(l -> {
                MenuForm mf = new MenuForm(theme);
                mf.affiche();

            });
            achatsForm.add(titre);
            achatsForm.add(payer);
        }

    }

    public void initAchats(Achat achat) {

        if (isFirst) {
            achatContainer = (Container) uib.findByName("AchatContainer", achatsForm);
            itemContainer = (Container) uib.findByName("ItemContainer", achatContainer);
            buttonContainer = (Container) uib.findByName("ButtonContainer", achatsForm);
            righButtonContainer = (Container) uib.findByName("RightButtonContainer", achatsForm);
            libelle = (Label) uib.findByName("Libelle", achatsForm);
            prix = (Label) uib.findByName("Prix", achatsForm);

            dateAchat = (Label) uib.findByName("DateAchat", achatsForm);

            consulter = (Button) uib.findByName("Reclamer", achatsForm);

            System.out.println(achat.getList());

            System.out.println("First");
            libelle.setText(achat.getEtat());
            prix.setText(String.valueOf(achat.getPrix()));
            dateAchat.setText(achat.getDate_achat());
            consulter.addActionListener(l -> {
                DetailAchatForm a = new DetailAchatForm(theme, achat.getList());
                a.affiche();
            });
            isFirst = false;
        } else {

            Container achatContainer = new Container(new FlowLayout());
            ((FlowLayout) achatContainer.getLayout()).setFillRows(true);
            achatContainer.setUIID("MyColdGreenTab");

            Container itemContainer = new Container(BoxLayout.y());
            //Container for buttons
            FlowLayout layout = new FlowLayout();
            layout.setFillRows(true);
            Container buttonContainer = new Container(layout);
            ((FlowLayout) buttonContainer.getLayout()).setFillRows(true);
            //Container to align button on the left

            Container righButtonContainer = new Container(new FlowLayout());
            ((FlowLayout) righButtonContainer.getLayout()).setFillRows(false);
            ((FlowLayout) righButtonContainer.getLayout()).setAlign(RIGHT);

            Label libelle = new Label(achat.getEtat(), theme.getImage("label.png"), "MyLabel1");
            Label prix = new Label(String.valueOf(achat.getPrix()), theme.getImage("coins (1).png"), "MyLabel1");

            Label dateAchat = new Label(achat.getDate_achat().toString(), theme.getImage("calendar (2).png"), "MyLabel1");
            Button consulter = new Button("Consulter", theme.getImage("content.png"));
            consulter.addActionListener(l -> {
                DetailAchatForm a = new DetailAchatForm(theme, achat.getList());
                a.affiche();
            });
            consulter.setUIID("RedButton");
            buttonContainer.add(righButtonContainer);
            righButtonContainer.add(consulter);
            itemContainer.add(libelle);
            itemContainer.add(prix);
            itemContainer.add(dateAchat);
            itemContainer.add(buttonContainer);
            achatContainer.add(itemContainer);

            achatsForm.add(achatContainer);

        }
    }



public void affiche() {

        init();
        MenuManager.createMenu(achatsForm, theme);
        achatsForm.show();
    }

}
