package com.embrace.springcloud.service.impl;

import com.embrace.springcloud.dao.PaymentDao;
import com.embrace.springcloud.entities.PaymentDto;
import com.embrace.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/11/25 22:20
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Resource
    private PaymentDao paymentDao;

    @Override
    public int save(PaymentDto paymentDto) {
        return paymentDao.save(paymentDto);
    }

    @Override
    public PaymentDto getPaymentById(long id) {
        return paymentDao.getPaymentById(id);
    }
}
