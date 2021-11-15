package bg.neshev.demo.algorithm.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Graph {
    private final List<Node> nodes;

    public Graph(){
        nodes = new ArrayList<>();
    }

    public List<Node> getNodes() {
        return nodes;
    }

    public Optional<Node> getByIndex(Integer id){
        return nodes.stream()
                    .filter(n -> n.getId().equals(id))
                    .findFirst();
    }
    public void addNode(Node a){
        nodes.add(a);
    }

    public boolean containsNode(Integer id){
        return nodes.stream()
                    .anyMatch(n -> n.getId().equals(id));
    }

    private Node getMinimalTemporal(List<Node> temporals){
        Node min = temporals.get(0);

        for(Node n : temporals){
            if(n.getMarkingValue() < min.getMarkingValue()) min = n;
        }

        return min;
    }

    public List<Node> findShortestPath(Node start, Node end){
        List<Node> marked = new ArrayList<>();
        List<Node> unmarked = nodes;
        marked.add(start);
        unmarked.remove(start);
        start.setMarkingValue(0);
        start.setPermanentlyMarked(true);

        Node current = start;

        while(!end.isPermanentlyMarked()){
            for(Arch ar : current.getArches()){
                if(!( marked.contains(ar.getNodes().get(0)) && marked.contains(ar.getNodes().get(1)))){
                    Node otherEnd = current.getOtherEnd(ar);
                    int markingValue = current.getMarkingValue() + ar.getPrice();
                    if(otherEnd.getMarkingValue() > markingValue){
                        otherEnd.setMarkingValue(markingValue);
                        otherEnd.setMinArch(ar);
                    }
                }
            }

            Node n = getMinimalTemporal(unmarked);
            n.setPermanentlyMarked(true);
            unmarked.remove(n);
            current = n;
        }

        List<Node> minPath = new ArrayList<>();
        Node from = start;
        Node to = end;

        minPath.add(to);

        while(!to.equals(from)){
            to = to.getOtherEnd(to.getMinArch());
            minPath.add(0, to);
        }

        return minPath;
    }

}

