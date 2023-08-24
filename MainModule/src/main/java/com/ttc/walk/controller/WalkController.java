package com.ttc.walk.controller;

import com.ttc.spring.controller.BaseController;
import com.ttc.spring.model.BaseResponse;
import com.ttc.walk.num.TimePeriod;
import com.ttc.walk.request.UpdateStepsDailyRequest;
import com.ttc.walk.response.UserWalkDataResponse;
import com.ttc.walk.service.WalkService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/walking")
@RequiredArgsConstructor
@Api(tags = "API dữ liệu đi bộ momo")
public class WalkController extends BaseController {
    private final WalkService service;
    @PutMapping("/update")
    public BaseResponse<Void> rejectTransaction(@Valid @RequestBody UpdateStepsDailyRequest request) {
        service.update(request);
        return BaseResponse.ofSucceeded();
    }

    @GetMapping("/daily-steps")
    public BaseResponse<UserWalkDataResponse> getDailySteps() {
        return BaseResponse.ofSucceeded(service.getDailySteps());
    }

    @GetMapping("/weekly-steps")
    public BaseResponse<UserWalkDataResponse> getWeeklySteps() {
        return BaseResponse.ofSucceeded(service.getWeeklySteps());
    }

    @GetMapping("/monthly-steps")
    public BaseResponse<UserWalkDataResponse> getMonthlySteps() {
        return BaseResponse.ofSucceeded(service.getMonthlySteps());
    }

    @GetMapping("/statistic")
    public BaseResponse<List<UserWalkDataResponse>> getStatistic(@RequestParam TimePeriod timePeriod) {
        return BaseResponse.ofSucceeded(service.getStatistic(timePeriod));
    }


}
