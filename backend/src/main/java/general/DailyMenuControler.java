package general;

import models.DailyMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.IDailyMenuService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class DailyMenuControler {

    @Autowired
    IDailyMenuService dailyMenuService;

    // CREATE
    @RequestMapping(value = "/dailymenu", method = RequestMethod.POST)
    public ResponseEntity<Object> createDailyMenu(@RequestBody DailyMenu dailyMenu) {
        Integer errorCode = dailyMenuService.createDailyMenu(dailyMenu);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Daily menu: #" + dailyMenu.getDailyMenuId() + " created.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + dailyMenu.getDailyMenuId() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // READ
    @RequestMapping(value = "/dailymenu", method = RequestMethod.GET)
    public ResponseEntity<Object> getDailyMenus() {
        return new ResponseEntity<>(dailyMenuService.getDailyMenu(), HttpStatus.OK);
    }

    @RequestMapping(value = "/dailymenu/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getDailyMenu(@PathVariable("id") int id) {
        DailyMenu dailyMenu = dailyMenuService.getDailyMenu(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (dailyMenu != null) {
            httpStatus = HttpStatus.OK;
            return new ResponseEntity<>(dailyMenu, httpStatus);
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
            return new ResponseEntity<>(new Message(errorMessage), httpStatus);
        }
    }

    // UPDATE
    @RequestMapping(value = "/dailymenu", method = RequestMethod.PUT)
    public ResponseEntity<Object> updateDailyMenu(@RequestBody DailyMenu dailyMenu) {
        Integer errorCode = dailyMenuService.updateDailyMenu(dailyMenu);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Daily menu: #" + dailyMenu.getDailyMenuId() + " updated.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + dailyMenu.getDailyMenuId() + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }

    // DELETE
    @RequestMapping(value = "/dailymenu/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> deleteDailyMenu(@PathVariable("id") int id) {
        Integer errorCode = dailyMenuService.deleteDailyMenu(id);
        String errorMessage;
        HttpStatus httpStatus;

        if (errorCode == 0) {
            httpStatus = HttpStatus.OK;
            errorMessage = "Daily menu: #" + id + " deleted.";
        } else {
            httpStatus = HttpStatus.BAD_REQUEST;
            errorMessage = "Error with: #" + id + ".";
        }

        return new ResponseEntity<>(new Message(errorMessage), httpStatus);
    }
}
