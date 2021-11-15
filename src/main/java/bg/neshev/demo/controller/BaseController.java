package bg.neshev.demo.controller;

import bg.neshev.demo.dto.BaseDTO;
import bg.neshev.demo.dto.BasePageableDTO;
import bg.neshev.demo.dto.PathDTO;
import bg.neshev.demo.mapper.BaseMapper;
import bg.neshev.demo.mapper.PathMapper;
import bg.neshev.demo.service.BaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/bases")
@RequiredArgsConstructor
public class BaseController {
    private final BaseService baseService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BaseDTO findById(@PathVariable("id") Integer id) {
        return BaseMapper.toDto(baseService.findById(id));
    }

    @GetMapping("/{id}/paths")
    @ResponseStatus(HttpStatus.OK)
    public List<PathDTO> findPaths(@PathVariable("id") Integer id) {
        return PathMapper.toDtoList(baseService.findPaths(id));
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BaseDTO> findAll() {
        return BaseMapper.toDtoList(baseService.findAll());
    }

    @GetMapping("/pageable")
    @ResponseStatus(HttpStatus.OK)
    public Page<BaseDTO> findAllPageable(@Valid BasePageableDTO params) {
        return baseService.findPageable(params).map(BaseMapper::toDto);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer save(@RequestBody @Valid BaseDTO baseDTO) {
        return baseService.save(baseDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Integer id, @RequestBody @Valid BaseDTO baseDTO) {
        baseService.update(id, baseDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        baseService.delete(id);
    }

}
