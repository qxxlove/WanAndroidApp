package com.tjbool.httpwww.wanandroid.mvp.ui.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.tjbool.httpwww.wanandroid.R;
import com.tjbool.httpwww.wanandroid.app.base.BaseActivity;
import com.tjbool.httpwww.wanandroid.app.base.BaseFragment;
import com.tjbool.httpwww.wanandroid.app.utils.BottomNavagationUtils;
import com.tjbool.httpwww.wanandroid.app.utils.StatusBarUtil;
import com.tjbool.httpwww.wanandroid.dagger.component.ApplicationComponent;
import com.tjbool.httpwww.wanandroid.dagger.component.DaggerMainComponent;
import com.tjbool.httpwww.wanandroid.dagger.module.MainModule;
import com.tjbool.httpwww.wanandroid.mvp.contract.MainContract;
import com.tjbool.httpwww.wanandroid.mvp.model.api.Constants;
import com.tjbool.httpwww.wanandroid.mvp.presenter.MainPresenter;
import com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt.HomeFramgent;
import com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt.KnowledgeSystemFragment;
import com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt.NavigationFragment;
import com.tjbool.httpwww.wanandroid.mvp.ui.fragemnt.ProjectFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * description: 主界面
 * autour: TMM
 * date: 2018/7/25 11:20
 * update: 2018/7/25
 * version:
 *
 *     dagger 学习
 *     https://www.jianshu.com/p/8fd84680939c
 *
 */

public class MainActivity extends BaseActivity<MainPresenter> implements MainContract.View {

    @BindView(R.id.group_main_activity)
    FrameLayout groupMainActivity;
    @BindView(R.id.bottom_navigation_view_main_activity)
    BottomNavigationView bottomNavigationViewMainActivity;
    @BindView(R.id.common_toolbar_title_tv)
    TextView commonToolbarTitleTv;
    @BindView(R.id.common_toolbar)
    Toolbar commonToolbar;

    private ArrayList<BaseFragment> mFragments;
    private TextView mUsTv;
    private HomeFramgent mMainPagerFragment;
    private KnowledgeSystemFragment mKnowledgeHierarchyFragment;
    private NavigationFragment mNavigationFragment;
    private ProjectFragment mProjectFragment;
    private int mLastFgIndex;


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        initToolbar();
        mFragments = new ArrayList<>();
        BottomNavagationUtils.disableShiftMode(bottomNavigationViewMainActivity);
        if (savedInstanceState == null) {
            mMainPagerFragment = HomeFramgent.getInstance(false, null);
            mFragments.add(mMainPagerFragment);
            initFragemnts();
            initClick();
            switchFragment(Constants.TYPE_MAIN_PAGER);
        } else {
            bottomNavigationViewMainActivity.setSelectedItemId(R.id.tab_main_pager);
            mMainPagerFragment = HomeFramgent.getInstance(true, null);
            mFragments.add(mMainPagerFragment);
            initFragemnts();
            initClick();
            switchFragment(Constants.TYPE_SETTING);
        }
    }

    /**
     * 初始化Fragment 
     */
    private void initFragemnts() {
        mKnowledgeHierarchyFragment = KnowledgeSystemFragment.getInstance(null, null);
        mNavigationFragment = NavigationFragment.getInstance(null, null);
        mProjectFragment = ProjectFragment.getInstance(null, null);

        mFragments.add(mKnowledgeHierarchyFragment);
        mFragments.add(mNavigationFragment);
        mFragments.add(mProjectFragment);
    }

    /**
     * 切换fragment
     * @param position 要显示的fragment的下标
     */
    private void switchFragment(int position) {
        if (position >= mFragments.size()) {
            return;
        }
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment targetFg = mFragments.get(position);
        Fragment lastFg = mFragments.get(mLastFgIndex);
        mLastFgIndex = position;
        ft.hide(lastFg);
        if (!targetFg.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(targetFg).commit();
            ft.add(R.id.group_main_activity, targetFg);
        }
        ft.show(targetFg);
        ft.commitAllowingStateLoss();
    }

    

    
    private void initClick() {
        bottomNavigationViewMainActivity.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.tab_main_pager:
                        commonToolbarTitleTv.setText(getString(R.string.home_pager));
                        switchFragment(0);
                        //  mMainPagerFragment.reload();
                        //  mPresenter.setCurrentPage(Constants.TYPE_MAIN_PAGER);
                        break;
                    case R.id.tab_knowledge_hierarchy:
                        commonToolbarTitleTv.setText(getString(R.string.knowledge_hierarchy));
                        switchFragment(1);
                        // mKnowledgeHierarchyFragment.reload();
                        //  mPresenter.setCurrentPage(Constants.TYPE_KNOWLEDGE);
                        break;
                    case R.id.tab_navigation:
                        switchNavigation();
                        break;
                    case R.id.tab_project:
                        switchProject();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
     
    }

    private void switchProject() {
        commonToolbarTitleTv.setText(getString(R.string.project));
        switchFragment(3);
    }

    private void switchNavigation() {
        commonToolbarTitleTv.setText(getString(R.string.navigation));
        switchFragment(2);
    }


    private void initToolbar() {
        setSupportActionBar(commonToolbar);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(false);
        commonToolbarTitleTv.setText(getString(R.string.home_pager));
        StatusBarUtil.setStatusColor(getWindow(), ContextCompat.getColor(this, R.color.main_status_bar_blue), 1f);
        //mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }



    @Override
    public void initInjector(ApplicationComponent appComponent) {
        DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build()
                .inject(this);
    }

   

   

    @Override
    public void showSuccess() {

    }

    @Override
    public void showFaild() {

    }

    @Override
    public void showNoNet() {

    }

    @Override
    public void onRetry() {

    }


}
