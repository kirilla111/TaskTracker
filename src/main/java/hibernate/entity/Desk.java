package hibernate.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Table(name = "DESKS")
public class Desk {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desk_id_generator")
    @SequenceGenerator(name = "desk_id_generator", sequenceName = "sq_desk_id", allocationSize = 1)
    private Long id;

    private String title;
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Desk() {
    }

    public Desk(String title, String description, User owner) {
        this.title = title;
        this.owner = owner;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
