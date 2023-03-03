package softuni.exam.service;


import java.io.IOException;

// TODO: Implement all methods
public interface CountryService {

    boolean areImported();

    String readCountriesFromFile() throws IOException;
	
	String importCountries() throws IOException;
}
