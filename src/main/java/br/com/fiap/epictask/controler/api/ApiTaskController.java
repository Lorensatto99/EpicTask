package br.com.fiap.epictask.controler.api;

import br.com.fiap.epictask.model.Task;
import br.com.fiap.epictask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/api/task")
public class ApiTaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping
    @Cacheable("tasks")
    public Page<Task> index(@RequestParam(required = false) String title, @PageableDefault(size = 5) Pageable pageable) {
        return title == null ? repository.findAll(pageable) : repository.findByTitleLike("%" + title + "%", pageable);
    }

    @PostMapping
    public ResponseEntity<Task> create(@Valid @RequestBody Task task, UriComponentsBuilder uriBuilder) {
        repository.save(task);

        //Preparando uma requisição resposta para o cliente
        URI uri = uriBuilder
                .path("/api/task/{id}")
                .buildAndExpand(task.getId())
                .toUri();

        return ResponseEntity.created(uri).body(task);
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> show(@PathVariable Long id) {
        //Optional porque caso não apresente nenhum resultado, ele não gera uma exceção...
        Optional<Task> task = repository.findById(id);
        return ResponseEntity.of(task);
        /*
        o retorno acima faz o seguinte teste:
        if (task.isPresent()) {return ResponseEntity.ok(task.get());}
        return ResponseEntity.notFound().build();
        */
    }

    @PutMapping("{id}")
    @CacheEvict(value = "tasks", allEntries = true)
    public ResponseEntity<Task> change(@Valid @RequestBody Task newtask, @PathVariable Long id, Errors errors) {
        Optional<Task> oldTask = repository.findById(id);


        if (oldTask.isPresent()) {
            Task task = oldTask.get();
            task.setTitle(newtask.getTitle());
            task.setDescription(newtask.getDescription());
            task.setPoints(newtask.getPoints());
            repository.save(task);

            return ResponseEntity.ok(task);
        }

        return ResponseEntity.notFound().build();


    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "tasks", allEntries = true)
    public ResponseEntity<Task> destroy(@PathVariable Long id) {
        Optional<Task> task = repository.findById(id);

        //Retorna "Not Found" se o valor não existir
        if (task.isEmpty()) return ResponseEntity.notFound().build();
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
