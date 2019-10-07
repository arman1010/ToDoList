package todolist.task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import todolist.task.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
}
