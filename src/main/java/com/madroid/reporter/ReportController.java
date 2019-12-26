package com.madroid.reporter;

import com.madroid.reporter.DataProvider.DataProviderService;
import com.madroid.reporter.service.PdfGenerator;
import com.madroid.reporter.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileNotFoundException;

@RestController
@RequestMapping("/Reports")
public class ReportController {
    @Autowired
    private DataProviderService service;

    @Autowired
    private ReportService reportService;

    @Autowired
    private PdfGenerator util;

    @GetMapping("/Users/{type}")
    public String generatereport(@PathVariable String type) throws FileNotFoundException, JRException {
        return reportService.generateReport(type);

    }

    @GetMapping("/UsersChart/{type}")
    public String generateChart(@PathVariable String type) throws FileNotFoundException, JRException {
        return reportService.generateChartReport(type);
    }

    @GetMapping("/UsersChartPie/{type}")
    public String generateChartPie(@PathVariable String type) throws FileNotFoundException, JRException {
        return reportService.generatePieChartReport(type);
    }

    @GetMapping("/MultiChart/{type}")
    public String generateMultiChartReport(@PathVariable String type) throws FileNotFoundException, JRException {
        return reportService.generateMultiChartReport(type);
    }

    @GetMapping("/GeneratePDF")
    public String getPDFView(Model model) throws Exception {
//        List<User> users = getUser();
//        Map<Object, Object> data = new HashMap<>();
//        data.put("users", users);
        util.createPdf("homepage");
        model.addAttribute("message", "PDF Downloaded successfully..");
        return "success";
    }
}
