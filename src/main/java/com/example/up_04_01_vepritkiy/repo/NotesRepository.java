package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.Notes;
import com.example.up_04_01_vepritkiy.models.Tasks;
import com.example.up_04_01_vepritkiy.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface NotesRepository extends CrudRepository<Notes,Long> {

    List<Notes> findNotesByUser(User user);

    Notes findNotesById(Long id);

    @Query("SELECT m FROM Notes m WHERE m.nameNotes LIKE ?1% and m.user = ?2")
    List<Notes> searchByRatingStartsWith(String full_text, User user);
}
