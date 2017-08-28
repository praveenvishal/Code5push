package org.code5.code5push.push;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by User on 8/28/2017.
 */

public class NotificationID {
    private final static AtomicInteger c = new AtomicInteger(0);
    public static int getID() {
        return c.incrementAndGet();
    }
}