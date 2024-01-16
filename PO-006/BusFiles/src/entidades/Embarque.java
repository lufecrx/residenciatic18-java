package entidades;

import utils.RegistroInterface;

public class Embarque implements RegistroInterface {
    
    private Passageiro passageiro;
    private Parada parada;

    public Embarque(Passageiro passageiro, Parada parada) {
        this.passageiro = passageiro;
        this.parada = parada;
        parada.embarcar(passageiro);
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public Parada getParada() {
        return parada;
    }

}
