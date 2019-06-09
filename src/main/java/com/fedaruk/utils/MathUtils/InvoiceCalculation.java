package com.fedaruk.utils.MathUtils;

import com.fedaruk.model.Invoice;
import com.fedaruk.model.InvoicesHolder;
import org.apache.commons.math3.util.Precision;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class InvoiceCalculation {

    public static void calculateValuesForInvoices(InvoicesHolder holder, double primaryDeviation, double secondaryDeviation) {
        List<Invoice> filteredInvoices = holder.getInvoices().stream()
                .peek(InvoiceCalculation::calculateVATSum)
                .peek(InvoiceCalculation::calculateVATDeviation)
                .peek(invoice -> setDeviations(invoice, primaryDeviation, secondaryDeviation))
                .filter(getPrimaryDeviationFilter().or(getSecondaryDeviationFilter()))
                .peek(InvoiceCalculation::calculateColumnSumVAT)
                .peek(InvoiceCalculation::calculatePercent)
                .peek(InvoiceCalculation::calculateValueToChange)
                .peek(invoice -> invoice.setValueToChange(negateValue(invoice.getValueToChange())))
                .collect(Collectors.toList());
        holder.setInvoices(filteredInvoices);
    }

    private static void setDeviations(Invoice invoice, double primaryDeviation, double secondaryDeviation) {
        invoice.setPrimaryDeviation(primaryDeviation);
        invoice.setSecondaryDeviation(secondaryDeviation);
    }

    private static Predicate<Invoice> getPrimaryDeviationFilter () {
        return invoice -> ((invoice.getInvoiceCountry().equalsIgnoreCase("LU"))
                && ((invoice.getVATDeviation() >= invoice.getPrimaryDeviation()) || (invoice.getVATDeviation() <= negateValue(invoice.getPrimaryDeviation()))));
    }

    private static Predicate <Invoice> getSecondaryDeviationFilter () {
        return invoice -> ((!(invoice.getInvoiceCountry().equalsIgnoreCase("LU")))
                && ((invoice.getVATDeviation() >= invoice.getSecondaryDeviation()) || (invoice.getVATDeviation() <= negateValue(invoice.getSecondaryDeviation()))));
    }

    private static void calculateVATSum(Invoice invoice) {
        invoice.setVATSum(Precision.round(((invoice.getVATBasis() * invoice.getVATpersent()) / 100.0), 5));
    }

    private static void calculateVATDeviation(Invoice invoice) {
        invoice.setVATDeviation(Precision.round((invoice.getVATSum() - invoice.getVATamount()), 5));
    }

    private static void calculateColumnSumVAT(Invoice invoice) {
        invoice.setColumnSumVAT(Precision.round((invoice.getVATamount() + invoice.getVATBasis()), 5));
    }

    private static void calculatePercent(Invoice invoice) {
        invoice.setPercentCalculated(Precision.round((1 + invoice.getVATpersent() / 100), 2));
    }

    private static void calculateValueToChange(Invoice invoice) {
        invoice.setValueToChange(Precision.round(((invoice.getColumnSumVAT() / invoice.getPercentCalculated()) - invoice.getVATBasis()), 2));
    }

    private static double negateValue(Double value) {
        return -value;
    }
}
