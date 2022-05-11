package hibernate.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "DESKS")
public class Desk implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "desk_id_generator")
    @SequenceGenerator(name = "desk_id_generator", sequenceName = "sq_desk_id", allocationSize = 1)
    private Long id;

    private String title;
    private String description;

    //@JsonIgnore
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User owner;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
        name = "desk_pillar",
        joinColumns = @JoinColumn(name = "desk_id"),
        inverseJoinColumns = @JoinColumn(name = "pillar_id")
    )
    private Set<Pillar> pillars = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(
        name = "user_desk",
        joinColumns = @JoinColumn(name = "desk_id"),
        inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Pillar> getPillars() {
        return pillars;
    }

    public void setPillars(Set<Pillar> pillars) {
        this.pillars = pillars;
    }

    public void addPillars(Pillar... pillars) {
        this.pillars.addAll(Arrays.asList(pillars));
    }

    public void addPillar(Pillar pillar) {
        this.pillars.add(pillar);
    }

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

    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Desk desk = (Desk) o;
        return title.equals(desk.title) && description.equals(desk.description) && owner.equals(desk.owner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, owner);
    }
}
