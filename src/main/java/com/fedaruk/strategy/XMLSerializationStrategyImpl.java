package com.fedaruk.strategy;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fedaruk.model.InvoicesHolder;
import com.fedaruk.utils.MathUtils.InvoiceCalculation;

import java.io.File;
import java.io.IOException;

public class XMLSerializationStrategyImpl implements SerializationStrategy {
    private XmlMapper mapper = new XmlMapper();

    @Override
    public void serializeObject(File destination, InvoicesHolder holder) throws IOException {
        mapper = new XmlMapper();
        InvoiceCalculation.calculateValuesForInvoices(holder, 0.0054,0.024);
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        mapper.writerWithDefaultPrettyPrinter().writeValue(destination, holder);
    }

    @Override
    public InvoicesHolder deserializeObject(File source) throws IOException {
        mapper = new XmlMapper();
        return mapper.readValue(source, InvoicesHolder.class);
    }
}
