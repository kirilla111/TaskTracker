package hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Arrays;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "CARDS")
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "card_id_generator")
    @SequenceGenerator(name = "card_id_generator", sequenceName = "sq_card_id", allocationSize = 1)
    private Long id;

    private String title;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "pillar_id", insertable = false, updatable = false)
    Pillar pillar;

    @Column(name = "short_description")
    private String shortDescription;

    private String description;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    public Card(String title, String shortDescription, String description) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.description = description;
    }

}
