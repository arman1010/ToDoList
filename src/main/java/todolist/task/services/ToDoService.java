package todolist.task.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import todolist.task.model.ToDo;

import java.awt.*;
import java.util.Optional;

@Service
public interface ToDoService {
    void save(ToDo toDo);



    Optional<ToDo> findById(int id);

    Page<ToDo> findAll(PageRequest of);


//    Page<ToDo> findAll(Pageable pageable);
}


