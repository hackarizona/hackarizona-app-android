package com.hackarizona.hackaz.events;

/**
 * Created by Emily on 12/10/2017.
 */

public class Event {
    private String eventName;
    private String sponsorName; //only used for tech talks
    private String time;
    private String location;
    private String description;

    public Event(String eventName,String sponsorName,String time,
                 String location, String description){
        this.eventName = eventName;
        this.sponsorName = sponsorName;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public String getName(){
        String ref  = eventName;
        return ref;
    }

    public String getSponsorName(){
        String ref  = sponsorName;
        return ref;
    }

    public String getTime(){
        String ref  = time;
        return ref;
    }

    public String getLocation(){
        String ref  = location;
        return ref;
    }

    public String getDescription(){
        String ref  = description;
        return ref;
    }

}
