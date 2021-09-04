package edu.sabanciuniv.ipamdemo.service.job;

import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.service.IpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IpDetailsChronJob {
    @Autowired
    IpService ipService;

    @Scheduled(cron = "0 0 3 * * *")
    public void updateIpDetails(){
        List<IpAddress> ipAddresses = (List<IpAddress>) ipService.getAllIpAddresses().getResponse();

        for (IpAddress ipAddress:ipAddresses) {
            ipService.syncIp(ipAddress.getId());
        }
    }
}
