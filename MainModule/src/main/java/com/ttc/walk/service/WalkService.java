package com.ttc.walk.service;

import com.ttc.walk.num.TimePeriod;
import com.ttc.walk.request.UpdateStepsDailyRequest;
import com.ttc.walk.response.*;

import java.util.List;

public interface WalkService {
    void update(UpdateStepsDailyRequest request);
    UserWalkDataResponse getDailySteps();
    UserWalkDataResponse getWeeklySteps();
    UserWalkDataResponse getMonthlySteps();
    List<UserWalkDataResponse> getStatistic(TimePeriod timePeriod);
}
