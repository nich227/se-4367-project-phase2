package agent; 

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import java.util.Set;
import java.util.HashSet;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String mName;
    int line;
    Set<Integer> visited_vars;

    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName = name;
        this.visited_vars = new HashSet<Integer>();
    }

    // statement coverage collection
    @Override
    public void visitLineNumber(int line, Label start) {
        if(line != 0)
        {
        	this.line = line;
            
            mv.visitLdcInsn(mName);
            mv.visitLdcInsn(line);
        	mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "agent/CollectCoverage", "addCoveredLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        }
        
        super.visitLineNumber(line, start);
    }

    // visit a label
    @Override
    public void visitLabel(Label label) {
    	if(line != 0)
        {
            mv.visitLdcInsn(mName);
            mv.visitLdcInsn(line);
        	mv.visitMethodInsn(INVOKESTATIC, "java/lang/Integer", "valueOf", "(I)Ljava/lang/Integer;", false);
            mv.visitMethodInsn(INVOKESTATIC, "agent/CollectCoverage", "addCoveredLine", "(Ljava/lang/String;Ljava/lang/Integer;)V", false);
        }
        
        super.visitLabel(label);
    }

    // Visits a variable instruction
    @Override
    public void visitVarInsn(int opcode, int var) {
        if(visited_vars.contains(var)) {
            return;
        }

        visited_vars.add(var);
        String var_type = "";
        char char_type = (char)0;

        if(opcode == ILOAD){
            var_type = "java/lang/Integer";
            char_type = "I";
        }
        else if(opcode == FLOAD){
            var_type = "java/lang/Float";
            char_type = "F";
        }
        else if(opcode == LLOAD){
            var_type = "java/lang/Long";
            char_type = "L";
        }
        else if(opcode == DLOAD){
            var_type = "java/lang/Double";
            char_type = "D";
        }
        else if(opcode == ALOAD) {
            var_type = "java/lang/Object";
        }

        if((int)char_type != 0) {
            mv.visitMethodInsn(INVOKESTATIC, var_type, "valueOf", "(" + char_type + ")L" + var_type + ";", false);
        }
        mv.visitMethodInsn(INVOKESTATIC, "agent/CollectCoverage", "addLocalVar", "(Ljava/lang/Double;)V", false);

        super.visitVarInsn(opcode, var);
    }
}