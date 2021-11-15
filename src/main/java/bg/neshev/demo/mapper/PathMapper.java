package bg.neshev.demo.mapper;

import bg.neshev.demo.db.model.Base;
import bg.neshev.demo.db.model.Path;
import bg.neshev.demo.dto.PathDTO;

import java.util.List;
import java.util.stream.Collectors;

public class PathMapper {
    private PathMapper() { }

    public static Path toNewEntity(PathDTO dto, List<Base> bases) {
        return Path.builder()
                   .distance(dto.getDistance())
                   .bases(bases)
                   .build();
    }

    public static PathDTO toDto(Path entity) {
        return PathDTO.builder()
                      .id(entity.getId())
                      .distance(entity.getDistance())
                      .bases(BaseMapper.toDtoList(entity.getBases()))
                      .build();
    }

    public static List<PathDTO> toDtoList(List<Path> paths) {
        return paths.stream()
                    .map(PathMapper::toDto)
                    .collect(Collectors.toList());
    }

    public  static void merge(Path entity, PathDTO dto) {
        entity.setDistance(dto.getDistance());
    }

}
