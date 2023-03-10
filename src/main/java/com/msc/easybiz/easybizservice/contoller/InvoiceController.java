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

    @GetMapping(path = "/client/{id}")
    public ResponseEntity<?> getClientInvAll(@PathVariable("id") String userId) {
        logger.info("Request to get inv for client id : {}", userId);
        return new ResponseEntity<>(invoiceService.getClientInvAll(userId), HttpStatus.OK);
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
        Map<String,String> cDetails = (Map<String, String>) invoiceService.getProjClient(data.get("project_id"));
        Map<String,String> mP = (Map<String, String>) invoiceService.insert(cDetails.get("user_id"), data.get("invoice_date"),data.get("due_date"),data.get("late_fee"),data.get("total_amount"), data.get("title"), data.get("payment_status"), data.get("project_id"));
        return new ResponseEntity<>(invoiceService.updateInvoiceId(mP.get("id"),mP.get("project_id")), HttpStatus.OK);
    }

    @PutMapping(path = "/expense/{id}")
    public ResponseEntity<?> updatePaidStatus(@PathVariable("id") String projId, @RequestBody String body) {
        logger.info("Request to update paid status of expenses for id : {}", projId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(invoiceService.update(data.get("is_paid"), projId), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateInvoicePaidStatus(@PathVariable("id") String invId, @RequestBody String body) {
        logger.info("Request to update paid status of invoice for id : {}", invId);
        Map<String, String> data = util.getData(body);
        Map<String, String> res = (Map<String, String>) invoiceService.updateInvPaymentStatus(data.get("payment_status"), invId);
        return new ResponseEntity<>(invoiceService.update(invId),HttpStatus.OK);
    }

    @PutMapping(path = "/expense/inv/{id}")
    public ResponseEntity<?> updateInvId(@PathVariable("id") String projId, @RequestBody String body) {
        logger.info("Request to update invoice id of expense record for id : {}", projId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(invoiceService.update(data.get("invoice_id"), projId), HttpStatus.OK);
    }
}
