package hibernate.service;

import hibernate.entity.Desk;
import hibernate.entity.User;
import hibernate.repository.DeskRepository;
import hibernate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeskService {

    private DeskRepository deskRepository;
    private UserRepository userRepository;

    @Autowired
    public DeskService(DeskRepository deskRepository, UserRepository userRepository) {
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void createDesk(String title, String description, User user) {
        Desk desk = deskRepository.save(new Desk(title, description, user));
        System.out.println(desk.getId());
        user.addDesk(desk);
        userRepository.save(user);
    }
}
