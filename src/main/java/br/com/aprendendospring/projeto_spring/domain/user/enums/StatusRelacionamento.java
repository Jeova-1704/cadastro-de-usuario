package br.com.aprendendospring.projeto_spring.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusRelacionamento {
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    NOIVO("Noivo"),
    RELACIONAMENTO_ABERTO("Relacionamento Aberto"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viúvo");

    private final String descricao;

    StatusRelacionamento(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static StatusRelacionamento fromDescricao(String descricao) {
        for (StatusRelacionamento status : StatusRelacionamento.values()) {
            if (status.descricao.equals(descricao)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }

    @JsonValue
    public String toDescricao() {
        return descricao;
    }
}
