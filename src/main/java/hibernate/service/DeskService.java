package hibernate.service;

import hibernate.entity.Desk;
import hibernate.entity.Pillar;
import hibernate.entity.User;
import hibernate.repository.DeskRepository;
import hibernate.repository.PillarRepository;
import hibernate.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeskService {

    private DeskRepository deskRepository;
    private UserRepository userRepository;
    //private PillarRepository pillarRepository;

    @Autowired
    public DeskService(DeskRepository deskRepository, UserRepository userRepository, PillarRepository pillarRepository) {
        this.deskRepository = deskRepository;
        this.userRepository = userRepository;
        //this.pillarRepository = pillarRepository;
    }

    @Transactional
    public void createDesk(String title, String description, User user) {
        Desk desk = new Desk(title, description, user);
        desk.addPillars(pillar("To-Do"), pillar("In Progress"), pillar("Done"));
        desk.addUser(user);
        desk.setOwner(user);
        deskRepository.save(desk);
    }

    @Transactional
    public void updateDesk(User user, Desk desk) throws NotFoundException {
        Desk deskToUpdate = user.getDesks()
            .stream()
            .filter(desk1 -> desk1.getId().equals(desk.getId()))
            .findAny()
            .orElseThrow((() -> new NotFoundException("No such desk")));

        deskToUpdate.setDescription(desk.getDescription());
        deskToUpdate.setTitle(desk.getTitle());

        deskRepository.saveAndFlush(deskToUpdate);
    }

    @Transactional
    public void addUser(User user, String newUserLogin, Long deskId) throws NotFoundException {
        System.out.println("transaction started -> ");
        //        user.getDesks()
        //            .stream()
        //            .filter(desk1 -> desk1.getId().equals(deskId))
        //            .findAny()
        //            .orElseThrow((() -> new NotFoundException("No such desk")));

        Desk deskToUpdate = deskRepository.findById(deskId)
            .orElseThrow((() -> new NotFoundException("No such desk")));

        System.out.println(deskToUpdate.getUsers().contains(user));
        if (!deskToUpdate.getUsers().contains(user)) {
            throw new NotFoundException("You have no rule to update this desk");
        }

        User userToAdd = userRepository.findByLogin(newUserLogin)
            .orElseThrow((() -> new NotFoundException("No such user")));

        deskToUpdate.addUser(userToAdd);

        deskRepository.saveAndFlush(deskToUpdate);
    }

    public void removeDesk(User user, Desk desk) throws NotFoundException {
        Desk deskToRemove = user.getDesks()
            .stream()
            .filter(desk1 -> desk1.getId().equals(desk.getId()))
            .findAny()
            .orElseThrow((() -> new NotFoundException("No such desk")));

        deskRepository.delete(deskToRemove);
    }

    private Pillar pillar(String title) {
        //Pillar pillar = new Pillar(title);
        //return pillarRepository.saveAndFlush(pillar);
        return new Pillar(title);
    }
}
