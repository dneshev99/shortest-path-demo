package bg.neshev.demo.controller;

import bg.neshev.demo.dto.PathDTO;
import bg.neshev.demo.dto.PathPageableDTO;
import bg.neshev.demo.mapper.PathMapper;
import bg.neshev.demo.service.PathService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/paths")
@RequiredArgsConstructor
public class PathController {
    private final PathService pathService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PathDTO findById(@PathVariable("id") Integer id) {
        return PathMapper.toDto(pathService.findById(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PathDTO> findAll() {
        return PathMapper.toDtoList(pathService.findAll());
    }

    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.OK)
    public Page<PathDTO> findAllPageable(@Valid PathPageableDTO params) {
        return pathService.findPageable(params).map(PathMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid PathDTO pathDTO) {
        return pathService.save(pathDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid PathDTO pathDTO) {
        pathService.update(id, pathDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        pathService.delete(id);
    }

}
