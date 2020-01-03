package com.madroid.reporter;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.madroid.reporter.DataProvider.DataProviderService;
import com.madroid.reporter.DataProvider.User;
import com.madroid.reporter.DataProvider.UserList;
import com.madroid.reporter.service.PdfGenerator;
import com.madroid.reporter.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/Reports")
public class ReportController {
    @Autowired
    private DataProviderService service;

    @Autowired
    private ReportService reportService;

    @Autowired
    private PdfGenerator util;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${userlist.url}")
    private String url;

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

    @GetMapping("/MultiChartRest/{type}")
    public String generateMultiChartReportFromRest(@PathVariable String type) throws FileNotFoundException, JRException {
       System.out.println(url);
        UserList userList = restTemplate.getForObject(url.trim(), UserList.class);
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<List<User>> entity = new HttpEntity<List<User>>(headers);
//        List<User> userList = restTemplate.exchange("http://localhost:9099/User/getAllUsers", HttpMethod.GET, entity, new ParameterizedTypeReference<List<User>>() {
//        }).getBody();
        assert userList != null;
        return reportService.generateReportRest(userList.getUserList());
    }

    @GetMapping("/UserReport")
    public MappingJacksonValue getUserReport(){
        List<User> users = service.findAll();
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("name");
        FilterProvider filters = new SimpleFilterProvider().addFilter("UserFilter", filter);
        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);
        return mapping;
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
