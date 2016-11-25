package com.aconex.gedcom;

public class XmlElement {
    private final String tag;
    private final String content;

    public XmlElement(String tag, String content) {
        this.tag = tag;
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("<%s></%s>", tag, tag);
    }
}
