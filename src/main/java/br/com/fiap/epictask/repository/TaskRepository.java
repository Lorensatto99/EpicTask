package br.com.fiap.epictask.repository;

import br.com.fiap.epictask.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Long> {


    Page<Task> findByTitleLike(String title, Pageable pageable);
}
