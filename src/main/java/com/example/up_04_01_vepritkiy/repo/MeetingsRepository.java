package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.Meetings;
import com.example.up_04_01_vepritkiy.models.Tasks;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingsRepository extends CrudRepository<Meetings, Long> {
    @Query("SELECT m FROM Meetings m WHERE m.meetingsName LIKE ?1% or m.meetingsDescription LIKE ?1%")
    List<Meetings> searchByRatingStartsWith(String full_text);

    Meetings findMeetingsById(Long id);
}
