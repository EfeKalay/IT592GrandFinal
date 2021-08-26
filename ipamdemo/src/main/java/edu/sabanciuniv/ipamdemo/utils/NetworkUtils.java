package edu.sabanciuniv.ipamdemo.utils;

import org.apache.commons.net.util.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NetworkUtils {


    public static List<String> getIpsFromSubnetMask(String address , String mask){
        //String address = "192.168.0.3";
        //String mask = "255.255.255.254";
        SubnetUtils utils = new SubnetUtils(address, mask);
        ArrayList<String> ipAddressList = (ArrayList<String>) Arrays.asList(utils.getInfo().getAllAddresses());

        return ipAddressList;
    }

    public static List<String> getIpsFromSubnetCIDR(String cidr){
       // String cidr = "192.168.0.3/31";
        SubnetUtils utils = new SubnetUtils(cidr);
        String[] ipAddressArray = utils.getInfo().getAllAddresses();


        return Arrays.asList(ipAddressArray.clone());
    }
}
