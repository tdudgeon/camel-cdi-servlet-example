package org.squonk.example;

import javax.enterprise.inject.Default;

/**
 * Created by timbo on 21/02/2016.
 */
@Default
public class MyBean {

    public String hello(String something) {
        return "Hello " + something;
    }

}
