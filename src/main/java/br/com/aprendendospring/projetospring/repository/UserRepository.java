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

}
