package com.aconex.gedcom;

import java.util.ArrayList;
import java.util.List;

public class XmlElement {
    private final String tag;
    private final String content;
    private String[] attribute;
    private List<XmlElement> children = new ArrayList<>();
    private XmlElement parent;

    public XmlElement(String tag, String content, String[] attribute) {
        this.tag = tag.toLowerCase();
        this.content = content;
        this.attribute = attribute;
    }

    public String getMarkup(int numberOfTabs) {
        String tabs = new String(new char[numberOfTabs]).replace('\0', '\t');
        if (children.isEmpty())
            return String.format("%s<%s%s>%s</%s>", tabs, tag, getAttribute(), getContent(), tag);
        else
            return String.format("%s<%s%s%s>%s%s</%s>", tabs, tag, getContentAsValueAttribute(), getAttribute(), getChildrenMarkup(numberOfTabs + 1), tabs, tag);
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

    public void addChild(XmlElement xmlElement) {
        children.add(xmlElement);
        xmlElement.setParent(this);
    }

    private String getChildrenMarkup(int numberOfTabs) {
        StringBuilder markup = new StringBuilder("\n");
        for (XmlElement xmlElement : children) {
            markup.append(xmlElement.getMarkup(numberOfTabs)).append("\n");
        }
        return markup.toString();
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
