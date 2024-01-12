package br.com.aprendendospring.projetospring.repository;

import br.com.aprendendospring.projetospring.domain.user.UserModel;
import br.com.aprendendospring.projetospring.domain.user.enums.GeneroEnum;
import br.com.aprendendospring.projetospring.domain.user.enums.NivelEscolaridadeEnum;
import br.com.aprendendospring.projetospring.domain.user.enums.StatusRelacionamentoEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query("SELECT u FROM UserModel u WHERE UPPER(TRIM(u.nome)) LIKE %:nome%")
    List<UserModel> procurarNome(@Param("nome") String nome);

    @Query("SELECT u FROM UserModel u WHERE u.cpf LIKE %:cpf%")
    List<UserModel> procurarCPF(@Param("cpf") String cpf);

    @Query("SELECT u FROM UserModel u WHERE u.genero = :genero")
    List<UserModel> procurarGenero (@Param("genero") GeneroEnum genero);

    @Query(value = "SELECT u FROM UserModel u WHERE u.idade = :idade")
    List<UserModel> procurarIdade(Integer idade);

    @Query("SELECT u FROM UserModel u WHERE u.genero = :statusRelacionamento")
    List<UserModel> procurarRelacionamenot(StatusRelacionamentoEnum statusRelacionamento);

    @Query("SELECT u FROM UserModel u WHERE u.nivelEscolaridade = :nivelEscolaridade")
    List<UserModel> procurarNivelEscolaridade(NivelEscolaridadeEnum nivelEscolaridade);

}
