package br.com.aprendendospring.projeto_spring.services;

import br.com.aprendendospring.projeto_spring.DAO.UserDAO;
import br.com.aprendendospring.projeto_spring.Infra.UserNotFoundException;
import br.com.aprendendospring.projeto_spring.domain.user.UserModel;
import br.com.aprendendospring.projeto_spring.domain.user.enums.GeneroEnum;
import br.com.aprendendospring.projeto_spring.domain.user.enums.NivelEscolaridadeEnum;
import br.com.aprendendospring.projeto_spring.domain.user.enums.StatusRelacionamentoEnum;
import br.com.aprendendospring.projeto_spring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<UserModel> listarTodos() {
        return repository.findAll();
    }

    public UserModel buscarPorId(Long id) {
        try {
            return repository.getReferenceById(id);
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(id);
        }
    }

    public UserModel salvar(UserDAO userDAO) {
        UserModel userModel = new UserModel(userDAO);
        return repository.save(userModel);
    }

    public String deletarPorId(UserModel user) {
        try {
            repository.deleteById(user.getId());
            return "Usuario deletado com sucesso";
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(user.getCpf());
        }
    }

    public List<UserModel> buscarNome(String nome) {
        return repository.procurarNome(nome.trim().toUpperCase());
    }

    public List<UserModel> buscarCPF(String cpf) {
        return repository.procurarCPF(cpf);
    }

    public List<UserModel> buscarEscolaridade(NivelEscolaridadeEnum nivelEscolaridade) {
        return repository.procurarNivelEscolaridade(nivelEscolaridade);
    }

    public List<UserModel> buscarRelacionamento(StatusRelacionamentoEnum statusRelacionamento) {
        return repository.procurarRelacionamenot(statusRelacionamento);
    }

    public List<UserModel> buscarGenero(GeneroEnum genero) {
        return repository.procurarGenero(genero);
    }

    public List<UserModel> buscarIdade(Integer idade) {
        return repository.procurarIdade(idade);
    }

    public UserModel update(UserModel usuario) {
        return repository.saveAndFlush(usuario);
    }
}
