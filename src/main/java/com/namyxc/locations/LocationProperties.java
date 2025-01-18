package com.namyxc.locations;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "locations")
public class LocationProperties {

    private boolean touppercase;
}
