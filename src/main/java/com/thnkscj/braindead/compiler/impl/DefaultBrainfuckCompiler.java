package com.thnkscj.braindead.compiler.impl;

import com.thnkscj.braindead.ast.BrainfuckTokenParser;
import com.thnkscj.braindead.ast.node.ASTNode;
import com.thnkscj.braindead.exception.CompilerException;
import com.thnkscj.braindead.instruction.BraindeadInstruction;
import com.thnkscj.braindead.instruction.BraindeadInstructionFactory;
import com.thnkscj.braindead.io.BrainfuckSource;
import com.thnkscj.braindead.ir.IRGenerator;
import com.thnkscj.braindead.ir.IROp;
import com.thnkscj.braindead.lexer.BrainfuckLexer;
import com.thnkscj.braindead.lexer.Token;
import com.thnkscj.braindead.transform.IRToInstructionTransformer;

import java.util.List;

public final class DefaultBrainfuckCompiler extends AsmBrainfuckCompiler {
    public DefaultBrainfuckCompiler(BrainfuckSource parser) {
        super(parser);
    }

    @Override
    protected void processInstructions() {
        try {
            String source = parser.read();
            BrainfuckLexer lexer = new BrainfuckLexer();
            List<Token> tokens = lexer.lex(source);
            BrainfuckTokenParser tokenParser = new BrainfuckTokenParser(tokens);
            List<ASTNode> ast = tokenParser.parse();

            IRGenerator irGenerator = new IRGenerator();
            List<IROp> ir = irGenerator.generate(ast);

            BraindeadInstructionFactory factory = new BraindeadInstructionFactory();
            IRToInstructionTransformer transformer = new IRToInstructionTransformer(factory);

            for (BraindeadInstruction instruction : transformer.transform(ir)) {
                instruction.execute(methodVisitor);
            }
        } catch (Exception e) {
            throw new CompilerException("Error processing instructions", e);
        }
    }
}
