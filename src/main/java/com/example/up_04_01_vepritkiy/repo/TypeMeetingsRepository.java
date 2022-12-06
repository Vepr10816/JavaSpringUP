package com.example.up_04_01_vepritkiy.repo;

import com.example.up_04_01_vepritkiy.models.TypeMeetings;
import org.springframework.data.repository.CrudRepository;

public interface TypeMeetingsRepository extends CrudRepository<TypeMeetings,Long> {

    TypeMeetings findTypeMeetingsByMeetingsType(String meetingsType);
}
