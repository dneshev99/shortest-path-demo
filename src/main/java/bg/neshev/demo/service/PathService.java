package bg.neshev.demo.service;

import bg.neshev.demo.db.model.Base;
import bg.neshev.demo.db.model.Path;
import bg.neshev.demo.db.repository.PathRepository;
import bg.neshev.demo.db.specification.PathSpecification;
import bg.neshev.demo.dto.BaseDTO;
import bg.neshev.demo.dto.PathDTO;
import bg.neshev.demo.dto.PathPageableDTO;
import bg.neshev.demo.exception.ApiErrorException;
import bg.neshev.demo.mapper.PathMapper;
import bg.neshev.demo.validation.enums.ApiErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PathService {
    private final PathRepository pathRepository;

    private BaseService baseService;

    public Path findById(Integer id) {
        return findPathById(id);
    }

    @Cacheable("paths")
    public List<Path> findAll() {
        return pathRepository.findAll(PathSpecification.fetchBases());
    }

    public Page<Path> findPageable(PathPageableDTO params) {
        return pathRepository.findAll(
                PathSpecification.byDistance(params.getDistance()),
                constructPageRequest(params)
        );
    }

    public Integer save(PathDTO dto) {
        if (dto.getBases().stream().map(BaseDTO::getId).distinct().count() != 2)
            throw new ApiErrorException(ApiErrorCode.SAME_BASES_PATH_CREATION);

        List<Base> bases = baseService.findAllByIds(getBasesIdsFromDto(dto));
        Path path = pathRepository.save(PathMapper.toNewEntity(dto, bases));

        return path.getId();
    }

    @CacheEvict(value = "paths", allEntries = true)
    public void update(Integer id, PathDTO dto) {
        Path entity = findPathById(id);
        PathMapper.merge(entity, dto);

        pathRepository.save(entity);
    }

    @CacheEvict(value = "paths", allEntries = true)
    public void delete(Integer id) {
        Path entity = findPathById(id);
        pathRepository.delete(entity);
    }

    @CacheEvict(value = "paths", allEntries = true)
    public void deleteAll(List<Path> paths) {
        pathRepository.deleteAll(paths);
    }

    private Path findPathById(Integer id) {
        return pathRepository.findById(id)
                .orElseThrow(() -> new ApiErrorException(ApiErrorCode.PATH_NOT_FOUND));
    }

    private List<Integer> getBasesIdsFromDto(PathDTO dto) {
        return dto.getBases()
                  .stream()
                  .map(BaseDTO::getId)
                  .collect(Collectors.toList());
    }

    private PageRequest constructPageRequest(PathPageableDTO params) {
        Sort sort = Sort.by(params.getSortBy());
        sort = params.getIsAsc() ? sort.ascending() : sort.descending();

        return PageRequest.of(params.getPageNum(), params.getPageSize(), sort);
    }

    @Autowired
    public void setBaseService(BaseService baseService) {
        this.baseService = baseService;
    }
}
