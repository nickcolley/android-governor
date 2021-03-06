package com.governorapp.server;

import android.content.Context;

import java.io.IOException;

import fi.iki.elonen.NanoHTTPD;
import com.governorapp.config.Configuration;
import com.governorapp.config.Route;

/**
 * Governor HTTP server.
 */
public class Server extends NanoHTTPD {
    protected Configuration config;
    protected Context appContext;

    public Server(Context context, Configuration config) throws IOException {
        super(config.getPort());

        this.appContext = context;
        this.config = config;
    }

    @Override
    public Response serve(IHTTPSession session) {
        String path = session.getUri();
        Response response;

        try {
            Route route = config.getRoute(path);
            response = route.getResponse(appContext, config, session);
        } catch (IOException e) {
            response = new Response(Response.Status.NOT_FOUND, MIME_PLAINTEXT, "404 Not Found");
        }

        if (config.getEnableCors()) {
            response.addHeader("Access-Control-Allow-Methods", "DELETE, GET, POST, PUT");
            response.addHeader("Access-Control-Allow-Origin",  "*");
            response.addHeader("Access-Control-Allow-Headers", "X-Requested-With");
        }

        return response;
    }
}
