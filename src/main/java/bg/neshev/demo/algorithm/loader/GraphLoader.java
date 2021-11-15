package bg.neshev.demo.algorithm.loader;

import bg.neshev.demo.algorithm.model.Arch;
import bg.neshev.demo.algorithm.model.Graph;
import bg.neshev.demo.algorithm.model.Node;
import bg.neshev.demo.db.model.Base;
import bg.neshev.demo.db.model.Path;

import java.util.List;

public class GraphLoader {

    public static Graph constructGraph(List<Path> paths) {
        Graph graph = new Graph();

        for (Path path : paths) {
            List<Base> basesForPath = path.getBases();

            Node firstNode = graph.getByIndex(basesForPath.get(0).getId())
                    .orElse(new Node(basesForPath.get(0).getId(), graph));

            Node secondNode = graph.getByIndex(basesForPath.get(1).getId())
                    .orElse(new Node(basesForPath.get(1).getId(), graph));

            Arch arch = new Arch(path.getDistance(), firstNode, secondNode, path.getId());
            firstNode.addArch(arch);
            secondNode.addArch(arch);
        }

        return graph;
    }

}
