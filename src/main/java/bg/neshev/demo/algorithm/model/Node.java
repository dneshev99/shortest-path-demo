package bg.neshev.demo.algorithm.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class Node {
    private boolean permanentlyMarked;
    private Integer markingValue;
    private List<Arch> arches;
    private Arch minArch;
    private Integer id;

    public Node(){
        arches = new ArrayList<>();
        permanentlyMarked = false;
        markingValue = Integer.MAX_VALUE;
    }

    public Node(Integer id, Graph g){
        arches = new ArrayList<>();
        permanentlyMarked = false;
        markingValue = Integer.MAX_VALUE;
        this.id = id;

        if(!g.containsNode(id)) {
            g.addNode(this);
        }
    }

    public void addArch(Arch a){
        arches.add(a);
    }

    public Node getOtherEnd(Arch a){
        return a.getNodes().get(0).equals(this) ? a.getNodes().get(1) : a.getNodes().get(0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return Objects.equals(id, node.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

