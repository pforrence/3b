import java_cup.runtime.Symbol;
import java.io.*;
import java.util.*;
import visitor.*;
import syntaxtree.*;
import symboltable.*;

public class Compiler {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.err.println("ERROR: Invalid number of command line arguments.");
			System.err.println("Usage: java Interpreter file.asm");
			System.exit(1);
		}
		Symbol parse_tree = null;
		try {
			/*Parsing Phase*/

            Reader reader = new java.io.InputStreamReader(new java.io.FileInputStream(args[0]));
            ExprParser parser = new ExprParser(new ExprLex(reader));
            parse_tree = parser.parse();
            	
            //symboltable = new symboltablevisitor();
            Program program = ((Program) parse_tree.value);
            
            if(program != null) {
                
                //Visit the tree starting at root (program) to build the symbol table
                BuildSymbolTableVisitor bstVisitor = new BuildSymbolTableVisitor();
                bstVisitor.visit(program);
                
                //Get a copy of the symboltable
                Scope symbolTable = bstVisitor.getFirstScope();
                
                if(symbolTable == null) //Check for error making symbol table
                {
                    System.err.println("Oh no... the symbol table is null!");
                    return;
                }
                //symbolTable.print(0);
                //Visit the tree starting at the root to check for undefined variables
                UndefinedVariableVisitor undefinedVisitor = new UndefinedVariableVisitor(symbolTable);
                undefinedVisitor.visit(program);
                
                //Visit the tree starting at the root to check for type errors
                TypeCheckingVisitor typeCheckVisitor = new TypeCheckingVisitor(symbolTable);
                typeCheckVisitor.visit(program);
                
                //If any errors occured, do not generate IR
                if(bstVisitor.errorDetected || undefinedVisitor.errorDetected || typeCheckVisitor.errorDetected)
                {
                    return;
                }
                
                //program.accept(new IRVisitor(symboltable));
                //program.accept(new IRVisitor());
                Visitor IRVisit = new IRVisitor(symbolTable);
                System.out.println("Three Address Code: ");
                program.accept(IRVisit);
                for(int i = 0; i < ((IRVisitor)IRVisit).IR.size(); i++)
                    System.out.println(((IRVisitor)IRVisit).IR.get(i));
                ((IRVisitor)IRVisit).reset();
                
            }

		} catch (IOException e) {
			System.err.println("ERROR: Unable to open file: " + args[0]);
		} catch (Exception e) {
			e.printStackTrace(System.err);
		}
	}
}
