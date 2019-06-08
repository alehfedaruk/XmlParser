package com.fedaruk.utils;

import com.fedaruk.model.Invoice;
import com.fedaruk.model.InvoicesHolder;
import org.apache.commons.math3.util.Precision;

import javax.xml.ws.Holder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Utils {

    public static String inputStreamToString(InputStream inputStream) {
        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
        return builder.toString();
    }

    public static InvoicesHolder calculateValuesForInvoices(InvoicesHolder holder) {
        Predicate<Invoice> withPrimaryDeviation = invoice -> ((invoice.getInvoiceCountry().equalsIgnoreCase("LU"))
                && ((invoice.getVATDeviation() >= invoice.getPrimaryDeviation()) || (invoice.getVATDeviation() <= negateValue(invoice.getPrimaryDeviation()))));

        Predicate<Invoice> withSecondaryDeviation =  invoice -> ((!(invoice.getInvoiceCountry().equalsIgnoreCase("LU")))
                && ((invoice.getVATDeviation() >= invoice.getSecondaryDeviation()) || (invoice.getVATDeviation() <= negateValue(invoice.getSecondaryDeviation()))));

        List<Invoice> filteredInvoices =
                holder.getInvoices().stream()
                        .peek(Utils::calculateVATSum)
                        .peek(Utils::calculateVATDeviation)
                        .peek(invoice -> setDeviations(invoice, 0.0054, 0.024))
                        .filter(withPrimaryDeviation.or(withSecondaryDeviation))
                        .peek(Utils::calculateColumnSumVAT)
                        .peek(Utils::calculatePercent)
                        .peek(Utils::calculateValueToChange)
                        .peek(invoice -> invoice.setValueToChange(negateValue(invoice.getValueToChange())))
                        .collect(Collectors.toList());
        holder.setInvoices(filteredInvoices);
        return holder;
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

    private static void setDeviations(Invoice invoice, double primaryDeviation, double secondaryDeviation) {
        invoice.setPrimaryDeviation(primaryDeviation);
        invoice.setSecondaryDeviation(secondaryDeviation);
    }
}
