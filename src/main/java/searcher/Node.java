package searcher;

import java.util.Comparator;
import java.util.HashMap;

public class Node implements Comparator<Node> {
    private String className;
    private Long modificationDate;

    public long maxModDate;
    public Character character;
    public HashMap<Character, Node> children;

    Node(Character c) {
        character = c;
        children = new HashMap<>();
        maxModDate = 0L;
    }
    public void setNameDate(String name, Long date) {
        className = name;
        modificationDate = date;
    }

    @Override
    public int compare(Node o1, Node o2) {
        int foo = o1.modificationDate.compareTo(o2.modificationDate);
        if (foo == 0) foo = o1.className.compareTo(o2.className);
        return foo;
    }
}
