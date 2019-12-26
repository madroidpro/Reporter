package com.madroid.reporter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

@Component
public class PdfGenerator {
    @Autowired
    private TemplateEngine templateEngine;
    private String path = "C:\\Madhusudhan\\springboot\\reports\\";
    public void createPdf(String templateName)
            throws Exception {
        Assert.notNull(templateName, "The templateName can not be null");
        Context ctx = new Context();
      /*  if (map != null) {
            Iterator<Entry<Object, Object>> itMap = map.entrySet().iterator();
            while (itMap.hasNext()) {
                Map.Entry<Object, Object> pair = itMap.next();
                ctx.setVariable(pair.getKey().toString(), pair.getValue());
            }
        }*/
        String processedHtml = templateEngine.process(templateName, ctx);
        FileOutputStream os = null;
        String fileName = "users";
        try {
            final File outputFile = File.createTempFile(fileName, ".pdf",
                    new File(path));
            os = new FileOutputStream(outputFile);
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(processedHtml);
            renderer.layout();
            renderer.createPDF(os, false);
            renderer.finishPDF();
            System.out.println("PDF created successfully");
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) { /* ignore */
                }
            }
        }

    }
}
