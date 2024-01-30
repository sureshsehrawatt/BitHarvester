package com.boldbit.bitharvester.Harvester.Extractor.Fields.Data;

public class FieldsData {
    // Primitive data types
    public static final transient byte byteVar = 123; // Primitive: byte
    private byte byteSVar = 111; // Primitive: byte
    protected byte byteTVar = 52; // Primitive: byte
    Byte byteObjectVar = 100; // Wrapper: Byte
    short shortVar = 1000; // Primitive: short
    Short shortObjectVar = 1000; // Wrapper: Short
    int intVar = 100000; // Primitive: int
    Integer integerVar = 9876543; // Wrapper: Integer
    int intSecondVar = 1234; // Primitive: int
    long longVar = 1000000000L; // Primitive: long
    Long longObjectVar = 1000000000L; // Wrapper: Long
    float floatVar = 3.14f; // Primitive: float
    Float floatObjectVar = 3.14f; // Wrapper: Float
    double doubleVar = 3.14159265359; // Primitive: double
    Double doubleObjectVar = 3.14159265359; // Wrapper: Double
    char charVar = 'A'; // Primitive: char
    Character charObjectVar = 'A'; // Wrapper: Character
    boolean boolVar = true; // Primitive: boolean
    Boolean boolObjectVar = true; // Wrapper: Boolean

    // Non-primitive data types
    String stringVar = "Hello, Java!"; // String is a class
    int[] intArray = { 1, 2, 3, 4, 5 }; // int[] is an array
    Object objectVar = new Object(); // Object is a class

}