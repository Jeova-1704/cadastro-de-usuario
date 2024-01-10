package br.com.aprendendospring.projeto_spring.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum NivelEscolaridade {
    ENSINO_FUNDAMENTAL_INCOMPLETO("Ensino Fundamental Incompleto"),
    ENSINO_FUNDAMENTAL_COMPLETO("Ensino Fundamental Completo"),
    ENSINO_MEDIO_INCOMPLETO("Ensino Médio Incompleto"),
    ENSINO_MEDIO_COMPLETO("Ensino Médio Completo"),
    SUPERIOR_INCOMPLETO("Superior Incompleto"),
    SUPERIOR_COMPLETO("Superior Completo");

    private final String descricao;

    NivelEscolaridade(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static NivelEscolaridade fromDescricao(String descricao) {
        for (NivelEscolaridade nivel : NivelEscolaridade.values()) {
            if (nivel.descricao.equals(descricao)) {
                return nivel;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }

    @JsonValue
    public String toDescricao() {
        return descricao;
    }
}
