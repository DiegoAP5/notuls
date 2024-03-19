package com.example.notuls.repositories;

import com.example.notuls.entities.Note;
import com.example.notuls.entities.projections.NoteProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INoteRepository extends JpaRepository<Note, Long> {

    @Query(value = "SELECT notes.*, users.id AS userId FROM notes "+
                "INNER JOIN users ON notes.user_id = users.id " +
                "WHERE notes.user_id = :userId",nativeQuery = true)
    List<NoteProjection> listAllNotesByUser(Long userId);
}
