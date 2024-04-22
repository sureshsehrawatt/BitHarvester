package com.boldbit.bitharvester.Services;

import org.springframework.stereotype.Service;

import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;

@Service
public interface DataService {
    String processcode(StaticSourceFile staticSourceFile);
}
