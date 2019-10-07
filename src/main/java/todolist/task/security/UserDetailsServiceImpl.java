package todolist.task.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import todolist.task.model.User;
import todolist.task.services.UserService;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> byEmail = userService.findByEmail(email);
        if (byEmail.isPresent()) {
            return new SpringUser(byEmail.get());
        }
        throw new UsernameNotFoundException("user with" + email + "does not exist");
    }


}
