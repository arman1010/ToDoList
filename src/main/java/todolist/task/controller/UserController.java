package todolist.task.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import todolist.task.model.User;
import todolist.task.model.UserType;
import todolist.task.services.ToDoService;
import todolist.task.services.UserService;

@Controller
public class UserController {
    private ToDoService toDoService;
    private UserService userService;
    private PasswordEncoder passwordEncoder;

    public UserController(ToDoService toDoService, UserService userService, PasswordEncoder passwordEncoder) {
        this.toDoService = toDoService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login() {
        if (!userService.findByEmail("admin").isPresent()) {
            User user = new User();
            user.setName("Admin");
            user.setEmail("admin");
            user.setPassword(passwordEncoder.encode("123"));
            user.setUserType(UserType.ADMIN);
            userService.save(user);
        }
        return "login";
    }

    @GetMapping("/loginSuccess")
    public String loginView() {
        return "redirect:/admin";
    }


}
