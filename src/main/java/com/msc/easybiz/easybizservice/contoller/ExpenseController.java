package com.msc.easybiz.easybizservice.contoller;

import com.msc.easybiz.easybizservice.service.ExpenseService;
import com.msc.easybiz.easybizservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("/expense")
@RequestMapping(path = "/management/expense", consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
public class ExpenseController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @GetMapping(path = "/")
    public ResponseEntity<?> getExpenses() {
        logger.info("Request to get all expenses");
        return new ResponseEntity<>(expenseService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{status}")
    public ResponseEntity<?> getExpense(@PathVariable("status") String status) {
        logger.info("Request to get expense details for id : {}", status);
        return new ResponseEntity<>(expenseService.getAll(status), HttpStatus.OK);
    }

    @PostMapping(path = "/")
    public ResponseEntity<?> addExpense(@RequestBody String body) {
        logger.info("Request to add a new expense");
        Map<String,String> data = util.getData(body);
        return new ResponseEntity<>(expenseService.insert(data.get("project_id"), data.get("invoice_id"),data.get("amount"),data.get("record_date"),
                data.get("description"), data.get("exp_status"), data.get("is_active")), HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateExpense(@PathVariable("id") String expId, @RequestBody String body) {
        logger.info("Request to update expense details for id : {}", expId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(expenseService.update(data.get("amount"), data.get("description"), data.get("record_date"), data.get("exp_status"), expId), HttpStatus.OK);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable("id") String expId) {
        logger.info("Request to delete expense with code: {}", expId);
        expenseService.delete(expId);
        return new ResponseEntity<>(expenseService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/requests/")
    public ResponseEntity<?> getRequests() {
        logger.info("Request to get all requested expenses");
        return new ResponseEntity<>(expenseService.getAllRequests(), HttpStatus.OK);
    }

    @PutMapping(path = "/status/{id}")
    public ResponseEntity<?> updateStatus(@PathVariable("id") String expId, @RequestBody String body) {
        logger.info("Request to update status details for id : {}", expId);
        Map<String, String> data = util.getData(body);
        return new ResponseEntity<>(expenseService.updateStatus(data.get("exp_status"), expId), HttpStatus.OK);
    }
}
