package k17.example.readingbook.service;

import k17.example.readingbook.entity.User;
import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.dto.UserDto;
import k17.example.readingbook.model.mapper.UserMapper;
import k17.example.readingbook.model.request.ParamAdminUpdateUser;
import k17.example.readingbook.model.request.ParamCreateUser;
import k17.example.readingbook.model.request.ParamUserUpdateUser;
import k17.example.readingbook.model.request.ParamsLogin;
import k17.example.readingbook.repository.UserRepository;
import k17.example.readingbook.security.ProvideJwt;
import k17.example.readingbook.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ProvideJwt jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginDto login(ParamsLogin params) {
        User user = userRepository.findByEmail(params.getEmail());
        LoginDto loginDto =new LoginDto();
        loginDto.setToken(jwtProvider.generateTokenForUser(user));
        loginDto.setUserId(user.getUserId());
        loginDto.setRoleId(user.getRoleId());
        return loginDto;
    }


    @Override
    public List<UserDto> getAllUser() {
        List<UserDto> userDtos = new ArrayList<>();
        List<User> users = userRepository.findAllBy();

//        if (users == null) {
//            throw new NotFoundException(MessageError.NOT_FOUND_USER);
//        }

        for (User user: users) {
            if (!user.getIsDelete()) {
                userDtos.add(UserMapper.toUserDto(user));
            }
        }
        Collections.sort(userDtos, new UserUtils());

        return userDtos;
    }

    @Override
    public UserDto getUserById(int id) {
        User user = userRepository.findByUserId(id);
//        if (user == null || user.getIsDelete()) {
//            throw new NotFoundException(MessageError.NOT_FOUND_USER);
//        }
        return UserMapper.toUserDto(user);
    }

    @Override
    public void deleteUserById(int id) {
        User user = userRepository.findByUserId(id);
//        if (user == null || user.getIsDelete()) {
//            throw new NotFoundException(MessageError.NOT_FOUND_USER);
//        }
        user.setIsDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id) {
        User user = userRepository.findByUserId(id);
//        if (!userRepository.existsById(id) || user.getIsDelete()) {
//            throw new NotFoundException(MessageError.NOT_FOUND_USER);
//        }
        user.setFullName(req.getFullName());
        user.setRoleId(req.getRoleId());
        user.setAddress(req.getAddress());
//        user.setUpdatedAt(now);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUserByUser(ParamUserUpdateUser req, int id) {
        User user = userRepository.findByUserId(id);
//        if (!userRepository.existsById(id) || user.getIsDelete()) {
//            throw new NotFoundException(MessageError.NOT_FOUND_USER);
//        }
        user.setFullName(req.getFullName());
        user.setAddress(req.getAddress());

//        user.setUpdatedAt(now);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto createUser(ParamCreateUser req) {
        List<User> users = userRepository.findAllBy();
//        for (User user: users) {
//            if (user.getEmail().equals(req.getEmail()) {
//                throw new DuplicateKeyException(MessageError.EMAIL_ALREADY_EXIST);
//            }
//            if (user == null) {
//                break;
//            }
//        }
        User user = UserMapper.toCreateUser(req);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto register(ParamCreateUser req) {
        List<User> users = userRepository.findAllBy();
//        for (User user: users) {
//            if (user.getEmail().equals(req.getEmail()) {
//                throw new DuplicateKeyException(MessageError.EMAIL_ALREADY_EXIST);
//            }
//            if (user == null) {
//                break;
//            }
//        }
        User user = UserMapper.toRegister(req);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }


}
