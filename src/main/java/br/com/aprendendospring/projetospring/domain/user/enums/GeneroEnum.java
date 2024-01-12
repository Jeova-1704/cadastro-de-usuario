package br.com.aprendendospring.projetospring.domain.user.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum GeneroEnum {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não Binário"),
    OUTRO("Outro");

    private final String descricao;

    GeneroEnum(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    @JsonCreator
    public static GeneroEnum fromDescricao(String descricao) {
        for (GeneroEnum genero : GeneroEnum.values()) {
            if (genero.descricao.equals(descricao)) {
                return genero;
            }
        }
        throw new IllegalArgumentException("Descrição inválida: " + descricao);
    }

    @JsonValue
    public String toDescricao() {
        return descricao;
    }
}
