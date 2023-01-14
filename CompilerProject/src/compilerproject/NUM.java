/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

/**
 *
 * @author MUHAMMAD FASEEH
 * @param <T>
 */
public class NUM<T> extends AST {

    public T Token;
    public int value;

    public NUM(Token token) {
        super();
        this.Token = (T) token;
        this.value = (int) token.value;
    }
}
