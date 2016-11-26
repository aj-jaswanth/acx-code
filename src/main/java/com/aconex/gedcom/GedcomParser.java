package com.aconex.gedcom;

public class GedcomParser {

    public XmlElement parse(String gedcomLine) {
        String tag, data = null;
        if (gedcomLine.contains(" ")) {
            int spaceIndex = gedcomLine.indexOf(' ');
            tag = gedcomLine.substring(0, spaceIndex);
            data = gedcomLine.substring(spaceIndex + 1, gedcomLine.length());
        } else {
            tag = gedcomLine;
        }
        if (tag.matches("@.*@")) {
            return new XmlElement(data, null, new String[]{"id", tag});
        } else {
            return new XmlElement(tag, data, null);
        }
    }
}
