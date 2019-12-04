package edu.vt.controllers;

import edu.vt.EntityBeans.AllRides;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.AllRidesFacade;
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

@Named("allRidesController")
@SessionScoped
public class AllRidesController implements Serializable {

    private Integer driver_id;
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
    private int useDefaultCar;
    
    private List<AllRides> searchedItems;
    private String searchCategory;
    private String searchString;
    
    private final String key = "AIzaSyB12-poUJmOudwUfRF0Y4Rwjg4Rfx2R86c";
    private final String url = "https://maps.googleapis.com/maps/api/distancematrix/json?units=imperial&origins=";
    
    @EJB
    private edu.vt.FacadeBeans.AllRidesFacade ejbFacade;
    private List<AllRides> items = null;
    private AllRides selected;
    
    @Inject
    private UserController user;
    @Inject 
    private UserRidesController userRidesController;

    public AllRidesController() {
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(Integer driver_id) {
        this.driver_id = driver_id;
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

    public UserController getUser() {
        return user;
    }

    public void setUser(UserController user) {
        this.user = user;
    }

    public UserRidesController getUserRidesController() {
        return userRidesController;
    }

    public void setUserRidesController(UserRidesController userRidesController) {
        this.userRidesController = userRidesController;
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

    public List<AllRides> getSearchedItems() {
        return searchedItems;
    }

    public void setSearchedItems(List<AllRides> searchedItems) {
        this.searchedItems = searchedItems;
    }

    public String getSearchCategory() {
        return searchCategory;
    }

    public void setSearchCategory(String searchCategory) {
        this.searchCategory = searchCategory;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getUseDefaultCar() {
        return useDefaultCar;
    }

    public void setUseDefaultCar(int useDefaultCar) {
        this.useDefaultCar = useDefaultCar;
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
                "&units=imperial" +
                "&origin=" + start +
                "&destination=" + end;
    }
    
    public AllRidesFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(AllRidesFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }

    
    public AllRides getSelected() {
        return selected;
    }

    public void setSelected(AllRides selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private AllRidesFacade getFacade() {
        return ejbFacade;
    }

    public void share(){
        Methods.preserveMessages();
        if (user.isLoggedIn()){
            //System.out.println("share");
            userRidesController.setSelected(userRidesController.prepareCreate(selected.getId(),
                    selected.getDriverId().getUsername(),
                    selected.getPassanger1Id(),selected.getPassanger2Id(),selected.getPassanger3Id(), 
                    selected.getPassanger4Id(), selected.getPassanger5Id(), selected.getPassanger6Id(), 
                    selected.getSeatsAvailable(),
                    selected.getStartingAddress1(), selected.getStartingCity(), selected.getStartingState(),
            selected.getStartingZipcode(), selected.getEndingAddress1(), selected.getEndingCity(),
            selected.getEndingState(), selected.getEndingZipcode(), selected.getTrip_time(), selected.getTrip_distance(),
            selected.getTrip_cost(), selected.getCarMake(), selected.getCarModel(), selected.getCarColor(), selected.getCarMpg(),
            selected.getCarLicensePlate(), selected.getTripDate(),
                    selected.getNumberOfPassangers()));
            userRidesController.create();
        }
        else{
            Methods.showMessage("Information", "Unable to Share!", "To share a video, a user must have signed in!");
        }
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
    public AllRides prepareCreate() throws Exception {
        selected = new AllRides();
        initializeEmbeddableKey();
        getTripInfo();
        return selected;
    }
    
    public AllRides prepareCreate(Integer id, int passanger1Id, int passanger2Id, 
            int passanger3Id, int passanger4Id, int passanger5Id, int passanger6Id, 
            int seatsAvailable, String startingAddress1, String startingCity, String startingState,
            String startingZipcode, String endingAddress1, String endingCity,
            String endingState, String endingZipcode, int trip_time, int trip_distance,
            int trip_cost, String carMake, String carModel, String carColor, int carMpg,
            String carLicensePlate,  
            Date tripDate, int numberOfPassangers){
        selected = new AllRides(id, passanger1Id, passanger2Id, passanger3Id, 
                passanger4Id, passanger5Id, passanger6Id, seatsAvailable, 
                startingAddress1, startingCity, startingState, startingZipcode, 
                endingAddress1, endingCity, endingState, endingZipcode,
                trip_time, trip_distance, trip_cost, carMake, carModel, carColor, 
                carMpg, carLicensePlate, tripDate, numberOfPassangers);
        initializeEmbeddableKey();
        return selected;
    }
    
    public String search() {
        switch(searchCategory){
            case "all":
                searchedItems = getFacade().findByStartingCity(searchString);
                searchedItems.addAll(getFacade().findByEndingCity(searchString));
                break;
            case "startingLocation":
                searchedItems = getFacade().findByStartingCity(searchString);
                break;
            case "endingLocation":
                searchedItems = getFacade().findByEndingCity(searchString);
                break;
        }
        return "/search/Results?faces-redirect=true";
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("AllRidesCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
            share();
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("AllRidesUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("AllRidesDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<AllRides> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
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

    public AllRides getAllRides(java.lang.Integer id) {
        return getFacade().find(id);
    }

    public List<AllRides> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<AllRides> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = AllRides.class)
    public static class AllRidesControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AllRidesController controller = (AllRidesController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "allRidesController");
            return controller.getAllRides(getKey(value));
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
            if (object instanceof AllRides) {
                AllRides o = (AllRides) object;
                return getStringKey(o.getId());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), AllRides.class.getName()});
                return null;
            }
        }

    }

}
