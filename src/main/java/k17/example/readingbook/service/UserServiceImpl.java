package k17.example.readingbook.service;

import k17.example.readingbook.constant.MessageError;
import k17.example.readingbook.entity.User;
import k17.example.readingbook.exception.InValidEmailException;
import k17.example.readingbook.exception.NotFoundException;
import k17.example.readingbook.exception.UnauthorizedException;
import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.dto.UserDto;
import k17.example.readingbook.model.mapper.UserMapper;
import k17.example.readingbook.model.request.*;
import k17.example.readingbook.repository.UserRepository;
import k17.example.readingbook.security.ProvideJwt;
import k17.example.readingbook.utils.EmailValidate;
import k17.example.readingbook.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ProvideJwt jwtProvider;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginDto login(ParamsLogin params) {
        if(!EmailValidate.validateEmail(params.getEmail())) {
            throw new InValidEmailException();
        }
        User user = userRepository.findByEmail(params.getEmail());
        if (user == null || !encoder.matches(params.getPassword(), user.getPassword()) || user.getIsDelete()) {
            throw new UnauthorizedException();
        }
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

        if (users == null) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
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
        if (user == null || user.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateAvtUserById(ParamsUpdateAvt avt, int id) {
        User user = userRepository.findByUserId(id);
        user.setImg_avt(avt.getAvt());
        if (user == null || user.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto getInfoUserFromToken(String token) {
        if (jwtProvider.validateToken(token) == null) {
            throw new UnauthorizedException();
        }
        return getUserById(jwtProvider.getUserIdFromJWT(token));
    }


    @Override
    public void deleteUserById(int id) {
        User user = userRepository.findByUserId(id);
        if (user == null || user.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        user.setIsDelete(true);
        userRepository.save(user);
    }

    @Override
    public UserDto updateUserByAdmin(ParamAdminUpdateUser req, int id) {
        User user = userRepository.findByUserId(id);
        if (!userRepository.existsById(id) || user.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        user.setFullName(req.getFullName());
        user.setRoleId(req.getRoleId());
        user.setAddress(req.getAddress());
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto updateUserByUser(ParamUserUpdateUser req, int id) {
        User user = userRepository.findByUserId(id);
        if (!userRepository.existsById(id) || user.getIsDelete()) {
            throw new NotFoundException(MessageError.NOT_FOUND_USER);
        }
        user.setFullName(req.getFullName());
        user.setAddress(req.getAddress());
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto createUser(ParamCreateUser req) {
        String avtDefault="http://res.cloudinary.com/dja5fb2gg/image/upload/v1647591134/wg1v61ivmxdwhsfeifgy.jpg";
        List<User> users = userRepository.findAllBy();

        for (User user: users) {
            if (user.getEmail().equals(req.getEmail())) {
                throw new DuplicateKeyException(MessageError.EMAIL_ALREADY_EXIST);
            }
            if (user == null) {
                break;
            }
        }
        User user = UserMapper.toCreateUser(req);
        user.setPassword(encoder.encode(req.getPassword()));
        user.setImg_avt(avtDefault);
        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public UserDto register(ParamCreateUser req) {
        String avtDefault="http://res.cloudinary.com/dja5fb2gg/image/upload/v1647591134/wg1v61ivmxdwhsfeifgy.jpg";
        List<User> users = userRepository.findAllBy();
        for (User user: users) {
            if (user.getEmail().equals(req.getEmail())) {
                throw new DuplicateKeyException(MessageError.EMAIL_ALREADY_EXIST);
            }
            if (user == null) {
                break;
            }
        }
        User user = UserMapper.toRegister(req);
        user.setPassword(encoder.encode(req.getPassword()));
        user.setImg_avt(avtDefault);
        userRepository.save(user);
        return UserMapper.toUserDto(user);
    }
}
