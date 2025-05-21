package com.codercampus.Assignment11.web;

import com.codercampus.Assignment11.domain.Transaction;
import com.codercampus.Assignment11.service.TransactionService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping("/transactions")
    public String getTransactions(ModelMap model) {
        List<Transaction> transactions = transactionService.findAll();
        model.put("transactions", transactions);
        return "transactions";
    }

    @GetMapping("/transaction/{transactionId}")
    public String getTransactionById(@PathVariable Long transactionId, ModelMap model) {
        Transaction transaction = transactionService.findById(transactionId);
        model.addAttribute("transactionId", transactionId);
        model.addAttribute("transactionType", transaction.getType());
        model.addAttribute("transactionDate", transaction.getDate());
        model.addAttribute("transactionAmount", transaction.getAmount());
        model.addAttribute("transactionDescription", transaction.getDescription());
        return "transaction";
    }
}
