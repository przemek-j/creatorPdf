package com.pjada.GeneratorPdf.repo;

import com.pjada.GeneratorPdf.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Lob;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
    Optional<User> findByUserName(String userName);
    User findById(Long id);
}
