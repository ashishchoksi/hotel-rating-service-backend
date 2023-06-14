package com.example.hotel.service.service;

import com.example.hotel.service.entities.HotelCassandra;
import com.example.hotel.service.exceptions.ResourceNotFoundException;
import com.example.hotel.service.repositories.HotelCassandraRepository;
import com.example.hotel.service.services.impl.HotelServiceImpl;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

// below is to enable @Mock & @InjectMock
@ExtendWith(MockitoExtension.class)
public class HotelServiceTest {
    @Mock
    private HotelCassandraRepository hotelRepository;

    /*
    One way is to use @InjectMock that will inject all the @Mocks in this class and we actually test this class
    Other way is to create Object of this class with all mocks pass as constructor argument
     */
    @InjectMocks
    private HotelServiceImpl hotelService;

    @Test
    public void testSaveHotel() {
        HotelCassandra request = createHotelRequest();
        when(hotelRepository.save(request)).thenReturn(request);
        HotelCassandra response = hotelService.save(request);
        verify(hotelRepository).save(request);
        assertEquals(request, response);
    }

    @Test
    public void testGetAllHotels() {
        List<HotelCassandra> hotelsRequest = ImmutableList.of(createHotelRequest());
        when(hotelRepository.findAll()).thenReturn(hotelsRequest);
        List<HotelCassandra> hotelsResponse = hotelService.getAllHotels();
        verify(hotelRepository).findAll();
        assertEquals(hotelsRequest, hotelsResponse);
    }

    @Test
    public void testValidGetHotelById() {
        String hotelId = "123";
        HotelCassandra request = createHotelRequest();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.of(request));
        HotelCassandra response = hotelService.getHotel(hotelId);
        verify(hotelRepository).findById(hotelId);
        assertEquals(request, response);
    }

    @Test
    public void testInvalidGetHotelById() {
        String hotelId = "123";
        HotelCassandra request = createHotelRequest();
        when(hotelRepository.findById(hotelId)).thenReturn(Optional.empty());
        ResourceNotFoundException resourceNotFoundException = assertThrows(ResourceNotFoundException.class, () -> hotelService.getHotel(hotelId));
        verify(hotelRepository).findById(hotelId);
        assertEquals(resourceNotFoundException.getMessage(), String.format("No hotel found for id: %s", hotelId));
    }

    private HotelCassandra createHotelRequest() {
        HotelCassandra hotel = new HotelCassandra();
        hotel.setHotelId("test");
        hotel.setAbout("good hotel");
        hotel.setLocation("surat");
        hotel.setName("yuvraj");
        return hotel;
    }
}
