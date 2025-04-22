package com.thnkscj.braindead.ast;

import com.thnkscj.braindead.ast.node.ASTNode;
import com.thnkscj.braindead.ast.node.impl.InstructionNode;
import com.thnkscj.braindead.ast.node.impl.LoopNode;
import com.thnkscj.braindead.lexer.Token;
import com.thnkscj.braindead.lexer.TokenType;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class BrainfuckTokenParser {
    private final Iterator<Token> tokens;

    public BrainfuckTokenParser(List<Token> tokens) {
        this.tokens = tokens.iterator();
    }

    public List<ASTNode> parse() {
        List<ASTNode> program = new ArrayList<>();
        while (tokens.hasNext()) {
            program.add(parseNode());
        }
        return program;
    }

    private ASTNode parseNode() {
        if (!tokens.hasNext()) return null;
        Token token = tokens.next();
        if (Objects.requireNonNull(token.type) == TokenType.LOOP_START) {
            return new LoopNode(parseLoopBody());
        }
        return new InstructionNode(token.type);
    }

    private List<ASTNode> parseLoopBody() {
        List<ASTNode> body = new ArrayList<>();
        while (tokens.hasNext()) {
            Token next = tokens.next();
            if (next.type == TokenType.LOOP_END) break;
            if (next.type == TokenType.LOOP_START) {
                body.add(new LoopNode(parseLoopBody()));
            } else {
                body.add(new InstructionNode(next.type));
            }
        }
        return body;
    }
}
