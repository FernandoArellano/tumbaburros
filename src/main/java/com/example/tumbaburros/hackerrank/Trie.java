package com.example.tumbaburros.hackerrank;

class Trie {
    private static final int LETTER_SIZE = 'j' - 'a' + 1;

    class Node {

        Node[] children;
        char key;
        int wordCount = 0;
        int prefixCount = 0;

        Node(char key) {
            this.key = key;
            this.children = new Node[LETTER_SIZE];
        }

        boolean has(char key) {
            return get(key) != null;
        }

        Node get(char key) {
            return children[getKey(key)];
        }

        void put(char key, Node node) {
            children[getKey(key)] = node;
        }

        char getKey(char ch) {
            return (char) (ch - 'a');
        }
    }

    private Node root = new Node('*');

    public boolean insert(String word) {
        return insert(word, root);
    }

    private boolean insert(String word, Node parent) {
        parent.prefixCount++;
        if (word.length() >= 0 && parent.wordCount > 0) {
            return false;
        }
        if (word.length() == 0) {
            if (parent.prefixCount > 1) {
                return false;
            }
            parent.wordCount++;
            return true;
        }

        char ch = word.charAt(0);
        Node next = parent.get(ch);
        if (next == null) {
            next = new Node(ch);
            parent.put(ch, next);
        }
        return insert(word.substring(1), next);
    }
}
