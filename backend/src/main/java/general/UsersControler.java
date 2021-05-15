package general;

import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IUsersService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class UsersControler {

    @Autowired
    IUsersService usersService;

    // CREATE
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        Integer errorCode = usersService.createUser(user);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "User: " + user.getImie() + " " + user.getNazwisko() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + user.getImie() + " " + user.getNazwisko() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<Object> getUsers() {
        return new ResponseEntity<>(usersService.getUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getUser(@PathVariable("id") int id) {
        User user = usersService.getUser(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (user != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(user, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        Integer errorCode = usersService.updateUser(user);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "User: " + user.getImie() + " " + user.getNazwisko() + " updated.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: " + user.getImie() + " " + user.getNazwisko() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteUser(@PathVariable("id") int id) {
        Integer errorCode = usersService.deleteUser(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "User: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
