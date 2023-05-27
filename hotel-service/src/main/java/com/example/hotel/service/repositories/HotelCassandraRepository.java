package com.example.hotel.service.repositories;

import com.example.hotel.service.entities.HotelCassandra;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelCassandraRepository extends CassandraRepository<HotelCassandra, String> {

}
