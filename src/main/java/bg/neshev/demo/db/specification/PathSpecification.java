package bg.neshev.demo.db.specification;

import bg.neshev.demo.db.model.Path;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;

public class PathSpecification {
    private PathSpecification() { }

    public static Specification<Path> byDistance(Integer distance) {
        return (Root<Path> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (distance != null) {
                return builder.equal(root.get(Path._distance), distance);
            }

            return builder.conjunction();
        };
    }

    public static Specification<Path> fetchBases() {
        return (Root<Path> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            root.fetch(Path._bases, JoinType.LEFT);
            return builder.conjunction();
        };
    }

}
