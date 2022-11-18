package kitri.dagachi.repository;

import kitri.dagachi.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface AuthRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);
}

