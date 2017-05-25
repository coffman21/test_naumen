package searcher;

import java.util.HashMap;

public class Searcher implements ISearcher {
    private Node root;
    Searcher() {
        root = new Node('\\', null);
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
                    children.put(currChar, new Node(currChar, currNode));
                }
                if (currNode.maxModDate < modificationDates[i]) {
                    currNode.maxModDate = modificationDates[i];
                }
                currNode = children.get(currChar);
                children = currNode.children;

            }
            currNode.setClassName(classNames[i]);
            currNode.setModificaionDate(modificationDates[i]);
            if (i % 10000 == 0) {
                System.out.println("iteration: " + i);
                System.out.println("total memory: " + Runtime.getRuntime().maxMemory());
                System.out.println("free memory:  " + Runtime.getRuntime().freeMemory());
            }
        }
    }

    private void fillCurrModDate(Node root) {
        for (Node node : root.children.values()) {
            node.currModDate = node.maxModDate;
            if (node.children != null) {
                fillCurrModDate(node);
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
        Node root = currNode;
        fillCurrModDate(root); // ?
        for (int i = 0; i < 12; i++) {
            String s = getLatestName(root);
        }
        fillCurrModDate(root);

        return new String[0];
    }

    private String getLatestName(Node node) {
        String name = null;
        if (node.children == null) {
            name = node.getClassName();
            node.currModDate = 0;
            return name;
        }
        long latestDate = 0;
        Node nextNode = null;

        for (Node child : node.children.values()) {
            if (child.currModDate > latestDate) {
                latestDate = child.currModDate;
                nextNode = child;
            }
        }

        if (nextNode != null) {
            name = getLatestName(nextNode);
            latestDate = 0;
            for (Node child : nextNode.children.values()) {
                if (child.currModDate > latestDate) {
                    nextNode.currModDate = latestDate;
                }
            }
        }
        return name;
    }
}