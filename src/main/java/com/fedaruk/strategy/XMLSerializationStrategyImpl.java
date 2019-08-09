package com.fedaruk.strategy;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.fedaruk.model.InvoicesHolder;
import com.fedaruk.utils.MathUtils.InvoiceCalculation;

import java.io.File;
import java.io.IOException;

public class XMLSerializationStrategyImpl implements SerializationStrategy {
    private XmlMapper mapper;

    @Override
    public void serializeObject(File destination, InvoicesHolder holder, double primaryDeviation, double secondaryDeviation) throws IOException {
        mapper = new XmlMapper();
        InvoiceCalculation.calculateValuesForInvoices(holder, primaryDeviation, secondaryDeviation);
        mapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
        mapper.writerWithDefaultPrettyPrinter().writeValue(destination, holder);
    }

    @Override
    public InvoicesHolder deserializeObject(File source) throws IOException {
        mapper = new XmlMapper();
        return mapper.readValue(source, InvoicesHolder.class);
    }
}
