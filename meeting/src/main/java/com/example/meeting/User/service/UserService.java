package com.example.meeting.User.service;


import com.example.meeting.Room.Dto.RoomDto;
import com.example.meeting.Room.domain.Room;
import com.example.meeting.User.Dto.SignInDto;
import com.example.meeting.User.domain.Role;
import com.example.meeting.User.domain.User;
import com.example.meeting.User.repository.UserRepository;
import com.example.meeting.Room.repository.RoomRepository;
import com.example.meeting.User.Dto.UserDto;
import com.example.meeting.common.Jwt.Dto.TokenDto;
import com.example.meeting.common.Jwt.JwtProvider;
import com.example.meeting.common.Jwt.JwtString;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    private final RoomRepository roomRepository;

    private final JwtProvider jwtProvider;

    @Transactional
    public String createUser(SignInDto signInDto) {

        User user = userRepository.findUserByUserEmailAndUserName(signInDto.getUser_email() , signInDto.getUser_name())
                .orElseGet(() -> userRepository.save(User.createUser(
                        UserDto.builder()
                                .user_email(signInDto.getUser_email())
                                .user_name(signInDto.getUser_name())
                                .role(Role.USER).build()
                )));

        return user.getUserEmail();
    }


    @Transactional
    public TokenDto invalidProvide(SignInDto signInDto) throws Exception {

        User user = userRepository.findUserByUserEmailAndUserName(signInDto.getUser_email() , signInDto.getUser_name())
                .orElseThrow(() -> new Exception("사용자를 찾을 수 없습니다."));

        return jwtProvider.createToken(user.getUserEmail());
    }

    public String findAllUserRooms(String token) throws Exception {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String user_uuid = jwtProvider.getUserEmail(token);

        List<Room> rooms = roomRepository.findRoomByUserUserEmail(user_uuid);


        List<RoomDto> roomDtos = new ArrayList<>();

        for (Room room : rooms){
            RoomDto roomdto = new RoomDto(room);
            roomDtos.add(roomdto);
        }
        String json = ow.writeValueAsString(roomDtos);
        return json;
    }

    public String findUser(String token) throws  Exception{
        return jwtProvider.getUserEmail(token);
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
