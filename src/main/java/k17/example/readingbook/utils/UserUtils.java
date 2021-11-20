package k17.example.readingbook.utils;

import k17.example.readingbook.model.dto.UserDto;

import java.util.Comparator;

public class UserUtils implements Comparator<UserDto> {
    @Override
    public int compare(UserDto userDto1, UserDto userDto2) {
        return userDto1.getFullName().compareTo(userDto2.getFullName());
    }
}