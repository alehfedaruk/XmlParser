package com.fedaruk;


import com.fedaruk.model.InvoicesHolder;
import com.fedaruk.strategy.XMLSerializationStrategyImpl;

import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        XMLSerializationStrategyImpl serializationStrategy = new XMLSerializationStrategyImpl();
        InvoicesHolder holder1 = serializationStrategy.deserializeObject(new File("C:\\Users\\Aleh\\IdeaProjects\\XMLVAT20PARSER\\target\\classes\\Vat.xml"));
        serializationStrategy.serializeObject(new File("C:\\Users\\Aleh\\IdeaProjects\\XMLVAT20PARSER\\target\\classes\\TestedFile.xml"), holder1);
    }
}
