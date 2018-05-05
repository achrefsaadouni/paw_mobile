/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;
/**
 *
 * @author gmehd
 */
public class AnnonceAccouplement extends Annonce{
    private String type_poil  ; 
    private String vaccin ; 
    private String dossier ;
    private String lieu;

    public AnnonceAccouplement(String type_poil, String vaccin, String dossier, String lieu, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, String images) {
        super(id, age, couleur, sex, race, message_complementaire, type, images);
        this.type_poil = type_poil;
        this.vaccin = vaccin;
        this.dossier = dossier;
        this.lieu = lieu;
    }
    
    public AnnonceAccouplement(String type_poil, String vaccin, String dossier, int id, int age, String couleur, String sex, String race, String message_complementaire, String type, String images) {
        super(id, age, couleur, sex, race, message_complementaire, type);
        this.type_poil = type_poil;
        this.vaccin = vaccin;
        this.dossier = dossier;
    }

    public AnnonceAccouplement(String type_poil, String vaccin, String dossier, int id, int age, String couleur, String sex, String race, String message_complementaire, String type) {
        super(id, age, couleur, sex, race, message_complementaire, type);
        this.type_poil = type_poil;
        this.vaccin = vaccin;
        this.dossier = dossier;
    }

    public AnnonceAccouplement() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    

    public String getType_poil() {
        return type_poil;
    }

    public void setType_poil(String type_poil) {
        this.type_poil = type_poil;
    }

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

      

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    
}
