package com.stef.spring.batch.jpa.listener.processor;

import com.stef.spring.batch.jpa.model.Pays;
import com.stef.spring.batch.jpa.model.PaysFrancais;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemProcessListener;

@Slf4j
public class ProcessListener implements ItemProcessListener< Pays, PaysFrancais > {
    @Override
    public void beforeProcess(Pays pays) {
        log.debug("beforeProcess");
    }

    @Override
    public void afterProcess(Pays pays, PaysFrancais paysFrancais) {
        log.debug("afterProcess: {}  ---> {}", pays, paysFrancais);
    }

    @Override
    public void onProcessError(Pays pays, Exception e) {
        log.error("Error Processor",e);
    }
}
