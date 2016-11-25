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

    public void process(String line) {
        int spaceIndex = line.indexOf(' ');
        int depth = Integer.parseInt(line.substring(0, spaceIndex));

        String gedcomLine = line.substring(spaceIndex + 1, line.length());
        XmlElement xmlElement = gedcomParser.parse(gedcomLine);

        if (depth == 0) {
            rootXmlElement.addChildElement(xmlElement);
        }
    }
}
