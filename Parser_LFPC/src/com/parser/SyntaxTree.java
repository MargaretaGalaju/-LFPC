package com.parser;
public class SyntaxTree {
    public SyntaxNode root = null;
    public String toString() {
        return root.toString();
    }

    Tree[] trees;

        public SyntaxTree(String[][] input) {
            Tree[] trees = new Tree[input[0].length];
            for (int i = 0; i<input[0].length; i++){
                Tree tree = new Tree();
                tree.setParent(input[0][i]);
                String[] children = new String[5];
                for(int k=0;k<input.length-1;k++){
                    children[k] = input[k+1][i];
                }
                tree.setChildren(children);
                trees[i] = tree;
            }
            this.trees = trees;
            print();
        }

        public void setTrees(Tree[] trees) {
            this.trees = trees;
        }

        public void print(){
            System.out.println("Parse tree:");
            for(int i =0;i<this.trees.length;i++){
                System.out.print(i +". ");
                Tree.print(this.trees[i]);
                System.out.println();
            }
        }
}