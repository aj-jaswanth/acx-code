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

        assertThat(xmlElement.toString(), is("<name>James Gordon</name>"));
    }

    @Test
    public void shouldParseLineHavingIdAndData() {
        XmlElement xmlElement = gedcomParser.parse("@I1@ INDI");

        assertThat(xmlElement.toString(), is("<indi id=\"@I1@\"></indi>"));
    }
}
