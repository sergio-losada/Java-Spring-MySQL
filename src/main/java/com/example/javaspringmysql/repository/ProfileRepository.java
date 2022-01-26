package com.example.javaspringmysql.repository;

import com.example.javaspringmysql.dto.Profile;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, String> {
    
}
