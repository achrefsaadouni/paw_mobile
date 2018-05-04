/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import Entity.Panier;
import Entity.Produit;
import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.stripe.model.Charge;
import service.ServiceAchat;
import util.PaymentOrder;

/**
 *
 * @author achref
 */
public class StripeForm {

    private Resources theme;
    private Form stripePaymentDialog;
    private UIBuilder uib;
    private TextField cardNumber;
    private TextField expYear;
    private TextField expMonth;
    private TextField cvv;
    private Button valider;
    private Button annuler;

    public StripeForm(Resources theme) {
        this.theme = theme;
        uib = new UIBuilder();
        stripePaymentDialog = (Form) uib.createContainer(theme, "StripeDialog").getComponentForm();
        cardNumber = (TextField) uib.findByName("CardNumber", stripePaymentDialog);
        expYear = (TextField) uib.findByName("ExpYear", stripePaymentDialog);
        expMonth = (TextField) uib.findByName("ExpMonth", stripePaymentDialog);
        cvv = (TextField) uib.findByName("cvv", stripePaymentDialog);
        valider = (Button) uib.findByName("Valider", stripePaymentDialog);
        annuler = (Button) uib.findByName("Annuler", stripePaymentDialog);
    }

    public void init() {

        ServiceAchat sa = new ServiceAchat();
        valider.addActionListener((evt) -> {
            String number = cardNumber.getText();
            String month = expMonth.getText();
            String year = expYear.getText().substring(2, 4);
            System.out.println(year);
            String code = cvv.getText();
            Charge a = null;
            PaymentOrder po = new PaymentOrder(number, code, month, year, Panier.prixtotal(), "test", "test", "test", "test", "tunisia", "7111");
            try {
               
                a = po.createCharge("sk_test_s3qNFSFh0IqhB0vaSTwTe9n8", po.getAmmount(), "test", po.getCardnumber(), po.getExp_month(), po.getExp_year(), po.getCvv(), po.getAddress(), po.getCity(), po.getState(), po.getCountry(), po.getZip(), po.getEmail());
               
            } catch (Exception ex) {
                System.out.println("ex");
            }
            if (a == null)
                {

                Dialog.show("Erreur", "Verifiez vos donnez S'il vous plait", "OK", null);
                }
            else if (a.getStatus().equals("succeeded")) {
                 Dialog ip = new InfiniteProgress().showInifiniteBlocking();
                ip.setTimeout(4000);
                sa.Payer("Payer");
                Panier.panier.clear();
                
                Dialog.show("Succés", "Votre Achat a été enregistrer", "OK", null);
                PanierForm pf = new PanierForm(theme);
                pf.affichePanier();
            } else {
              
                Dialog.show("Erreur", "Verifiez vos donnez S'il vous plait", "OK", null);
            }
        });

        annuler.addActionListener((evt) -> {
            PanierForm pf = new PanierForm(theme);
            pf.affichePanier();
        });
    }

    public void show() {
        init();
        stripePaymentDialog.show();
    }

}
