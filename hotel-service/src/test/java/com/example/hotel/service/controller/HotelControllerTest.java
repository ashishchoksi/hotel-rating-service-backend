package com.example.hotel.service.controller;

import com.example.hotel.service.controllers.HotelController;
import com.example.hotel.service.entities.HotelCassandra;
import com.example.hotel.service.services.impl.HotelServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(HotelController.class)
@ExtendWith(MockitoExtension.class)
// below is to use for point different yml file
@TestPropertySource(properties = { "spring.config.location=classpath:application-test.yml" })
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HotelServiceImpl hotelService;

    private final ObjectMapper mapper = new ObjectMapper();

    @Test
    public void testCreateHotel() throws Exception {
        HotelCassandra hotel = createHotelRequest();
        when(hotelService.save(hotel)).thenReturn(hotel);
        MvcResult mvcResult = mockMvc.perform(
                post("/hotels")
                        .content(mapper.writeValueAsString(hotel))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();
        verify(hotelService).save(hotel);
        assertEquals(HttpStatus.CREATED.value(), mvcResult.getResponse().getStatus());
        assertEquals(mapper.writeValueAsString(hotel), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetHotelById() throws Exception {
        HotelCassandra hotel = createHotelRequest();
        String hotelId = "1234";
        when(hotelService.getHotel(hotelId)).thenReturn(hotel);

        MvcResult mvcResult = mockMvc.perform(
                get("/hotels/{hotelId}", hotelId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();

        verify(hotelService).getHotel(hotelId);
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(mapper.writeValueAsString(hotel), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testGetAllHotels() throws Exception {
        List<HotelCassandra> hotels = ImmutableList.of(createHotelRequest());
        when(hotelService.getAllHotels()).thenReturn(hotels);

        MvcResult mvcResult = mockMvc.perform(
                get("/hotels")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        ).andDo(print()).andReturn();

        verify(hotelService).getAllHotels();
        assertEquals(HttpStatus.OK.value(), mvcResult.getResponse().getStatus());
        assertEquals(mapper.writeValueAsString(hotels), mvcResult.getResponse().getContentAsString());
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
