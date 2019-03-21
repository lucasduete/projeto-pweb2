package io.github.pw2.horarioservice.enums;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

public enum TipoTurno {

    MANHA("Manha"),
    TARDE("Tarde"),
    NOITE("Noite"),
    INTEGRAL("Integral");

    private String nome;

    TipoTurno(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }

    public static List<String> valuesToString() {
        List<String> values = new ArrayList<>();

        for (TipoTurno value : TipoTurno.values()) values.add(value.getNome());

        return ImmutableList.copyOf(values);
    }

    public static TipoTurno of(String nome) {
        for (TipoTurno value : TipoTurno.values())
            if (value.getNome().toUpperCase().equals(nome.toUpperCase())) return value;

        return TipoTurno.MANHA;
    }

}
