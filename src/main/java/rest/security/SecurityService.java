package rest.security;

import hibernate.entity.Desk;
import hibernate.entity.User;
import hibernate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SecurityService {
    private static Random RND = new Random();
    private static char[] TOKEN_CHARS = new char[]
        {
            'a', 'b', 'c', 'e', 'f', 'g', 'h', 'j', 'z', 'x', 'c', 'v', 'b', 'n', 'm', 'q', 'w', 'e', 'r', 't',
            '1', '2', '3', '4', '5', '6', '7', '8', '9', '0'
        };
    private static int TOKEN_LENGTH = 30;
    private static final int TOKEN_EXPIRATION = 20*60*1000;

    private Map<String, UserPrinciple> usersMap;
    private UserService userService;

    @Autowired
    public SecurityService(UserService userService) {
        this.userService = userService;
        this.usersMap = new HashMap<>();
    }

    public String authorize(String login, String password) throws IllegalStateException {
        User user = userService.findByLoginAndPassword(login, password);

        String token = generateToken();
        usersMap.put(token, new UserPrinciple(user));
        return token;
    }

    public User getUserByToken(String token) throws Exception {
        if (!usersMap.containsKey(token)) {
            throw new Exception("Wrong token!");
        }

        UserPrinciple user = usersMap.get(token);
        if (new Date().getTime() - user.getLoginDate().getTime() < TOKEN_EXPIRATION) {
            System.out.println("desks->");
            for (Desk d : user.getUser().getDesks()) {
                System.out.println(d.getTitle());
            }
            return user.getUser();
        } else {
            usersMap.remove(token);
            throw new Exception("Token already expired!");
        }
    }

    private String generateToken() {
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            token.append(TOKEN_CHARS[RND.nextInt(TOKEN_CHARS.length - 1)]);
        }
        return token.toString();
    }
}
