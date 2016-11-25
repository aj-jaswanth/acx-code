package com.aconex.gedcom;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class XmlElementTest {

    @Test
    public void shouldReturnElementWithNoEmptyContent() {
        XmlElement xmlElement = new XmlElement("TAG", "");
        assertThat(xmlElement.toString(), is("<TAG></TAG>"));
    }
}
