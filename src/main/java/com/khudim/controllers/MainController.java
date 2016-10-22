package com.khudim.controllers;

import com.khudim.scanner.FileScanner;
import com.khudim.users.User;
import com.khudim.users.UserService;
import com.khudim.webm.Webm;
import com.khudim.webm.WebmService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;


@Controller
@RequestMapping("/")
public class MainController {

    private static Logger logger = Logger.getLogger("controller");

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "webmService")
    private WebmService webmService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        logger.debug("Received request to show all persons");
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "usersPage";
    }
    @RequestMapping(value = "/video/{webmName}",method = RequestMethod.GET)
    public String getVideo(@PathVariable String webmName, HttpServletResponse response) throws IOException {
        OutputStream oos = response.getOutputStream();
        byte[] buf = new byte[8192];
        int c;
        try(FileInputStream inputStream = new FileInputStream(webmService.getWebmPath(webmName+".webm").toFile())){
            response.setContentType("audio/webm");
            response.setContentLength(inputStream.available());
            while ((c = inputStream.read(buf, 0, buf.length)) > 0) {
                oos.write(buf, 0, c);
                oos.flush();
            }
        }
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWebms(Model model) {
        logger.debug("Received request to show all webms");
        List<Webm> webms = webmService.getAll();
        model.addAttribute("webms", webms);
        return "index";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Adding new User");
        model.addAttribute("userAttribute", new User());
        return "addPage";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("userAttribute") User users) {
        logger.debug("Received request to add new User");
        userService.add(users);
        return "addedPage";
    }
    
}
