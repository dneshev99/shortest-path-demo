package bg.neshev.demo.controller;

import bg.neshev.demo.dto.BaseDTO;
import bg.neshev.demo.mapper.BaseMapper;
import bg.neshev.demo.service.ShortestPathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/shortest-path")
@RequiredArgsConstructor
public class ShortestPathController {
    private final ShortestPathService shortestPathService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BaseDTO> findShortestPath(@RequestParam("from") Integer fromBaseId,
                                          @RequestParam("to") Integer toBaseId) {
        return BaseMapper.toDtoList(shortestPathService.findShortestPath(fromBaseId, toBaseId));
    }

}
