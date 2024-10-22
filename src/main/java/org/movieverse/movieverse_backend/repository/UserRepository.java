package org.movieverse.movieverse_backend.repository;

import org.movieverse.movieverse_backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
