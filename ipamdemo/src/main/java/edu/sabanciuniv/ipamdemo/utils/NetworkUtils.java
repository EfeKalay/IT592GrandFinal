package edu.sabanciuniv.ipamdemo.utils;

import edu.sabanciuniv.ipamdemo.controller.IpAddressResource;
import edu.sabanciuniv.ipamdemo.controller.NetworkResource;
import org.apache.commons.net.util.*;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Logger;

public class NetworkUtils {
    private static final Logger LOG = Logger.getLogger(NetworkUtils.class.getName());


    public static List<String> getIpsFromSubnetMask(String address , String mask){
        SubnetUtils utils = new SubnetUtils(address, mask);
        ArrayList<String> ipAddressList = (ArrayList<String>) Arrays.asList(utils.getInfo().getAllAddresses());
        return ipAddressList;
    }

    public static List<String> getIpsFromSubnetCIDR(String cidr){
        SubnetUtils utils = new SubnetUtils(cidr);
        String[] ipAddressArray = utils.getInfo().getAllAddresses();
        return Arrays.asList(ipAddressArray.clone());
    }


    public static String getHostName(String ipaddress){
        //ipaddress = "85.111.30.111";
        try {
            InetAddress remote = InetAddress.getByName(ipaddress);
            String hostName = remote.getHostName();
            LOG.info("Hostname:   " +hostName);
            return hostName;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "Hostname unavailable";
        }
    }

    public static String isIpReachable(String ipaddress){
        int[] ports = {21, 22, 25, 53, 80, 110, 143, 443, 587, 3306, 1433};
        for (int port : ports) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipaddress, port), 100);
                socket.close();
                LOG.info("IP Address ["+ipaddress+"] Port " + port + " is OPEN");
                return "Unavailable";
            } catch (Exception ex) {
                LOG.info("IP Address ["+ipaddress+"] Port " + port + " is CLOSE");
            }
        }
        return "Available";

    }

    public static LinkedHashMap<String, Object> getPortDetails(String ipaddress){

        int[] ports = {21, 22, 25, 53, 80, 110, 143, 443, 587, 3306, 1433};
        List<Integer> openPortList = new ArrayList<>();
        List<Integer> closedPortList = new ArrayList<>();

        for (int port : ports) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipaddress, port), 100);
                socket.close();
                openPortList.add(port);
                LOG.info("IP Address ["+ipaddress+"] Port " + port + " is OPEN");
            } catch (Exception ex) {
                closedPortList.add(port);
                LOG.info("IP Address ["+ipaddress+"] Port " + port + " is CLOSE");
            }
        }

        LinkedHashMap<String, Object> portDetails = new LinkedHashMap<>();
        portDetails.put("open", openPortList);
        portDetails.put("close", closedPortList);

        return portDetails;
    }


}
