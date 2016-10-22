package com.khudim.scanner;

import com.khudim.webm.Webm;
import com.khudim.webm.WebmService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beaver.
 */

public class FileScanner {

    private static String dir = "/tmp";

    private WebmService webmService;


    @Autowired
    public FileScanner(WebmService webmService) {
        this.webmService = webmService;
    }

    public void addWebmToBase() {
        List<Path> paths = searchVideo();
        Webm webm;
        for(Path path : paths){
            webm = new Webm();
            webm.setPath(path.toString());
            webm.setDate(System.currentTimeMillis());
            webm.setName(path.getFileName().toString());
            webmService.add(webm);
        }
    }


    public  List<Path> searchVideo(){
        File fileDir = new File(dir);
        File[] files = fileDir.listFiles();
        List<Path> webmPaths = new ArrayList<>();
        if(files!=null) {
            for (File file : files) {
                if (file.getName().endsWith(".webm")) {
                    try {
                        webmPaths.add(Paths.get(file.getCanonicalPath()));
                        System.out.println(file.getCanonicalPath());
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
        return webmPaths;
    }

}
