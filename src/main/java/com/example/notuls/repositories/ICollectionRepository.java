package com.example.notuls.repositories;

import com.example.notuls.entities.Collection;
import com.example.notuls.entities.projections.CollectionProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICollectionRepository extends JpaRepository<Collection, Long> {

    @Query(value = "SELECT collections.*, users.id AS userId FROM collections " +
                    "INNER JOIN users on collections.user_id = users.id "+
                    "WHERE collections.user_id = :userId",nativeQuery = true)
    List<CollectionProjection> getCollectionByUser(Long userId);
}
