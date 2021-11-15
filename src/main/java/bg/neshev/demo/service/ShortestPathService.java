package bg.neshev.demo.service;

import bg.neshev.demo.algorithm.loader.GraphLoader;
import bg.neshev.demo.algorithm.model.Graph;
import bg.neshev.demo.algorithm.model.Node;
import bg.neshev.demo.db.model.Base;
import bg.neshev.demo.db.model.Path;
import bg.neshev.demo.exception.ApiErrorException;
import bg.neshev.demo.validation.enums.ApiErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShortestPathService {
    private final PathService pathService;
    private final BaseService baseService;

    public List<Base> findShortestPath(Integer fromBaseId, Integer toBaseId) {
        if (fromBaseId.equals(toBaseId)) {
            throw new ApiErrorException(ApiErrorCode.SAME_BASES);
        }

        List<Path> paths = pathService.findAll();
        Graph graph = GraphLoader.constructGraph(paths);

        Node fromNode = graph.getByIndex(fromBaseId)
                .orElseThrow(() -> new ApiErrorException(ApiErrorCode.BASE_NOT_FOUND));

        Node toNode = graph.getByIndex(toBaseId)
                .orElseThrow(() -> new ApiErrorException(ApiErrorCode.BASE_NOT_FOUND));

        return graph.findShortestPath(fromNode, toNode)
                .stream()
                .map(Node::getId)
                .map(baseService::findById)
                .collect(Collectors.toList());
    }

}
