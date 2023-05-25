package edu.uoc.tfg.crm.application.kafka;

import edu.uoc.tfg.crm.SesionData;
import edu.uoc.tfg.crm.domain.service.CrmService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class KafkaClassListener {

    @Autowired
    private CrmService crmService;

    @KafkaListener(topics = KafkaConstants.TOPIC_SESSION, groupId = "group-1")
    void setSession(SesionData sesion) {
        log.trace("SessionSet");

        crmService.setSession(sesion);
    }

}