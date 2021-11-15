package bg.neshev.demo.db.specification;

import bg.neshev.demo.db.model.Base;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import java.util.List;

public class BaseSpecification {
    private BaseSpecification() { }

    public static Specification<Base> byIdAndFetchPaths(Integer id) {
        return (Root<Base> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            root.fetch(Base._paths, JoinType.LEFT);
            return builder.equal(root.get(Base._id), id);
        };
    }

    public static Specification<Base> byIds(List<Integer> ids) {
        return (Root<Base> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            return root.in(ids);
        };
    }

    public static Specification<Base> byName(String name) {
        return (Root<Base> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (name != null && !name.isBlank()) {
                return builder.like(builder.lower(root.get(Base._name)), "%" + name.toLowerCase() + "%");
            }

            return builder.conjunction();
        };
    }

}
