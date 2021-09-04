package edu.sabanciuniv.ipamdemo.repository;

import edu.sabanciuniv.ipamdemo.model.Network;
import edu.sabanciuniv.ipamdemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>  {

    User findByUsername(String username);

    boolean existsByUsername(String username);
}
