package fi.dalitso.ticketsystem.service;

import fi.dalitso.ticketsystem.domain.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getAuthenticatedUser() {
        User user = new User();
        user.setRealName("Authenticated User");
        user.setNickname("autheuser");
        user.setEmail("authenticated.user@example.org");
        return user;
    }
}
