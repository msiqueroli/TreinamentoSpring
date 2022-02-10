package br.com.solinftec.treinamentospringboot.configuration;

import lombok.Data;

@Data
public class TreinamentoDefaultException extends Exception {

    public TreinamentoDefaultException() {super();}

    public TreinamentoDefaultException(String message) {
        super(message);
    }

}
