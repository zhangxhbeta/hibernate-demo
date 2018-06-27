package top.xhbeta.demo.hibernate.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import top.xhbeta.demo.hibernate.domain.uni.onetomany.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
