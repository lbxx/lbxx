package com.cdhaixun.test.service;

import com.cdhaixun.common.util.Pager;
import com.cdhaixun.domain.Menu;
import com.github.pagehelper.Page;

import java.util.List;

public interface ITestService {
    List<Menu> getMenuList();
}
