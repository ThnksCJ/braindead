package com.thnkscj.braindead.ast.node.impl;

import com.thnkscj.braindead.ast.node.ASTNode;
import com.thnkscj.braindead.lexer.TokenType;

public class InstructionNode implements ASTNode {
    public final TokenType type;

    public InstructionNode(TokenType type) {
        this.type = type;
    }
}
