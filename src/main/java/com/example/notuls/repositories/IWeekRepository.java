package com.example.notuls.repositories;

import com.example.notuls.entities.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWeekRepository extends JpaRepository<Week, Long> {
}
