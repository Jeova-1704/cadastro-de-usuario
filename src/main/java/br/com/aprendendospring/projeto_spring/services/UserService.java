package br.com.aprendendospring.projeto_spring.services;

import br.com.aprendendospring.projeto_spring.DAO.UserDAO;
import br.com.aprendendospring.projeto_spring.Infra.UserNotFoundException;
import br.com.aprendendospring.projeto_spring.domain.user.UserModel;
import br.com.aprendendospring.projeto_spring.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

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


    public String deletarPorId(Long id) {
        try {
            repository.deleteById(id);
            return "Usuario deletado com sucesso";
        } catch (EntityNotFoundException e) {
            throw new UserNotFoundException(id);
        }
    }

    public List<UserModel> buscarPorNome(String nome) {
        return repository.buscarPorNome(nome.trim().toUpperCase());
    }
}
