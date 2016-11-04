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

    public Path getWebmPathById(String id) {
        long webmId = Long.parseLong(id);
        Webm webm = repository.getWebmById(webmId);
        if(webm!=null) {
            return Paths.get(webm.getPath());
        }
        return null;
    }

    public byte[] getImage(String id) {
        long webmId = Long.parseLong(id);
        Webm webm = repository.getWebmById(webmId);
        if(webm!=null){
            return webm.getImage();
        }
        return new byte[0];
    }

    public Object getWebmPathByPath(String path) {
        Webm webm = repository.getWebmByPath(path);
        if(webm!=null) {
            return Paths.get(webm.getPath());
        }
        return null;
    }
}
