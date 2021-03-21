package com.stef.spring.batch.jpa.listener.reader;

import com.stef.spring.batch.jpa.model.Pays;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;

@Slf4j
public class ReaderListener implements ItemReadListener< Pays > {
    @Override
    public void beforeRead() {
        log.debug("beforeRead");
    }

    @Override
    public void afterRead(Pays pays) {
        log.debug("afterRead: Pays {}" , pays.getId());
    }

    @Override
    public void onReadError(Exception e) {
        log.error("Erreur Reader",e);
    }

}
