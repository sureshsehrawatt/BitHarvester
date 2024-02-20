package com.boldbit.bitharvester.Harvester.ByteCodeParser.Extractor.Fields;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class FieldExtractor {
    private final Map<String, Map<String, Map<String, Map<String, String>>>> variables = new HashMap<>();

    public static void main(String[] args) {
        String filePath = "/Users/cavisson/Documents/Projects/bitharvester/bitharvester_backend/bitharvester/src/main/java/com/boldbit/bitharvester/Harvester/Extractor/Fields/Data/FieldsData.txt"; // Update with the actual file path
        try {
            FieldExtractor obj = new FieldExtractor();
            obj.extractReferences(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void extractReferences(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder bytecodeText = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                bytecodeText.append(line).append("\n");
            }
    
            Pattern fieldPattern = Pattern
                    .compile("(\\w+)\\s(\\w+);\\s+descriptor:\\s(\\S+)\\s+flags:\\s\\(([^)]+)\\)");
            Matcher matcher = fieldPattern.matcher(bytecodeText.toString());
            System.out.println("Field References:");
            while (matcher.find()) {
                String type = matcher.group(1);
                String name = matcher.group(2);
                String descriptor = matcher.group(3);
                String flags = matcher.group(4);
                if (flags.startsWith("0x")) {
                    flags = flags.substring(2); // Remove the "0x" prefix
                }

                Map<String, String> data = new HashMap<>();
                data.put("descriptor", descriptor);
                dataStore(type, name, "descriptor", data);

                Map<String, String> flagsMap = convertFlags(Integer.parseInt(flags, 16));
                dataStore(type, name, "flags", flagsMap);
            }
    
        }
        System.out.println("Done.....");
        convertToJson(variables);
    }    

    private void dataStore(String type, String name, String dataName, Map<String, String> data) {
        variables.computeIfAbsent(type, k -> new HashMap<>()).computeIfAbsent(name, k -> new HashMap<>()).put(dataName, data);
    }

    public static final int ACC_PUBLIC = 0x0001;
    public static final int ACC_PRIVATE = 0x0002;
    public static final int ACC_PROTECTED = 0x0004;
    public static final int ACC_STATIC = 0x0008;
    public static final int ACC_FINAL = 0x0010;
    public static final int ACC_SYNCHRONIZED = 0x0020;
    public static final int ACC_VOLATILE = 0x0040;
    public static final int ACC_TRANSIENT = 0x0080;
    public static final int ACC_NATIVE = 0x0100;
    public static final int ACC_INTERFACE = 0x0200;
    public static final int ACC_ABSTRACT = 0x0400;
    public static final int ACC_STRICT = 0x0800;

    private static final Map<Integer, String> flagMap = new HashMap<>();
    static {
        flagMap.put(ACC_PUBLIC, "ACC_PUBLIC");
        flagMap.put(ACC_PRIVATE, "ACC_PRIVATE");
        flagMap.put(ACC_PROTECTED, "ACC_PROTECTED");
        flagMap.put(ACC_STATIC, "ACC_STATIC");
        flagMap.put(ACC_FINAL, "ACC_FINAL");
        flagMap.put(ACC_SYNCHRONIZED, "ACC_SYNCHRONIZED");
        flagMap.put(ACC_VOLATILE, "ACC_VOLATILE");
        flagMap.put(ACC_TRANSIENT, "ACC_TRANSIENT");
        flagMap.put(ACC_NATIVE, "ACC_NATIVE");
        flagMap.put(ACC_INTERFACE, "ACC_INTERFACE");
        flagMap.put(ACC_ABSTRACT, "ACC_ABSTRACT");
        flagMap.put(ACC_STRICT, "ACC_STRICT");
    }

    public Map<String, String> convertFlags(int flags) {
        Map<String, String> map = new HashMap<>();
        for (Map.Entry<Integer, String> entry : flagMap.entrySet()) {
            int flag = entry.getKey();
            String name = entry.getValue();
            if ((flags & flag) != 0) {
                map.put(Integer.toHexString(flag), name);
            }
        }
        if(map.isEmpty()){
            map.put("0", "Default");
        }
        return map;
    }   
    
    public String convertToJson(Map<String, Map<String, Map<String, Map<String, String>>>> variables) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(variables);
        System.out.println(json);
        return json;
    }

}
