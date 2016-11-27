package com.aconex.gedcom;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class XmlElementTest {

    @Test
    public void shouldReturnXmlElementWithNoContent() {
        XmlElement xmlElement = new XmlElement("TAG", null, null);
        assertThat(xmlElement.getXmlMarkup(0), is("<tag></tag>"));
    }

    @Test
    public void shouldReturnXmlElementWithContent() {
        XmlElement xmlElement = new XmlElement("TAG", "content", null);
        assertThat(xmlElement.getXmlMarkup(0), is("<tag>content</tag>"));
    }

    @Test
    public void shouldReturnXmlElementWithAttribute() {
        String[] attribute = {"height", "21"};
        XmlElement xmlElement = new XmlElement("DIV", "content", attribute);

        assertThat(xmlElement.getXmlMarkup(0), is("<div height=\"21\">content</div>"));
    }

    @Test
    public void shouldFetchXmlMarkUpFromChildNodes() {
        XmlElement xmlElement = new XmlElement("VIDEO", null, null);
        XmlElement childXmlElement = new XmlElement("LENGTH", "60", null);
        xmlElement.addChildElement(childXmlElement);

        assertThat(xmlElement.getXmlMarkup(0), is("<video>\n\t<length>60</length>\n</video>"));
    }

    @Test
    public void shouldFetchXmlMarkupFromMultipleChildNodesProperly() {
        XmlElement xmlElement = new XmlElement("VIDEO", null, null);
        XmlElement childXmlElement1 = new XmlElement("LENGTH", "60", null);
        XmlElement childXmlElement2 = new XmlElement("SIZE", "2480", null);
        xmlElement.addChildElement(childXmlElement1);
        xmlElement.addChildElement(childXmlElement2);

        assertThat(xmlElement.getXmlMarkup(0), is("<video>\n\t<length>60</length>\n\t<size>2480</size>\n</video>"));
    }

    @Test
    public void shouldSetContentOfXmlElementAsValueWhenChildNodesArePresent() {
        XmlElement xmlElement = new XmlElement("VIDEO", "future_of_programming.mp4", null);
        XmlElement childXmlElement = new XmlElement("LENGTH", "60", null);
        xmlElement.addChildElement(childXmlElement);

        assertThat(xmlElement.getXmlMarkup(0), is("<video value=\"future_of_programming.mp4\">\n\t<length>60</length>\n</video>"));
    }

    @Test
    public void addingChildElementMustSetTheCurrentNodeAsItsParent() {
        XmlElement xmlElement = new XmlElement("VIDEO", "future_of_programming.mp4", null);
        XmlElement childXmlElement = mock(XmlElement.class);

        xmlElement.addChildElement(childXmlElement);

        verify(childXmlElement, times(1)).setParent(xmlElement);
    }

    @Test
    public void shouldGetCorrectParentElementForAChildElement() {
        XmlElement xmlElement = new XmlElement("VIDEO", "future_of_programming.mp4", null);
        XmlElement childXmlElement = new XmlElement("LENGTH", "60", null);
        xmlElement.addChildElement(childXmlElement);

        assertThat(childXmlElement.getParent(), is(xmlElement));
    }

    @Test
    public void shouldProperlyIndentGeneratedXmlMarkupWithChildElements() {
        XmlElement xmlElement = new XmlElement("VIDEO", "tesla_auto.mp4", null);
        XmlElement lengthXmlElement = new XmlElement("LENGTH", "60", null);
        XmlElement unitsXmlElement = new XmlElement("UNITS", "minutes", null);
        xmlElement.addChildElement(lengthXmlElement);
        lengthXmlElement.addChildElement(unitsXmlElement);

        assertThat(xmlElement.getXmlMarkup(0), is("<video value=\"tesla_auto.mp4\">\n\t<length value=\"60\">\n\t\t<units>minutes</units>\n\t</length>\n</video>"));
    }

    @Test
    public void shouldIncludeElementsAttributeAsWellAsItsContentAsValueAttributeWhenItHasChildren() {
        XmlElement xmlElement = new XmlElement("VIDEO", "future_of_programming.mp4", new String[]{"id", "2dba-2asf"});
        XmlElement childXmlElement = new XmlElement("LENGTH", "60", null);
        xmlElement.addChildElement(childXmlElement);

        assertThat(xmlElement.getXmlMarkup(0), is("<video value=\"future_of_programming.mp4\" id=\"2dba-2asf\">\n\t<length>60</length>\n</video>"));
    }
}
