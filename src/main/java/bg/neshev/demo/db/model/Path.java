package bg.neshev.demo.db.model;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Builder
@Table(name = "paths")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Path {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    public static final String _id = "id";

    @Column(name = _distance)
    private Integer distance;
    public static final String _distance = "distance";

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "r_base_paths",
            joinColumns = @JoinColumn(name = "path_id"),
            inverseJoinColumns = @JoinColumn(name = "base_id")
    )
    private List<Base> bases;
    public static final String _bases = "bases";

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Path path = (Path) o;
        return Objects.equals(id, path.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
