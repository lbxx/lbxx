package com.cdhaixun.test.service;

import com.cdhaixun.common.util.Pager;

public interface ITestService {
   Pager getMenuList(Pager pager, String name);
}
