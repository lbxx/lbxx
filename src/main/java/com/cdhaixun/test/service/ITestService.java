package com.cdhaixun.test.service;

import com.cdhaixun.common.util.Pager;
import com.cdhaixun.domain.Menu;

import java.util.List;

public interface ITestService {
    List<Menu> getMenuList(Pager pager);
}
