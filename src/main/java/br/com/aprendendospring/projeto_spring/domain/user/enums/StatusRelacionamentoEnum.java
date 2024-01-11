package br.com.aprendendospring.projeto_spring.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum StatusRelacionamentoEnum {
    SOLTEIRO("Solteiro"),
    CASADO("Casado"),
    NOIVO("Noivo"),
    RELACIONAMENTO_ABERTO("Relacionamento Aberto"),
    DIVORCIADO("Divorciado"),
    VIUVO("Viúvo");

    private final String descricao;

    StatusRelacionamentoEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static StatusRelacionamentoEnum fromDescricao(String descricao) {
        for (StatusRelacionamentoEnum status : StatusRelacionamentoEnum.values()) {
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
