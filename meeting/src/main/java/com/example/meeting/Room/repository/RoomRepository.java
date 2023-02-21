//package com.example.meeting.Room.repository;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.UUID;
//import com.example.meeting.Room.domain.Room;
//
//
//@Repository
//public interface RoomRepository extends JpaRepository<Room, UUID> {
//    List<Room> findRoomsByInterviewer(@Param("interviewer_email") String interviewerEmail );
//
//    List<Room> findRoomsByTargetUser(@Param("target_user") String target_user_email);
//
//}
//
