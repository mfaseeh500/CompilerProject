/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MUHAMMAD FASEEH
 */
public class Parser<T> {

    public Lexer lexer;
    public Token current_token;
    private final String INTEGER = "INTEGER", PLUS = "PLUS", MINUS = "MINUS", MUL = "MUL", DIV = "DIV", EOF = "EOF",
            LP = "LP", RP = "RP";

    public Parser(Lexer lex) {
        this.lexer = lex;
        try {
            this.current_token = this.lexer.get_next_token();
        } catch (Exception ex) {
            Logger.getLogger(Parser.class.getName()).log(Level.SEVERE, null, "Class : Parser : Method contructor : Error = " + ex);
        }
    }

    // to handle unrecognized token

    public void error() throws Exception {
        throw new Exception("Invalid token");
    }

    //verify the current Token else throw error

    public void verify_token(String token_type) throws Exception {
        if (this.current_token.type.equals(token_type)) {
            this.current_token = this.lexer.get_next_token();
        } else {
            this.error();
        }
    }

    // just verifies if current token is an Integer
    // and then return the value

    public T factor() throws Exception {
        Token token = this.current_token;
        T node = null;
        switch (token.type) {
            case INTEGER:
                this.verify_token(INTEGER);
                return (T) new NUM(token);
            case LP:
                this.verify_token(LP);
                node = this.expr();
                this.verify_token(RP);
                return node;
        }
        return null;

    }

    public T term() throws Exception {

        T node = this.factor();
        // loops through to check if next tokens are Multiplication or division
        while (this.current_token != null && (this.current_token.type.equals(DIV) || this.current_token.type.equals(MUL))) {
            // gets the token 
            Token token = this.current_token;
            if (token.type.equals(MUL)) { // verify
                this.verify_token(MUL);

            } else if (token.type.equals(DIV)) {
                this.verify_token(DIV);

            }
            node = (T) new BinOP(node, token, this.factor());
        }
        return node; // output returned

    }

    public T expr() throws Exception {

        T node = this.term();
        // loops through to check if next tokens are Multiplication or division
        while (this.current_token != null && (this.current_token.type.equals(PLUS) || this.current_token.type.equals(MINUS))) {
            // gets the token 
            Token token = this.current_token;
            if (token.type.equals(PLUS)) { // verify
                this.verify_token(PLUS);

            } else if (token.type.equals(MINUS)) {
                this.verify_token(MINUS);

            }
            node = (T) new BinOP(node, token, this.term());
        }
        return node; // output returned

    }

    public T parse() throws Exception {
        return this.expr();
    }
}

