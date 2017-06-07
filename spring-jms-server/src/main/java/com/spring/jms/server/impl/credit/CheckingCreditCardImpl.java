package com.spring.jms.server.impl.credit;

import com.spring.jms.service.CheckingCreditCardService;
import org.springframework.stereotype.Service;

/**
 * Created by bangnl on 6/3/17.
 */
@Service("checkingCreditCardService")
public class CheckingCreditCardImpl implements CheckingCreditCardService {
    @Override
    public String cancelCreditCard(String id) {
        return "Cancel credit card id: " + id;
    }
}
