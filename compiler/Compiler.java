package compiler;

import ast.Program;
import parser.ParserWrapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Compiler {
    // Process return codes
    public static final int EXIT_SUCCESS = 0;
    public static final int EXIT_PARSING_ERROR = 1;

    public static void main(String[] args) {
        String filename = args[0];
        Program astRoot = null;
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(filename));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        try {
            astRoot = ParserWrapper.parse(reader, filename);
        } catch (Exception ex) {
            fatalError("Uncaught parsing error: " + ex, EXIT_PARSING_ERROR);
        }

        // Simplistic pretty printing of the AST
        astRoot.print(System.out);

        System.exit(EXIT_SUCCESS);
    }

    public static void fatalError(String message, int processReturnCode) {
        System.out.println(message);
        System.exit(processReturnCode);
    }
}
