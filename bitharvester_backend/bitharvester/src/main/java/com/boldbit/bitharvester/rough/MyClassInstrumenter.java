package com.boldbit.bitharvester.rough;
import org.objectweb.asm.*;
import java.io.*;

public class MyClassInstrumenter {
    public static void main(String[] args) throws IOException {
        // Read the original class file
        FileInputStream fis = new FileInputStream("/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/rough/DataWarehouse.class");
        ClassReader cr = new ClassReader(fis);

        // Create a ClassWriter that will receive the modified bytecode
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
        
        // Create a ClassVisitor to visit the original class and apply modifications
        ClassVisitor cv = new MyClassVisitor(cw);

        // Apply the ClassVisitor to the original class
        cr.accept(cv, 0);

        // Write the modified bytecode to a new class file
        FileOutputStream fos = new FileOutputStream("MyClassModified.class");
        fos.write(cw.toByteArray());
        fos.close();
    }
}

class MyClassVisitor extends ClassVisitor {
    public MyClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM9, cv);
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        
        // Insert a println statement at the beginning of each method
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Entering method: " + name);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        
        return mv;
    }
}
