package agent; 

import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.FieldVisitor;

class MethodTransformVisitor extends MethodVisitor implements Opcodes {

	String mName;
    int line;

    public MethodTransformVisitor(final MethodVisitor mv, String name) {
        super(ASM5, mv);
        this.mName = name;
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
    //Local Variables
    @Override
    public void visitLocalVariable(String name, String desc,String signature, Label start, Label end, int index) {
        System.out.println("local variable:"+name);
        System.out.println("local description:"+desc);
        System.out.println("local signature:"+signature);

        mv.visitLocalVariable(name, desc, signature, start, end, index);

    }



    @Override
    public void visitVarInsn(int opcode, int var) {
        System.out.println("var instruction"+Integer.toHexString(opcode));
        System.out.println("var value"+ var);
        mv.visitVarInsn(opcode, var);
    }


    @Override
    public void visitFieldInsn(final int opcode,final String owner,final String name,final String descriptor){
        System.out.println("field:"+name);
        System.out.println("field owner:"+owner);
        System.out.println("field descriptor:"+descriptor);
        mv.visitFieldInsn(opcode, owner, name, descriptor);

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
}