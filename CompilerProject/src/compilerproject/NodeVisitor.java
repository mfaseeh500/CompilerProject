/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 *
 * @author MUHAMMAD FASEEH
 * @param <T>
 */
public class NodeVisitor<T> {

    public T visit(T node) throws NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method visitor;
        String method = "visit_" + node.getClass().getSimpleName();
        visitor = getattr(this.getClass(), method, "generic_visit");
        return (T) visitor.invoke(this, node);
    }

    public void generic_visit(T node) throws Exception {
        throw new Exception("Method Not found");
    }

    public Method getattr(Class c, String methodName, String exceptionMethodName) throws NoSuchMethodException {

        if (methodName.equals("visit_BinOP")) {
            return c.getMethod(methodName, BinOP.class);
        }
        if (methodName.equals("visit_NUM")) {
            return c.getMethod(methodName, NUM.class);
        }
        return c.getMethod("generic_visit", BinOP.class);

    }

}