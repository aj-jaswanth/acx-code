package com.aconex.gedcom;

import java.util.ArrayList;

public class XmlElement {
    private final String tag;
    private final String content;
    private String[] attribute;
    private ArrayList<XmlElement> children = new ArrayList<>();
    private XmlElement parent;

    public XmlElement(String tag, String content, String[] attribute) {
        this.tag = tag.toLowerCase();
        this.content = content;
        this.attribute = attribute;
    }

    public String getXmlMarkup(int tabSize) {
        String tabs = new String(new char[tabSize]).replace('\0', '\t');
        if (children.size() == 0)
            return String.format("%s<%s%s>%s</%s>", tabs, tag, getAttribute(), getContent(), tag);
        else
            return String.format("%s<%s%s>%s%s</%s>", tabs, tag, getContentAsValueAttribute(), getChildrenXmlMarkup(tabSize + 1), tabs, tag);
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

    public void addChildElement(XmlElement xmlElement) {
        children.add(xmlElement);
        xmlElement.setParent(this);
    }

    private String getChildrenXmlMarkup(int tabSize) {
        String markup = "\n";
        for (XmlElement xmlElement : children) {
            markup += xmlElement.getXmlMarkup(tabSize) + "\n";
        }
        return markup;
    }

    public String getContentAsValueAttribute() {
        if (content == null)
            return "";
        return String.format(" value=\"%s\"", content);
    }

    public XmlElement getParent() {
        return parent;
    }

    public void setParent(XmlElement parent) {
        this.parent = parent;
    }
}
