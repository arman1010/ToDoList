package todolist.task.services;

import org.springframework.stereotype.Service;
import todolist.task.model.User;

import java.util.Optional;

@Service
public interface UserService {
    Optional<User> findByEmail(String email);

    void save(User user);
}

