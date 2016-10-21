package com.khudim.webm;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@Transactional
public class WebmService {

    @Autowired
    private WebmRepository repository;

    public WebmService() {
    }

    public List<Webm> getAll(){
        return repository.getAllWebm();
    }

    public void add(Webm webm){
        System.out.println("add "+ webm);
       repository.addWebm(webm);
    }

    public Path getWebmPath(String name){
        Webm webm = repository.getWebmPath(name);
        return Paths.get(webm.getPath());
    }

    public Webm get(int id){
        return repository.getWebm(id);
    }
}
