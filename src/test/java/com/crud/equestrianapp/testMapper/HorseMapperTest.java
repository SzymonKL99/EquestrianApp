package com.crud.equestrianapp.testMapper;

import com.crud.equestrianapp.domain.Horse;
import com.crud.equestrianapp.service.dto.HorseDto;
import com.crud.equestrianapp.service.mapper.HorseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class HorseMapperTest {

    @Autowired
    HorseMapper horseMapper;

    @Test
    void testMapToHorse() {
        // Given
        HorseDto horseDto = new HorseDto(1L, "Storm", "Storm is a beautiful horse");

        // When
        Horse horse = horseMapper.mapToHorse(horseDto);

        // Then
        assertEquals(horseDto.getId(), horse.getId());
        assertEquals(horseDto.getName(), horse.getName());
        assertEquals(horseDto.getDescription(), horse.getDescription());
    }

    @Test
    void testMapToHorseDto() {
        // Given
        Horse horse = new Horse(1L, "Storm", "Storm is a beautiful horse");

        // When
        HorseDto horseDto = horseMapper.mapToHorseDto(horse);

        // Then
        assertEquals(horse.getId(), horseDto.getId());
        assertEquals(horse.getName(), horseDto.getName());
        assertEquals(horse.getDescription(), horseDto.getDescription());
    }

    @Test
    void testMapToHorseList() {
        // Given
        List<HorseDto> horseDtoList = Arrays.asList(
                new HorseDto(1L, "Storm", "Storm is a beautiful horse"),
                new HorseDto(2L, "Spirit", "Spirit is a powerful horse")
        );

        // When
        List<Horse> horseList = horseMapper.mapToHorseList(horseDtoList);

        // Then
        assertEquals(horseDtoList.size(), horseList.size());
        for (int i = 0; i < horseDtoList.size(); i++) {
            assertEquals(horseDtoList.get(i).getId(), horseList.get(i).getId());
            assertEquals(horseDtoList.get(i).getName(), horseList.get(i).getName());
            assertEquals(horseDtoList.get(i).getDescription(), horseList.get(i).getDescription());
        }
    }

    @Test
    void testMapToHorseDtoList() {
        // Given
        List<Horse> horseList = Arrays.asList(
                new Horse(1L, "Storm", "Storm is a beautiful horse"),
                new Horse(2L, "Spirit", "Spirit is a powerful horse")
        );

        // When
        List<HorseDto> horseDtoList = horseMapper.mapToHorseDtoList(horseList);

        // Then
        assertEquals(horseList.size(), horseDtoList.size());
        for (int i = 0; i < horseList.size(); i++) {
            assertEquals(horseList.get(i).getId(), horseDtoList.get(i).getId());
            assertEquals(horseList.get(i).getName(), horseDtoList.get(i).getName());
            assertEquals(horseList.get(i).getDescription(), horseDtoList.get(i).getDescription());
        }
    }
}
