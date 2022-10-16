package com.hx.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hx.reggie.domain.AddressBook;
import com.hx.reggie.mapper.AddressBookMapper;
import com.hx.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @Description
 * @Author 229hexi
 * @Create 2022-10-06 15:28
 * @Version 1.0
 **/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
