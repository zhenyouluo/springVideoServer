package com.khudim.controllers;

import com.khudim.scanner.FileScanner;
import com.khudim.users.User;
import com.khudim.users.UserService;
import com.khudim.webm.Webm;
import com.khudim.webm.WebmService;
import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @RequestMapping(value = "/video/{webmPath}", method = RequestMethod.GET)
    public void getVideo(@PathVariable String webmPath, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String range = request.getHeader("Range");
        int start = parseStartRange(range);
        int emd = parseStopRange(range);

            try (OutputStream oos = response.getOutputStream()) {
                byte[] buf = new byte[8192];
                int c;
                try (FileInputStream inputStream = new FileInputStream(webmService.getWebmPath(webmPath + ".webm").toFile())) {
                   // response.setContentType("video/webm");
                    response.setContentLength(inputStream.available());
                    while ((c = inputStream.read(buf, 0, buf.length)) > 0) {
                        oos.write(buf, 0, c);
                        oos.flush();
                    }
                }
            }
    }
/*
    @RequestMapping(value = "/video/{webmPath}", produces = "video/webm")
    public ResponseEntity<byte[]> getVideo(@PathVariable String webmPath, HttpServletResponse response) throws IOException {
        byte[] b;
        try (ByteArrayOutputStream oos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[8192];
            int c;
            try (FileInputStream inputStream = new FileInputStream(webmService.getWebmPath(webmPath + ".webm").toFile())) {
                response.setContentType("video/webm");
                response.setContentLength(inputStream.available());
                while ((c = inputStream.read(buf, 0, buf.length)) > 0) {
                    oos.write(buf, 0, c);
                    oos.flush();
                }
                b = oos.toByteArray();
            }
        }
        return new ResponseEntity<>(b, HttpStatus.OK);
    }*/

    @RequestMapping(value = "/img/{webmPath}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String webmPath, HttpServletResponse response) throws IOException {
        byte[] imageBytes = webmService.getImage(webmPath + ".webm");
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

    private int parseStopRange(String range) {
        return 0;
    }

    private int parseStartRange(String range) {
        return 0;
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
