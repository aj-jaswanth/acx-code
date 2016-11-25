package com.aconex.gedcom;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

public class GedcomConverterTest {

    private GedcomConverter gedcomConverter;

    @Before
    public void setUp() {
        GedcomParser gedcomParser = mock(GedcomParser.class);
        this.gedcomConverter = new GedcomConverter(gedcomParser);
    }

    @Test
    public void shouldGiveEmptyGedcomXmlMarkupIfNoInputIsPresent() {
        String xml = gedcomConverter.toXml();

        assertThat(xml, is("<gedcom></gedcom>"));
    }
}