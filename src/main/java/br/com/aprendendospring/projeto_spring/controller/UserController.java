package br.com.aprendendospring.projeto_spring.controller;

import br.com.aprendendospring.projeto_spring.DTO.UserDTO;
import br.com.aprendendospring.projeto_spring.domain.user.UserModel;
import br.com.aprendendospring.projeto_spring.services.UserService;
import jakarta.validation.Valid;
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
    private UserService service;

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
    public ResponseEntity<UserModel> salvar(@RequestBody @Valid UserDTO userDTO ){
        UserModel user = service.salvar(userDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(uri).body(user);
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        UserModel usuario = service.buscarPorId(id);
        String msg = service.deletarPorId(id);
        return ResponseEntity.ok().body(msg);
    }

    @GetMapping("buscarnome/{nome}")
    public ResponseEntity<List<UserModel>> buscarPorNome(@PathVariable String nome){
        List<UserModel> listaUsuarios = service.buscarPorNome(nome);
        return ResponseEntity.ok().body(listaUsuarios);
    }
}
