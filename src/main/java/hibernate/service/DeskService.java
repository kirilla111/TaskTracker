package hibernate.service;

import hibernate.entity.Desk;
import hibernate.entity.Pillar;
import hibernate.entity.User;
import hibernate.repository.DeskRepository;
import hibernate.repository.PillarRepository;
import hibernate.repository.UserRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class DeskService {
    public static String TODO_PILLAR_TITLE = "To-Do";
    public static String IN_PROGRESS_PILLAR_TITLE = "In progress";
    public static String DONE_PILLAR_TITLE = "Done";

    private final DeskRepository deskRepository;
    private final UserRepository userRepository;

    private final PillarRepository pillarRepository;

    @Transactional
    public void createDesk(String title, String description, User user) {
        Desk desk = new Desk(title, description, user);
        desk.addPillars(pillar(TODO_PILLAR_TITLE), pillar(IN_PROGRESS_PILLAR_TITLE), pillar(DONE_PILLAR_TITLE));
        desk.addUser(user);
        desk.setOwner(user);
        deskRepository.save(desk);
    }

    @Transactional
    public void updateDesk(User user, Desk desk) throws NotFoundException {
        Desk deskToUpdate = deskRepository
                .findById(desk.getId())
                .orElseThrow(() -> new NotFoundException("Desk not found"));

        if (user.getDesks().stream()
                .anyMatch(desk1 -> desk1.getId().equals(deskToUpdate.getId()))) {
            deskToUpdate.setDescription(desk.getDescription());
            deskToUpdate.setTitle(desk.getTitle());
            deskRepository.save(deskToUpdate);
        }
    }

    @Transactional
    public void addUser(User user, String newUserLogin, Long deskId) throws NotFoundException {
        Desk deskToUpdate = deskRepository.findById(deskId)
                .orElseThrow((() -> new NotFoundException("No such desk")));

        if (!deskToUpdate.getUsers().contains(user)) {
            throw new NotFoundException("You have no rule to update this desk");
        }

        User userToAdd = userRepository.findByLogin(newUserLogin)
                .orElseThrow((() -> new NotFoundException("No such user")));

        deskToUpdate.addUser(userToAdd);

        deskRepository.saveAndFlush(deskToUpdate);
    }

    @Transactional
    public void removeDesk(User user, Desk desk) throws NotFoundException {
        Desk deskToRemove = deskRepository
                .findById(desk.getId())
                .orElseThrow(() -> new NotFoundException("Desk not found"));

        if (user.getDesks().stream()
                .anyMatch(desk1 -> desk1.getId().equals(deskToRemove.getId()))) {
            deskToRemove.setDescription(desk.getDescription());
            deskToRemove.setTitle(desk.getTitle());
            deskRepository.delete(deskToRemove);
        }
    }

    @Transactional
    public Set<Pillar> getAllPillars(User userByToken, Long deskId) throws NotFoundException {
        Optional<User> user = userRepository.findById(userByToken.getId());

        if (!user.isPresent()) {
            throw new NotFoundException("User not found!");
        }

        return user.get().getDesks()
                .stream()
                .filter(desk -> desk.getId().equals(deskId))
                .findAny()
                .map(Desk::getPillars)
                .get();
    }

    private Pillar pillar(String title) {
        Pillar pillar = new Pillar();
        pillar.setTitle(title);
        return pillarRepository.save(pillar);
    }

    public void removeDesk(User userByToken, Long deskId) throws NotFoundException {
        Desk desk = deskRepository.findById(deskId).orElseThrow(() -> new NotFoundException("No such desk found!"));

        if (desk.getOwner().getId().equals(userByToken.getId()))
            deskRepository.delete(desk);
        else
            throw new NotFoundException("No Access");
    }
}
