package bg.neshev.demo.algorithm.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter @Setter
@AllArgsConstructor
public class Arch {
    private Integer price;
    private Node firstNode;
    private Node secondNode;
    private boolean marked;
    private Integer id;

    public Arch(Integer price, Node firstNode, Node secondNode, Integer id){
        this.price = price;
        this.firstNode = firstNode;
        this.secondNode = secondNode;
        marked = false;
        this.id = id;
    }

    public List<Node> getNodes(){
        return List.of(firstNode, secondNode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Arch arch = (Arch) o;
        return Objects.equals(id, arch.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
