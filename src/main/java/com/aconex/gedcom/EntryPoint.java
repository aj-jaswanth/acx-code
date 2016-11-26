package com.aconex.gedcom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class EntryPoint {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        GedcomParser gedcomParser = new GedcomParser();
        XmlElement xmlElement = new XmlElement("GEDCOM", null, null);
        GedcomConverter gedcomConverter = new GedcomConverter(gedcomParser, xmlElement);

        String input;
        while ((input = br.readLine()) != null) {
            gedcomConverter.process(input);
        }
        System.out.println(gedcomConverter.toXml());
    }
}
