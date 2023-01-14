/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilerproject;

import java.util.Scanner;

/**
 *
 * @author MUHAMMAD FASEEH
 */
public class CompilerProject {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        Scanner myObj = new Scanner(System.in);
        String input = myObj.nextLine();
        Lexer lexer = new Lexer(input);
        Parser parser = new Parser(lexer);
        Interpretor intepretor = new Interpretor(parser);
        int result = intepretor.interpret();
        System.out.println("YOUR ANSWER IS: ");
        System.out.println(result);
    }

}

    

