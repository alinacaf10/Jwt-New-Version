package org.example.jwtnewversion.repository;

import org.example.jwtnewversion.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OurUsersRepo extends JpaRepository<OurUsers,Integer> {
   Optional<OurUsers> findByEmail(String email);
}
