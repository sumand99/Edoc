package com.infogain.edoc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

import fr.opensagres.xdocreport.converter.Options;
import fr.opensagres.xdocreport.converter.XDocConverterException;

@Component
public class MainApp {

	public static void ConvertToPdf(String src, String desc) throws FileNotFoundException, XDocConverterException {
		Options options = null;
		Converter converter = new Converter();
		File file1 = new File(src);
		InputStream in = new FileInputStream(file1);
		File file2 = new File(desc);
		OutputStream out = new FileOutputStream(file2);
		converter.convert(in, out, options);
	}

}
