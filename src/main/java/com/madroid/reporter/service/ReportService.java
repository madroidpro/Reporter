package com.madroid.reporter.service;

import com.madroid.reporter.DataProvider.ChartData;
import com.madroid.reporter.DataProvider.DataProviderService;
import com.madroid.reporter.DataProvider.PieData;
import com.madroid.reporter.DataProvider.User;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private DataProviderService users;

    public String generateReport(String type) throws FileNotFoundException, JRException {
        String path = "C:\\Madhusudhan\\springboot\\reports";
        List<User> user = users.findAll();
        File file = ResourceUtils.getFile("classpath:report.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(user);
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("createdBy", "HND6KOR");
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if (type.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users.html");
        }
        if (type.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users.pdf");
        }

        return "report generated in path : " + path;

    }

    public String generateChartReport(String type) throws FileNotFoundException, JRException {
        String path = "C:\\Madhusudhan\\springboot\\reports";
        List<ChartData> cList = new ArrayList<ChartData>();
        cList.add(new ChartData("hoursNormal", "month1", 0.3)); //The use of resources or static text is beyond this example
        cList.add(new ChartData("hoursTravel", "month1", 3.2));
        cList.add(new ChartData("hoursOvertime", "month1", 1.3));
        cList.add(new ChartData("hoursNormal", "month2", 16.4));
        cList.add(new ChartData("hoursTravel", "month2", 5.2));
        cList.add(new ChartData("hoursOvertime", "month2", 4.1));

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("CHART_DATASET", new JRBeanCollectionDataSource(cList));

        File file = ResourceUtils.getFile("classpath:simplechart.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap);
        if (type.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users_chart.html");
        }
        if (type.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users_chart.pdf");
        }

        return "report generated in path : " + path;

    }

    public String generatePieChartReport(String type) throws FileNotFoundException, JRException {
        String path = "C:\\Madhusudhan\\springboot\\reports";
        List<PieData> cList = new ArrayList<PieData>();
        cList.add(new PieData("ele1", 30.0)); //The use of resources or static text is beyond this example
        cList.add(new PieData("ele2", 60.0)); //The use of resources or static text is beyond this example
        cList.add(new PieData("ele3", 80.0)); //The use of resources or static text is beyond this example
        cList.add(new PieData("ele4", 20.0)); //The use of resources or static text is beyond this example

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("PieData_SET", new JRBeanCollectionDataSource(cList));

        File file = ResourceUtils.getFile("classpath:piechart.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(cList);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap);
        if (type.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\users_chartpie.html");
        }
        if (type.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\users_chartpie.pdf");
        }

        return "report generated in path : " + path;

    }

    public String generateMultiChartReport(String type) throws FileNotFoundException, JRException {
        String path = "C:\\Madhusudhan\\springboot\\reports";
        List<PieData> cList = new ArrayList<PieData>();
        cList.add(new PieData("ele1", 30.0)); //The use of resources or static text is beyond this example
        cList.add(new PieData("ele2", 60.0)); //The use of resources or static text is beyond this example
        cList.add(new PieData("ele3", 80.0)); //The use of resources or static text is beyond this example
        cList.add(new PieData("ele4", 20.0)); //The use of resources or static text is beyond this example

        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("PieData_SET", new JRBeanCollectionDataSource(cList));

        List<ChartData> cList2 = new ArrayList<ChartData>();
        cList2.add(new ChartData("hoursNormal", "month1", 0.3)); //The use of resources or static text is beyond this example
        cList2.add(new ChartData("hoursTravel", "month1", 3.2));
        cList2.add(new ChartData("hoursOvertime", "month1", 1.3));
        cList2.add(new ChartData("hoursNormal", "month2", 16.4));
        cList2.add(new ChartData("hoursTravel", "month2", 5.2));
        cList2.add(new ChartData("hoursOvertime", "month2", 4.1));

        paramMap.put("CHART_DATASET", new JRBeanCollectionDataSource(cList2));

        File file = ResourceUtils.getFile("classpath:multichart.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, paramMap);
        if (type.equalsIgnoreCase("html")) {
            JasperExportManager.exportReportToHtmlFile(jasperPrint, path + "\\multi_chart.html");
        }
        if (type.equalsIgnoreCase("pdf")) {
            JasperExportManager.exportReportToPdfFile(jasperPrint, path + "\\multi_chart.pdf");
        }

        return "report generated in path : " + path;

    }
}
