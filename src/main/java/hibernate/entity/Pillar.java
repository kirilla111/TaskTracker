package hibernate.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Set;

@Getter @Setter
@NoArgsConstructor
@Entity
@Table(name = "PILLAR")
public class Pillar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pillar_id_generator")
    @SequenceGenerator(name = "pillar_id_generator", sequenceName = "sq_pillar_id", allocationSize = 1)
    private Long id;

    private String title;

    @org.hibernate.annotations.OrderBy(clause = "id ASC")
    @OneToMany
    @JoinColumn(name = "pillar_id")
    private Set<Card> cards;

    public Pillar(String title) {
        this.title = title;
    }


    public void addCard(Card... cards) {
        this.cards.addAll(Arrays.asList(cards));
    }

    public void addCard(Card card) {
        this.cards.add(card);
    }
}
