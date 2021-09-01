package edu.sabanciuniv.ipamdemo.utils;

import org.apache.commons.net.util.*;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

public class NetworkUtils {


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


    public static void getIpDetails(String ipaddress){
        try {
            InetAddress remote = InetAddress.getByName(ipaddress);
            String hostName = remote.getHostName();
            System.out.println(hostName);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        System.out.println("ip details çalıştı");
    }

    public static LinkedHashMap<String, List<Integer>> getPortDetails(String ipaddress){

        List<Integer> openPortList = new ArrayList<>();
        List<Integer> closedPortList = new ArrayList<>();

        for (int port = 79; port <= 81; port++) {
            try {
                Socket socket = new Socket();
                socket.connect(new InetSocketAddress(ipaddress, port), 1000);
                socket.close();
                openPortList.add(port);
                System.out.println("Port " + port + " is open");
            } catch (Exception ex) {
                closedPortList.add(port);
                System.out.println("Port " + port + " is close");
            }
        }

        LinkedHashMap<String, List<Integer>> portDetails = new LinkedHashMap<>();
        portDetails.put("open", openPortList);
        portDetails.put("close", closedPortList);

        return portDetails;
    }


}
