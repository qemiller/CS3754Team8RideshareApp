package edu.vt.controllers;

import edu.vt.EntityBeans.AllRides;
import edu.vt.EntityBeans.User;
import edu.vt.EntityBeans.UserRides;
import edu.vt.FacadeBeans.AllRidesFacade;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.UserRidesFacade;
import edu.vt.globals.Methods;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import org.primefaces.json.JSONArray;
import org.primefaces.json.JSONObject;

@Named("userRidesController")
@SessionScoped
public class UserRidesController implements Serializable {

    private Integer allRidesId;
    private String driver_username;
    private Integer passenger_1_id;
    private Integer passenger_2_id;
    private Integer passenger_3_id;
    private Integer passenger_4_id;
    private Integer passenger_5_id;
    private Integer passenger_6_id;
    private Integer seats_available;
    private String startingAddress1; 
    private String startingCity;
    private String startingState;
    private String startingZipcode;
    private String endingAddress1;
    private String endingCity;
    private String endingState;
    private String endingZipcode;
    private int trip_time;
    private int trip_distance;
    private int trip_cost;
    private String carMake;
    private String carModel;
    private String carColor; 
    private String carLicensePlate;
    private Date trip_date;
    private Integer number_of_passengers;
    @EJB
    private UserRidesFacade userRidesFacade;
    @EJB 
    private AllRidesFacade allRidesFacade;
    
    private List<UserRides> items = null;
    private UserRides selected;
    @Inject
    private AllRidesController allRidesController;
    
    private final String key = "AIzaSyB12-poUJmOudwUfRF0Y4Rwjg4Rfx2R86c";
    private final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";

    public UserRidesController() {
    }

    public UserRides getSelected() {
        return selected;
    }

    public void setSelected(UserRides selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private UserRidesFacade getUserRidesFacade() {
        return userRidesFacade;
    }

    public String getDriver_username() {
        return driver_username;
    }

    public void setDriver_username(String driver_username) {
        this.driver_username = driver_username;
    }

    public Integer getAllRidesId() {
        return allRidesId;
    }

    public void setAllRidesId(Integer allRidesId) {
        this.allRidesId = allRidesId;
    }

    public Integer getPassenger_1_id() {
        return passenger_1_id;
    }

    public void setPassenger_1_id(Integer passenger_1_id) {
        this.passenger_1_id = passenger_1_id;
    }

    public Integer getPassenger_2_id() {
        return passenger_2_id;
    }

    public void setPassenger_2_id(Integer passenger_2_id) {
        this.passenger_2_id = passenger_2_id;
    }

    public Integer getPassenger_3_id() {
        return passenger_3_id;
    }

    public void setPassenger_3_id(Integer passenger_3_id) {
        this.passenger_3_id = passenger_3_id;
    }

    public Integer getPassenger_4_id() {
        return passenger_4_id;
    }

    public void setPassenger_4_id(Integer passenger_4_id) {
        this.passenger_4_id = passenger_4_id;
    }

    public Integer getPassenger_5_id() {
        return passenger_5_id;
    }

    public void setPassenger_5_id(Integer passenger_5_id) {
        this.passenger_5_id = passenger_5_id;
    }

    public Integer getPassenger_6_id() {
        return passenger_6_id;
    }

    public void setPassenger_6_id(Integer passenger_6_id) {
        this.passenger_6_id = passenger_6_id;
    }

    public Integer getSeats_available() {
        return seats_available;
    }

    public void setSeats_available(Integer seats_available) {
        this.seats_available = seats_available;
    }

    public String getStartingAddress1() {
        return startingAddress1;
    }

    public void setStartingAddress1(String startingAddress1) {
        this.startingAddress1 = startingAddress1;
    }

    public String getStartingCity() {
        return startingCity;
    }

    public void setStartingCity(String startingCity) {
        this.startingCity = startingCity;
    }

    public String getStartingState() {
        return startingState;
    }

    public void setStartingState(String startingState) {
        this.startingState = startingState;
    }

    public String getStartingZipcode() {
        return startingZipcode;
    }

    public void setStartingZipcode(String startingZipcode) {
        this.startingZipcode = startingZipcode;
    }

    public String getEndingAddress1() {
        return endingAddress1;
    }

    public void setEndingAddress1(String endingAddress1) {
        this.endingAddress1 = endingAddress1;
    }

    public String getEndingCity() {
        return endingCity;
    }

    public void setEndingCity(String endingCity) {
        this.endingCity = endingCity;
    }

    public String getEndingState() {
        return endingState;
    }

    public void setEndingState(String endingState) {
        this.endingState = endingState;
    }

    public String getEndingZipcode() {
        return endingZipcode;
    }

    public void setEndingZipcode(String endingZipcode) {
        this.endingZipcode = endingZipcode;
    }

    public int getTrip_time() {
        return trip_time;
    }

    public void setTrip_time(int trip_time) {
        this.trip_time = trip_time;
    }

    public int getTrip_distance() {
        return trip_distance;
    }

    public void setTrip_distance(int trip_distance) {
        this.trip_distance = trip_distance;
    }

    public int getTrip_cost() {
        return trip_cost;
    }

    public void setTrip_cost(int trip_cost) {
        this.trip_cost = trip_cost;
    }

    public String getCarMake() {
        return carMake;
    }

    public void setCarMake(String carMake) {
        this.carMake = carMake;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    

    public Date getTrip_date() {
        return trip_date;
    }

    public void setTrip_date(Date trip_date) {
        this.trip_date = trip_date;
    }

    public Integer getNumber_of_passengers() {
        return number_of_passengers;
    }

    public void setNumber_of_passengers(Integer number_of_passengers) {
        this.number_of_passengers = number_of_passengers;
    }

    public AllRidesFacade getAllRidesFacade() {
        return allRidesFacade;
    }

    public void setAllRidesFacade(AllRidesFacade allRidesFacade) {
        this.allRidesFacade = allRidesFacade;
    }

    public AllRidesController getAllRidesController() {
        return allRidesController;
    }

    public void setAllRidesController(AllRidesController allRidesController) {
        this.allRidesController = allRidesController;
    }

    public void getTripInfo() throws Exception{
        Methods.preserveMessages();
        String start;
        String end;
        if(!selected.getStartingAddress2().isEmpty()){
            start = selected.getStartingAddress1().replace(" ", "+") + "+" +
                    selected.getStartingAddress2().replace(" ", "+") + "+" +
                    selected.getStartingCity().replace(" ", "+") + "+" +
                    selected.getStartingState();
        }
        else {
            start = selected.getStartingAddress1().replace(" ", "+") + "+" +
                    selected.getStartingCity().replace(" ", "+") + "+" +
                    selected.getStartingState();
        }
        
        if(!selected.getEndingAddress2().isEmpty()){
            end = selected.getEndingAddress1().replace(" ", "+") + "+" +
                    selected.getEndingAddress2().replace(" ", "+") + "+" +
                    selected.getEndingCity().replace(" ", "+") + "+" +
                    selected.getEndingState();
        }
        else {
            end = selected.getEndingAddress1().replace(" ", "+") + "+" +
                    selected.getEndingCity().replace(" ", "+") + "+" +
                    selected.getEndingState();
        }
        try{
            String totalUrl = url+start+"&destinations="+end+"&key="+key;
            String urlResultsJsonData = readUrlContent(totalUrl);
            JSONObject resultsJsonObject = new JSONObject(urlResultsJsonData);
            JSONArray jsonArrayFoundObjects = resultsJsonObject.getJSONArray("rows");
            JSONArray elements = jsonArrayFoundObjects.optJSONArray(0);
            JSONObject duration = elements.optJSONObject(1);
            int seconds = duration.optInt("value");
            JSONObject distance = elements.optJSONObject(2);
            int feet = distance.optInt("value");
            double minutes = (double)seconds/60;
            selected.setTrip_time((int)minutes);
            double miles = (double)feet/5280;
            selected.setTrip_distance((int)miles);
        }catch(Exception ex){
            Methods.showMessage("Fatal Error", "The Google Maps Database was not accessed correctly.", "See: " + ex.getMessage());
        }
        
    }
    
    public String getMapUrl(){
        String start;
        String end;
        if(selected.getStartingAddress2() != null){
            start = selected.getStartingAddress1().replace(" ", "+") + "+" +
                    selected.getStartingAddress2().replace(" ", "+") + "+" +
                    selected.getStartingCity().replace(" ", "+") + "+" +
                    selected.getStartingState();
        }
        else {
            start = selected.getStartingAddress1().replace(" ", "+") + "+" +
                    selected.getStartingCity().replace(" ", "+") + "+" +
                    selected.getStartingState();
        }
        if(selected.getEndingAddress2() != null){
            end = selected.getEndingAddress1().replace(" ", "+") + "+" +
                    selected.getEndingAddress2().replace(" ", "+") + "+" +
                    selected.getEndingCity().replace(" ", "+") + "+" +
                    selected.getEndingState();
        }
        else {
            end = selected.getEndingAddress1().replace(" ", "+") + "+" +
                    selected.getEndingCity().replace(" ", "+") + "+" +
                    selected.getEndingState();
        }
        return "https://www.google.com/maps/embed/v1/directions" +
                "?key=" + key +
                "&origin=" + start +
                "&destination=" + end +
                "&units=imperial" +
                "&mode=driving";
    }
    
        /**
     * Return the content of a given URL as String
     *
     * @param webServiceURL to retrieve the JSON data from
     * @return JSON data from the given URL as String
     * @throws Exception
     */
    public String readUrlContent(String webServiceURL) throws Exception {
        /*
        reader is an object reference pointing to an object instantiated from the BufferedReader class.
        Currently, it is "null" pointing to nothing.
         */
        BufferedReader reader = null;

        try {
            // Create a URL object from the webServiceURL given
            URL url = new URL(webServiceURL);
            /*
            The BufferedReader class reads text from a character-input stream, buffering characters
            so as to provide for the efficient reading of characters, arrays, and lines.
             */
            reader = new BufferedReader(new InputStreamReader(url.openStream()));

            // Create a mutable sequence of characters and store its object reference into buffer
            StringBuilder buffer = new StringBuilder();

            // Create an array of characters of size 10240
            char[] chars = new char[10240];

            int numberOfCharactersRead;
            /*
            The read(chars) method of the reader object instantiated from the BufferedReader class
            reads 10240 characters as defined by "chars" into a portion of a buffered array.

            The read(chars) method attempts to read as many characters as possible by repeatedly
            invoking the read method of the underlying stream. This iterated read continues until
            one of the following conditions becomes true:

                (1) The specified number of characters have been read, thus returning the number of characters read.
                (2) The read method of the underlying stream returns -1, indicating end-of-file, or
                (3) The ready method of the underlying stream returns false, indicating that further input requests would block.

            If the first read on the underlying stream returns -1 to indicate end-of-file then the read(chars) method returns -1.
            Otherwise the read(chars) method returns the number of characters actually read.
            -Balci
             */
            while ((numberOfCharactersRead = reader.read(chars)) != -1) {
                buffer.append(chars, 0, numberOfCharactersRead);
            }

            // Return the String representation of the created buffer
            return buffer.toString();

        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
    
    public UserRides prepareCreate() throws Exception {
        selected = new UserRides();
        initializeEmbeddableKey();
        getTripInfo();
        return selected;
    }
    
    public UserRides prepareCreate(int allRidesId, String driverUsername, 
            int passanger1Id, int passanger2Id, int passanger3Id, 
            int passanger4Id, int passanger5Id, int passanger6Id, 
            int seatsAvailable, String startingAddress1, String startingCity, String startingState,
            String startingZipcode, String endingAddress1, String endingCity,
            String endingState, String endingZipcode, int trip_time, int trip_distance,
            int trip_cost, String carMake, String carModel, String carColor, int carMpg,
            String carLicensePlate, 
            Date tripDate, int numberOfPassangers){
        selected = new UserRides();
        selected.setAll(allRidesId, driverUsername, passanger1Id, passanger2Id, 
                passanger3Id, passanger4Id, passanger5Id, passanger6Id, 
                seatsAvailable, startingAddress1, startingCity, startingState,
                startingZipcode, endingAddress1, endingCity, endingState,
                endingZipcode, trip_time, trip_distance, trip_cost, carMake, carModel,
                carColor, carMpg, carLicensePlate, tripDate, numberOfPassangers);
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("UserRidesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }
    public void edit(){
        User user = (User)Methods.sessionMap().get("user");
        if (selected.getDriverUsername().equals(user.getUsername())){
            update();
            AllRides allRide = getAllRidesFacade().find(selected.getAllRides_id());
            allRide.setNumberOfPassangers(selected.getNumberOfPassangers());
            allRide.setPassanger1Id(selected.getPassanger1Id());
            allRide.setPassanger2Id(selected.getPassanger2Id());
            allRide.setPassanger3Id(selected.getPassanger3Id());
            allRide.setPassanger4Id(selected.getPassanger4Id());
            allRide.setPassanger5Id(selected.getPassanger5Id());
            allRide.setPassanger6Id(selected.getPassanger6Id());
            allRide.setCarColor(selected.getCarColor());
            allRide.setCarLicensePlate(selected.getCarLicensePlate());
            allRide.setCarMake(selected.getCarMake());
            allRide.setCarModel(selected.getCarModel());
            allRide.setCarMpg(selected.getCarMpg());
            allRide.setEndingAddress1(selected.getEndingAddress1());
            allRide.setEndingCity(selected.getEndingCity());
            allRide.setEndingState(selected.getEndingState());
            allRide.setEndingZipcode(selected.getEndingZipcode());
            allRide.setStartingAddress1(selected.getStartingAddress1());
            allRide.setStartingCity(selected.getStartingCity());
            allRide.setStartingState(selected.getStartingState());
            allRide.setStartingZipcode(selected.getStartingZipcode());
            allRide.setTrip_cost(selected.getTrip_cost());
            allRide.setTrip_distance(selected.getTrip_distance());
            allRide.setTrip_time(selected.getTrip_time());
            allRide.setTripDate(selected.getTripDate());
            allRide.setSeatsAvailable(selected.getSeatsAvailable());
        }
        else {
            Methods.preserveMessages();
            Methods.showMessage("Information", "Only the driver can edit a ride","");
        }
    }
    
    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("UserRidesUpdated"));
    }

    public void delete(){
        Methods.preserveMessages();
        // get corresponding allrides
        AllRides allRide = getAllRidesFacade().find(selected.getAllRides_id());
        // set it to selected so that selected is changed
        allRidesController.setSelected(allRide);
        // get the logged in users username
        User user = (User)Methods.sessionMap().get("user");
        // if the user is the driver then destroy the ride in both databases and 
        // send a message to the riders
        if (selected.getDriverUsername().equals(user.getUsername())){
            allRidesController.destroy();
            // send message
        }
        // if the user is a passenger, set the passengerId of the allRides to -1
        // indicating that there is no passenger in that id then increment the seats available
        // and decrement the number of passengers
        else{
            if(allRide.getPassanger1Id() == user.getId()){
                allRide.setPassanger1Id(-1);
            }
            else if(allRide.getPassanger2Id() == user.getId()){
                allRide.setPassanger2Id(-1);
            }
            else if(allRide.getPassanger3Id() == user.getId()){
                allRide.setPassanger3Id(-1);
            }
            else if(allRide.getPassanger4Id() == user.getId()){
                allRide.setPassanger4Id(-1);
            }
            else if(allRide.getPassanger5Id() == user.getId()){
                allRide.setPassanger5Id(-1);
            }
            else if(allRide.getPassanger6Id() == user.getId()){
                allRide.setPassanger6Id(-1);
            }
            else {
                Methods.showMessage("Error", "Deletion Unsuccessful", "");
                allRide.setNumberOfPassangers(allRide.getNumberOfPassangers() + 1);
                allRide.setSeatsAvailable(allRide.getNumberOfPassangers() - 1);
            }
            allRide.setNumberOfPassangers(allRide.getNumberOfPassangers() - 1);
            allRide.setSeatsAvailable(allRide.getSeatsAvailable() + 1);
            destroy();
        }
    }
    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("UserRidesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<UserRides> getItems() {
        if (items == null) {
            items = getUserRidesFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getUserRidesFacade().edit(selected);
                } else {
                    getUserRidesFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public UserRides getUserRides(java.lang.Integer id) {
        return getUserRidesFacade().find(id);
    }

    public List<UserRides> getItemsAvailableSelectMany() {
        return getUserRidesFacade().findAll();
    }

    public List<UserRides> getItemsAvailableSelectOne() {
        return getUserRidesFacade().findAll();
    }

    @FacesConverter(forClass = UserRides.class)
    public static class UserRidesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            UserRidesController controller = (UserRidesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "userRidesController");
            return controller.getUserRides(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof UserRides) {
                UserRides o = (UserRides) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), UserRides.class.getName()});
                return null;
            }
        }

    }

}
