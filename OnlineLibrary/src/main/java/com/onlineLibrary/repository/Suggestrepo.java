package com.onlineLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.onlineLibrary.Entity.Suggestbook;



@Repository
public interface Suggestrepo extends JpaRepository<Suggestbook, Integer>{

}
