package com.boldbit.bitharvester;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.boldbit.bitharvester.Services.codeanalysis.CodeExtractor;
import com.boldbit.bitharvester.assets.AsciiLogo;

@SpringBootApplication
public class BitharvesterApplication {

	public static void main(String[] args) {
		SpringApplication.run(BitharvesterApplication.class, args);
		System.out.println(AsciiLogo.ASCII_LOGO_FUNKY);
		// CodeExtractor.extract();

	}
}


/*
 5c5iu521OmDu1h0g
 mongodb+srv://vemoge8230:5c5iu521OmDu1h0g@bitmongocluster.4lbfcrd.mongodb.net/?retryWrites=true&w=majority
 mongodb+srv://vemoge8230:<password>@bitmongocluster.4lbfcrd.mongodb.net/
 */