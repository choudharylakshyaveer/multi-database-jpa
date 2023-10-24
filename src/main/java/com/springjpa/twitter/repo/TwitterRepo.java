package com.springjpa.twitter.repo;

import com.springjpa.twitter.entitiy.TwitterIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TwitterRepo extends JpaRepository<TwitterIds, Integer> {

}
