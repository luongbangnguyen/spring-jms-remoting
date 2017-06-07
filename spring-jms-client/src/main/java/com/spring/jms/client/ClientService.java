package com.spring.jms.client;

import com.spring.jms.service.CheckingAccountService;
import com.spring.jms.service.CheckingCreditCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by bangnl on 6/1/17.
 */
@Service
public class ClientService {

    @Autowired
    private CheckingAccountService checkingAccountService;

    @Autowired
    private CheckingCreditCardService checkingCreditCardService;

    public String executeServiceAccount(Long id){
        return this.checkingAccountService.cancelAccount(id);
    }

    public String executeServiceCreditCard(String id){
        return this.checkingCreditCardService.cancelCreditCard(id);
    }
}
