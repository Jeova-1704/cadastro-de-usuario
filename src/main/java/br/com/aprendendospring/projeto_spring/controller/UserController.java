package br.com.aprendendospring.projeto_spring.controller;

import br.com.aprendendospring.projeto_spring.DAO.UserDAO;
import br.com.aprendendospring.projeto_spring.domain.user.UserModel;
import br.com.aprendendospring.projeto_spring.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @GetMapping("/todos")
    public ResponseEntity<List<UserModel>> bucarUsuarios() {
        List<UserModel> usuarios = service.listarTodos();
        return ResponseEntity.ok().body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserModel> buscarUsuarioId(@PathVariable Long id) {
        UserModel usuario = service.buscarPorId(id);
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/salvar")
    public ResponseEntity<UserModel> salvar(@RequestBody @Valid UserDAO userDAO){
        UserModel user = service.salvar(userDAO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getCpf()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        UserModel usuario = service.buscarPorId(id);
        String msg = service.deletarPorId(usuario);
        return ResponseEntity.ok().body(msg);
    }

    @PutMapping("/atualizar")
    public ResponseEntity<UserModel> atualizar(@RequestBody UserModel usuario) {
        UserModel userAtualizado = service.update(usuario);
        return ResponseEntity.ok().body(userAtualizado);
    }

    @GetMapping("buscarnome/{nome}")
    public ResponseEntity<List<UserModel>> buscarPorNome(@PathVariable String nome){
        List<UserModel> listaUsuarios = service.buscarNome(nome);
        return ResponseEntity.ok().body(listaUsuarios);
    }
}
