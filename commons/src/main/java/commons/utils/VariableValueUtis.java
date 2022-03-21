package commons.utils;

import com.alibaba.fastjson.JSONObject;
import org.camunda.community.rest.client.dto.VariableValueDto;

import java.util.HashMap;
import java.util.Map;

public class VariableValueUtis {

    public static Map<String, VariableValueDto> mapToVars(Map<String, Object> map) {
        Map<String, VariableValueDto> vars = new HashMap<>();
        map.forEach((k,v)->vars.put(k, new VariableValueDto().value(v)));
        return vars;
    }

    public static Map<String, VariableValueDto> jsonToVars(JSONObject json) {
        Map<String, VariableValueDto> vars = new HashMap<>();
        json.forEach((k,v)->vars.put(k, new VariableValueDto().value(v)));
        return vars;
    }

}
