package org.infinity.dubbo.democommon.service;

import org.infinity.dubbo.democommon.dto.AdminMenuTreeDTO;

import java.util.List;

public interface AdminMenuService {

    List<AdminMenuTreeDTO> getMenus();

}