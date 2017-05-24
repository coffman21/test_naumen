package searcher;

import java.util.HashMap;

public class Node {
    private String className;
    private Long modificationDate;

    public Character character;
    public HashMap<Character, Node> children;

    Node(Character c) {
        character = c;
        children = new HashMap<>();
    }
    public void setNameDate(String name, Long date) {
        className = name;
        modificationDate = date;
    }

    public HashMap<String, Long> getNodes() {
        return null;
    }
}
