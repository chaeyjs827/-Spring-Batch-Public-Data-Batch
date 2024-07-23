package com.perfect.public_data.domain.general.restaurant.listener;

import com.perfect.public_data.domain.general.restaurant.dto.GeneralRestaurantRow;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
@Slf4j
public class GeneralRestaurantReadListener implements ItemReadListener<GeneralRestaurantRow> {

    @Override
    public void onReadError(Exception ex) {
        if (ex instanceof FileNotFoundException) {
            log.error(ex.getMessage());
        }
    }

}
