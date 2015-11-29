package com.ocunha.election.rest;

import org.glassfish.jersey.server.ResourceConfig;

/**
 * Created by osnircunha on 8/13/15.
 */
public class WebApplication extends ResourceConfig {

    public WebApplication(){
        packages("com.ocunha.election.rest");
    }

}
