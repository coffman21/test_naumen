package searcher;

import java.util.HashMap;
import java.util.PriorityQueue;

public class Searcher implements ISearcher {
    private Node root;
    Searcher() {
        root = new Node('\\');
    }
    // Имена классов не более 32 символов, содержать лишь латинские буквы и цифры.
    // Используем лишь короткие имена классов, все уникальны, повторений нет.

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        for (int i = 0; i < classNames.length; i++) {
            Node currNode = this.root;
            HashMap<Character, Node> children = currNode.children;
            for (int j = 0; j < classNames[i].length(); j++) {
                Character currChar = classNames[i].charAt(j);
                if (!children.containsKey(currChar)) {
                    children.put(currChar, new Node(currChar));
                }
                currNode = children.get(currChar);
                children = currNode.children;
                if (currNode.maxModDate < modificationDates[i]) {
                    currNode.maxModDate = modificationDates[i];
                }
            }
            currNode.setNameDate(classNames[i], modificationDates[i]);
            if (i % 10000 == 0) {
                System.out.println("iteration: " + i);
                System.out.println("total memory: " + Runtime.getRuntime().maxMemory());
                System.out.println("free memory:  " + Runtime.getRuntime().freeMemory());
            }
        }
    }

    @Override
    public String[] guess(String start) {
        Node currNode = this.root;
        for (int i = 0; i < start.length(); i++) {
            char currChar = start.charAt(i);
            currNode = currNode.children.get(currChar);
        }

        return new String[0];
    }
}