/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.accouplement;

import Entity.AnnonceAccouplement;
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
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import java.util.Map;
import rest.file.uploader.tn.FileUploader;
import service.ServiceAccouplement;
import util.MenuManager;

/**
 *
 * @author gmehd
 */
public class AjoutAccouplement {

    Form f;

    TextField lieu;
    TextField age;
    TextField couleur;
    TextField sexe;
    TextField race;
    TextField message;
    TextField type;
    String filePath;
    String fileName;
    String imgname;

    private CheckBox chat;
    private CheckBox chien;
    private CheckBox cheval;
    private CheckBox rongeur;

//    private CheckBox poil1;
//    private CheckBox poil2;
//    private CheckBox poil3;
//    private CheckBox poil4;
//    private CheckBox poil5;
//
//    private CheckBox male;
//    private CheckBox female;
//
//    private CheckBox oui;
//    private CheckBox non;
//
//    private CheckBox oui1;
//    private CheckBox non1;

    Resources theme;
    Button btnajout, btnaff, choisirImage, mesAnnonces;
    private Label label, label1, label3, label4;
    private Label label2;
    private Container c1, c4, c5, c6;
    private Container c2;
    private String fileNameInServer = "";

    public AjoutAccouplement(Resources theme) {

        this.theme = theme;
        f = new Form("Annonce d'accouplement");
        //colier = new TextField("","Porte t'il un Colier",30,TextArea.ANY);
        lieu = new TextField("", "Lieu", 30, TextArea.ANY);
        age = new TextField("", "Age", 30, TextArea.ANY);
        couleur = new TextField("", "Couleur", 30, TextArea.ANY);
        label = new Label("Sexe");
        label4 = new Label("Type de poil");
        label1 = new Label("Vaccin");
        label3 = new Label("Dossier médical");

        c1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        c6 = new Container(new BoxLayout(BoxLayout.Y_AXIS));

        c1.add(label);
        c4.add(label4);
        c5.add(label1);
       c6.add(label3);

        f.add(c1);
        //      f.add(c4);
        //    f.add(c5);
//        f.add(c6);

        ComboBox<Map<String, Object>> combo = new ComboBox<>(
                "Chien", "Chat", "Cheval", "Rongeur");
        ComboBox<Map<String, Object>> combo1 = new ComboBox<>(
                "Female", "Male");

        ComboBox<Map<String, Object>> combo4 = new ComboBox<>(
                "Nus", "Sans sous-poil", "Double pelage", "Poils courts", "Poils longs");

        ComboBox<Map<String, Object>> combo2 = new ComboBox<>(
                "OUI", "NON");

        ComboBox<Map<String, Object>> combo3 = new ComboBox<>(
                "OUI", "NON");

        c1.add(combo1);
        c4.add(combo4);
        c5.add(combo2);
         c6.add(combo3);
        race = new TextField("", "Race", 30, TextArea.ANY);
        message = new TextField("", "Saisir un message complaimentaire", 30, TextArea.ANY);
        c2 = new Container();
        label2 = new Label("Veuillez choisir l'animal :");

        c2.add(label2);
        c2.add(combo);
        f.add(c2);
        f.add(c4);
              f.add(c5);
              f.add(c6);
        Container c3 = new Container(new BoxLayout(BoxLayout.X_AXIS));
        btnajout = new Button("Valider");
        choisirImage = new Button("Choisir image");
        c3.add(btnajout);
        c3.add(choisirImage);
        //btnaff = new Button("Affichage");
        //mesAnnonces= new Button ("Mes Annonces") ;             
        //f.add(colier);
        f.add(lieu);
        f.add(age);
        f.add(couleur);
        f.add(race);
        f.add(message);
        f.add(FlowLayout.encloseCenter(c3));

        //f.add(btnaff);
        //f.add(mesAnnonces) ;
        choisirImage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    FileUploader fu = new FileUploader("http://localhost/paw_web/web/images");

                    //Upload
                    Display.getInstance().openGallery(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent v) {
                            if (v == null || v.getSource() == null) {
                                System.out.println("choisir image fail !");
                                return;
                            }

                            String filePath = ((String) v.getSource()).substring(7);
                            System.out.println(filePath);

                            try {
                                fileNameInServer = fu.upload(filePath);

                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }

                        }
                    }, Display.GALLERY_IMAGE);

                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });

        btnajout.addActionListener((e) -> {

            ServiceAccouplement ser = new ServiceAccouplement();

            String sexe = "";
            switch (combo1.getModel().getSelectedIndex()) {
                case 0:
                    sexe = "Male";
                case 1:
                    sexe = "Female";
            }

            String animal = "";
            switch (combo.getModel().getSelectedIndex()) {
                case 0:
                    animal = "Chien";
                case 1:
                    animal = "Chat";
                case 2:
                    animal = "Cheval";
                case 3:
                    animal = "Rongeur";
            }

            String poil = "";
            switch (combo4.getModel().getSelectedIndex()) {
                case 0:
                    poil = "Nus";
                case 1:
                    poil = "Sans sous-poil";
                case 2:
                    poil = "Double pelage";
                case 3:
                    poil = "Poils courts";
                case 4:
                    poil = "Poils longs";
            }

            String vaccin = "";
            switch (combo2.getModel().getSelectedIndex()) {
                case 0:
                    vaccin = "OUI";
                case 1:
                    vaccin = "NON";

            }

            String dossier = "";
            switch (combo3.getModel().getSelectedIndex()) {
                case 0:
                    dossier = "OUI";
                case 1:
                    dossier = "NON";

            }

            //System.out.println(combo.getModel().getSelectedIndex());
            //AnnonceAccouplement a = new AnnonceAccouplement(colier.getText(),lieu_trouvee.getText(),0,Integer.valueOf(age.getText()),couleur.getText(),sexe,race.getText(),message.getText(),animal,fileNameInServer);
            AnnonceAccouplement a = new AnnonceAccouplement(poil, vaccin, dossier, lieu.getText(),0, Integer.valueOf(age.getText()), couleur.getText(), sexe, race.getText(), message.getText(), animal, fileNameInServer);

            System.out.println(a.getImages());
            System.out.println(fileNameInServer);
            ser.ajoutAnnonceAccouplement(a);

        });
    }

    public Form getF() {
        MenuManager.createMenu(f, theme);
        return f;
    }

}
