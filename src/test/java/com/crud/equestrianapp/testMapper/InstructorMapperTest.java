package com.crud.equestrianapp.testMapper;

import com.crud.equestrianapp.domain.Instructor;
import com.crud.equestrianapp.service.dto.InstructorDto;
import com.crud.equestrianapp.service.mapper.InstructorMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class InstructorMapperTest {

    @Autowired
    InstructorMapper instructorMapper;

    @Test
    void testMapToInstructor() {
        //Given
        InstructorDto instructorDto = new InstructorDto(1L, "Adam", "Kowalski", "Hello, I'm Adam...");

        //When
        Instructor instructor = instructorMapper.mapToInstructor(instructorDto);

        //Then
        assertEquals(instructorDto.getId(), instructor.getId());
        assertEquals(instructorDto.getFirstName(), instructor.getFirstName());
        assertEquals(instructorDto.getLastName(), instructor.getLastName());
        assertEquals(instructorDto.getDescription(), instructor.getDescription());
    }

    @Test
    void testMapToInstructorDto() {
        //Given
        Instructor instructor = new Instructor(1L, "Adam", "Kowalski", "Hello, I'm Adam...");

        //When
        InstructorDto instructorDto = instructorMapper.mapToInstructorDto(instructor);

        //Then
        assertEquals(instructor.getId(), instructorDto.getId());
        assertEquals(instructor.getFirstName(), instructorDto.getFirstName());
        assertEquals(instructor.getLastName(), instructorDto.getLastName());
        assertEquals(instructor.getDescription(), instructorDto.getDescription());
    }

    @Test
    void testMapToInstructorList() {
        //Given
        InstructorDto dto = new InstructorDto(1L, "Adam", "Kowalski", "Hello, I'm Adam...");
        List<InstructorDto> instructorDtoList = new ArrayList<>();
        instructorDtoList.add(dto);

        //When
        List<Instructor> instructorList = instructorMapper.mapToInstructorList(instructorDtoList);

        //Then
        assertEquals(instructorDtoList.size(), instructorList.size());
        assertEquals(instructorDtoList.get(0).getId(),instructorList.get(0).getId());
        assertEquals(instructorDtoList.get(0).getFirstName(), instructorList.get(0).getFirstName());
        assertEquals(instructorDtoList.get(0).getLastName(), instructorList.get(0).getLastName());
        assertEquals(instructorDtoList.get(0).getDescription(), instructorList.get(0).getDescription());
    }

    @Test
    void testMapToInstructorDtoList() {
        //Given
        Instructor instructor = new Instructor(1L, "Adam", "Kowalski", "Hello, I'm Adam...");
        List<Instructor> instructorList = new ArrayList<>();
        instructorList.add(instructor);

        //When
        List<InstructorDto> instructorDtoList = instructorMapper.mapToInstructorDtoList(instructorList);

        //Then
        assertEquals(instructorList.size(), instructorDtoList.size());
        assertEquals(instructorList.get(0).getId(), instructorDtoList.get(0).getId());
        assertEquals(instructorList.get(0).getFirstName(), instructorDtoList.get(0).getFirstName());
        assertEquals(instructorList.get(0).getLastName(), instructorDtoList.get(0).getLastName());
        assertEquals(instructorList.get(0).getDescription(), instructorDtoList.get(0).getDescription());
    }
}
