package com.example.meeting.User.Dto;

import com.example.meeting.Room.Dto.RoomDto;
import com.example.meeting.User.domain.Role;
import lombok.*;

import java.util.List;


@Getter

@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class RoomListDto {

    private String user_name;
    private Role role;
    private int roomListTotal;
    private List<RoomDto> roomList;

    public RoomListDto(String user_name, Role role, List<RoomDto> roomList) {
        this.user_name = user_name;
        this.role = role;
        this.roomList = roomList;
        this.roomListTotal = this.roomList.size();
    }
}
