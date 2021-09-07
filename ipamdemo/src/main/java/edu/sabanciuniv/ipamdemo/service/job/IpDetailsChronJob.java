package edu.sabanciuniv.ipamdemo.service.job;

import edu.sabanciuniv.ipamdemo.model.IpAddress;
import edu.sabanciuniv.ipamdemo.service.IpService;
import edu.sabanciuniv.ipamdemo.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class IpDetailsChronJob {
    private static final Logger LOG = Logger.getLogger(IpDetailsChronJob.class.getName());

    @Autowired
    IpService ipService;

    @Scheduled(cron = "0 0 3 * * *")
    public void updateIpDetails(){
        LOG.info("Job updateIpDetails() has been triggered.");
        List<IpAddress> ipAddresses = (List<IpAddress>) ipService.getAllIpAddresses().getResponse();

        for (IpAddress ipAddress:ipAddresses) {
            ipService.syncIp(ipAddress.getId());
        }
    }
}
