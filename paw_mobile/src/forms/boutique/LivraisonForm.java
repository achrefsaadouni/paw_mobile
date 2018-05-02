/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.Panier;
import Entity.Utilisateur;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;

import com.codename1.ui.Dialog;

import com.codename1.ui.TextField;

import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import service.ServiceAchat;
import util.Mail;

/**
 *
 * @author achref
 */
public class LivraisonForm {

    private String code;
    private String mail;
    private TextField passcode;
    private Dialog dialogPasscode;
    private UIBuilder uib;
    private Resources theme;
    private Button poursuivre;
    private Button annuler;
    public ServiceAchat sa = new ServiceAchat();

    public LivraisonForm(Resources theme) {
        uib = new UIBuilder();
        this.theme = theme;
        dialogPasscode = (Dialog) uib.createContainer(theme, "PasscodeDialog").getComponentForm();
        passcode = (TextField) uib.findByName("Passcode", dialogPasscode);
        annuler = (Button) uib.findByName("Annuler", dialogPasscode);
        poursuivre = (Button) uib.findByName("Poursuivre", dialogPasscode);

    }

    public void sendCode() {
        code = sa.generateCode();
        System.out.println(code);
        mail = Utilisateur.membre.getEmail();

        Mail.send(mail, code);

    }

    public void affiche() {

        sendCode();

        poursuivre.addActionListener((evt) -> {
            if (code.equals(passcode.getText())) {
                Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.setTimeout(1000);
                sa.Payer();
                Panier.panier.clear();
                Dialog.show("Succés", "Votre Achat a été enregistrer", "OK", null);
                PanierForm pf = new PanierForm(theme);
                pf.affichePanier();
                
            }
        });

        annuler.addActionListener((evt) -> {
            dialogPasscode.dispose();
        });
        dialogPasscode.show();

    }

}
