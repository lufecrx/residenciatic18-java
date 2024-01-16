package entidades;

import java.util.List;

import utils.RegistroInterface;

public class Jornada implements RegistroInterface {
    
    private List<Trajeto> trajetos;
    private Motorista motorista;
    private Veiculo veiculo;

    public Jornada(List<Trajeto> trajetos, Motorista motorista, Veiculo veiculo) {
        this.trajetos = trajetos;
        this.motorista = motorista;
        this.veiculo = veiculo;
    }

    public List<Trajeto> getTrajetos() {
        return trajetos;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    @Override
    public String toFormattedString() {
        return trajetos.toString() + "//" + motorista.toFormattedString() + "//" + veiculo.toFormattedString();
    }
}
