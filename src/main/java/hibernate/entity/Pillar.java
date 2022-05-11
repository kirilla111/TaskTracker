package hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "PILLAR")
public class Pillar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pillar_id_generator")
    @SequenceGenerator(name = "pillar_id_generator", sequenceName = "sq_pillar_id", allocationSize = 1)
    private Long id;

    private String title;

    public Pillar(String title) {
        this.title = title;
    }

    public Pillar() {
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
}
