package com.khudim.controllers;

import com.khudim.main.Users;
import com.khudim.main.Webm;
import com.khudim.services.UserService;
import com.khudim.services.WebmService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;


@Controller
@RequestMapping("/main")
public class MainController {

    protected static Logger logger = Logger.getLogger("controller");

    @Resource(name = "userService")
    private UserService userService;

    @Resource(name = "webmService")
    private WebmService webmService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String getUsers(Model model) {
        logger.debug("Received request to show all persons");

        List<Users> users = userService.getAll();
        List<Webm> webms = webmService.getAll();
        model.addAttribute("users", users);
        model.addAttribute("webms", webms);

        return "usersPage";
    }
    @RequestMapping(value = "/webm", method = RequestMethod.GET)
    public String getWebms(Model model) {
        logger.debug("Received request to show all webms");

        List<Webm> webms = webmService.getAll();

        model.addAttribute("webms", webms);
        return "webmPage";
    }


    @RequestMapping(value = "/users/add", method = RequestMethod.GET)
    public String getAdd(Model model) {
        logger.debug("Adding new User");

        model.addAttribute("userAttribute", new Users());

        return "addPage";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String add(@ModelAttribute("userAttribute") Users users) {
        logger.debug("Received request to add new Users");

        userService.add(users);

        return "addedPage";
    }

    @RequestMapping(value = "/users/delete", method = RequestMethod.GET)
    public String delete(@RequestParam(value = "id", required = true) int id, Model model) {
        logger.debug("Received request to delete existing person");
        userService.delete(id);
        model.addAttribute("id", id);

        return "deletedpage";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.GET)
    public String getEdit(@RequestParam(value = "id", required = true) int id, Model model) {
        logger.debug("Received request to edit existing person");
        model.addAttribute("userAttribute", userService.get(id));
        return "editPage";
    }

    @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
    public String saveEdit(@ModelAttribute("userAttribute")
                           Users users, @RequestParam(value = "id", required = true) int id, Model model) {

        users.setId(id);
        userService.edit(users);
        model.addAttribute("id", id);
        return "editedPage";

    }


}
