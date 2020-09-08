package com;

public class Demo {
    
    public static void main(String[] args) {
        final long ONE_MINUTE = 60000;
        double time = Double.valueOf("8.5");
        long milliSeconds = (long) (time * ONE_MINUTE);
        
        System.out.println("Each Playlist will run for - " + (((float)milliSeconds)/ONE_MINUTE));
    }
}
