package com.aconex.gedcom;

public class GedcomConverter {
    private GedcomParser gedcomParser;
    private XmlElement gedcomXmlElement = new XmlElement("GEDCOM", null, null);

    public GedcomConverter(GedcomParser gedcomParser) {
        this.gedcomParser = gedcomParser;
    }

    public String toXml() {
        return gedcomXmlElement.toString();
    }
}
