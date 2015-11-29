package com.ocunha.election.rest;

import com.ocunha.election.annotation.Filter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Created by osnircunha on 11/28/15.
 */
@Filter
@Provider
public class Security implements ContainerRequestFilter, ContainerResponseFilter {
    Logger LOGGER = LoggerFactory.getLogger(Security.class);

    /**
     *
     * <code>ContainerRequestFilter</code>
     *
     * @param requestContext
     * @throws IOException
     */
    public void filter(ContainerRequestContext requestContext) throws IOException {
        for(String s: requestContext.getHeaders().keySet()){
            for(String v : requestContext.getHeaders().get(s)) {
                LOGGER.debug(v);
            }
        }
        if(requestContext.getSecurityContext() != null ) {
            LOGGER.debug("SecurityContext:\n{}", requestContext.getSecurityContext().getAuthenticationScheme());
        }
    }

    /**
     *
     * <code>ContainerResponseFilter</code>
     *
     * @param requestContext
     * @param responseContext
     * @throws IOException
     */
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) throws IOException {
        LOGGER.debug("======= Response Intercepted ========");
    }
}
