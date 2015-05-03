package ua.com.tracksee.logic.driver;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.postgresql.geometric.PGpath;
import ua.com.tracksee.dao.TaxiOrderDAO;
import ua.com.tracksee.entities.ServiceUserEntity;
import ua.com.tracksee.entities.TaxiOrderEntity;
import ua.com.tracksee.entities.TaxiOrderItemEntity;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Maria Komar on 30.04.2015.
 */
@Stateless
public class DriverOrderBean {
    @EJB
    private TaxiOrderDAO taxiOrderDao;

    public List<TaxiOrderEntity> getAvailableOrders(ServiceUserEntity driver){
       return taxiOrderDao.getAvailableOrders(driver);
    }

    public String getAddressByGpsCoordinates(String lng, String lat)
            throws MalformedURLException, IOException, org.json.simple.parser.ParseException {

        URL url = new URL("http://maps.googleapis.com/maps/api/geocode/json?latlng="
                + lat + "," + lng + "&sensor=true");
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        String formattedAddress = "";

        try {
            InputStream in = url.openStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));
            String result, line = reader.readLine();
            result = line;
            while ((line = reader.readLine()) != null) {
                result += line;
            }

            JSONParser parser = new JSONParser();
            JSONObject rsp = (JSONObject) parser.parse(result);

            if (rsp.containsKey("results")) {
                JSONArray matches = (JSONArray) rsp.get("results");
                JSONObject data = (JSONObject) matches.get(0); //TODO: check if idx=0 exists
                formattedAddress = (String) data.get("formatted_address");
            }

            return "";
        } finally {
            urlConnection.disconnect();
            return formattedAddress;
        }
    }

    public Map<Integer, String> getStringAddressForList(List<TaxiOrderEntity> taxiOrderEntityList){
        Map<Integer, String> addressMap = new HashMap<Integer, String>();
        for(TaxiOrderEntity taxiOrderEntity : taxiOrderEntityList){
           TaxiOrderItemEntity taxiOrderItemEntity = taxiOrderDao.getPgPath(taxiOrderEntity);
//            PGpath pGpath = taxiOrderDao.getPgPath(taxiOrderEntity);
//            PGpath pGpath = taxiOrderItemEntity.getPath();
//            List<String> addressList = getAddressList(pGpath);
//            for(String address : addressList){
//                addressMap.put(taxiOrderEntity.getTrackingNumber(), address);
//            }
        }
        return addressMap;
    }

    //Todo end this method
    public List<String> getAddressList(PGpath pGpath){
        //String path = pGpath.toString();
        String lng;
        String lat;
        List<String> addresses = new ArrayList<String>();
        //addresses.add(path);
        //String address = getAddressByGpsCoordinates(lng, lat);
        return addresses;
    }

    public List<TaxiOrderEntity> getHistoryOfOrders(int id){
        return taxiOrderDao.getHistoryOfOrders(id);
    }

    public TaxiOrderEntity getAssignedOrder(int id){
        return taxiOrderDao.getAssignedOrder(id);
    }

    public void setAssignOrder(ServiceUserEntity driver, TaxiOrderEntity taxiOrderEntity, Timestamp carArriveTime){
        taxiOrderDao.setAssignOrder(driver, taxiOrderEntity, carArriveTime);
    }

    public void setInProgressOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setInProgressOrder(taxiOrderEntity);
    }

    public void setCompletedOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setCompletedOrder(taxiOrderEntity);
    }

    public void setRefusedOrder(TaxiOrderEntity taxiOrderEntity){
        taxiOrderDao.setRefusedOrder(taxiOrderEntity);
    }

    public int getOrdersPagesCount(int id){
        return taxiOrderDao.getOrdersPagesCount(id);
    }
}
