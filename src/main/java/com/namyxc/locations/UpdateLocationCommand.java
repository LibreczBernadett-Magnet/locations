package com.namyxc.locations;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateLocationCommand {

    @Schema(description = "name of location", example = "New York")
    @NotBlank(message = "Name can not be blank")
    private String name;

    @Schema(description = "latitude of location", example = "40.730610")
    @Coordinate(type = CoordinateType.LAT)
    private double lat;

    @Schema(description = "longitude of location", example = "-73.935242")
    @Coordinate(type = CoordinateType.LON)
    private double lon;
}
