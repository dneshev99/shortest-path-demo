package bg.neshev.demo.mapper;

import bg.neshev.demo.db.model.Base;
import bg.neshev.demo.dto.BaseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class BaseMapper {

    private BaseMapper() { }

    public static Base toNewEntity(BaseDTO dto) {
        return Base.builder()
                   .name(dto.getName())
                   .build();
    }

    public static BaseDTO toDto(Base entity) {
        return BaseDTO.builder()
                      .id(entity.getId())
                      .name(entity.getName())
                      .build();
    }

    public static List<BaseDTO> toDtoList(List<Base> entities) {
        return entities.stream()
                       .map(BaseMapper::toDto)
                       .collect(Collectors.toList());
    }

    public static void merge(Base entity, BaseDTO dto) {
        entity.setName(dto.getName());
    }
}
