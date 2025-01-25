package com.namyxc.locations;

import lombok.Data;

@Data
public class EventDto {
    private int id;
    private String source;
    private String datetime;
    private String message;
}
