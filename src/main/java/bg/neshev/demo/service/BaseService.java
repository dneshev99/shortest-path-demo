package bg.neshev.demo.service;

import bg.neshev.demo.db.model.Base;
import bg.neshev.demo.db.model.Path;
import bg.neshev.demo.db.repository.BaseRepository;
import bg.neshev.demo.db.specification.BaseSpecification;
import bg.neshev.demo.dto.BaseDTO;
import bg.neshev.demo.dto.BasePageableDTO;
import bg.neshev.demo.exception.ApiErrorException;
import bg.neshev.demo.mapper.BaseMapper;
import bg.neshev.demo.validation.enums.ApiErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BaseService {
    private final BaseRepository baseRepository;

    private PathService pathService;

    public Base findById(Integer id) {
        return findBaseById(id);
    }

    @Cacheable("bases")
    public List<Base> findAll() {
        return baseRepository.findAll();
    }

    public List<Base> findAllByIds(List<Integer> basesIds) {
        return baseRepository.findAll(BaseSpecification.byIds(basesIds));
    }

    public Page<Base> findPageable(BasePageableDTO params) {
        return baseRepository.findAll(
                BaseSpecification.byName(params.getName()),
                constructPageRequest(params)
        );
    }

    public List<Path> findPaths(Integer id) {
        Base entity = findBaseByIdAndFetchPaths(id);
        return entity.getPaths();
    }

    public Integer save(BaseDTO dto) {
        return baseRepository.save(BaseMapper.toNewEntity(dto)).getId();
    }

    @CacheEvict(value = "bases", allEntries = true)
    public void update(Integer id, BaseDTO dto) {
        Base entity = findBaseById(id);
        BaseMapper.merge(entity, dto);

        baseRepository.save(entity);
    }

    @Transactional
    @CacheEvict(value = "bases", allEntries = true)
    public void delete(Integer id) {
        Base entity = findBaseByIdAndFetchPaths(id);

        pathService.deleteAll(entity.getPaths());
        baseRepository.delete(entity);
    }

    private PageRequest constructPageRequest(BasePageableDTO params) {
        Sort sort = Sort.by(params.getSortBy());
        sort = params.getIsAsc() ? sort.ascending() : sort.descending();

        return PageRequest.of(params.getPageNum(), params.getPageSize(), sort);
    }

    private Base findBaseById(Integer id) {
        return baseRepository.findById(id)
                .orElseThrow(() -> new ApiErrorException(ApiErrorCode.BASE_NOT_FOUND));
    }

    private Base findBaseByIdAndFetchPaths(Integer id) {
        return baseRepository.findOne(BaseSpecification.byIdAndFetchPaths(id))
                .orElseThrow(() -> new ApiErrorException(ApiErrorCode.BASE_NOT_FOUND));
    }

    @Autowired
    public void setPathService(PathService pathService) {
        this.pathService = pathService;
    }

}
