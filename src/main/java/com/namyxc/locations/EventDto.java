package com.namyxc.locations;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EventDto {
    private int id;
    private String source;
    private String datetime;
    private String message;
}
