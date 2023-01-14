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
public class Token<T> {

    public String type;
    public T value;

    public Token(String type, T value) {
        this.type = type;
        this.value = value;
    }
}