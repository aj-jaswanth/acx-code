package com.aconex.gedcom;

public class XmlElement {
    private final String tag;
    private final String content;
    private String[] attribute;

    public XmlElement(String tag, String content) {
        this.tag = tag.toLowerCase();
        this.content = content;
    }

    public XmlElement(String tag, String content, String[] attribute) {
        this(tag, content);
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        String xmlAttribute = "";
        if (attribute != null) {
            xmlAttribute = String.format(" %s=\"%s\"", attribute[0], attribute[1]);
        }
        return String.format("<%s%s>%s</%s>", tag, xmlAttribute, content, tag);
    }
}
