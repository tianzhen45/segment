package my.st.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word,String> {

    List<Word> findByCnName(String cnName);

    List<Word> findByEnName(String enName);
}
