package com.crud.equestrianapp.service.mapper;
import com.crud.equestrianapp.domain.Instructor;
import com.crud.equestrianapp.service.dto.InstructorDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InstructorMapper {

    public Instructor mapToInstructor(final InstructorDto instructorDto) {
        return new Instructor(
                instructorDto.getId(),
                instructorDto.getFirstName(),
                instructorDto.getLastName(),
                instructorDto.getDescription()
        );
    }

    public InstructorDto mapToInstructorDto(final Instructor instructor) {
        return new InstructorDto(
                instructor.getId(),
                instructor.getFirstName(),
                instructor.getLastName(),
                instructor.getDescription()
        );
    }

    public List<Instructor> mapToInstructorList(final List<InstructorDto> instructorDtoList) {
        return instructorDtoList.stream()
                .map(this::mapToInstructor)
                .collect(Collectors.toList());
    }

    public List<InstructorDto> mapToInstructorDtoList(final List<Instructor> instructorList) {
        return instructorList.stream()
                .map(this::mapToInstructorDto)
                .collect(Collectors.toList());
    }
}
