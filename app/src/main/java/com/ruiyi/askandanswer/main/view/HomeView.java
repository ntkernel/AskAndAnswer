package com.ruiyi.askandanswer.main.view;

import com.ruiyi.askandanswer.base.BaseView;
import com.ruiyi.askandanswer.main.model.response.BannerData;
import com.ruiyi.askandanswer.main.model.response.HomeCanKnow;

import java.util.List;

public interface HomeView extends BaseView {

    void onCanKnowResult(HomeCanKnow homeCanKnow);

    void onBannerResult(List<BannerData> bannerData);
}
