package com.restful.booker.model;

public class BookingPojo {

    private String firstname;
    private String lastname;
    private int totalprice;
    private Boolean depositpaid;
    private BookingDates bookingDates;
    private String additionalneeds;

    public BookingDates getBookingDates(){
        return bookingDates;
    }
    public void setBookingDates(BookingDates bookingDates){
        this.bookingDates = bookingDates;

    }
    public String getFirstname(){
        return firstname;
    }
    public void setFirstname(String firstname){
        this.firstname = firstname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setLastname(String lastname){
        this.lastname = lastname;
    }
    public int getTotalprice(){
        return totalprice;
    }
    public void setTotalprice(int totalprice){
        this.totalprice = totalprice;
    }
    public Boolean getDepositpaid(){
        return depositpaid;
    }
    public void setDepositpaid(Boolean depositpaid){
        this.depositpaid = depositpaid;
    }
    public String getAdditionalneeds(){
        return additionalneeds;
    }
    public void setAdditionalneeds(String additionalneeds){
        this.additionalneeds = additionalneeds;
    }

    public static BookingPojo getBookingPojo(String firstname, String lastname, int totalprice, Boolean depositpaid, BookingDates bookingDates, String additionalneeds){
        BookingPojo bookingPojo = new BookingPojo();
        bookingPojo.setFirstname(firstname);
        bookingPojo.setLastname(lastname);
        bookingPojo.setTotalprice(totalprice);
        bookingPojo.setDepositpaid(depositpaid);
        bookingPojo.setBookingDates(bookingDates);
        bookingPojo.setAdditionalneeds(additionalneeds);
        return  bookingPojo;
    }

}
