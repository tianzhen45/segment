package my.st.domain.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word,String> {

    List<Word> findByCnName(String cnName);

    List<Word> findByEnName(String enName);

    Page<Word> findWordsByCnNameIsLikeAndEnNameIsLike(String cnName, String enName, Pageable pageable);

    Page<Word> findWordsByCnNameIsLike(String cnName, Pageable pageable);

    Page<Word> findWordsByEnNameIsLike(String enName, Pageable pageable);

}
