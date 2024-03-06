package com.boldbit.bitharvester.Harvester.CodeParser.Parsers.ClassInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PackageParser {
    public static Map<String, String> getPackageName(BufferedReader file) {
        Map<String, String> map=new HashMap<>();
        String line, packageName = "";
        try {
            while ((line = file.readLine()) != null) {
                if (line.startsWith("package ")) {
                    packageName = line.substring(8, line.lastIndexOf(';')).trim();
                    break;
                }
            }
            map.put("packageName", packageName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }
}
