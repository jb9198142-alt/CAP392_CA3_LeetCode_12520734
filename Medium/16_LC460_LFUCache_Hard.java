import java.util.*;

class LFUCache {
    private static class Node {
        int key;
        int value;
        int frequency;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            frequency = 1;
        }
    }

    private int capacity;
    private int minimumFrequency;
    private Map<Integer, Node> nodes;
    private Map<Integer, LinkedHashSet<Node>> frequencyLists;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        minimumFrequency = 0;
        nodes = new HashMap<>();
        frequencyLists = new HashMap<>();
    }

    public int get(int key) {
        if (!nodes.containsKey(key)) {
            return -1;
        }

        Node node = nodes.get(key);
        increaseFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (nodes.containsKey(key)) {
            Node node = nodes.get(key);
            node.value = value;
            increaseFrequency(node);
            return;
        }

        if (nodes.size() == capacity) {
            LinkedHashSet<Node> leastUsed =
                    frequencyLists.get(minimumFrequency);
            Node oldest = leastUsed.iterator().next();

            leastUsed.remove(oldest);
            nodes.remove(oldest.key);

            if (leastUsed.isEmpty()) {
                frequencyLists.remove(minimumFrequency);
            }
        }

        Node newNode = new Node(key, value);
        nodes.put(key, newNode);

        if (!frequencyLists.containsKey(1)) {
            frequencyLists.put(1, new LinkedHashSet<>());
        }

        frequencyLists.get(1).add(newNode);
        minimumFrequency = 1;
    }

    private void increaseFrequency(Node node) {
        int oldFrequency = node.frequency;
        LinkedHashSet<Node> oldList = frequencyLists.get(oldFrequency);
        oldList.remove(node);

        if (oldList.isEmpty()) {
            frequencyLists.remove(oldFrequency);

            if (minimumFrequency == oldFrequency) {
                minimumFrequency++;
            }
        }

        node.frequency++;

        if (!frequencyLists.containsKey(node.frequency)) {
            frequencyLists.put(node.frequency, new LinkedHashSet<>());
        }

        frequencyLists.get(node.frequency).add(node);
    }
}
