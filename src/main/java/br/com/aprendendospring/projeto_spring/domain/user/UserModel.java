package br.com.aprendendospring.projeto_spring.domain.user;

import br.com.aprendendospring.projeto_spring.DTO.UserDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_usuario")
    private Long id;
    private String nome;
    private Integer idade;

    public UserModel(UserDTO userDTO) {
        this.nome = userDTO.nome();
        this.idade = userDTO.idade();
    }
}
