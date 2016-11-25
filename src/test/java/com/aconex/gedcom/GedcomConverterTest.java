package com.aconex.gedcom;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class GedcomConverterTest {

    private GedcomConverter gedcomConverter;
    private GedcomParser gedcomParser;
    private XmlElement rootXmlElement;

    @Before
    public void setUp() {
        this.gedcomParser = mock(GedcomParser.class);
        this.rootXmlElement = mock(XmlElement.class);
        this.gedcomConverter = new GedcomConverter(gedcomParser, rootXmlElement);
    }

    @Test
    public void shouldReturnXmlMarkUpOfRootXmlElement() {
        when(rootXmlElement.toString()).thenReturn("<root></root>");

        assertThat(gedcomConverter.toXml(), is("<root></root>"));
    }

    @Test
    public void shouldAddXmlElementAsChildToTheRootXmlElementIfLevelIsZero() {
        when(gedcomParser.parse("TAG DATA")).thenReturn(new XmlElement("TAG", "DATA", null));

        gedcomConverter.process("0 TAG DATA");

        verify(rootXmlElement, times(1)).addChildElement(any(XmlElement.class));
    }

    @Test
    public void shouldAddXmlElementAsSiblingToTheRootElementIfLevelIsZeroForTwoConsecutiveEntries() {
        when(gedcomParser.parse("TAG1 DATA1")).thenReturn(new XmlElement("TAG1", "DATA1", null));
        when(gedcomParser.parse("TAG2 DATA2")).thenReturn(new XmlElement("TAG2", "DATA2", null));

        gedcomConverter.process("0 TAG1 DATA1");
        gedcomConverter.process("0 TAG2 DATA2");

        verify(rootXmlElement, times(2)).addChildElement(any(XmlElement.class));
    }
}