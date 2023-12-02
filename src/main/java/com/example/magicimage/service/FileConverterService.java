package com.example.magicimage.service;

import java.io.IOException;

public interface FileConverterService {
     void convert(String pdfFilePath, String outputFolderPath) throws IOException;
}

