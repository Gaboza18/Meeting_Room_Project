package com.example.meeting.User.Dto;


import com.example.meeting.Room.domain.Progress;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class RoomListDto {

    private UUID uuid;

    private String class_name;

    private Date  created_at;

    private Progress progress;

}
