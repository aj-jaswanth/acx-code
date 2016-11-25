package com.aconex.gedcom;

public class GedcomConverter {
    private GedcomParser gedcomParser;
    private XmlElement rootXmlElement;

    public GedcomConverter(GedcomParser gedcomParser, XmlElement rootXmlElement) {
        this.gedcomParser = gedcomParser;
        this.rootXmlElement = rootXmlElement;
    }

    public String toXml() {
        return rootXmlElement.toString();
    }
}
