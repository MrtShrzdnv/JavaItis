package controller;

import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.UserService;
import utils.Verifier;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * Created by Marat_2 on 01.12.2016.
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<String> registration(@RequestBody String name, @RequestBody String login, @RequestBody String password){
        Verifier verifier = new Verifier();
        if (!verifier.verifyUserIsExist(login)){
            // TODO hashFunction
            User user = User.newBuilder().setName(name).setLogin(login).setHashPassword(password).build();
            userService.save(user);
            String token = new BigInteger(130, new SecureRandom()).toString(32);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else{
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestHeader String login, @RequestHeader String password){
        // TODO hashFunction
        User user = userService.findByLoginAndPassword(login, password);
        if (user != null){
            String token = new BigInteger(130, new SecureRandom()).toString(32);
            userService.updateToken(user.getId(), token);
            return new ResponseEntity<>(token, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

}
