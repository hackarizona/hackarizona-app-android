package com.example.hackaz.schedule;

/**
 * Created by Emily on 12/10/2017.
 */

public class ScheduleEvent {
    private String eventName;
    private String eventType; //only used for tech talks
    private String time;
    private String location;
    private String description;

    public ScheduleEvent(String eventName, String eventType, String time,
                         String location, String description){
        this.eventName = eventName;
        this.eventType = eventType;
        this.time = time;
        this.location = location;
        this.description = description;
    }

    public String getName(){
        String ref  = eventName;
        return ref;
    }

    public String getEventType(){
        String ref  = eventType;
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
