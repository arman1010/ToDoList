package todolist.task.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import todolist.task.model.ToDo;
import todolist.task.repository.ToDoRepository;
import todolist.task.services.ToDoService;

import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ToDoServiceImpl implements ToDoService {

    private final ToDoRepository toDoRepository;

    @Override
    public void save(ToDo toDo) {
        toDoRepository.save(toDo);
    }

    @Override
    public Optional<ToDo> findById(int id) {
        return toDoRepository.findById(id);
    }

    @Override
    public Page<ToDo> findAll(PageRequest of) {
        return toDoRepository.findAll(of);
    }


}




