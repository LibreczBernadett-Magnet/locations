package com.namyxc.locations;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateLocationCommand {

    @Schema(description = "name of location", example = "New York")
    private String name;

    @Schema(description = "latitude of location", example = "40.730610")
    private double lat;

    @Schema(description = "longitude of location", example = "-73.935242")
    private double lon;
}
