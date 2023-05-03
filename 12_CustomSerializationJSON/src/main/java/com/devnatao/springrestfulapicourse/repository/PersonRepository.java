package com.devnatao.springrestfulapicourse.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devnatao.springrestfulapicourse.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> { 

}
