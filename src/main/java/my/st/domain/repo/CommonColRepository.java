package my.st.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommonColRepository extends JpaRepository<CommonCol,Integer> {

    List<CommonCol> findBySystemCode(String systemCode);
}
