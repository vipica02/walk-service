package com.ttc.walk.service.impl;


import com.ttc.walk.num.TimePeriod;
import com.ttc.walk.request.UpdateStepsDailyRequest;
import com.ttc.walk.response.UserWalkDataResponse;
import com.ttc.walk.service.WalkService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class WalkServiceImpl implements WalkService {

    @Override
    public void update(UpdateStepsDailyRequest request) {

    }
    @Override
    public UserWalkDataResponse getDailySteps() {
        return null;
    }

    @Override
    public UserWalkDataResponse getWeeklySteps() {
        return null;
    }

    @Override
    public UserWalkDataResponse getMonthlySteps() {
        return null;
    }

    @Override
    public List<UserWalkDataResponse> getStatistic(TimePeriod timePeriod) {
        return null;
    }
}
