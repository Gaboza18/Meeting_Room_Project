package com.example.meeting.User.Dto;

import com.example.meeting.User.domain.Role;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class UserDto {
    private String user_email;

    private String user_name;

    private Role role;

}
