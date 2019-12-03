package edu.vt.controllers;

import edu.vt.EntityBeans.AllRides;
import edu.vt.controllers.util.JsfUtil;
import edu.vt.controllers.util.JsfUtil.PersistAction;
import edu.vt.FacadeBeans.AllRidesFacade;
import edu.vt.globals.Methods;

import java.io.Serializable;
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
    private String starting_location;
    private String ending_location;
    private Date trip_date;
    private Integer number_of_passengers;
    
    private List<AllRides> searchedItems;
    private String searchCategory;
    private String searchString;
    
    
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

    public String getStarting_location() {
        return starting_location;
    }

    public void setStarting_location(String starting_location) {
        this.starting_location = starting_location;
    }

    public String getEnding_location() {
        return ending_location;
    }

    public void setEnding_location(String ending_location) {
        this.ending_location = ending_location;
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
            userRidesController.setSelected(userRidesController.prepareCreate(userRidesController.getItems().size(),
                    selected.getDriverId().getUsername(),
                    selected.getPassanger1Id(),selected.getPassanger2Id(),selected.getPassanger3Id(), 
                    selected.getPassanger4Id(), selected.getPassanger5Id(), selected.getPassanger6Id(), 
                    selected.getSeatsAvailable(),
                    selected.getStartingLocation(), selected.getEndingLocation(), selected.getTripDate(),
                    selected.getNumberOfPassangers()));
            userRidesController.create();
        }
        else{
            Methods.showMessage("Information", "Unable to Share!", "To share a video, a user must have signed in!");
        }
    }
    public AllRides prepareCreate() {
        selected = new AllRides();
        initializeEmbeddableKey();
        return selected;
    }
    
    public AllRides prepareCreate(Integer id, int passanger1Id, int passanger2Id, 
            int passanger3Id, int passanger4Id, int passanger5Id, int passanger6Id, 
            int seatsAvailable, String startingLocation, String endingLocation, 
            Date tripDate, int numberOfPassangers){
        selected = new AllRides(id, passanger1Id, passanger2Id, passanger3Id, 
                passanger4Id, passanger5Id, passanger6Id, seatsAvailable, 
                startingLocation, endingLocation, tripDate, numberOfPassangers);
        initializeEmbeddableKey();
        return selected;
    }
    
    public String search() {
        switch(searchCategory){
            case "all":
                searchedItems = getFacade().findByStartingLocation(searchString);
                searchedItems.addAll(getFacade().findByEndingLocation(searchString));
                break;
            case "startingLocation":
                searchedItems = getFacade().findByStartingLocation(searchString);
                break;
            case "endingLocation":
                searchedItems = getFacade().findByEndingLocation(searchString);
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
