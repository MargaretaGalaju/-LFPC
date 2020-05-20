package com.parser;

import java.util.ArrayList;
import com.lexer.Token;

public class Parser {
    
    private ParseStack stack = new ParseStack();

    public void parse(ArrayList<Token> tokens) {
        for (Token t : tokens) {
            shift(t);
            reduce();
        }

        System.out.println(stack);
    }

    public void shift(Token t) {
        stack.push(new SyntaxNode(t));
    }

    public boolean reduce() {
        boolean reduceMade = false;
        if (stack.size() >= 3) {
            ArrayList<SyntaxNode> test = stack.peek(3);
            if (reduceINSTR(test)) {
                stack.pop(3);
                stack.push(new SyntaxNode(NodeType.INSTR));
            }
        }
        return reduceMade;
    }
    public boolean reduceINSTR(ArrayList<SyntaxNode> test) {
        return test.get(0).getType() == NodeType.Integer && 
                test.get(1).getType() == NodeType.Assignment && 
                    test.get(2).getType() == NodeType.UserDefinedName;
    }

}
