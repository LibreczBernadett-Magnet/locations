package com.namyxc.locations;

import com.namyxc.locations.dtos.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationsRepository extends JpaRepository<Location, Long> {
}
