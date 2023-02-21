package com.example.meeting.User.controller;


import com.example.meeting.User.Dto.MainHomeDto;
import com.example.meeting.User.Dto.SignInDto;
import com.example.meeting.User.Dto.UserDto;
import com.example.meeting.User.service.UserService;

import com.example.meeting.common.Jwt.Dto.TokenDto;
import com.example.meeting.common.Jwt.JwtFilter;
import com.example.meeting.common.Jwt.JwtProvider;
import com.example.meeting.common.Jwt.JwtString;
import com.example.meeting.common.ResponseResult;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    private final JwtProvider jwtProvider;

    @PostMapping("/sign")
    public ResponseEntity<ResponseResult<MainHomeDto>> SignIn(){
        // userEmail을 통한 권한 확인

        // 응시자일 경우 UserDto 데이터 응답

        // interviewer
        MainHomeDto result = new MainHomeDto();
        return ResponseEntity.ok().body(new ResponseResult<>(HttpStatus.OK.value(), result));
    }

    @Transactional
    @PostMapping("/made")
    public ResponseEntity<ResponseResult<String>> SignUp(@RequestBody SignInDto signInDto){
        String savedUser = userService.createUser(signInDto);
        return ResponseEntity.ok()
                .body(new ResponseResult<>(HttpStatus.OK.value() , savedUser));
   }
   @PostMapping("/signin")
    public ResponseEntity<ResponseResult<TokenDto>> getToken(@RequestBody SignInDto signinDto) throws Exception {
       TokenDto tokenDto = userService.invalidProvide(signinDto);


       return ResponseEntity.ok()
               .body(new ResponseResult<>(HttpStatus.OK.value() , tokenDto));
   }

   @GetMapping("/test")
   public ResponseEntity<ResponseResult<String>> TestTokenController(@RequestHeader(JwtString.HEADER_STRING) String userToken) throws Exception {
       return ResponseEntity.ok()
               .body(new ResponseResult<>(HttpStatus.OK.value() , userService.findUser(userService.resolveToken(userToken))));
   }

   @GetMapping("/rooms")
    public ResponseEntity<ResponseResult<List>> getAllUserRooms(@RequestHeader(JwtString.HEADER_STRING) String userToken) throws Exception {
        return ResponseEntity.ok()
                .body(new ResponseResult<>(HttpStatus.OK.value(), userService.findAllUserRooms(userService.resolveToken(userToken))));
   }

}
