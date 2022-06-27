package hibernate.service;

import hibernate.entity.Card;
import hibernate.entity.Desk;
import hibernate.entity.Pillar;
import hibernate.entity.User;
import hibernate.repository.CardRepository;
import hibernate.repository.DeskRepository;
import hibernate.repository.PillarRepository;
import javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static hibernate.service.DeskService.*;

@Service
public class PillarSevice {
    DeskRepository deskRepository;
    CardRepository cardRepository;
    PillarRepository pillarRepository;

    public PillarSevice(DeskRepository deskRepository, PillarRepository pillarRepository, CardRepository cardRepository) {
        this.deskRepository = deskRepository;
        this.pillarRepository = pillarRepository;
        this.cardRepository = cardRepository;
    }

    @Transactional
    public void createPillar(String title, Long deskId) throws NotFoundException {
        Optional<Desk> desk = deskRepository.findById(deskId);

        Pillar newPillar = new Pillar(title);

        if (desk.isPresent()) {
            desk.get().addPillars(newPillar);
            deskRepository.save(desk.get());
        } else {
            throw new NotFoundException("Desk not found");
        }
    }

    @Transactional
    public void createCard(User userByToken, String title, String shortDescription, String description, Long deskId) throws NotFoundException {
        Optional<Desk> desk = deskRepository.findById(deskId);

        Card card = new Card(title, shortDescription, description);
        card.setOwner(userByToken);

        Optional<Pillar> toDoPillar;
        if (desk.isPresent()) {
            cardRepository.save(card);
            toDoPillar = desk.get().getPillars().stream().filter(pillar -> pillar.getTitle().equals(TODO_PILLAR_TITLE)).findAny();
        } else {
            throw new NotFoundException("Desk not founded");
        }

        if (toDoPillar.isPresent()) {
            toDoPillar.get().addCard(card);
            pillarRepository.save(toDoPillar.get());
        } else {
            throw new NotFoundException("Pillar not founded");
        }

    }

    @Transactional
    public void editCard(User user, Card card) throws NotFoundException {
        Card cardToEdit = cardRepository.findById(card.getId()).orElseThrow(() -> new NotFoundException("No such card"));

        cardToEdit.setPillar(card.getPillar());
        cardToEdit.setDescription(card.getDescription());
        cardToEdit.setShortDescription(card.getShortDescription());
        cardToEdit.setTitle(card.getTitle());

        cardRepository.save(cardToEdit);
    }

    @Transactional
    public void removeCard(User userByToken, Card card) throws Exception {
        Card cardToRemove = cardRepository.findById(card.getId()).orElseThrow(() -> new NotFoundException("No such card"));
        cardRepository.delete(cardToRemove);
    }

    public void moveCard(User userByToken, Long pillarId, Card card) throws Exception {
        Card cardToMove = cardRepository.findById(card.getId()).orElseThrow(() -> new NotFoundException("No such card"));
        Pillar newPillar = pillarRepository.findById(pillarId).orElseThrow(() -> new NotFoundException("No such pillar"));
        newPillar.addCard(card);
        cardToMove.setPillar(newPillar);

        pillarRepository.save(newPillar);
        cardRepository.save(cardToMove);
    }

    @Transactional
    public void deleteCard(User userByToken, Long cardId) throws NotFoundException {
        Card card = cardRepository.findById(cardId).orElseThrow(() -> new NotFoundException("Card not found!"));
        if (card.getOwner().getId().equals(userByToken.getId()))
            cardRepository.delete(card);
    }

    @Transactional
    public void moveCard(User userByToken, Long pillarId, Long fromPillarId, Long cardId) throws NotFoundException {
        Pillar toPillar = pillarRepository.findById(pillarId).orElseThrow(() -> new NotFoundException("Pillar not found"));
        Pillar fromPillar = pillarRepository.findById(fromPillarId).orElseThrow(() -> new NotFoundException("Pillar not found"));
        Card card = fromPillar.getCards()
                .stream()
                .filter(c -> c.getId().equals(cardId)).findAny()
                .orElseThrow(() -> new NotFoundException("Card not found"));
        card.setPillar(toPillar);
        toPillar.addCard(card);
        pillarRepository.save(toPillar);
    }
}
