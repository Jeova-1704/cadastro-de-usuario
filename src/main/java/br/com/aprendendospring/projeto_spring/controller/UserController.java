package br.com.aprendendospring.projeto_spring.controller;

import br.com.aprendendospring.projeto_spring.domain.user.UserModel;
import br.com.aprendendospring.projeto_spring.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
    @RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/todos")//Lista todos os usuarios do meu banco de dados
    public ResponseEntity<List<UserModel>> bucarUsuarios() {
        List<UserModel> usuarios = repository.findAll();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> buscarUsuarioId(@PathVariable Long id) {
        Optional<UserModel> usuarioOptional = repository.findById(id);
        UserModel usuario = usuarioOptional.get();
        return ResponseEntity.ok().body(usuario);
    }

    @GetMapping("/buscarid")
    public ResponseEntity<UserModel> buscarUsuarioPorId(@RequestBody Map<String, Long> requestBody) {
        Long id = requestBody.get("id");

        Optional<UserModel> usuarioOptional = repository.findById(id);

        if (usuarioOptional.isPresent()) {
            UserModel usuario = usuarioOptional.get();
            return ResponseEntity.ok().body(usuario);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/salvar")
    public ResponseEntity<UserModel> salvar(@RequestBody UserModel userModel ){
        UserModel user = repository.save(userModel);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/deletar")
    public ResponseEntity<String> delete(@RequestBody Map<String, Long> requestBody) {
        Long id = requestBody.get("id");
        UserModel usuario = repository.getReferenceById(id);
        repository.delete(usuario);

        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        UserModel usuario = repository.getReferenceById(id);
        repository.delete(usuario);
        return ResponseEntity.ok().body("Usuário deletado com sucesso");
    }


    @PutMapping("/atualizar")
    public ResponseEntity<?> atualizar(@RequestBody UserModel userModel ){

        if(userModel.getId() == null) {
            return ResponseEntity.badRequest().body("Id não informado para atualização. Por favor, fornecer id não nulo e válido.");
        }

        UserModel user = repository.saveAndFlush(userModel);
        return ResponseEntity.ok().body(user);
    }


    @GetMapping("buscarnome/{nome}")
    public ResponseEntity<List<UserModel>> buscarPorNome(@PathVariable String nome){
        List<UserModel> listaUsuarios = repository.buscarPorNome(nome.trim().toUpperCase());
        return ResponseEntity.ok().body(listaUsuarios);
    }
}
