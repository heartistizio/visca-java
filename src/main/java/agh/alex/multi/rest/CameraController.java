package agh.alex.multi.rest;

import agh.alex.multi.Main;
import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@CrossOrigin("*")
@Controller
public class CameraController {
    @RequestMapping(value = "/control", method = RequestMethod.POST, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody ArrayList<String> controlCamera(@RequestBody String command) {
        System.out.println(command);
        Main.out.add("Command:" + command);
        Main.translateCommand(command);
        return Main.out;
    }



    @RequestMapping(value = "/control", method = RequestMethod.GET, produces = {"application/json"})
    @ResponseStatus(HttpStatus.OK)
    public @ResponseBody String controlCamera() {
        return new Gson().toJson(Main.out);
    }
}
