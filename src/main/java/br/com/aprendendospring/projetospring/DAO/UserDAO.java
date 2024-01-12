package br.com.aprendendospring.projetospring.DAO;

import br.com.aprendendospring.projetospring.domain.user.enums.GeneroEnum;
import br.com.aprendendospring.projetospring.domain.user.enums.NivelEscolaridadeEnum;
import br.com.aprendendospring.projetospring.domain.user.enums.StatusRelacionamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public record UserDAO(
        @NotNull
        @NotBlank
        @CPF
        String cpf,
        @NotNull
        @NotBlank
        @Size(min = 5, max = 255)
        String nome,
        @NotNull
        @Positive
        @Max(value = 100)
        Integer idade,
        @NotNull
        @NotBlank
        String email,
        @NotNull
        @JsonFormat(pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,
        @NotNull
        GeneroEnum genero,
        @NotNull
        @NotBlank
        String telefone,
        @NotNull
        @NotBlank
        String endereco,
        @NotNull
        @NotBlank
        String profissao,
        @NotNull
        NivelEscolaridadeEnum nivelEscolaridade,
        @NotNull
        StatusRelacionamentoEnum statusRelacionamento
) {
}
