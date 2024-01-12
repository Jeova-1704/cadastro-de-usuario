package br.com.aprendendospring.projetospring.domain.user;

import br.com.aprendendospring.projetospring.DAO.UserDAO;
import br.com.aprendendospring.projetospring.domain.user.enums.GeneroEnum;
import br.com.aprendendospring.projetospring.domain.user.enums.NivelEscolaridadeEnum;
import br.com.aprendendospring.projetospring.domain.user.enums.StatusRelacionamentoEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@SequenceGenerator(name = "seq_usuario", sequenceName = "seq_usuario", allocationSize = 1, initialValue = 1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CPF
    private String cpf;

    private String nome;

    private Integer idade;
    private String email;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @Enumerated(EnumType.STRING)
    private GeneroEnum genero;

    private String telefone;
    private String endereco;
    private String profissao;

    @Enumerated(EnumType.STRING)
    private NivelEscolaridadeEnum nivelEscolaridade;

    @Enumerated(EnumType.STRING)
    private StatusRelacionamentoEnum statusRelacionamento;

    public UserModel(UserDAO userDAO) {
        this.cpf = userDAO.cpf();
        this.nome = userDAO.nome();
        this.idade = userDAO.idade();
        this.email = userDAO.email();
        this.dataNascimento = userDAO.dataNascimento();
        this.genero = userDAO.genero();
        this.telefone = userDAO.telefone();
        this.endereco = userDAO.endereco();
        this.profissao = userDAO.profissao();
        this.nivelEscolaridade = userDAO.nivelEscolaridade();
        this.statusRelacionamento = userDAO.statusRelacionamento();
    }
}
