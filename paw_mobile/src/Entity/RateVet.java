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
public class RateVet {
    private int id_vet;
    private float rate;
    private int nbr;

    public RateVet() {
        
    }

    @Override
    public String toString() {
        return "RateVet{" + "id_vet=" + id_vet + ", rate=" + rate + ", nbr=" + nbr + '}';
    }
    
    

    public int getId_vet() {
        return id_vet;
    }

    public void setId_vet(int id_vet) {
        this.id_vet = id_vet;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public int getNbr() {
        return nbr;
    }

    public void setNbr(int nbr) {
        this.nbr = nbr;
    }
    
}
