package com.thnkscj.braindead.lexer;

import java.util.ArrayList;
import java.util.List;

public class BrainfuckLexer {
    public List<Token> lex(String source) {
        List<Token> tokens = new ArrayList<>();
        for (char c : source.toCharArray()) {
            switch (c) {
                case '>':
                    tokens.add(new Token(TokenType.INC_PTR));
                    break;
                case '<':
                    tokens.add(new Token(TokenType.DEC_PTR));
                    break;
                case '+':
                    tokens.add(new Token(TokenType.INC_VAL));
                    break;
                case '-':
                    tokens.add(new Token(TokenType.DEC_VAL));
                    break;
                case '.':
                    tokens.add(new Token(TokenType.OUTPUT));
                    break;
                case ',':
                    tokens.add(new Token(TokenType.INPUT));
                    break;
                case '[':
                    tokens.add(new Token(TokenType.LOOP_START));
                    break;
                case ']':
                    tokens.add(new Token(TokenType.LOOP_END));
                    break;
            }
        }
        return tokens;
    }
}
