package com.aconex.gedcom;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))) {
            GedcomConverter gedcomConverter = createGedcomConverter();
            String input;
            while ((input = br.readLine()) != null) {
                if (!input.isEmpty()) {
                    gedcomConverter.process(input);
                }
            }
            System.out.println(gedcomConverter.toXml());
        }
    }

    private static GedcomConverter createGedcomConverter() {
        GedcomParser gedcomParser = new GedcomParser();
        XmlElement xmlElement = new XmlElement("GEDCOM", null, null);
        return new GedcomConverter(gedcomParser, xmlElement);
    }
}
