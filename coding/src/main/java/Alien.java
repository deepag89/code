// https://www.geeksforgeeks.org/given-sorted-dictionary-find-precedence-characters/

import java.util.*;

class Node {
	Character ch;
	LinkedList<Node> adj;
	
	Node(char c) {
		ch = c;
		adj = new LinkedList<>();
	}
	
	void addEdge(Node to) {
		adj.add(to);
	}
}

class Graph {
	LinkedList<Node> nodes;
	Map<Character, Node> charToNodeMap;
	
	Graph() {
		nodes = new LinkedList<>();
		charToNodeMap = new HashMap<>();
	}
	
	Node createNode(char ch) {
		if(!charToNodeMap.containsKey(ch)) {
			Node newNode = new Node(ch);
			nodes.add(newNode);
			charToNodeMap.put(ch, newNode);
		}
		return charToNodeMap.get(ch);
	}
	
	void addEdge(char from, char to) {
		Node fromNode = createNode(from);
		Node toNode = createNode(to);
		fromNode.addEdge(toNode);
	}
	
	void topoSort(Node node, Stack<Character> stack, Map<Node, Boolean> visited) {
		if(visited.containsKey(node))
			return;
		visited.put(node, true);
		for(Iterator<Node>it = node.adj.iterator(); it.hasNext();) {
			Node nextNode = it.next();
			topoSort(nextNode, stack, visited);
		}
		
		stack.push(node.ch);
	}
	
	void topoSort() {
		Stack<Character> stack = new Stack<Character>();
		Map<Node, Boolean> visited = new HashMap<>();
		for(Iterator<Node> it = nodes.iterator(); it.hasNext(); ) {
			Node node = it.next();
			topoSort(node, stack, visited);
		}
		while(!stack.isEmpty()) {
			System.out.println(stack.pop() + " ");
		}
	}
}

class Alien {
	
	public static void main(String args[]) {
		String[] words = {"hello", "hell", "helu"};
		electionWinner("eioeehelleadca");
	}

	static boolean isVowel(char ch) {
		if(ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' ||
				ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U') {
			return true;
		}
		return false;
	}
	private static void electionWinner(String s) {
		String first = "";
		List<String> words = new ArrayList<>();
		int curWordStart = -1;
		int curWordEnd = -1;
		for(int i=0; i<s.length(); i++) {
			char ch = s.charAt(i);
			if(curWordStart == -1 && isVowel(ch)) {
				curWordStart = i;
			}
			if(!isVowel(ch) && curWordStart != -1) {
				curWordEnd = i;
				while(isVowel(s.charAt(curWordStart))) {
					words.add(s.substring(curWordStart, curWordEnd + 1));
					curWordStart++;
				}
				curWordStart = -1;
			}
			//
		}
		System.out.println(words);
	}
	
}