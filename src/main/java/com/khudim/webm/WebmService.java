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

    public List<Webm> getAll() {
        return repository.getAllWebm();
    }

    public void add(Webm webm) {
        repository.addWebm(webm);
    }

    public Path getWebmPath(String path) {
        Webm webm = repository.getWebmPath(path);
        if(webm!=null) {
            return Paths.get(webm.getPath());
        }
        return null;
    }

    public Webm get(int id) {
        return repository.getWebm(id);
    }

    public byte[] getImage(String webmPath) {
        Webm webm = repository.getWebmPath(webmPath);
        if(webm!=null){
            return webm.getImage();
        }
        return new byte[0];
    }
}
