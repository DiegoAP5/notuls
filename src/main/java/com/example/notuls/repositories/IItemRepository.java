package com.example.notuls.repositories;

import com.example.notuls.entities.Item;
import com.example.notuls.entities.projections.ItemProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT items.*, collections.id AS collectionId FROM items "+
                    "INNER JOIN collections ON items.collection_id = collections.id "+
                    "WHERE items.collection_id = :collectionId",nativeQuery = true)
    List<ItemProjection> getItemByUser(Long collectionId);
}
