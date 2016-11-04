package com.khudim.controllers;

import com.khudim.users.User;
import com.khudim.users.UserService;
import com.khudim.webm.Webm;
import com.khudim.webm.WebmService;
import org.apache.commons.lang.StringUtils;
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
import java.nio.file.Path;
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

    @RequestMapping(value = "/video/{webmId}", method = RequestMethod.GET)
    public void getVideo(@PathVariable String webmId, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String range = request.getHeader("Range");
        Path filePath = webmService.getWebmPathById(webmId);
        OutputStream oos = response.getOutputStream();
        response.setContentType("video/webm");
        byte[] buf = new byte[8192];
//        if (StringUtils.isEmpty(range)) {
        int c;
        try (FileInputStream inputStream = new FileInputStream(filePath.toFile())) {
            response.setContentLength((int) filePath.toFile().length());
            while ((c = inputStream.read(buf, 0, buf.length)) > 0) {
                oos.write(buf, 0, c);
            }
        }
    /*    } else {
            long start = parseRange(range, "start");
            long stop = parseRange(range, "stop");
            if (stop == 0) {
                stop = filePath.toFile().length();
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try (FileInputStream inputStream = new FileInputStream(filePath.toFile())) {
                if (start != 0) {
                    RandomAccessFile rnd = new RandomAccessFile(filePath.toFile(), "r");
                    rnd.seek(start);
                    while (byteArrayOutputStream.size() <= stop - start) {
                        byteArrayOutputStream.write(rnd.read(buf));
                    }
                } else {
                    while (byteArrayOutputStream.size() <= stop - start) {
                        byteArrayOutputStream.write(inputStream.read(buf));
                    }
                }
            }
            response.setContentLength(byteArrayOutputStream.size());
            oos.write(byteArrayOutputStream.toByteArray());
        }*/
    }
/*
    @RequestMapping(value = "/video/{webmPath}", produces = "video/webm")
    public ResponseEntity<byte[]> getVideo(@PathVariable String webmPath, HttpServletResponse response) throws IOException {
        byte[] b;
        try (ByteArrayOutputStream oos = new ByteArrayOutputStream()) {
            byte[] buf = new byte[8192];
            int c;
            try (FileInputStream inputStream = new FileInputStream(webmService.getWebmById(webmPath + ".webm").toFile())) {
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

    @RequestMapping(value = "/img/{webmId}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable String webmId, HttpServletResponse response) throws IOException {
        byte[] imageBytes = webmService.getImage(webmId);
        response.setContentType(MediaType.IMAGE_PNG_VALUE);
        response.setContentLength(imageBytes.length);
        return new ResponseEntity<>(imageBytes, HttpStatus.OK);
    }

/*    private long parseRange(String range, String part) {
        String[] splitedRange = range.split("=");
        String rangePart = splitedRange[1];
        String[] startStopRange = rangePart.split("-");
        if (part.equals("start")) {
            return Integer.parseInt(startStopRange[0]);
        } else if (part.equals("stop") && startStopRange.length > 1) {
            return Integer.parseInt(startStopRange[1]);
        }
        return 0;
    }*/

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getWebms(Model model) {
        logger.debug("Received request to show all webms");
        List<Webm> webms = webmService.getAll();
        model.addAttribute("webms", webms);
        model.addAttribute("webm", webms.size());
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
