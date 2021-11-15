package bg.neshev.demo.db.repository;

import bg.neshev.demo.db.model.Path;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PathRepository extends JpaRepository<Path, Integer>, JpaSpecificationExecutor<Path> {

}
