// package com.boldbit.bitharvester.rough;

// import java.util.Arrays;

// // // import java.io.DataInputStream;
// // // import java.io.FileInputStream;
// // // import java.io.IOException;

// // // public class Rough {
// // //     public static void method() {
// // //         try (FileInputStream fis = new FileInputStream("/Users/cavisson/Documents/tmp/DataWarehouse.class");
// // //                 DataInputStream dis = new DataInputStream(fis)) {

// // //              // Read and display magic number, minor version, and major version
// // //              int magicNumber = dis.readInt();
// // //              int minorVersion = dis.readShort();
// // //              int majorVersion = dis.readShort();
 
// // //              System.out.println("Magic Number: " + Integer.toHexString(magicNumber));
// // //              System.out.println("Minor Version: " + minorVersion);
// // //              System.out.println("Major Version: " + majorVersion);
 
// // //              // Read constant pool count
// // //              int constantPoolCount = dis.readShort();
// // //              System.out.println("Constant Pool Count: " + constantPoolCount);
 
// // //              // Read constant pool entries (simplified, doesn't handle all entry types)
// // //              for (int i = 1; i < constantPoolCount; i++) {
// // //                  int tag = dis.readByte();
// // //                  System.out.println("Constant Pool Entry " + i + " - Tag: " + tag);
 
// // //                  // Read entry based on tag (simplified, doesn't handle all cases)
// // //                  if (tag == 1) { // UTF-8
// // //                      int length = dis.readShort();
// // //                      byte[] bytes = new byte[length];
// // //                      dis.readFully(bytes);
// // //                      String utf8Value = new String(bytes, "UTF-8");
// // //                      System.out.println("  UTF-8 Value: " + utf8Value);
// // //                  } else {
// // //                      // Handle other constant pool entry types if needed
// // //                  }
// // //              }
 
// // //              // Continue reading and printing other fields...
 
// // //          } catch (IOException e) {
// // //             e.printStackTrace();
// // //         }

// // //     }
// // // }
// // // // /Users/cavisson/Documents/tmp

// // import java.io.DataInputStream;
// // import java.io.FileInputStream;
// // import java.io.IOException;

// // public class Rough {

// //     public static void main(String[] args) {
// //         try (FileInputStream fis = new FileInputStream("/Users/cavisson/Documents/tmp/DataWarehouse.class");
// //              DataInputStream dis = new DataInputStream(fis)) {

// //             // Read and display magic number, minor version, and major version
// //             int magicNumber = dis.readInt();
// //             int minorVersion = dis.readShort();
// //             int majorVersion = dis.readShort();

// //             System.out.println("Magic Number: " + Integer.toHexString(magicNumber));
// //             System.out.println("Minor Version: " + minorVersion);
// //             System.out.println("Major Version: " + majorVersion);

// //             // Read constant pool count
// //             int constantPoolCount = dis.readShort();
// //             System.out.println("Constant Pool Count: " + constantPoolCount);

// //             // Read and display constant pool entries
// //             for (int i = 1; i < constantPoolCount; i++) {
// //                 int tag = dis.readByte();
// //                 System.out.println("Constant Pool Entry " + i + " - Tag: " + tag);

// //                 // Read entry based on tag
// //                 switch (tag) {
// //                     case 1: // UTF-8
// //                         int length = dis.readShort();
// //                         byte[] bytes = new byte[length];
// //                         dis.readFully(bytes);
// //                         String utf8Value = new String(bytes, "UTF-8");
// //                         System.out.println("  UTF-8 Value: " + utf8Value);
// //                         break;
// //                     // Add cases for other constant pool entry types if needed
// //                     default:
// //                         // Handle other cases if needed
// //                 }
// //             }

// //             // Read and display access flags, this class, and super class
// //             int accessFlags = dis.readShort();
// //             int thisClass = dis.readShort();
// //             int superClass = dis.readShort();

// //             System.out.println("Access Flags: " + accessFlags);
// //             System.out.println("This Class: " + thisClass);
// //             System.out.println("Super Class: " + superClass);

// //             // Read and display interfaces count and interfaces
// //             int interfacesCount = dis.readShort();
// //             System.out.println("Interfaces Count: " + interfacesCount);

// //             for (int i = 0; i < interfacesCount; i++) {
// //                 int interfaceIndex = dis.readShort();
// //                 System.out.println("Interface " + i + ": " + interfaceIndex);
// //             }

// //             // Read and display fields count and fields
// //             int fieldsCount = dis.readShort();
// //             System.out.println("Fields Count: " + fieldsCount);

// //             for (int i = 0; i < fieldsCount; i++) {
// //                 // Read and display fields information (simplified)
// //                 // ...
// //             }

// //             // Read and display methods count and methods
// //             int methodsCount = dis.readShort();
// //             System.out.println("Methods Count: " + methodsCount);

// //             for (int i = 0; i < methodsCount; i++) {
// //                 // Read and display methods information (simplified)
// //                 // ...
// //             }

// //             // Read and display attributes count and attributes
// //             int attributesCount = dis.readShort();
// //             System.out.println("Attributes Count: " + attributesCount);

// //             for (int i = 0; i < attributesCount; i++) {
// //                 int attributeNameIndex = dis.readShort();
// //                 System.out.println("Attribute " + i + " - Attribute Name Index: " + attributeNameIndex);

// //                 // Read attribute length
// //                 int attributeLength = dis.readInt();
// //                 System.out.println("  Attribute Length: " + attributeLength);

// //                 // Handle specific attributes based on the attribute name index
// //                 // This is a simplified example, and you may need to handle additional attribute types
// //                 switch (attributeNameIndex) {
// //                     case 1: // ConstantValue attribute
// //                         int constantValueIndex = dis.readShort();
// //                         System.out.println("  ConstantValue Index: " + constantValueIndex);
// //                         break;

// //                     // Add cases for other attribute types if needed

// //                     default:
// //                         // Handle unrecognized attribute or skip it
// //                         byte[] unknownAttributeData = new byte[attributeLength];
// //                         dis.readFully(unknownAttributeData);
// //                         System.out.println("  Unrecognized Attribute (skipped): " + byteArrayToHexString(unknownAttributeData));
// //                 }
// //             }

// //         } catch (IOException e) {
// //             e.printStackTrace();
// //         }
// //     }

// //     private static String byteArrayToHexString(byte[] byteArray) {
// //         StringBuilder hexStringBuilder = new StringBuilder();
// //         for (byte b : byteArray) {
// //             hexStringBuilder.append(String.format("%02X ", b));
// //         }
// //         return hexStringBuilder.toString().trim();

// //     }
// // }

// /**
//  * Rough
//  */
// public class Rough {

//     public static void main(String[] args) {
//         // int[] x = {120, 200, 011};
//         // System.out.println(Arrays.toString(x));
//         // for(int i=0; i<x.length; i++)
//             // System.out.println(x[i]);
//         out.println("fgvbhnjkm,l");
//     }
// }