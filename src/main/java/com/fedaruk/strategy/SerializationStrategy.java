package com.fedaruk.strategy;

import com.fedaruk.model.InvoicesHolder;

import java.io.File;
import java.io.IOException;

public interface SerializationStrategy {
    void serializeObject(File file, InvoicesHolder holder) throws IOException;
    InvoicesHolder deserializeObject(File file) throws IOException;
}
