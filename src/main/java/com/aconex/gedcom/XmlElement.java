package com.aconex.gedcom;

public class XmlElement {
    private final String tag;
    private final String content;
    private String[] attribute;

    public XmlElement(String tag, String content, String[] attribute) {
        this.tag = tag.toLowerCase();
        this.content = content;
        this.attribute = attribute;
    }

    @Override
    public String toString() {
        return String.format("<%s%s>%s</%s>", tag, getAttribute(), getContent(), tag);
    }

    private String getContent() {
        return content == null ? "" : content;
    }

    private String getAttribute() {
        if (attribute != null) {
            return String.format(" %s=\"%s\"", attribute[0], attribute[1]);
        }
        return "";
    }
}
