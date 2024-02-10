package com.crud.equestrianapp.service.mapper;

import com.crud.equestrianapp.domain.Horse;
import com.crud.equestrianapp.service.dto.HorseDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HorseMapper {

    public Horse mapToHorse(final HorseDto horseDto) {
        return new Horse(
                horseDto.getId(),
                horseDto.getName(),
                horseDto.getDescription()
        );
    }

    public HorseDto mapToHorseDto(final Horse horse) {
        return new HorseDto(
                horse.getId(),
                horse.getName(),
                horse.getDescription()
        );
    }

    public List<Horse> mapToHorseList(final List<HorseDto> horseDtoList) {
        return horseDtoList.stream()
                .map(this::mapToHorse)
                .collect(Collectors.toList());
    }

    public List<HorseDto> mapToHorseDtoList(final List<Horse> horseList) {
        return horseList.stream()
                .map(this::mapToHorseDto)
                .collect(Collectors.toList());
    }
}