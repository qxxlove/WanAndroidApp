package com.tjbool.httpwww.wanandroid.app.utils;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2018/7/28.
 * 邮箱：123123@163.com
 */

public class BottomNavagationUtils {


    /**
     * 解决三个menu以上各怪异现象
     * @param view
     */
    public static void disableShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i =0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("BNVEffect","Unable to get shift mode field", e);
        } catch (IllegalAccessException e) {
            Log.e("BNVEffect","Unable to change value of shift mode", e);
        }
    }
}
