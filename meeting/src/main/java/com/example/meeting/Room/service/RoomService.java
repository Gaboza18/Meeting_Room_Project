//package com.example.meeting.Room.service;
//
//import com.example.meeting.Room.domain.Room;
//import com.example.meeting.Room.repository.RoomRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@RequiredArgsConstructor
//@Service
//public class RoomService {
//
//    private final RoomRepository roomRepository;
//
//    @Transactional
//    public List<Room> getInterviewerRooms(String interviewr_email){
//        List<Room> interviewer_rooms = roomRepository.findRoomsByInterviewer(interviewr_email);
//        return interviewer_rooms;
//    }
//    public List<Room> getTargetEmailRooms(String target_email){
//        List<Room> target_user_rooms = roomRepository.findRoomsByTargetUser(target_email);
//        return target_user_rooms;
//    }
//}
