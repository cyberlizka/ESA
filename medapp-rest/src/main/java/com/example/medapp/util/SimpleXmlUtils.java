package com.example.medapp.util;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class SimpleXmlUtils {

    public String toXmlWithXsl(Object object, String xslPath) throws Exception {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.findAndRegisterModules();

        String xml = xmlMapper.writeValueAsString(object);

        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<?xml-stylesheet type=\"text/xsl\" href=\"" + xslPath + "\"?>\n" +
                xml;
    }
}