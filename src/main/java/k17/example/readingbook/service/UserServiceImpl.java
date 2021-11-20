package k17.example.readingbook.service;

import k17.example.readingbook.entity.User;
import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.request.ParamsLogin;
import k17.example.readingbook.repository.UserRepository;
import k17.example.readingbook.security.ProvideJwt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private ProvideJwt jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Override
    public LoginDto login(ParamsLogin params) {
        System.out.println("hello12");
        User user = userRepository.findByEmail(params.getEmail());
        System.out.println("hello");
        System.out.println(user);
        LoginDto loginDto =new LoginDto();
        loginDto.setToken(jwtProvider.generateTokenForUser(user));
        return loginDto;
    }

    @Override
    public String getInfo() {
        System.out.println("abc");
        return "hello";
    }
}
