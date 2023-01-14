/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

/**
 *
 * @author MUHAMMAD FASEEH
 */
public class Lexer {
     private char current_char ;
    private int pos;
    // final because it a constant value doesnt change
    private final String text; 
    private final String 
        INTEGER = "INTEGER", PLUS = "PLUS", MINUS = "MINUS", MUL = "MUL", DIV = "DIV", EOF = "EOF" ,
        LP = "LP" , RP ="RP";

    public Lexer(String text) {
        this.text = text; // input recieved from user
        this.pos = 0; // initializing
        this.current_char = this.text.charAt(this.pos); // getting the first charcter of string/grammer
    }
      // to handle unrecognized token
    public void error() throws Exception {
        throw new Exception("Invalid token");
    }

    // moving on to next character if less than expression
    public void advance() {
        this.pos = this.pos + 1;
        if (this.pos >= this.text.length()) {
            this.current_char = '\0';
        } else {
            this.current_char = this.text.charAt(this.pos);
        }
    }
        // skiping all white spaces
    public void skip_white_space() {
        while (this.current_char != '\0' && Character.isWhitespace(this.current_char)) {
            this.advance();
        }
    }
     // checking if current char is a digit then moving on to next
    // if next one is also a digit then concating them into a string
    // then converting the string to integer
    public int Integer() {
        String result = "";
        while (this.current_char != '\0' && Character.isDigit(this.current_char)) {
            result += this.current_char;
            this.advance();
        }
        return Integer.parseInt(result);
    }
        public Token get_next_token() throws Exception {

        // '\0' means null for character
        // running loop till not null
        while (this.current_char != '\0') {
            // checking if current char is white space or not
            if (Character.isWhitespace(this.current_char)) {
                this.skip_white_space(); // skipping space function called
                continue;
            } if (Character.isDigit(this.current_char)) { // if digit
                return new Token(INTEGER, this.Integer()); // new token with integer checking 
            }  if (this.current_char == '+') {
                this.advance();
                return new Token(PLUS, '+');
            }  if (this.current_char == '-') {
                this.advance();
                return new Token(MINUS, '-');
            }  if (this.current_char == '*') {
                this.advance();
                return new Token(MUL, '*');
            }  if (this.current_char == '/') {
                this.advance();
                return new Token(DIV, '/'); 
            }  if (this.current_char == '(') {
                this.advance();
                return new Token(LP, '(');
            } if (this.current_char == ')') {
                this.advance();
                return new Token(RP, ')');
            }  
        }
        return new Token(EOF , '\0');
    }
}

