package bg.neshev.demo.db.repository;

import bg.neshev.demo.db.model.Base;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BaseRepository extends JpaRepository<Base, Integer>,
        JpaSpecificationExecutor<Base>, PagingAndSortingRepository<Base, Integer> {

}
