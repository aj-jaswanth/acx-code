package com.aconex.gedcom;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlElementTest {

    @Test
    public void shouldReturnXmlElementWithNoContent() {
        XmlElement xmlElement = new XmlElement("TAG", null, null);
        assertThat(xmlElement.toString(), is("<tag></tag>"));
    }

    @Test
    public void shouldReturnXmlElementWithContent() {
        XmlElement xmlElement = new XmlElement("TAG", "content", null);
        assertThat(xmlElement.toString(), is("<tag>content</tag>"));
    }

    @Test
    public void shouldReturnXmlElementWithAttribute() {
        String[] attribute = {"height", "21"};
        XmlElement xmlElement = new XmlElement("DIV", "content", attribute);

        assertThat(xmlElement.toString(), is("<div height=\"21\">content</div>"));
    }
}