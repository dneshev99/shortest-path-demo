package bg.neshev.demo.db.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "bases")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Base {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static final String _id = "id";

    @Column(name = _name)
    private String name;
    public static final String _name = "name";

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "bases")
    private List<Path> paths;
    public static final String _paths = "paths";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Base base = (Base) o;
        return Objects.equals(id, base.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
