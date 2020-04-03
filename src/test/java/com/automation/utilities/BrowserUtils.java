package com.automation.utilities;

public class BrowserUtils {
    /**
     * Pause test for some time
     * @param second
     */

    public static void wait (int second) {
        try{
            Thread.sleep(1000*second);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
