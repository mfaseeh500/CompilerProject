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
public class BinOP<T> extends AST {
    public T left;
    public T right;
    public Token op;
    public Token token;
    public BinOP(T left,Token op,T right){
      super();
      this.left =(T)  left;
      this.token =  this.op = op;
      this.right = (T) right;
    }
}
