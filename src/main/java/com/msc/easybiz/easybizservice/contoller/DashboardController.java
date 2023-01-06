package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.DashboardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController("/dashboard")
@RequestMapping(path = "/management/dashboard", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class DashboardController extends BaseController{

    private static final Logger logger = LoggerFactory.getLogger(DashboardController.class);

    @Autowired
    private DashboardService dashboardService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getExpenses() {
        logger.info("Request dashboard details");
        List<Map<String, Object>> data = (List<Map<String, Object>>) dashboardService.getProjects();
        for (Map<String, Object> item: data) {
            logger.info(item.get("project_code").toString());
            item.put("users", dashboardService.getProjectUsers(item.get("id")));
            List<Map<String, Object>> invoices = (List<Map<String, Object>>) dashboardService.getProjectInvoices(item.get("id"));

            for (Map<String, Object> invoice: invoices) {
                invoice.put("expenses", dashboardService.getInvoiceExpenses(invoice.get("id")));
            }
            item.put("invoices", invoices);
        }
        return new ResponseEntity<>(data, HttpStatus.OK);
    }
}
