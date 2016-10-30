package com.khudim.scanner;

import com.khudim.webm.Webm;
import com.khudim.webm.WebmService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Beaver.
 */
public class FileScanner {

    private String directory = "D:/";

    @Autowired
    private WebmService webmService;

    public void addWebmToBase() {
        List<Path> paths = searchVideo();
        Webm webm;
        int i = 1;
        for (Path path : paths) {
            if (webmService.getWebmPath(path.toString()) == null) {
                int[] videoSize = findVideoSize(path.toString());
                byte[] image = getImageFromVideo(path);
                webm = new Webm();
                webm.setId(i++);
                webm.setPath(path.toString());
                webm.setDate(System.currentTimeMillis());
                webm.setName(path.getFileName().toString());
                webm.setWidth(videoSize[0]);
                webm.setHeight(videoSize[1]);
                webm.setImage(image);
                webmService.add(webm);
            }
        }
    }

    public byte[] getImageFromVideo(Path path) {
        Path tempFile = null;
        Process process = null;
        try {
            tempFile = Files.createTempFile("temp", ".png");
            String[] command = new String[]{
                    "ffmpeg",
                    "-ss",
                    "00:00:03",
                    "-i",
                    path.toString(),
                    "-vframes",
                    "1",
                    tempFile.toString(),
                    "-y"
            };
            process = new ProcessBuilder(Arrays.asList(command)).start();
            process.waitFor();
            return Files.readAllBytes(tempFile);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
            deleteFile(tempFile);
        }
        return new byte[0];
    }

    private void deleteFile(Path tempFile) {
        try {
            if (tempFile != null) {
                Files.delete(tempFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<Path> searchVideo() {
        File fileDir = new File(directory);
        File[] files = fileDir.listFiles();
        List<Path> webmPaths = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.getName().endsWith(".webm")) {
                    try {
                        webmPaths.add(Paths.get(file.getCanonicalPath()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return webmPaths;
    }

    public int[] findVideoSize(String videoPath) {
        Process pb = null;
        String[] command = new String[]{"ffprobe",
                "-v",
                "error",
                "-show_entries",
                "stream=width,height",
                "-of",
                "default=noprint_wrappers=1",
                videoPath};
        int width = 0;
        int height = 0;
        try {
            pb = new ProcessBuilder(Arrays.asList(command)).start();
            pb.waitFor();
            String[] output = output(pb.getInputStream());
            width = Integer.parseInt(output[0].split("=")[1]);
            height = Integer.parseInt(output[1].split("=")[1]);
            System.out.println(width + " " + height);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(pb!=null){
                pb.destroy();
            }
        }
        return new int[]{width, height};
    }

    private static String[] output(InputStream inputStream) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
            return sb.toString().split("\n");
        }
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public WebmService getWebmService() {
        return webmService;
    }

    public void setWebmService(WebmService webmService) {
        this.webmService = webmService;
    }
}
