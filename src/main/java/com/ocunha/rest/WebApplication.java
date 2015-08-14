package com.ocunha.rest;

import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ApplicationPath;

/**
 * Created by osnircunha on 8/13/15.
 */
@ApplicationPath("/rest")
public class WebApplication extends ResourceConfig {

    public WebApplication(){
        packages("com.ocunha.rest");
    }

}
