package com.stef.spring.batch.jpa.listener.writer;

import com.stef.spring.batch.jpa.model.PaysFrancais;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Slf4j
public class WriterListener implements ItemWriteListener< PaysFrancais > {
    @Override
    public void beforeWrite(List< ? extends PaysFrancais > list) {
        log.debug("beforeWrite");
    }

    @Override
    public void afterWrite(List< ? extends PaysFrancais > list) {
        list.forEach(paysFrancais -> log.debug("afterWrite :" + paysFrancais.toString()));
    }

    @Override
    public void onWriteError(Exception e, List< ? extends PaysFrancais > list) {
        log.error("Erreur Writer",e);
    }
}
