package com.boldbit.bitharvester.Services;

import org.springframework.stereotype.Service;

import com.boldbit.bitharvester.Harvester.compiler.Main;
import com.boldbit.bitharvester.Harvester.compiler.source.StaticSourceFile;

@Service
public class DataServiceImpl implements DataService {

    @Override
    public String processcode(StaticSourceFile staticSourceFile) {
        Main obj = new Main();
        return obj.processcode(staticSourceFile);
    }

}
