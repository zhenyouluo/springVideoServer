package com.khudim;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;


public class Dimension {

    private BufferedImage bimg;
    private int height;
    private int width;



    public Dimension() {

    }

    public void getPath(URL url){
        File file = new File(url.getPath());
        try {
            bimg = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.width = bimg.getWidth();
        this.height = bimg.getHeight();
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
}
