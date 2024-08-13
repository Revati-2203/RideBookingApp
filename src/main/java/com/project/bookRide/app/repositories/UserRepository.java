package com.project.bookRide.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.bookRide.app.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
