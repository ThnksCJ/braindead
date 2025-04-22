package com.thnkscj.braindead.ast.node.impl;

import com.thnkscj.braindead.ast.node.ASTNode;

import java.util.List;

public class LoopNode implements ASTNode {
    public final List<ASTNode> body;

    public LoopNode(List<ASTNode> body) {
        this.body = body;
    }
}
