package k17.example.readingbook.controller;


import k17.example.readingbook.model.dto.LoginDto;
import k17.example.readingbook.model.dto.UserDto;
import k17.example.readingbook.model.request.ParamAdminUpdateUser;
import k17.example.readingbook.model.request.ParamCreateUser;
import k17.example.readingbook.model.request.ParamUserUpdateUser;
import k17.example.readingbook.model.request.ParamsLogin;
import k17.example.readingbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/")
public class UserController
{

    @Autowired
    private UserService userService;

    @PostMapping(value = "/login")
    public LoginDto login(@Validated @RequestBody ParamsLogin params) {
        return userService.login(params);
    }


    @GetMapping(value="/users")
    public ResponseEntity<?> getListUsers() {
        List<UserDto> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping(value="users/{id}")
    public ResponseEntity<?> getUsersById(@PathVariable int id) {
        System.out.println(id);
        UserDto user =  userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok("delete success");
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<?> updateUserByAdmin(@Validated @RequestBody ParamAdminUpdateUser req, @PathVariable int id) {
        UserDto account = userService.updateUserByAdmin(req, id);
        return ResponseEntity.ok(account);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUserByUser(@Validated @RequestBody ParamUserUpdateUser req, @PathVariable int id) {
        UserDto account = userService.updateUserByUser(req, id);
        return ResponseEntity.ok(account);
    }

    @PostMapping("users/create")
    public ResponseEntity<?> createUser(@Validated @RequestBody ParamCreateUser req) {
        UserDto user = userService.createUser(req);
        return ResponseEntity.ok(user);
    }


    @PostMapping("users/register")
    public ResponseEntity<?> register(@Validated @RequestBody ParamCreateUser req) {
        UserDto user = userService.register(req);
        return ResponseEntity.ok(user);
    }
}
