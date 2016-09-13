package com.dtrajko.zoo.utils;

import com.squareup.otto.Bus;

/**
 * Created by Dejan Trajkovic on 9/13/2016.
 */
public class EventBus extends Bus {
    private static final EventBus bus = new EventBus();

    public static Bus getInstance() { return bus; };

    public EventBus() {};

}
