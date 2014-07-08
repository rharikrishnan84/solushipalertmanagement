package com.meritconinc.mmr.utilities;

import java.util.Comparator;

import com.meritconinc.mmr.model.common.MenuItemVO;

public class MenuItemVOComparator implements Comparator {
	public int compare(Object o1, Object o2) {
		MenuItemVO mVO1 = (MenuItemVO)o1;
		MenuItemVO mVO2 = (MenuItemVO)o2;
		return (mVO1.getId() == mVO2.getId())?0:-1;
	} 
}
