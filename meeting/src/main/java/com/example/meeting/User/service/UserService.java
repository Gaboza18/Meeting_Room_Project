package com.example.meeting.User.service;

import com.example.meeting.Room.Dto.RoomDto;
import com.example.meeting.Room.domain.Progress;
import com.example.meeting.Room.domain.Room;
import com.example.meeting.User.Dto.RoomListDto;
import com.example.meeting.User.Dto.SignInDto;
import com.example.meeting.User.domain.Role;
import com.example.meeting.User.domain.User;
import com.example.meeting.User.repository.UserRepository;
import com.example.meeting.Room.repository.RoomRepository;
import com.example.meeting.User.Dto.UserDto;
import com.example.meeting.common.Jwt.Dto.TokenDto;
import com.example.meeting.common.Jwt.JwtProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.parameters.P;
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

    public User getUserByEmail(String email) throws Exception {
        User user = userRepository.findUserByUserEmail(email);
        return user;

    }

    // 사용자에 따른 전체 강좌 조회
    public String findAllUserRooms(String token) throws Exception {

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter(); // JSON 변환 하기 위한 객체 선언

        String userEmail = jwtProvider.getUserEmail(token);
        User user = getUserByEmail(userEmail);

        List<Room> rooms = roomRepository.findRoomByUserUserEmail(userEmail);
        List<RoomDto> roomDtos = new ArrayList<>();

        for (Room room : rooms){
            RoomDto roomdto = new RoomDto(room);
            roomDtos.add(roomdto);
        }

        RoomListDto roomListDto = new RoomListDto(user.getUserEmail(),user.getRole(),roomDtos);

        String json = ow.writeValueAsString(roomListDto);

        return json;
    }

    // 과정에 따른 강좌 조회
//    public String findProgressRooms(String token) throws Exception{
//
//        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
//
//        String userEmail = jwtProvider.getUserEmail(token);
//        User user = getUserByEmail(userEmail);
//
//
//    }

    public User findUser(String token) throws  Exception{
        String userEmail= jwtProvider.getUserEmail(token);
        return this.getUserByEmail(userEmail);
    }

    public String findUserEmail(String token){
        return jwtProvider.getUserEmail(resolveToken(token));
    }

    public String resolveToken(String bearerToken) {
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
