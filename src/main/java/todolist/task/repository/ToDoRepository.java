package todolist.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.task.model.ToDo;

public interface ToDoRepository extends JpaRepository<ToDo, Integer> {
}
