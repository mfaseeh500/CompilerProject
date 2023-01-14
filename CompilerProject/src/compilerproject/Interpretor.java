/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

import java.lang.reflect.InvocationTargetException;

/**
 *
 * @author MUHAMMAD FASEEH
 */
public class Interpretor extends NodeVisitor {

    public Parser parser;
    private final String INTEGER = "INTEGER", PLUS = "PLUS", MINUS = "MINUS", MUL = "MUL", DIV = "DIV", EOF = "EOF",
            LP = "LP", RP = "RP";

    public Interpretor(Parser parser) {
        super();
        this.parser = parser;
    }

    public int visit_BinOP(BinOP node) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

        if (node.op.type.equals(PLUS)) {
            return (int) this.visit(node.left) + (int) this.visit(node.right);
        } else {
            if (node.op.type.equals(MINUS)) {
               return (int) this.visit(node.left) - (int) this.visit(node.right);
            } else {
                if (node.op.type.equals(MUL)) {
                    return (int) this.visit(node.left) * (int) this.visit(node.right);
                } else {
                    if (node.op.type.equals(DIV)) {
                        return (int) this.visit(node.left) / (int) this.visit(node.right);
                    }
                }
            }
        }
        return 0;
    }

    public int visit_NUM(NUM node) {
        return (int) node.value;
    }

    public int interpret() throws Exception {
        BinOP tree = (BinOP) this.parser.parse();
        return (int) this.visit(tree);
    }
}
