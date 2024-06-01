package com.onlineLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineLibrary.Entity.Books;


@Repository
public interface Bookrepo extends JpaRepository<Books, Integer>{

//	@Query("SELECT b FROM Books b WHERE b.email = ?1 AND u.password = ?2")
//    Optional<User> findByUsernameAndPassword(String email, String password);
}