package todolist.task.security;

import org.springframework.security.core.authority.AuthorityUtils;
import todolist.task.model.User;

public class SpringUser extends org.springframework.security.core.userdetails.User {
    private User user;

    public SpringUser(User user) {
        super(user.getEmail(), user.getPassword(), AuthorityUtils.createAuthorityList(user.getUserType().name()));
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
