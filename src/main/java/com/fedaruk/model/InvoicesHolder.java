package com.fedaruk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "InvoiceListForVATrecovery")
public class InvoicesHolder implements Serializable {

//    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "InvoiceList")
    private List<Invoice> invoices;

    public InvoicesHolder() {
        invoices = new LinkedList<>();
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    @Override
    public String toString() {
        return "InvoicesHolder{" +
                "invoices=" + invoices +
                '}';
    }
}
