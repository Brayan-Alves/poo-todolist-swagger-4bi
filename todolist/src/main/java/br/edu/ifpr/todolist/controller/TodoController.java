package br.edu.ifpr.todolist.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifpr.todolist.dto.TodoConclusaoRequestDTO;
import br.edu.ifpr.todolist.dto.TodoDetalhadoResponseDTO;
import br.edu.ifpr.todolist.dto.TodoRequestDTO;
import br.edu.ifpr.todolist.dto.TodoResponseDTO;
import br.edu.ifpr.todolist.service.TodoService;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService service;

    public TodoController(TodoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<TodoResponseDTO> criar(@RequestBody TodoRequestDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<List<TodoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/conclusao")
    public ResponseEntity<TodoDetalhadoResponseDTO> concluir(@PathVariable Long id,
            @RequestBody TodoConclusaoRequestDTO dto) {
        return ResponseEntity.ok(service.concluir(id, dto));
    }

    @GetMapping("/concluidas")
    public ResponseEntity<List<TodoDetalhadoResponseDTO>> listarConcluidas() {
        return ResponseEntity.ok(service.listarConcluidas());
    }

    @GetMapping("/pendentes")
    public ResponseEntity<List<TodoDetalhadoResponseDTO>> listarPendentes() {
        return ResponseEntity.ok(service.listarPendentes());
    }
}
