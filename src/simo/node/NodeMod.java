package simo.node;

/**
 * Created by simo on 24/06/15.
 */
public class NodeMod {
    private char name;
    private int count = 1;
    private int level = 1;

    public NodeMod(char name) {
        this.name = name;
    }

    public NodeMod(char name, int count) {
        this.name = name;
        this.count = count;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public char getName() {
        return name;
    }

    public void setName(char name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NodeMod nodeMod = (NodeMod) o;

        return name == nodeMod.name;

    }

    @Override
    public int hashCode() {
        return (int) name;
    }

    @Override
    public String toString() {
        return "NodeMod{" +
                "name=" + name +
                ", count=" + count +
                ", level=" + level +
                '}';
    }
}
