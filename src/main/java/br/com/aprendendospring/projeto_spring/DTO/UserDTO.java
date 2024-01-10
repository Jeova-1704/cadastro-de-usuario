package br.com.aprendendospring.projeto_spring.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDTO(@NotBlank @NotNull String nome, @NotBlank @NotNull Integer idade) {

}
