package com.cinsc.meituan.util;

import com.sankuai.sjst.platform.developer.domain.RequestDomain;
import com.sankuai.sjst.platform.developer.domain.RequestMethod;
import com.sankuai.sjst.platform.developer.request.CipCaterStringPairRequest;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Getter
@Setter
public class CipCaterTakeOutCommentQueryRequest extends CipCaterStringPairRequest {
    private String ePoiId;
    private Long startTime;
    private Long endTime;
    private Integer offset;
    private Integer limit;

    public CipCaterTakeOutCommentQueryRequest() {
        this.url = RequestDomain.preUrl.getValue() + "/waimai/poi/queryReviewList";
        this.requestMethod = RequestMethod.GET;
    }

    @Override
    public Map<String, String> getParams() {
        return new HashMap<String, String>() {{
            put("ePoiId", ePoiId);
            put("startTime", startTime.toString());
            put("endTime", endTime.toString());
            put("offset", offset.toString());
            put("limit", limit.toString());
        }};
    }
    @Override
    public boolean paramsAbsent() {
        return ePoiId == null || ePoiId.trim().isEmpty() || startTime == null || endTime == null
                || offset == null || limit == null;
    }
}
