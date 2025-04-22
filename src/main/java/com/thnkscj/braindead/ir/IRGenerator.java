package com.thnkscj.braindead.ir;

import com.thnkscj.braindead.ast.node.ASTNode;
import com.thnkscj.braindead.ast.node.impl.InstructionNode;
import com.thnkscj.braindead.ast.node.impl.LoopNode;

import java.util.ArrayList;
import java.util.List;

public class IRGenerator {
    public List<IROp> generate(List<ASTNode> ast) {
        List<IROp> ir = new ArrayList<>();
        for (ASTNode node : ast) generateNode(ir, node);
        return ir;
    }

    private void generateNode(List<IROp> ir, ASTNode node) {
        if (node instanceof InstructionNode inst) {
            ir.add(new IROp(inst.type.name()));
        } else if (node instanceof LoopNode loop) {
            ir.add(new IROp("LOOP_START"));
            for (ASTNode n : loop.body) generateNode(ir, n);
            ir.add(new IROp("LOOP_END"));
        }
    }
}
