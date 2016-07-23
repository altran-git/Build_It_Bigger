/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package com.a2g.nd.builditbigger.jokebackend;

import com.a2g.nd.jokesupplier.Joker;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.Random;

/** An endpoint class we are exposing */
@Api(
  name = "myJokeApi",
  version = "v1",
  namespace = @ApiNamespace(
    ownerDomain = "jokebackend.builditbigger.nd.a2g.com",
    ownerName = "jokebackend.builditbigger.nd.a2g.com",
    packagePath=""
  )
)
public class MyJokeEndpoint {

    /** A simple endpoint method that gets a joke */
    @ApiMethod(name = "getAJoke")
    public MyJoke getAJoke() {
        Joker joker = new Joker();
        String aJoke = joker.getRandomJoke(new Random().nextInt(1000));

        MyJoke response = new MyJoke();
        response.setJoke(aJoke);

       return response;
    }

}
