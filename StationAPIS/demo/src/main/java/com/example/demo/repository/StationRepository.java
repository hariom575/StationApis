package com.example.demo.repository;

import com.example.demo.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface StationRepository extends JpaRepository<Station,Integer> {
    @Transactional
    @Modifying
    @Query("delete from Station s")
    int deleteFirstBy();
}
