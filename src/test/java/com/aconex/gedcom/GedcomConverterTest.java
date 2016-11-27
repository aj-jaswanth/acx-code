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
        when(rootXmlElement.getMarkup(0)).thenReturn("<root></root>");

        assertThat(gedcomConverter.toXml(), is("<root></root>"));
    }

    @Test
    public void shouldAddXmlElementAsChildToTheRootXmlElementIfLevelIsZero() {
        when(gedcomParser.parse("TAG DATA")).thenReturn(new XmlElement("TAG", "DATA", null));

        gedcomConverter.process("0 TAG DATA");

        verify(rootXmlElement, times(1)).addChild(any(XmlElement.class));
    }

    @Test
    public void shouldAddXmlElementAsSiblingToTheRootElementIfLevelIsZeroForTwoConsecutiveEntries() {
        XmlElement mockXmlElement = mock(XmlElement.class);
        when(gedcomParser.parse("TAG1 DATA1")).thenReturn(mockXmlElement);
        when(gedcomParser.parse("TAG2 DATA2")).thenReturn(mock(XmlElement.class));
        when(mockXmlElement.getParent()).thenReturn(rootXmlElement);

        gedcomConverter.process("0 TAG1 DATA1");
        gedcomConverter.process("0 TAG2 DATA2");

        verify(rootXmlElement, times(2)).addChild(any(XmlElement.class));
    }

    @Test
    public void shouldAddXmlElementAsChildToCurrentNodeIfLevelIncreasesSubsequently() {
        XmlElement mockXmlElement = mock(XmlElement.class);
        when(gedcomParser.parse("TAG1 DATA1")).thenReturn(mockXmlElement);
        when(gedcomParser.parse("TAG2 DATA2")).thenReturn(new XmlElement("TAG2", "DATA2", null));

        gedcomConverter.process("0 TAG1 DATA1");
        gedcomConverter.process("1 TAG2 DATA2");

        verify(rootXmlElement, times(1)).addChild(any(XmlElement.class));
        verify(mockXmlElement, times(1)).addChild(any(XmlElement.class));
    }

    @Test
    public void shouldProperlyTraverseTheTreeUpwardsToInsertElementsInCorrectOrder() {
        XmlElement mockXmlElement1 = mock(XmlElement.class);
        when(gedcomParser.parse("TAG1 DATA1")).thenReturn(mockXmlElement1);
        XmlElement mockXmlElement2 = mock(XmlElement.class);
        when(gedcomParser.parse("TAG2 DATA2")).thenReturn(mockXmlElement2);
        when(gedcomParser.parse("TAG3 DATA3")).thenReturn(new XmlElement("TAG3", "DATA3", null));
        when(mockXmlElement1.getParent()).thenReturn(rootXmlElement);
        when(mockXmlElement2.getParent()).thenReturn(mockXmlElement1);
        gedcomConverter.process("0 TAG1 DATA1");
        gedcomConverter.process("1 TAG2 DATA2");
        gedcomConverter.process("0 TAG3 DATA3");

        verify(rootXmlElement, times(2)).addChild(any(XmlElement.class));
        verify(mockXmlElement1, times(1)).addChild(any(XmlElement.class));
    }
}