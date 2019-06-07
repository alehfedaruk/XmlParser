package com.fedaruk.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "InvoiceLine")
@JsonPropertyOrder({"ProductCode", "VATamount", "VATpersent", "VATBasis"})
public class InvoiceLine {
    @JsonProperty(value = "ProductCode")
    private String ProductCode;
    @JsonProperty(value = "VATamount")
    private double VATamount;
    @JsonProperty(value = "VATpersent")
    private double VATpersent;
    @JsonProperty(value = "VATBasis")
    private double VATBasis;

    public InvoiceLine() {
    }

    public InvoiceLine(String productCode, double VATamount, double VATpersent, double VATBasis) {
        ProductCode = productCode;
        this.VATamount = VATamount;
        this.VATpersent = VATpersent;
        this.VATBasis = VATBasis;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public double getVATamount() {
        return VATamount;
    }

    public void setVATamount(double VATamount) {
        this.VATamount = VATamount;
    }

    public double getVATpersent() {
        return VATpersent;
    }

    public void setVATpersent(double VATpersent) {
        this.VATpersent = VATpersent;
    }

    public double getVATBasis() {
        return VATBasis;
    }

    public void setVATBasis(double VATBasis) {
        this.VATBasis = VATBasis;
    }

    @Override
    public String toString() {
        return "InvoiceLines{" +
                "ProductCode='" + ProductCode + '\'' +
                ", VATamount=" + VATamount +
                ", VATpersent=" + VATpersent +
                ", VATBasis=" + VATBasis +
                '}';
    }
}
