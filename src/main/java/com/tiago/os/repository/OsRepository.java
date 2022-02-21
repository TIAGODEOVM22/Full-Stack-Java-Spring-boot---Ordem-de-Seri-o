package com.tiago.os.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiago.os.model.Os;

@Repository
public interface OsRepository extends JpaRepository<Os, Integer>{

}
