/*
 * Created by Justin Kennedy on 2019.11.15  * 
 * Copyright © 2019 Justin Kennedy. All rights reserved. * 
 */
package edu.vt.managers;

import edu.vt.pojo.SurveyQuestion;
import edu.vt.EntityBeans.User;
import edu.vt.FacadeBeans.UserFacade;
import edu.vt.globals.Methods;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import edu.vt.controllers.UserSurveyController;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.json.JSONArray;

@Named(value = "surveyManager")
@SessionScoped

public class SurveyManager implements Serializable {

    /*
    ==================
    Instance Variables
    ==================
     */
    // Inject (store) the UserFacade object reference into userFacade after it is instantiated at runtime
    @EJB
    private UserFacade userFacade;

    @Inject
    private UserSurveyController userSurveyController;

    // 'items' is a List containing the object references of SurveyQuestion objects
    private List<SurveyQuestion> items = null;
    // initialize answers 
    private String question1Answer = "1";
    private String slider1 = "1";
    private String question2Answer = "0";
    private String question3Answer;
    private String question4Answer = "0";
    private String question5Answer;
    private String question6Answer;
    private String question7Answer;
    private String question8Answer = "1";
    private String question9Answer = "0";
    private String question10Answer;

    public SurveyManager() {
    }

    /*
    GETTERS AND SETTERS
    */
    public UserFacade getUserFacade() {
        return userFacade;
    }

    public void setUserFacade(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    public UserSurveyController getUserSurveyController() {
        return userSurveyController;
    }

    public void setUserSurveyController(UserSurveyController userSurveyController) {
        this.userSurveyController = userSurveyController;
    }

    public List<SurveyQuestion> getItems() {
        return items;
    }

    public void setItems(List<SurveyQuestion> items) {
        this.items = items;
    }

    public String getQuestion1Answer() {
        return question1Answer;
    }

    public void setQuestion1Answer(String question1Answer) {
        this.question1Answer = question1Answer;
    }

    public String getQuestion2Answer() {
        return question2Answer;
    }

    public void setQuestion2Answer(String question2Answer) {
        this.question2Answer = question2Answer;
    }

    public String getQuestion3Answer() {
        return question3Answer;
    }

    public void setQuestion3Answer(String question3Answer) {
        this.question3Answer = question3Answer;
    }

    public String getQuestion4Answer() {
        return question4Answer;
    }

    public void setQuestion4Answer(String question4Answer) {
        this.question4Answer = question4Answer;
    }

    public String getQuestion5Answer() {
        return question5Answer;
    }

    public void setQuestion5Answer(String question5Answer) {
        this.question5Answer = question5Answer;
    }

    public String getQuestion6Answer() {
        return question6Answer;
    }

    public void setQuestion6Answer(String question6Answer) {
        this.question6Answer = question6Answer;
    }

    public String getQuestion7Answer() {
        return question7Answer;
    }

    public void setQuestion7Answer(String question7Answer) {
        this.question7Answer = question7Answer;
    }

    public String getQuestion8Answer() {
        return question8Answer;
    }

    public void setQuestion8Answer(String question8Answer) {
        this.question8Answer = question8Answer;
    }

    public String getQuestion9Answer() {
        return question9Answer;
    }

    public void setQuestion9Answer(String question9Answer) {
        this.question9Answer = question9Answer;
    }

    public String getQuestion10Answer() {
        return question10Answer;
    }

    public void setQuestion10Answer(String question10Answer) {
        this.question10Answer = question10Answer;
    }
    
    public String getSlider1() {
        return slider1;
    }

    public void setSlider1(String slider1) {
        this.slider1 = slider1;
    }

    /*
    ================
    Instance Methods
    ================

    ***********************************
    Process the Submitted Survey
    ***********************************
     */
    public String processSurvey() {

        // Instantiate a new 'items' List to contain the object references of the SurveyQuestion objects
        items = new ArrayList<>();
        
        // For each question I set the questionNumber, questionTitle, and get the questionAnswer
        // I then create a new SurveyQuestion and add it to the list

        Integer questionNumber;
        String questionTitle;
        String questionAnswer;
        /* 
         ******************
         *   Question 1   *
         ******************
         */

        questionNumber = 1;
        questionTitle = "How physically healthy are you?";
        questionAnswer = question1Answer;

        SurveyQuestion question1 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question1);

        /* 
         ******************
         *   Question 2   *
         ******************
         */
        questionNumber = 2;
        questionTitle = "How important is exercise to you?";
        questionAnswer = question2Answer;

        SurveyQuestion question2 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question2);

        /* 
         ******************
         *   Question 3   *
         ******************
         */
        questionNumber = 3;
        questionTitle = "What do you most often do to exercise?";
        questionAnswer = question3Answer;

        SurveyQuestion question3 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question3);

        /* 
         ******************
         *   Question 4   *
         ******************
         */
        questionNumber = 4;
        questionTitle = "How many times do you exercise per week?";
        questionAnswer = question4Answer;

        SurveyQuestion question4 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question4);

        /* 
         ******************
         *   Question 5   *
         ******************
         */
        questionNumber = 5;
        questionTitle = "How often do you drink regular soda or other sugared beverages?";
        questionAnswer = question5Answer;

        SurveyQuestion question5 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question5);

        /* 
         ******************
         *   Question 6   *
         ******************
         */
        questionNumber = 6;
        questionTitle = "How often do you consume alcohol?";
        questionAnswer = question6Answer;

        SurveyQuestion question6 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question6);

        /* 
         ******************
         *   Question 7   *
         ******************
         */
        questionNumber = 7;
        questionTitle = "What types of food do you usually snack on?";
        questionAnswer = question7Answer;

        SurveyQuestion question7 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question7);

        /* 
         ******************
         *   Question 8   *
         ******************
         */
        questionNumber = 8;
        questionTitle = "How many hours of sleep do you get each night on average?";
        questionAnswer = question8Answer;

        SurveyQuestion question8 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question8);

        /* 
         ******************
         *   Question 9   *
         ******************
         */
        questionNumber = 9;
        questionTitle = "What is your stress level?";
        questionAnswer = question9Answer;

        SurveyQuestion question9 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question9);

        /* 
         ******************
         *   Question 10   *
         ******************
         */
        questionNumber = 10;
        questionTitle = "Which of the following is most effective in coping with your stress?";
        questionAnswer = question10Answer;

        SurveyQuestion question10 = new SurveyQuestion(questionNumber, questionTitle, questionAnswer);
        items.add(question10);

        userSurveyController.prepareCreate();
        
        // the survey also has attributes such as date and userId that must be set

        //--------------------------------
        // Set dateEntered attribute value 
        //--------------------------------
        LocalDate localDate = java.time.LocalDate.now();
        Date currentDate = java.sql.Date.valueOf(localDate);

        // Set Date in the format of YYYY-MM-DD
        userSurveyController.getSelected().setDateEntered(currentDate);

        JSONArray jasonArray = new JSONArray(items);

        // Convert the JSON array into a String
        String survey = jasonArray.toString();

        // Set the questionnaire attribute value
        userSurveyController.getSelected().setSurvey(survey);

        //---------------------------
        // Set userId attribute value 
        //---------------------------
        // Obtain the object reference of the signed-in user
        User signedInUser = (User) Methods.sessionMap().get("user");

        userSurveyController.getSelected().setUserId(signedInUser);

        //----------------------------------------------------------
        // Store the newly created UserSurvey in the database
        //----------------------------------------------------------
        userSurveyController.create();

        //-----------------------------------------------------------
        // Clear the survey content without displaying message
        //-----------------------------------------------------------
        clearSurvey(false);

        return "/index?faces-redirect=true";
    }

    /*
    ************************************************
    Clear All of the Selections in the Questionnaire
    ************************************************
     */
    public void clearSurvey(Boolean displayMessage) {
        items = null;

        question2Answer = null;
        question3Answer = null;
        question4Answer = null;
        question5Answer = null;
        question6Answer = null;
        question7Answer = null;
        question8Answer = null;
        question9Answer = null;
        question10Answer = null;
        
        if (displayMessage) {
            Methods.showMessage("Information", "Cleared!", "All Selections are Reset!");
        }
    
    }
    
    
    /*
    *******************************************
    Pre-process the PDF File to be in Landscape 
    Orientation on Letter Size Paper
    *******************************************
     */
    public void preProcessPDF(Object document) {
        Document pdf = (Document) document;
        pdf.setPageSize(PageSize.LETTER.rotate());
        pdf.open();
    }

    /*
    *****************************************
    Warn the User for 5 Minutes of Inactivity
    *****************************************
     */
    public void onIdle() {
        Methods.showMessage("Warning", "No User Action for the Last 5 Minutes!", "Do You Need More Time?");
    }

    /*
    ***************************************************
    Welcome Back the User After 5 Minutes of Inactivity
    ***************************************************
     */
    public void onActive() {
        Methods.showMessage("Warning", "Welcome Back!", "Please Continue Filling Out Your Survey!");
    }


}
