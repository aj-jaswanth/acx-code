package com.aconex.gedcom;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlElementTest {

    @Test
    public void shouldReturnXmlElementWithNoEmptyContent() {
        XmlElement xmlElement = new XmlElement("TAG", "");
        assertThat(xmlElement.toString(), is("<tag></tag>"));
    }

    @Test
    public void shouldReturnXmlElementWithContent() {
        XmlElement xmlElement = new XmlElement("TAG", "content");
        assertThat(xmlElement.toString(), is("<tag>content</tag>"));
    }
}
