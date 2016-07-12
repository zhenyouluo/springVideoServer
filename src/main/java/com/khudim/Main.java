package com.khudim;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Beaver on 10.07.2016.
 */
public class Main {


    public static void main(String[] args) throws IOException {
        BufferedImage bimg = ImageIO.read(new File("D:\\JavaProjects\\spring-hibernate-mysql\\src\\main\\webapp\\resources\\7.jpg"));
        int width          = bimg.getWidth();
        int height         = bimg.getHeight();

        System.out.println(height + "  " + width);
    }
}
