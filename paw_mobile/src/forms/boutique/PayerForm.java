/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forms.boutique;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;

/**
 *
 * @author achref
 */
public class PayerForm {
    
    private Dialog paymentMethodeDialog;
    private Resources theme;
    private Button payerCompte;
    private Button payerStripe;
    private Button annuler;
    private UIBuilder uib;

    public PayerForm(Resources theme) {
        this.theme = theme;
        uib = new UIBuilder();
        paymentMethodeDialog = (Dialog) uib.createContainer(theme, "PaymentDialog").getComponentForm();
    }
    
    public void init() {
        payerCompte = (Button) uib.findByName("PayerCompte", paymentMethodeDialog);
        payerStripe = (Button) uib.findByName("PayerStripe", paymentMethodeDialog);
        annuler = (Button) uib.findByName("Annuler", paymentMethodeDialog);
        
        payerCompte.addActionListener((evt) -> {
            
            paymentMethodeDialog.dispose();
            LivraisonForm lf = new LivraisonForm(theme);
            lf.affiche();
            
        });
        
        payerStripe.addActionListener((evt) -> {
          
            paymentMethodeDialog.dispose();
            System.out.println("stripe");
        });
        
        annuler.addActionListener((evt) -> {
            paymentMethodeDialog.setTimeout(1000);
        });
    }
    
    public void show() {
        init();
        paymentMethodeDialog.show();
    }
    
}
