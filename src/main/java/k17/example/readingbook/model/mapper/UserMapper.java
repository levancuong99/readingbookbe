package k17.example.readingbook.model.mapper;

import k17.example.readingbook.entity.User;
import k17.example.readingbook.model.dto.UserDto;
import k17.example.readingbook.model.request.ParamCreateUser;

public class UserMapper {

    public static UserDto toUserDto (User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setFullName(user.getFullName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setDateOfBirth(user.getDateOfBirth());
        userDto.setGender(user.getGender());
        userDto.setImg_avt(user.getImg_avt());
        userDto.setImg_face(user.getImg_face());
        userDto.setRoleId(user.getRoleId());
        userDto.setIsDelete(user.getIsDelete());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());

        return userDto;
    }

    public static User toCreateUser (ParamCreateUser request) {
        User user = new User();
//        String img_avt = Url.ACCOUNT_IMAGE;
        user.setFullName(request.getFullName());
//        String hash = BCrypt.hashpw(request.getUserPassword(), BCrypt.gensalt(12));
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setIsDelete(false);
        user.setRoleId(request.getRoleId());
        return user;
    }

    public static User toRegister (ParamCreateUser request) {
        User user = new User();
//        String img_avt = Url.ACCOUNT_IMAGE;
        user.setFullName(request.getFullName());
//        String hash = BCrypt.hashpw(request.getUserPassword(), BCrypt.gensalt(12));
        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        user.setGender(request.getGender());
        user.setAddress(request.getAddress());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setIsDelete(false);
        user.setRoleId(2);
        return user;
    }
}
