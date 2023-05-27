package com.example.hotel.service.entities;

import lombok.Data;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("hotel")
@Data
public class HotelCassandra {
    @PrimaryKey
    private String hotelId;
    private String name;
    private String location;
    private String about;
}
