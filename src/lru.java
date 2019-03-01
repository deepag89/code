import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class LRUCache {
	
	class Node {
		int key, value;
		Node pre, next;
		
		public Node(int key, int value) {
			super();
			this.key = key;
			this.value = value;
		}

		@Override
		public String toString() {
			return "[key=" + key + ", value=" + value + "]";
		}
		
	}
	
	Node head, tail;
	HashMap<Integer, Node> keyToNodeMap = new HashMap<Integer, Node>();
	int capacity;
	
	public LRUCache(int cap) {
		capacity = cap;
	}
	
	void set(int key, int value) {
		Node old = null;
		Node node = null;

		// get the old node
		if(keyToNodeMap.containsKey(key)) {
			old = keyToNodeMap.get(key);
		}
		else if(keyToNodeMap.size() == capacity) {
			old = head;
		}

		// create/replace old node with new node
		if(old != null) {
			removeLinks(old);
			keyToNodeMap.remove(old.key);
			old.key = key;
			old.value = value;
			node = old;
		}
		else {
            node = new Node(key, value);
        }
		addToEnd(node);
		keyToNodeMap.put(key, node);
	}
	
	void updateReferral(int key) {
		set(key, keyToNodeMap.get(key).value);
	}

	private void addToEnd(Node node) {
		if(head == null) {
			head = tail = node;
		}
		else {
			tail.next = node;
			node.pre = tail;
			tail = node;
		}
	}

	private void removeLinks(Node nodeToDel) {
		Node pre = nodeToDel.pre, next = nodeToDel.next;
		nodeToDel.next = nodeToDel.pre = null;
		if(pre != null) {
			pre.next = next;
		} else {
			head = next;
		}
		if(next != null) {
			next.pre = pre;
		} else {
			tail = pre;
		}
	}
	
	int refer(int key) {
		if(keyToNodeMap.containsKey(key)) {
			updateReferral(key);
			return keyToNodeMap.get(key).value;
		}
		else {
			return -1;
		}
	}

	@Override
	public String toString() {
		String pages = "";
		for(Node node = head; node != null; node = node.next) {
			pages += node;
		}
		return "Nodes= " + pages;
	}
}

public class lru {
	public static void main(String arg[]) {
		LRUCache cache = new LRUCache(3);
		LinkedList<Integer> pages = new LinkedList<>();
		pages.add(1);
		pages.add(2);
		pages.add(2);
		pages.add(1);
		pages.add(3);
		pages.add(4);
		pages.add(5);
		pages.add(4);
		pages.add(3);
		
		for(Iterator<Integer> pageIterator = pages.iterator(); pageIterator.hasNext(); ) {
			int key = pageIterator.next();
			int val = cache.refer(key);
			if(val != -1) {
				System.out.println("Cache hit for page: " + key + " value:"+ val);
			}
			else {
				int value = key * 10;
				System.out.println("Cache miss for page: " + key + " new value:"+ value);
				cache.set(key, value);
			}
			System.out.println("Cache: " + cache + "\n\n");
		}
	}
}
