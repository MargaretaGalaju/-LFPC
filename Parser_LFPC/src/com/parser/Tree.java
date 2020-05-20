package com.parser;

public class Tree {
    String parent;
    String[] children;

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public String[] getChildren() {
        return children;
    }

    public void setChildren(String[] children) {
        this.children = children;
    }
    public static void print(Tree tree){
        System.out.print(tree.getParent() + " -> ");
        int i=0;
        while (i!=tree.getChildren().length && tree.getChildren()[i]!= null){
            if(i == tree.getChildren().length-1 || tree.getChildren()[i+1] == null){
                System.out.print(tree.getChildren()[i]);
            }
            else
                System.out.print(tree.getChildren()[i] + ", ");
            i++;
        }
    }
}
