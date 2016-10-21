package com.khudim.controllers;


import com.khudim.users.File;
import com.khudim.webm.Webm;
import com.khudim.webm.WebmService;
import com.khudim.validators.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;

@Controller
@RequestMapping("/file")
public class FileController {

    @Autowired
    FileValidator validator;

    @Autowired
    WebmService webmService;

    @RequestMapping(method = RequestMethod.GET)
    public String getForm(Model model) {
        File fileModel = new File();
        model.addAttribute("file", fileModel);
        return "file";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String fileUploaded(Model model, File file, BindingResult result) {
        String name = "";
        String returnVal = "successFile";
        if (result.hasErrors()) {
            returnVal = "file";
        } else {
            MultipartFile multipartFile = file.getFile();
            name = multipartFile.getOriginalFilename();
            Webm webm = new Webm();
            webm.setDate(System.currentTimeMillis());
            try {
                byte[] bytes = multipartFile.getBytes();
                String rootPath = "D:\\JavaProjects\\spring-hibernate-mysql\\src\\main\\webapp\\resources";
                java.io.File dir = new java.io.File(rootPath + java.io.File.separator + "webmFiles");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                java.io.File loadFile = new java.io.File(dir.getAbsolutePath() + java.io.File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(loadFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                webm.setPath(loadFile.getAbsolutePath());
                webm.setName(name);
                webm.setImage(multipartFile.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            webmService.add(webm);
        }
        model.addAttribute("fileName", name);
        return returnVal;
    }
}