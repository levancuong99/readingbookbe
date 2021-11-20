package k17.example.readingbook.controller;


import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.request.ParamsLogin;
import k17.example.readingbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class UserController
{

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public LoginDto login(@RequestBody ParamsLogin params) {
        return userService.login(params);
    }

    @GetMapping(value = "/info")
    public String info() {
        return userService.getInfo();
    }
}
