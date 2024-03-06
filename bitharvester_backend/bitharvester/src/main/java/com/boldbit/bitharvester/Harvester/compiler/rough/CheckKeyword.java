// package rough;

// public class CheckKeyword {
//     private static String checkKeyword(String word) {
//         switch (word) {
//             // Primitive data types
//             case "boolean":
//             case "byte":
//             case "char":
//             case "double":
//             case "float":
//             case "int":
//             case "long":
//             case "short":
//                 return "PRIMITIVE_TYPE";

//             // Flow control
//             case "if":
//             case "else":
//             case "switch":
//             case "case":
//             case "default":
//             case "while":
//             case "do":
//             case "for":
//             case "break":
//             case "continue":
//             case "return":
//                 return "FLOW_CONTROL";

//             // Access modifiers
//             case "public":
//             case "private":
//             case "protected":
//             case "static":
//             case "final":
//             case "abstract":
//             case "synchronized":
//                 return "ACCESS_MODIFIER";

//             // Class-related keywords
//             case "class":
//             case "interface":
//             case "extends":
//             case "implements":
//             case "package":
//             case "import":
//                 return "CLASS_KEYWORD";

//             // Exception handling
//             case "try":
//             case "catch":
//             case "finally":
//             case "throw":
//             case "throws":
//                 return "EXCEPTION_HANDLING";

//             // Other keywords
//             case "this":
//             case "super":
//             case "new":
//             case "instanceof":
//             case "void":
//             case "transient":
//             case "volatile":
//             case "assert":
//             case "enum":
//             case "native":
//             case "strictfp":
//             case "const":
//                 return "OTHER_KEYWORD";

//             // Null keyword
//             case "null":
//                 return "NULL_KEYWORD";

//             default:
//                 return null;
//         }
//     }

// }
