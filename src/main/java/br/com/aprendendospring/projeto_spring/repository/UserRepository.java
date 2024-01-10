package br.com.aprendendospring.projeto_spring.repository;

import br.com.aprendendospring.projeto_spring.domain.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserModel, Long> {

    @Query(value = "select u from UserModel u where upper(trim(u.nome)) like %?1%")
    List<UserModel> buscarPorNome(String nome);

    List<UserModel> findByNomeContaining(String parteDoNome);

}
