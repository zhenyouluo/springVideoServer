package com.khudim.server;

import org.springframework.context.ApplicationContext;

/**
 * Created by Beaver.
 */
public interface Application {

    void setAppContext(ApplicationContext appContext);

    ApplicationContext getAppContext();

    void shutdown();

    void init() throws Exception;
}
