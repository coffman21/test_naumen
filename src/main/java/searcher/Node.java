package searcher;

import java.util.Comparator;
import java.util.HashMap;

public class Node implements Comparator<Node> {
    private String className;
    private Long modificationDate;

    //public boolean isLeaf = false;
    public long maxModDate;
    public long currModDate;

    public Character character;
    public HashMap<Character, Node> children;

    //public Node parent;

    Node(Character c, Node p) {
        character = c;
        children = new HashMap<>();
        maxModDate = 0L;
        currModDate = 0L;
      //  parent = p;
    }
    public void setNameDate(String name, Long date) {
        className = name;
        modificationDate = date;
        //isLeaf = true;
    }


    // TODO: probably won't needed
    @Override
    public int compare(Node o1, Node o2) {
        int foo = o1.modificationDate.compareTo(o2.modificationDate);
        if (foo == 0) foo = o1.className.compareTo(o2.className);
        return foo;
    }

    public void setModificaionDate(Long modificaionDate) {
        this.modificationDate = modificaionDate;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getModificationDate() {
        return modificationDate;
    }

    public String getClassName() {
        return className;
    }
}
