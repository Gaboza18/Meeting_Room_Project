package com.example.meeting.Room.Dto;


import com.example.meeting.Room.domain.Room;
import com.example.meeting.Room.domain.Progress;
import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PROTECTED)
public class RoomDto {

    private UUID uuid;
    private String class_name;
    private Timestamp create_at;
    private Progress progress;

    public RoomDto(Room room){
        this.uuid = room.getUuid();
        this.class_name = room.getRoom_name();
        this.create_at = room.getCreated_at();
        this.progress = room.getProgress();

    }
}
