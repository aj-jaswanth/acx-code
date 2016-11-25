package com.aconex.gedcom;

public class GedcomConverter {
    private GedcomParser gedcomParser;
    private XmlElement rootXmlElement;
    private XmlElement currentElement;
    private int currentDepth;

    public GedcomConverter(GedcomParser gedcomParser, XmlElement rootXmlElement) {
        this.gedcomParser = gedcomParser;
        this.rootXmlElement = rootXmlElement;
        this.currentElement = rootXmlElement;
        this.currentDepth = -1;
    }

    public String toXml() {
        return rootXmlElement.toString();
    }

    public void process(String line) {
        int spaceIndex = line.indexOf(' ');
        int depth = Integer.parseInt(line.substring(0, spaceIndex));

        String gedcomLine = line.substring(spaceIndex + 1, line.length());
        XmlElement xmlElement = gedcomParser.parse(gedcomLine);

        while (currentDepth >= depth) {
            currentDepth--;
            currentElement = currentElement.getParent();
        }

        currentElement.addChildElement(xmlElement);
        currentElement = xmlElement;
        currentDepth = depth;
    }
}