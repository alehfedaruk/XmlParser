package com.fedaruk.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "Invoice")
@JsonPropertyOrder({"E100customerKey", "InvoiceNumber", "InvoiceCountry","ProductCode","VATBasis","VATpersent","VATamount",
        "VATSum","VATDeviation","ColumnSumVAT","PercentCalculated","valueToChange"})
public class Invoice implements Serializable {

    @JsonProperty
    private String InvoiceNumber;
    @JsonProperty
    private String InvoiceCountry;
    @JsonProperty
    private String E100customerKey;
    @JsonProperty(value = "ProductCode")
    private String ProductCode;
    @JsonProperty(value = "VATamount")
    private Double VATamount;
    @JsonProperty(value = "VATpersent")
    private Double VATpersent;
    @JsonProperty(value = "VATBasis")
    private Double VATBasis;

    ///// Calculated fields /////
    @JsonProperty(value = "VATSum")
    private Double VATSum;
    @JsonProperty(value = "VATDeviation")
    private Double VATDeviation;
    @JsonProperty(value = "ColumnSumVAT")
    private Double ColumnSumVAT;
    @JsonProperty(value = "PercentCalculated")
    private Double PercentCalculated;
    @JsonProperty(value = "valueToChange")
    private Double valueToChange;

    @JsonIgnore
    private double primaryDeviation;
    @JsonIgnore
    private double secondaryDeviation;

    public Invoice() {
    }

    @JsonIgnore
    public String getInvoiceNumber() {
        return InvoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.InvoiceNumber = invoiceNumber;
    }

    @JsonIgnore
    public String getInvoiceCountry() {
        return InvoiceCountry;
    }

    public void setInvoiceCountry(String invoiceCountry) {
        this.InvoiceCountry = invoiceCountry;
    }

    @JsonIgnore
    public String getE100customerKey() {
        return E100customerKey;
    }

    public void setE100customerKey(String e100customerKey) {
        this.E100customerKey = e100customerKey;
    }

    @JsonIgnore
    public Double getVATamount() {
        return VATamount;
    }

    public void setVATamount(Double VATamount) {
        this.VATamount = VATamount;
    }

    @JsonIgnore
    public Double getVATpersent() {
        return VATpersent;
    }

    public void setVATpersent(Double VATpersent) {
        this.VATpersent = VATpersent;
    }

    @JsonIgnore
    public Double getVATBasis() {
        return VATBasis;
    }

    public void setVATBasis(Double VATBasis) {
        this.VATBasis = VATBasis;
    }

    @JsonIgnore
    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    @JsonIgnore
    public Double getVATSum() {
        return VATSum;
    }

    public void setVATSum(Double VATSum) {
        this.VATSum = VATSum;
    }

    @JsonIgnore
    public Double getVATDeviation() {
        return VATDeviation;
    }

    public void setVATDeviation(Double VATDeviation) {
        this.VATDeviation = VATDeviation;
    }

    @JsonIgnore
    public Double getColumnSumVAT() {
        return ColumnSumVAT;
    }

    public void setColumnSumVAT(Double columnSumVAT) {
        ColumnSumVAT = columnSumVAT;
    }

    @JsonIgnore
    public Double getPercentCalculated() {
        return PercentCalculated;
    }

    public void setPercentCalculated(Double percentCalculated) {
        PercentCalculated = percentCalculated;
    }

    @JsonIgnore
    public Double getValueToChange() {
        return valueToChange;
    }

    public void setValueToChange(Double valueToChange) {
        this.valueToChange = valueToChange;
    }

    @JsonIgnore
    public double getPrimaryDeviation() {
        return primaryDeviation;
    }

    @JsonIgnore
    public void setPrimaryDeviation(double primaryDeviation) {
        this.primaryDeviation = primaryDeviation;
    }

    @JsonIgnore
    public double getSecondaryDeviation() {
        return secondaryDeviation;
    }

    @JsonIgnore
    public void setSecondaryDeviation(double secondaryDeviation) {
        this.secondaryDeviation = secondaryDeviation;
    }

    @SuppressWarnings("unchecked")
    @JsonProperty(value = "InvoiceLines")
    private void unpackNested(Map<String, Object> invoiceLines) {
        Map<String, Object> InvoiceLine = (Map<String, Object>) invoiceLines.get("InvoiceLine");
        this.ProductCode = (String) InvoiceLine.get("ProductCode");
        this.VATamount = Double.valueOf(InvoiceLine.get("VATamount").toString().replaceAll(",", "."));
        this.VATpersent = Double.valueOf(InvoiceLine.get("VATpersent").toString().replaceAll(",", "."));
        this.VATBasis = Double.valueOf(InvoiceLine.get("VATBasis").toString().replaceAll(",", "."));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return Objects.equals(InvoiceNumber, invoice.InvoiceNumber) &&
                Objects.equals(E100customerKey, invoice.E100customerKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(InvoiceNumber, E100customerKey);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "InvoiceNumber='" + InvoiceNumber + '\'' +
                ", InvoiceCountry='" + InvoiceCountry + '\'' +
                ", E100customerKey='" + E100customerKey + '\'' +
                ", ProductCode='" + ProductCode + '\'' +
                ", VATamount=" + VATamount +
                ", VATpersent=" + VATpersent +
                ", VATBasis=" + VATBasis +
                ", VATSum=" + VATSum +
                ", VATDeviation=" + VATDeviation +
                ", ColumnSumVAT=" + ColumnSumVAT +
                ", PercentCalculated=" + PercentCalculated +
                ", valueToChange=" + valueToChange +
                ", primaryDeviation=" + primaryDeviation +
                ", secondaryDeviation=" + secondaryDeviation +
                '}';
    }
}
