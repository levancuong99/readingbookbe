package k17.example.readingbook.service;

import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.dto.UserDto;
import k17.example.readingbook.model.request.ParamAdminUpdateUser;
import k17.example.readingbook.model.request.ParamCreateUser;
import k17.example.readingbook.model.request.ParamUserUpdateUser;
import k17.example.readingbook.model.request.ParamsLogin;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    LoginDto login(ParamsLogin params);
    List<UserDto> getAllUser();
    UserDto getUserById(int id);
    UserDto updateAvtUserById(String avt,int id);
    UserDto getInfoUserFromToken(String token);
    void deleteUserById(int id);
    UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id);
    UserDto updateUserByUser(ParamUserUpdateUser req, int id);
    UserDto createUser(ParamCreateUser req);
    UserDto register(ParamCreateUser req);
}
