package com.embrace.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author embrace
 * @Description
 * @Date create in  2020/12/3 20:43
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto <T>{
    private Long id;
    private String serial;
}
