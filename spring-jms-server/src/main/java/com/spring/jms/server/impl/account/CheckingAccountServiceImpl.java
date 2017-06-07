package com.spring.jms.server.impl.account;

import com.spring.jms.service.CheckingAccountService;
import org.springframework.stereotype.Service;

/**
 * Created by bangnl on 5/29/17.
 */
@Service("checkingAccountServiceJms")
public class CheckingAccountServiceImpl implements CheckingAccountService{

    @Override
    public String cancelAccount(Long accountId) {
        return "Cancelling account ["+ accountId.toString() + "]";
    }
}
