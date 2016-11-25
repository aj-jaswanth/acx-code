package com.aconex.gedcom;

public class GedcomParser {

    public XmlElement parse(String gedcomLine) {
        int spaceIndex = gedcomLine.indexOf(' ');
        String tag = gedcomLine.substring(0, spaceIndex);
        String data = gedcomLine.substring(spaceIndex + 1, gedcomLine.length());
        return new XmlElement(tag, data, null);
    }
}
