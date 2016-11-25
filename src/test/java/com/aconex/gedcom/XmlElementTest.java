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

    @Test
    public void shouldFetchXmlMarkUpFromChildNodes() {
        XmlElement xmlElement = new XmlElement("VIDEO", null, null);
        XmlElement childXmlElement = new XmlElement("LENGTH", "60", null);
        xmlElement.addChildElement(childXmlElement);

        assertThat(xmlElement.toString(), is("<video>\n\t<length>60</length>\n</video>"));
    }

    @Test
    public void shouldFetchXmlMarkupFromMultipleChildNodesProperly() {
        XmlElement xmlElement = new XmlElement("VIDEO", null, null);
        XmlElement childXmlElement1 = new XmlElement("LENGTH", "60", null);
        XmlElement childXmlElement2 = new XmlElement("SIZE", "2480", null);
        xmlElement.addChildElement(childXmlElement1);
        xmlElement.addChildElement(childXmlElement2);

        assertThat(xmlElement.toString(), is("<video>\n\t<length>60</length>\n\t<size>2480</size>\n</video>"));
    }

    @Test
    public void shouldSetContentOfXmlElementAsValueWhenChildNodesArePresent() {
        XmlElement xmlElement = new XmlElement("VIDEO", "future_of_programming.mp4", null);
        XmlElement childXmlElement = new XmlElement("LENGTH", "60", null);
        xmlElement.addChildElement(childXmlElement);

        assertThat(xmlElement.toString(), is("<video value=\"future_of_programming.mp4\">\n\t<length>60</length>\n</video>"));
    }
}
