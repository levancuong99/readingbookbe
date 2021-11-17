package k17.example.readingbook.service;

import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.request.ParamsLogin;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    LoginDto login(ParamsLogin params);
}
