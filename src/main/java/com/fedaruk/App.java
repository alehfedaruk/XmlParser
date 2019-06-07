package com.fedaruk;


import com.fedaruk.model.Invoice;
import com.fedaruk.model.InvoicesHolder;
import com.fedaruk.strategy.XMLSerializationStrategyImpl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws IOException {
//        InvoicesHolder holder = new InvoicesHolder();
//        List<Invoice> invoices = Arrays.asList(
//                new Invoice("/69637","LT","19208","Diesel",84.91,21,404.33),
//                new Invoice("/22222","PL","16485","AdBlue",145.01,23,28.43));
//        holder.setInvoices(invoices);
        XMLSerializationStrategyImpl serializationStrategy = new XMLSerializationStrategyImpl();
//        serializationStrategy.serializeObject(new File("C:\\Users\\a.fedaruk\\Java\\XMLVAT20PARSER\\deserializedObject.xml"),holder);

        InvoicesHolder holder1 = serializationStrategy.deserializeObject(new File("C:\\Users\\a.fedaruk\\Java\\XMLVAT20PARSER\\выгрузка фактур агенту1.xml"));
        holder1.getInvoices().forEach(System.out::println);
    }
}
