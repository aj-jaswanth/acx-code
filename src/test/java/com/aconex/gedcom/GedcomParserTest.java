package com.aconex.gedcom;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GedcomParserTest {

    private GedcomParser gedcomParser;

    @Before
    public void setUp() {
        this.gedcomParser = new GedcomParser();
    }

    @Test
    public void shouldParseLineHavingTagAndData() {
        XmlElement xmlElement = gedcomParser.parse("NAME James Gordon");

        assertThat(xmlElement.getMarkup(0), is("<name>James Gordon</name>"));
    }

    @Test
    public void shouldParseLineHavingIdAndData() {
        XmlElement xmlElement = gedcomParser.parse("@I1@ INDI");

        assertThat(xmlElement.getMarkup(0), is("<indi id=\"@I1@\"></indi>"));
    }

    @Test
    public void shouldParseLinesWithoutHavingData() {
        XmlElement xmlElement = gedcomParser.parse("NAME");

        assertThat(xmlElement.getMarkup(0), is("<name></name>"));
    }
}
