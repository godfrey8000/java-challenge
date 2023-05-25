package jp.co.axa.apidemo.repositories;

import io.lettuce.core.dynamic.annotation.Param;
import jp.co.axa.apidemo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Query("select u from User u where u.loginId = :username")
    Optional<User> selectApplicationUserByUsername(@Param("username") String username);
}
