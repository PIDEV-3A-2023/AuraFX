/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.twilio.Twilio;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
/**
 * @author MSI
 */
public class Smsapi {

    public static final String ACCOUNT_SID = "ACfb8487d6227f400ce2f378a73b525430";
    public static final String AUTH_TOKEN = "09fe0551782002f166a8b9c5a3837f41";

    public static void sendSMS(String msg) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(/*num ili bch yjih il msg */new PhoneNumber("+21624333991"),new PhoneNumber("+191********"), msg).create();

        System.out.println(message.getSid());

    }
}
