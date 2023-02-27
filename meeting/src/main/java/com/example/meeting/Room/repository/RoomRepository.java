package com.example.meeting.Room.repository;

import com.example.meeting.Room.Dto.RoomDto;
import com.example.meeting.Room.domain.Progress;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;
import com.example.meeting.Room.domain.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, UUID> {

    // 로그인 한 유저에 대한 미팅 전체목록 조회
    List<Room> findRoomByUserUserEmail(@Param("target_user") String target_user);

    // 로그인 한 유저에 대한 미팅 조건 조회
    // List<Room> findRoomByUserUserEmailANDProgress(@Param("target_user") String target_user, @Param("progress") Progress progress);

}
