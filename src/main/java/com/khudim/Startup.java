package com.khudim;

import com.khudim.server.Application;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.UUID;
import java.util.concurrent.*;

/**
 * Created by Beaver.
 */
public class Startup {
    private static final Logger logger = LoggerFactory.getLogger(Startup.class);


    private static final String appCfgPath = "src/main/webapp/WEB-INF/yourwebm-config.xml";

    private static final String applicationBeanName = "application";
    private static final String appName = "yourWebm";

    private final CountDownLatch shutdownLatch = new CountDownLatch(1);

    private ScheduledExecutorService cmdService = null;
    private ExecutorService executor = null;

    public static void main(String[] args) throws IOException {
        new Startup().runApp();
    }

    private void runApp() {
        Application application = init();
        waitShutdown();
        shutdown(application);
    }

    private Application init() {

        ApplicationContext appContext = new FileSystemXmlApplicationContext(appCfgPath);

        Application application = appContext.getBean(applicationBeanName, Application.class);
        application.setAppContext(appContext);

        try {
            application.init();
        } catch (Exception exc) {
            logger.error("Can`t run application: " + appName, exc);
        }

        return application;
    }

    private void waitShutdown() {
        try {
            shutdownLatch.await();
        } catch (Exception exc) {
            logger.error("Shutdown waiting interrupted. ", exc);
        }
    }

    private void shutdown(Application application) {
        try {
            application.shutdown();
        } catch (Exception exc) {
            logger.warn("Can`t shutdown application.", exc);
        }

        if (executor != null) {
            executor.shutdown();
        }

        if (cmdService != null) {
            cmdService.shutdown();
        }
    }
}
