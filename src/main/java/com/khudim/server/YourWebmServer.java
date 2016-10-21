package com.khudim.server;

import com.khudim.security.FormLoginSecurityConfig;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.session.HashSessionIdManager;
import org.eclipse.jetty.server.session.HashSessionManager;
import org.eclipse.jetty.server.session.SessionHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;


import javax.servlet.ServletContext;


/**
 * Created by Beaver.
 */
@Service
public class YourWebmServer implements Application {

    private static final Logger logger = LoggerFactory.getLogger(YourWebmServer.class);

    private static final String webAppCfgPath = "file:src/main/webapp/WEB-INF/web-config.xml";

    private ApplicationContext appContext;

    public YourWebmServer() {
    }

    @Override
    public void init() throws Exception {
        this.startServer();
    }

    private void startServer() {
        Server server = appContext.getBean("server", org.eclipse.jetty.server.Server.class);
        HashSessionIdManager idManager = new HashSessionIdManager();
        server.setSessionIdManager(idManager);

        HashSessionManager manager = new HashSessionManager();
        SessionHandler sessionHandler = new SessionHandler(manager);

        Handler[] handlers = server.getChildHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof ServletContextHandler) {

                ServletContextHandler servletContextHandler = (ServletContextHandler) handler;
                servletContextHandler.setSessionHandler(sessionHandler);
                ServletContext servletContext = servletContextHandler.getServletContext();
                servletContextHandler.setResourceBase(".");

                servletContext.addFilter("springSecurityFilterChain", new DelegatingFilterProxy("springSecurityFilterChain"))
                        .addMappingForUrlPatterns(null, false, "/*");
                AnnotationConfigWebApplicationContext annotationConfigWebApplicationContext = new AnnotationConfigWebApplicationContext();
                annotationConfigWebApplicationContext.register(FormLoginSecurityConfig.class);
                annotationConfigWebApplicationContext.setParent(appContext);
                annotationConfigWebApplicationContext.refresh();
                XmlWebApplicationContext webAppContext = new XmlWebApplicationContext();
                webAppContext.setParent(annotationConfigWebApplicationContext);
                webAppContext.setConfigLocation(webAppCfgPath);
                webAppContext.setServletContext(servletContext);

                webAppContext.refresh();
                servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, webAppContext);
            }
        }

        try {
            server.start();
        } catch (Exception exc) {
            logger.error("Can`t start server. ", exc);
        }
    }

    @Override
    public void shutdown() {
        this.stopServer();
        this.closeAppContext();
    }

    private void stopServer() {
        Server server = appContext.getBean("server", org.eclipse.jetty.server.Server.class);

        try {
            server.stop();
        } catch (Exception exc) {
            logger.warn("Can`t stop server.", exc);
        }
    }

    private void closeAppContext() {
        if (appContext instanceof ConfigurableApplicationContext) {
            ((ConfigurableApplicationContext) appContext).close();
        }
    }

    @Override
    public void setAppContext(ApplicationContext appContext) {
        this.appContext = appContext;
    }

    @Override
    public ApplicationContext getAppContext() {
        return appContext;
    }
}


