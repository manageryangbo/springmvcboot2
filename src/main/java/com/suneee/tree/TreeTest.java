/**
 * Copyright (C), 2015-2018, XXX有限公司
 * FileName: TreeTest
 * Author:   martin
 * Date:     2018/4/8 16:31
 * Description: 测试属性菜单类
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名          修改时间          版本号            描述
 */
package com.suneee.tree;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TreeTest {
    //菜单树形结构
    public static JSONArray treeMenuList(JSONArray menuList, int parentId) {
        JSONArray childMenu = new JSONArray();
        for (Object object : menuList) {
            JSONObject jsonMenu = JSONObject.fromObject(object);
            int menuId =jsonMenu.getInt("id") ;
            int pid = jsonMenu.getInt("parentId");
            if (parentId == pid) {
                JSONArray c_node = treeMenuList(menuList, menuId);
                jsonMenu.put("childNode", c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }

    public static void main(String args[]) {
        JSONArray jsonArray = new JSONArray();
        Menu menu1 = new Menu();
        menu1.setId(1);
        menu1.setParentId(0);
        menu1.setLevel(0);
        Menu menu2 = new Menu();
        menu2.setId(2);
        menu2.setParentId(0);
        menu2.setLevel(0);
        Menu menu3 = new Menu();
        menu3.setId(3);
        menu3.setParentId(2);
        menu3.setLevel(1);
        Menu menu4 = new Menu();
        menu4.setId(4);
        menu4.setParentId(2);
        menu4.setLevel(1);
        Menu menu5 = new Menu();
        menu5.setId(5);
        menu5.setParentId(4);
        menu5.setLevel(2);
        Menu menu6 = new Menu();
        menu6.setId(6);
        menu6.setParentId(1);
        menu6.setLevel(1);

        jsonArray.add(menu1);
        jsonArray.add(menu2);
        jsonArray.add(menu3);
        jsonArray.add(menu4);
        jsonArray.add(menu5);
        jsonArray.add(menu6);

        System.out.print(treeMenuList(jsonArray,0));
    }
}
