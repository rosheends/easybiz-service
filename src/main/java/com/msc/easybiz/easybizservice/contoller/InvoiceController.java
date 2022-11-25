package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.InvoiceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/invoice")
@RequestMapping(path = "/management/invoice", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class InvoiceController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(InvoiceController.class);

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getInvoices() {
        logger.info("Request to get all invoices");
        return new ResponseEntity<>(invoiceService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getInvoice(@PathVariable("id") String id) {
        logger.info("Request to get invoice :: {}", id);
        Map<String, Object> data = (Map<String, Object>) invoiceService.get(id);
        data.put("expenses", invoiceService.getExpenses(id));
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping(path = "/expense/{id}")
    public ResponseEntity<?> getExpense(@PathVariable("id") String projId) {
        logger.info("Request to get expense details for id : {}", projId);
        return new ResponseEntity<>(invoiceService.getProjExpenses(projId), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> createInvoice(@RequestBody String body) {
        logger.info("Request to add a new invoice");
        Map<String,String> data = util.getData(body);
        return new ResponseEntity<>(invoiceService.insert(data.get("user_id"), data.get("invoice_date"),data.get("due_date"),data.get("late_fee"),data.get("total_amount"), data.get("title"), data.get("payment_status")), HttpStatus.OK);
    }
}
