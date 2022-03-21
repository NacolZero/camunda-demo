package org.nacol.camundarestclient.config.thread;

import lombok.extern.log4j.Log4j2;
import org.camunda.community.rest.client.invoker.ApiCallback;
import org.camunda.community.rest.client.invoker.ApiException;

import java.util.Map;

@Log4j2
public class DefualtApiCallback implements ApiCallback {

    @Override
    public void onFailure(ApiException e, int i, Map map) {
        log.info("onSuccess, i : {}, Map : {}", i, map);
        e.printStackTrace();
    }

    @Override
    public void onSuccess(Object o, int i, Map map) {
       log.info("onSuccess, Object: {}, i : {}, Map : {}", o, i, map);
    }

    @Override
    public void onUploadProgress(long l, long l1, boolean b) {
        log.info("onUploadProgress, l : {}, l1 : {}, b : {}", 1, l1, b);
    }

    @Override
    public void onDownloadProgress(long l, long l1, boolean b) {
        log.info("onDownloadProgress, l : {}, l1 : {}, b : {}", 1, l1, b);
    }
}
